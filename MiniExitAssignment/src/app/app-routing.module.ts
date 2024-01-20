import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { IndexComponent } from './index/index.component';
import { NoPageComponent } from './no-page/no-page.component';
import { HomeComponent } from './home/home.component';
import { AddNoteComponent } from './add-note/add-note.component';
import { authGuard } from './services/auth.guard';

const routes: Routes = [
  {
    component: LoginComponent,
    path: 'login',
    pathMatch: 'full',
  },

  {
    component: RegistrationComponent,
    path: 'registration',
    pathMatch: 'full',
  },

  {
    component: HomeComponent,
    path: 'home',
    pathMatch: 'full',
    canActivate: [authGuard],
  },
  {
    component: AddNoteComponent,
    path: 'addnote',
    pathMatch: 'full',
    canActivate: [authGuard],
  },
  {
    component: IndexComponent,
    path: '',
    pathMatch: 'full',
  },
  {
    path: '**',
    component: NoPageComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
