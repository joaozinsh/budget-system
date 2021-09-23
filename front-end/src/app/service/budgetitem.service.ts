import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BudgetItem } from '../model/BudgetItem';

@Injectable({
  providedIn: 'root'
})
export class BudgetItemService {

  constructor(
    private http: HttpClient
  ) { }

  findAll(): Observable<BudgetItem[]> {
    return this.http.get<BudgetItem[]>("http://localhost:8080/budgets-items");
  }

  findById(id: number): Observable<BudgetItem> {
    return this.http.get<BudgetItem>(`http://localhost:8080/budgets-items/${id}`);
  }

  save(budgetItem: BudgetItem): Observable<BudgetItem> {
    return this.http.post<BudgetItem>("http://localhost:8080/budgets-items", budgetItem);
  }

  update(budgetItem: BudgetItem): Observable<BudgetItem> {
    return this.http.put<BudgetItem>("http://localhost:8080/budgets-items", budgetItem);
  }

  delete(id: number) {
    return this.http.delete(`http://localhost:8080/budgets-items/${id}`);
  }

}
