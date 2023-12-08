import { Component } from '@angular/core';
import { AuthService } from '../../../auth/auth.service';
import { UsuarioService } from '../../../services/usuario.service';
import { Router } from '@angular/router';
import { NotificationService } from '../../../services/notification.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  email: string = '';
  senha: string = '';

  constructor(
    private authService: AuthService,
    private usuarioService: UsuarioService,
    private router: Router,
    private notificationService: NotificationService
    ) {}

  realizarLogin() {
    this.authService.login(this.email, this.senha).subscribe(
      (response) => {
        // Lógica para lidar com a resposta de sucesso
        console.log('Login bem-sucedido', response);

        // Redirecionar para a página de dashboard
        this.router.navigate(['/dashboard']);
      },
      (error) => {
        // Lógica para lidar com erros
        console.error('Erro no login', error);
      }
    );
  }

  realizarLogout() {
    this.authService.logout();
    this.router.navigate(['/home']);
  }
}
