import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/inicio/home/home.component';
import { CadastroComponent } from './components/inicio/cadastro/cadastro.component';
import { LoginComponent } from './components/inicio/login/login.component';

import { SimpleNotificationsModule } from 'angular2-notifications';
import { DashboardComponent } from './components/mid/dashboard/dashboard.component';
import { JwtInterceptor } from './token-interceptor.service';
import { ProfileComponent } from './components/mid/profile/profile.component';
import { ProfileEditComponent } from './components/mid/profile-edit/profile-edit.component';
import { AboutComponent } from './components/inicio/about/about.component';
import { DispositivoEnergiaComponent } from './components/mid/dispositivo-energia/dispositivo-energia.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    CadastroComponent,
    LoginComponent,
    DashboardComponent,
    ProfileComponent,
    ProfileEditComponent,
    AboutComponent,
    DispositivoEnergiaComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    SimpleNotificationsModule.forRoot(),
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: JwtInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
