import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../model/User';

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  constructor(
    private http: HttpClient
  ) { }

  findAllUsers(): Observable<User[]> {
    return this.http.get<User[]>("http://localhost:8080/users");
  }

  loginUser(userLogin: User): Observable<User> {
    return this.http.post<User>("http://localhost:8080/users/login", userLogin);
  }
  
  logoutUser(userLogin: User): Observable<User> {
    return this.http.post<User>("http://localhost:8080/users/logout", userLogin);
  }
}
