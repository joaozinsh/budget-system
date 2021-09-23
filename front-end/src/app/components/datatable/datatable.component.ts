import { Component, OnInit } from '@angular/core';
import { Budget } from 'src/app/model/Budget';
import { BudgetItem } from 'src/app/model/BudgetItem';
import { User } from 'src/app/model/User';
import { AlertsService } from 'src/app/service/alerts.service';
import { BudgetService } from 'src/app/service/budget.service';
import { BudgetItemService } from 'src/app/service/budgetitem.service';
import { environment } from 'src/environments/environment.prod';

@Component({
  selector: 'app-datatable',
  templateUrl: './datatable.component.html',
  styleUrls: ['./datatable.component.css']
})
export class DatatableComponent implements OnInit {

  userLogin = environment.username

  budgets: Budget[];
  budgetId: Budget = new Budget();
  editBudget: Budget = new Budget();

  editUser: User = new User();

  budgetItem: BudgetItem = new BudgetItem();
  editBudgetItem: BudgetItem = new BudgetItem();

  p: number = 1;

  constructor(
    private budgetService: BudgetService,
    private budgetItemService: BudgetItemService,
    private alerts: AlertsService
  ) { }

  ngOnInit(): void {
    this.getBudgetsForTable();
    this.editBudget.user = new User();
  }

  getBudgetsForTable() {
    this.budgetService.findAll().subscribe((resp: Budget[]) => {
      if (resp == null) {
        this.budgets = [];
      } else {
        this.budgets = resp;
      }
    });
  }

  idBudgetForBudgetItems: number;

  getBudgetsItemForTable(id: number) {
    this.idBudgetForBudgetItems = id;

    this.budgetService.findById(id).subscribe((resp: Budget) => {
      this.budgetId = resp;
    });
  }

  getBudgetForEdit(id: number) {
    this.budgetService.findById(id).subscribe((resp: Budget) => {
      this.editBudget = resp;
    });
  }

  getUser(event: any) {
    if (event.target.value === "1") {
      this.editUser.id = 1;
    }
    if (event.target.value === "2") {
      this.editUser.id = 2;
    }
  }

  updateBudget() {
    if (this.editUser.id == null) {
      this.editBudget.items = [];

      this.budgetService.update(this.editBudget).subscribe(() => {
        this.alerts.showAlertSuccess("Orçamento editado com sucesso!");
        this.refreshTable();
      }, erro => {
        if (erro.status == 400) {
          this.alerts.showAlertDanger("Ocorreu um erro, preencha corretamente todos os campos!");
        }
      })
    } else {
      this.editBudget.user = this.editUser
      this.editBudget.items = [];

      this.budgetService.update(this.editBudget).subscribe(() => {
        this.alerts.showAlertSuccess("Orçamento editado com sucesso!");
        this.refreshTable();
      }, erro => {
        if (erro.status == 400) {
          this.alerts.showAlertDanger("Ocorreu um erro, preencha corretamente todos os campos!");
        }
      })
    }
  }

  idForDelete: number;

  getBudgetIdForDelete(id: number) {
    this.idForDelete = id;
  }

  currentBudget: Budget = new Budget();

  saveBudgetItem() {
    this.currentBudget = this.budgetId;
    this.currentBudget.creationDate = new Date();
    this.budgetItem.budget = this.currentBudget;

    this.budgetItemService.save(this.budgetItem).subscribe(() => {
      this.alerts.showAlertSuccess("Item adicionado com sucesso!")
      this.budgetItem = new BudgetItem();
      this.refreshTable();
      this.refreshTableItems();
    }, erro => {
      if (erro.status == 400) {
        this.alerts.showAlertDanger("Ocorreu um erro, preencha corretamente todos os campos!");
      }
    })
  }

  idBudgetItem: number

  getBudgetItemId(id: number) {
    this.idBudgetItem = id;
  }

  getBudgetItemFromId(id: number) {
    this.idBudgetItem = id;
    this.budgetItemService.findById(this.idBudgetItem).subscribe((resp: BudgetItem) => {
      this.editBudgetItem = resp;
      this.editBudgetItem.budget.creationDate = new Date();
    })
  }

  updateBudgetItem() {
    this.budgetItemService.update(this.editBudgetItem).subscribe(() => {
      this.alerts.showAlertSuccess("Item editado com sucesso!");
      this.refreshTableItems();
      this.refreshTable();
    }, erro => {
      if (erro.status == 400) {
        this.alerts.showAlertDanger("Ocorreu um erro, preencha corretamente todos os campos!");
      }
    })
  }

  deleteBudget(id: number) {
    this.budgetService.delete(id).subscribe(() => {
      this.refreshTable();
      this.alerts.showAlertSuccess("Orçamento apagado com sucesso!");
    }, erro => {
      if (erro.status == 404) {
        this.alerts.showAlertDanger("Ocorreu um erro, preencha corretamente todos os campos!");
      }
    })
  }

  deleteBudgetItem(id: number) {
    this.budgetItemService.delete(id).subscribe(() => {
      this.refreshTableItems();
      this.refreshTable();
      this.alerts.showAlertSuccess("Item apagado com sucesso!");
    }, erro => {
      if (erro.status == 404) {
        this.alerts.showAlertDanger("Ocorreu um erro, preencha corretamente todos os campos!");
      }
    })
  }

  refreshTable() {
    this.getBudgetsForTable();
  }

  refreshTableItems() {
    this.getBudgetsItemForTable(this.idBudgetForBudgetItems);
  }

  order: string = 'id';
  key: string = 'id';
  reverse: boolean = false;

  sort(key: string) {
    this.key = key;
    if (this.order === key) {
      this.reverse = !this.reverse;
    }

    this.order = key;
  }
}
