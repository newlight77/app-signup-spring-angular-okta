import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LoginListComponent } from './login-list/login-list.component';
import { LoginService } from './login-service/login.service';
import { LoginRoutingModule } from './login-routing.module';
import { MaterialModule } from 'src/app/shared/material.module';
import { LoginFormComponent } from './login-form/login-form.component';
import { LoginHandler } from './login-service/login.handler';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    MaterialModule,
    LoginRoutingModule
  ],
  declarations: [
    LoginListComponent,
    LoginFormComponent,
  ],
  providers: [LoginService, LoginHandler],
  exports: [],
})
export class LoginModule { }
