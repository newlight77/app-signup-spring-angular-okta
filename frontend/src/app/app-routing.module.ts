import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { OktaCallbackComponent } from '@okta/okta-angular';
import { LandingComponent } from './core/landing/landing.component';

const routes: Routes = [
  { path: '', redirectTo: '', pathMatch: 'full' },
  { path: 'landing', component: LandingComponent },
  {
    path: 'login',
    loadChildren: () => import('./core/login/login.module').then((m) => m.LoginModule),
  },
  {
    path: 'register',
    loadChildren: () => import('./core/register/register.module').then((m) => m.RegisterModule),
  },
  {
    path: 'notes',
    loadChildren: () => import('./modules/note/note.module').then((m) => m.NoteModule),
  },
  { path: 'implicit/callback', component: OktaCallbackComponent },
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
