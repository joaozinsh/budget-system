import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/model/User';
import { UsersService } from 'src/app/service/user.service';
import { environment } from 'src/environments/environment.prod';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  username: string = environment.username;
  password: string = environment.password;

  userLogin: User = new User();

  constructor(
    private userService: UsersService,
    private route: Router
  ) { }

  ngOnInit(): void {
  }

  logOutUser() {
    this.userLogin.username = this.username;
    this.userLogin.password = this.password;

    this.userService.logoutUser(this.userLogin).subscribe((resp: User) => {
      this.userLogin = resp;

      environment.id = 0;
      environment.username = "";
      environment.password = "";
      environment.loggedIn = false;

      if (this.userLogin.loggedIn == false) {
        this.route.navigate(['/login']);
      }
    });
  }
  
}