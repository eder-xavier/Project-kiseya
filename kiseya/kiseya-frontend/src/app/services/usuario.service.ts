import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  private apiUrl = 'http://localhost:8080/api/usuarios';
  private usuarioUrl = '/api/usuarios';

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient) {}

  login(email: string, senha: string): Observable<any> {
    const credentials = { email, senha };
    return this.http.post(`${this.apiUrl}/login`, credentials);
  }

  cadastrarUsuario(usuario: any): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/cadastrar`, usuario, this.httpOptions)
      .pipe(
        catchError(this.handleError)
      );
  }

  private handleError(error: any): Observable<never> {
    console.error('Ocorreu um erro', error);
    return throwError('Erro ao processar a solicitação. Por favor, tente novamente mais tarde.');
  }

  uploadProfilePicture(file: File): Observable<any> {
    const formData: FormData = new FormData();
    formData.append('file', file, file.name);

    return this.http.post<any>(`${this.apiUrl}/upload-profile-picture`, formData);
  }

  getUsuarioLogado(): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/usuarios/me`);
  }
}
