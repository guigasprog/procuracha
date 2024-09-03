import { Cliente } from './../models/cliente.model';
import { API } from './../models/api';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ClienteService {
  private API_CLIENTE = `${API}/cliente`;

  constructor(private http: HttpClient) {}

  public getClienteLogin(cliente: Cliente): Observable<Cliente> {
    return this.http.post<Cliente>(`${this.API_CLIENTE}/logar`, cliente);
  }

  public postClienteRegistro(cliente: Cliente): Observable<Cliente> {
    return this.http.post<Cliente>(`${this.API_CLIENTE}/registro`, cliente);
  }
}
