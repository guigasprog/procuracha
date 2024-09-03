import { Profissional } from './../models/profissional.model';
import { API } from './../models/api';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ProfissionalService {
  private API_PROFISSIONAL = `${API}/profissional`;

  constructor(private http: HttpClient) {}

  public getProfissionais(id?: number): Observable<Array<Profissional>> {
    return this.http.get<Array<Profissional>>(`${this.API_PROFISSIONAL}/${id}`);
  }

  public getProfissional(cpf: string): Observable<Profissional> {
    return this.http.get<Profissional>(
      `${this.API_PROFISSIONAL}/buscar/${cpf}`
    );
  }

  public postProfissional(profissionalForm: any): Observable<Profissional> {
    return this.http.post<Profissional>(
      `${this.API_PROFISSIONAL}/salvar/servico`,
      profissionalForm
    );
  }
}
