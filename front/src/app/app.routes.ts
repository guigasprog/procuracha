import { Routes } from '@angular/router';
import { LogInComponent } from './components/logar/login.component';
import { PerfilComponent } from './components/perfil/perfil.component';
import { MenuComponent } from './components/menu/menu.component';

export const routes: Routes = [
  { path: 'login', component: LogInComponent },
  { path: 'user/:cpf', component: PerfilComponent },
  { path: '', component: MenuComponent },
];
