import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RegisterComponent } from './register-form/register-form.component';
import { RegisterActivatedComponent } from './register-activated/register-activated.component';

export const routes: Routes = [
  { path: '', component: RegisterComponent },
  { path: 'activated/:username', component: RegisterActivatedComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class RegisterRoutingModule {}
