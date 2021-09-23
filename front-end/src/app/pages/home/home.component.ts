import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AlertsService } from 'src/app/service/alerts.service';
import { environment } from 'src/environments/environment.prod';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {


  constructor(
    private route: Router,
    private alerts: AlertsService
  ) { }

  ngOnInit(): void {
    if (environment.loggedIn == false) {
      this.route.navigate(['/login']);

      this.alerts.showAlertInfo("Sua sessão expirou, entre com um usuário novamente!");
    }
  }
}
