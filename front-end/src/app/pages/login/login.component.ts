import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/model/User';
import { AlertsService } from 'src/app/service/alerts.service';
import { UsersService } from 'src/app/service/user.service';
import { environment } from 'src/environments/environment.prod';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit {

  userLogin: User = new User();
  
  constructor(
    private userService: UsersService,
    private route: Router,
    private alerts: AlertsService
  ) { }

  ngOnInit(): void {
  }

  userVendedor() {
    this.userLogin.username = "Vendedor";
    this.userLogin.password = "";
  }

  userGerente() {
    this.userLogin.username = "Gerente";
    this.userLogin.password = "";
  }

  login() {
    this.userService.loginUser(this.userLogin).subscribe((resp: User)=>{
      this.userLogin = resp;

      environment.id = this.userLogin.id;
      environment.username = this.userLogin.username;
      environment.loggedIn = this.userLogin.loggedIn;
      environment.password = this.userLogin.password;

      if (this.userLogin.loggedIn == true) {
        this.route.navigate(['/home']);
      }
    }, erro => {
      if (erro.status == 400) {
        this.alerts.showAlertInfo("Senha errada, tente novamente!");
      }
    });
  }
}
