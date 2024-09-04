import { API } from './../models/api';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ContratoForm } from '../models/contrato.form';
import { Contrato } from '../models/contrato.model';

@Injectable({
  providedIn: 'root',
})
export class ContratoService {
  private API_CONTRATO = `${API}/contrato`;

  constructor(private http: HttpClient) {}

  public getContratosProfissional(id: number): Observable<Array<Contrato>> {
    return this.http.get<Array<Contrato>>(
      `${this.API_CONTRATO}/profissional/${id}`
    );
  }

  public postContrato(contrato: ContratoForm): Observable<Contrato> {
    return this.http.post<Contrato>(`${this.API_CONTRATO}/criar`, contrato);
  }
}
