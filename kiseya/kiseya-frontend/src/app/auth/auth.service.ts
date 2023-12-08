import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { UsuarioService } from '../services/usuario.service';
import { switchMap } from 'rxjs/operators';
//import { NotificationService } from '../services/notification.service';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private token: string | null = null;
  private loggedInUser: any | null = null;

  constructor(private usuarioService: UsuarioService) {}

  login(email: string, senha: string): Observable<any> {
    // Chamar o serviço de usuário para autenticar o usuário e obter o token
    return this.usuarioService.login(email, senha).pipe(
      switchMap((response) => {
        this.token = response.token;
        this.loggedInUser = response.usuario;

        // Salvar o token e a data/hora do login no armazenamento local
        if (this.token) {
          localStorage.setItem('token', this.token);
          localStorage.setItem('loginTime', new Date().toString());
        }

        return of(response);
      })
    );
  }

  logout(): void {
    // Limpar as informações de autenticação do armazenamento local
    localStorage.removeItem('token');
    localStorage.removeItem('loginTime');

    // Limpar as variáveis locais
    this.token = null;
    this.loggedInUser = null;
  }

  isLoggedIn(): boolean {
    // Verificar se o usuário está autenticado com base no token e no tempo de sessão
    const token = localStorage.getItem('token');
    const loginTime = localStorage.getItem('loginTime');

    if (!token || !loginTime) {
      return false;
    }

    const currentTime = new Date();
    const sessionDuration = 30 * 60 * 1000; // 30 minutos

    return currentTime.getTime() - new Date(loginTime).getTime() < sessionDuration;
  }

  getToken(): string | null {
    return this.token;
  }

  isAuthenticated(): boolean {
    // Se o token esta presente
    const token = localStorage.getItem('token');
    return !!token; // Retorna true se o token estiver presente
  }

  getLoggedInUser(): any | null {
    return this.loggedInUser;
  }

  getAuthToken(): string | null {
    return localStorage.getItem('token');
}
}