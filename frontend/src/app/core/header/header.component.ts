import { Component, OnInit, HostListener } from '@angular/core';
import { OktaAuthService } from '@okta/okta-angular';
import { Router, NavigationStart } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
})
export class HeaderComponent implements OnInit {
  isAuthenticated: boolean;
  username: string;
  name: any;
  isSticky = false;

  constructor(
    public oktaAuth: OktaAuthService,
    private router: Router
  ) { }

  async ngOnInit() {
    this.isAuthenticated = await this.oktaAuth.isAuthenticated();

    // Subscribe to authentication state changes
    this.oktaAuth.$authenticationState.subscribe(
      (isAuthenticated: boolean) => (this.isAuthenticated = isAuthenticated)
    );

    this.oktaAuth.getUser().then(user => {
      this.username = user.email;
      this.name = user.email.split('@')[0];
      console.log('header user: ', user.email);
    });

    this.router.events.forEach(event => {
      if (event instanceof NavigationStart) {
        console.log('header event: ', event.url);
      }
    });
  }

  @HostListener('window:scroll', ['$event'])
  checkScroll() {
    this.isSticky = window.pageYOffset >= 250;
  }
}
