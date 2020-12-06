import { NgModule, APP_INITIALIZER } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CommonModule } from '@angular/common';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ReactiveFormsModule } from '@angular/forms';

import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { OKTA_CONFIG, OktaAuthModule } from '@okta/okta-angular';
import { AuthInterceptor } from './shared/okta/auth.interceptor';
import { environment } from '../environments/environment';

import { MaterialModule } from './shared/material.module';

import { CoreModule } from './core/core.module';
import { ModulesModule } from './modules/modules.module';
import { LoginHandler } from './core/login/login-service/login.handler';

@NgModule({
  declarations: [AppComponent],
  imports: [
    AppRoutingModule,

    CommonModule,
    BrowserModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    HttpClientModule,
    OktaAuthModule,

    MaterialModule,
    CoreModule,
    ModulesModule,
  ],
  providers: [
    { provide: OKTA_CONFIG, useValue: environment.env.oktaConfig },
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
