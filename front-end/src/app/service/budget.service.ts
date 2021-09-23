import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs';
import { Budget } from '../model/Budget';

@Injectable({
  providedIn: 'root'
})
export class BudgetService {

  constructor(
    private http: HttpClient
  ) { }

  findAll(): Observable<Budget[]> {
    return this.http.get<Budget[]>("http://localhost:8080/budgets");
  }

  findById(id: number): Observable<Budget> {
    return this.http.get<Budget>(`http://localhost:8080/budgets/${id}`);
  }

  save(budget: Budget): Observable<Budget> {
    return this.http.post<Budget>("http://localhost:8080/budgets", budget);
  } 

  update(budget: Budget): Observable<Budget> {
    return this.http.put<Budget>("http://localhost:8080/budgets", budget);
  }

  delete(id: number) {
    return this.http.delete<Budget>(`http://localhost:8080/budgets/${id}`);
  }
}
