import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from 'src/app/shared/material.module';
import { RegisterComponent } from './register-form/register-form.component';
import { RegisterRoutingModule } from './register-routing.module';
import { RegisterService } from './register-service/register.service';
import { RegisterActivatedComponent } from './register-activated/register-activated.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    MaterialModule,
    RegisterRoutingModule
  ],
  declarations: [
    RegisterComponent,
    RegisterActivatedComponent],
  providers: [RegisterService],
  exports: [],
})
export class RegisterModule { }
