import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';

import { MaterialModule } from '../shared/material.module';

import { HomeComponent } from './home/home.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { LandingComponent } from './landing/landing.component';
import { LoginModule } from './login/login.module';
import { RegisterModule } from './register/register.module';

@NgModule({
  declarations: [HomeComponent, HeaderComponent, FooterComponent, LandingComponent],
  imports: [
    CommonModule,
    RouterModule,

    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,

    MaterialModule,

    LoginModule,
    RegisterModule
  ],
  exports: [LoginModule, HomeComponent, HeaderComponent, FooterComponent],
  providers: [],
})
export class CoreModule { }
