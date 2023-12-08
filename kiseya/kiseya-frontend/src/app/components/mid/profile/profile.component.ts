import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../../auth/auth.service';
import { UsuarioService } from '../../../services/usuario.service';
import { HttpErrorResponse } from '@angular/common/http';
import { catchError } from 'rxjs/operators';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  loggedInUser: any | null;

  constructor(private authService: AuthService, private usuarioService: UsuarioService) {}

  ngOnInit(): void {
    this.getLoggedInUser();
  }

  getLoggedInUser(): void {
    if (this.authService.isAuthenticated()) {
      this.usuarioService.getUsuarioLogado().pipe(
        catchError((error: HttpErrorResponse) => {
          console.error('Erro ao obter usuário logado', error);
          throw error; // Rejeita o erro para que o bloco catch posterior também possa lidar com ele
        })
      ).subscribe(
        (user) => {
          this.loggedInUser = user;
        }
      );
    }
  }
}
