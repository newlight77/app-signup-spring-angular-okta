import { Component, OnInit } from '@angular/core';
import { Router, NavigationStart } from '@angular/router';
import { OktaAuthService } from '@okta/okta-angular';
import * as OktaSignIn from '@okta/okta-signin-widget';

import { environment } from 'src/environments/environment';
import { LoginHandler } from '../login-service/login.handler';


@Component({
  selector: 'app-login-form',
  templateUrl: 'login-form.component.html',
  styleUrls: ['login-form.component.scss'],
})
export class LoginFormComponent implements OnInit {
  feedback: any = {};

  widget = new OktaSignIn({
    baseUrl: environment.env.oktaUrl
  });

  constructor(
    private authService: OktaAuthService,
    private loginHandler: LoginHandler,
    private router: Router) { }

  ngOnInit() {
    // Show the widget when prompted, otherwise remove it from the DOM.
    this.router.events.forEach(event => {
      if (event instanceof NavigationStart) {
        if (event.url !== '/login') {
          this.widget.remove();
        }
      }
    });

    this.widget.renderEl({
      el: '#okta-signin-container'
    },
      (res) => {
        console.log(`response:`, res);
        if (res.status === 'SUCCESS') {
          this.onAuthSuccess(res);
        }
      },
      (err) => {
        throw err;
      }
    );
  }

  async onAuthSuccess(res) {
    console.log('login form onAuthSuccess: ', res.user.profile.login);
    // get a 401, the token is not yet ready
    await this.loginHandler.onAuthSuccess(res.user.profile.login);
    this.authService.loginRedirect('/login/detail', { sessionToken: res.session.token });
  }
}
