// app-routing.module.ts
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/inicio/home/home.component';
import { CadastroComponent } from './components/inicio/cadastro/cadastro.component';
import { LoginComponent } from './components/inicio/login/login.component';
import { AboutComponent } from './components/inicio/about/about.component';
import { DashboardComponent } from './components/mid/dashboard/dashboard.component';
import { ProfileComponent } from './components/mid/profile/profile.component';
import { DispositivoEnergiaComponent } from './components/mid/dispositivo-energia/dispositivo-energia.component';
import { AuthGuard } from './auth/auth.guard';

const routes: Routes = [
  { path: 'home', component: HomeComponent, children: [
    { path: 'cadastro', component: CadastroComponent },
    { path: 'login', component: LoginComponent },
    { path: 'about', component: AboutComponent },
  ] 
  },

  { path: 'dashboard', component: DashboardComponent, canActivate: [AuthGuard]  },
  { path: 'dispositivos-energia', component: DispositivoEnergiaComponent, canActivate: [AuthGuard]  },
  { path: 'profile', component: ProfileComponent, canActivate: [AuthGuard]  },
  { path: '', redirectTo: '/home', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
