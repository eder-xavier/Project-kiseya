import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class DispositivoEnergiaService {
  private apiUrl = 'http://localhost:8080/api/dispositivosEnergia';

  constructor(private http: HttpClient) {}

  getAllDispositivosEnergia(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }

  getDispositivoEnergiaById(id: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/${id}`);
  }

  saveDispositivoEnergia(dispositivoEnergia: any): Observable<any> {
    // Adicione o token ao cabeçalho da requisição
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: 'Bearer ' + localStorage.getItem('token'), // Assumindo que você armazena o token no localStorage
    });

    return this.http.post<any>(this.apiUrl, dispositivoEnergia, { headers });
  }

  deleteDispositivoEnergia(id: number): Observable<any> {
    // Adicione o token ao cabeçalho da requisição
    const headers = new HttpHeaders({
      Authorization: 'Bearer ' + localStorage.getItem('token'), // Assumindo que você armazena o token no localStorage
    });

    return this.http.delete<any>(`${this.apiUrl}/${id}`, { headers });
  }
}
