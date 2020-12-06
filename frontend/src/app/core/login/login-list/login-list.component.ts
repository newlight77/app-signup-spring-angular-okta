import { Component, OnInit } from '@angular/core';
import { LoginService } from '../login-service/login.service';
import { LoginModel, IpAddressModel } from '../login-service/login.model';
import { OktaAuthService } from '@okta/okta-angular';

@Component({
  selector: 'app-login-list',
  templateUrl: 'login-list.component.html',
  styleUrls: ['login-list.component.scss'],
})
export class LoginListComponent implements OnInit {

  loginModel = new LoginModel();
  loginList: LoginModel[] = [];

  constructor(
    private loginService: LoginService,
    private authService: OktaAuthService) { }

  async ngOnInit() {

    this.loginService.ipAddress().then((res: IpAddressModel) => {
      this.loginModel.ipAddress = res.ip;
      console.log(`login list ip: ${res.ip}`);
    });

    const user = await this.authService.getUser();
    this.loginModel.username = user.email;

    this.loginService.load().subscribe(
      (result) => {
        this.loginList = result;
      },
      (err) => {
        console.error('error loading', err);
      },
    );

  }

}
