import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Budget } from 'src/app/model/Budget';
import { BudgetItem } from 'src/app/model/BudgetItem';
import { User } from 'src/app/model/User';
import { AlertsService } from 'src/app/service/alerts.service';
import { BudgetService } from 'src/app/service/budget.service';
import { BudgetItemService } from 'src/app/service/budgetitem.service';
import { environment } from 'src/environments/environment.prod';

@Component({
  selector: 'app-create-budget',
  templateUrl: './create-budget.component.html',
  styleUrls: ['./create-budget.component.css']
})
export class CreateBudgetComponent implements OnInit {

  user: User = new User();

  budget: Budget = new Budget();

  budgetForListItems: Budget = new Budget();

  budgetItem: BudgetItem = new BudgetItem();

  budgetCreated: boolean = false;

  constructor(
    private budgetService: BudgetService,
    private budgetItemService: BudgetItemService,
    private route: Router,
    private alerts: AlertsService
  ) { }

  ngOnInit(): void {
    if (environment.loggedIn == false) {
      this.route.navigate(['/login']);
      
      this.alerts.showAlertInfo("Sua sessão expirou, entre com um usuário novamente!");
    }

    this.budgetCreated = false;
    this.budget.comments = "";
  }

  getItemsFromBudget() {
    this.budgetService.findById(this.currentBudget.id).subscribe((resp: Budget)=> {
      this.budgetForListItems.items = resp.items;
    });
  }

  currentBudget: Budget = new Budget();
  saveBuget() {
    this.user.id = environment.id;
    this.budget.user = this.user;

    this.budgetService.save(this.budget).subscribe((resp: Budget) => {
      this.alerts.showAlertSuccess("Orçamento criado com sucesso!");

      this.currentBudget = resp;
      this.currentBudget.creationDate = new Date();
      this.budgetCreated = true;
      this.getItemsFromBudget();

    }, erro => {
      if (erro.status == 400) {
        this.alerts.showAlertDanger("Ocorreu um erro, preencha corretamente todos os campos!");
      }
    });
  }

  
  saveBudgetItem() {
    this.budgetItem.budget = this.currentBudget;

    this.budgetItemService.save(this.budgetItem).subscribe(()=> {
      this.getItemsFromBudget();
    }, erro => {
      if (erro.status == 400) {
        this.alerts.showAlertDanger("Ocorreu um erro, preencha corretamente todos os campos!");
      }
    });
  }

  clean() {
    this.budget = new Budget();
    this.budget.comments = "";
    this.budgetItem = new BudgetItem();
    this.budgetForListItems.items = []
    this.budgetCreated = false;
  }
}