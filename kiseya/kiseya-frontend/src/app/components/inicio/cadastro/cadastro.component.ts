// cadastro.component.ts

import { Component } from '@angular/core';
import { UsuarioService } from '../../../services/usuario.service';
import { Router } from '@angular/router';
import { NotificationsService } from 'angular2-notifications';

@Component({
  selector: 'app-cadastro',
  templateUrl: './cadastro.component.html',
  styleUrls: ['./cadastro.component.css']
})
export class CadastroComponent {
  usuario = { nomeCompleto: '', email: '', cpfCnpj: '', isPessoaFisica: true, senha: '' };

  constructor(private usuarioService: UsuarioService, private router: Router, private notifications: NotificationsService) { }

  cadastrarUsuario() {
    this.usuarioService.cadastrarUsuario(this.usuario)
  .subscribe(
    response => {
      console.log('Cadastro realizado com sucesso:', response);
      this.notifications.success('Sucesso', 'Cadastro realizado com sucesso');
      this.router.navigate(['/home/login']);
    },
    error => {
      console.error('Erro no cadastro:', error);
      console.error(error.error);
      this.notifications.error('Erro', 'Os Requisitos não foram preenchidos');
    }
  );

      }
}
