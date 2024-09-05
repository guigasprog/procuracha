import { Cliente } from '../models/cliente.model';
import { API } from '../models/api';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { FeedbackProfissional } from '../models/feedback-profissional.model';
import { Feedback } from '../models/feedback.model';

@Injectable({
  providedIn: 'root',
})
export class FeedbackService {
  private API_FEEDBACK = `${API}/feedback`;

  constructor(private http: HttpClient) {}

  public getFeedback(idProfissional: number): Observable<Array<Feedback>> {
    return this.http.get<Array<Feedback>>(
      `${this.API_FEEDBACK}/buscar/${idProfissional}`
    );
  }

  public aceitarFeedback(idContrato: number): Observable<Feedback> {
    return this.http.get<Feedback>(
      `${this.API_FEEDBACK}/aceitar/${idContrato}`
    );
  }

  public postFeedback(
    id: number,
    feedbackForm: Feedback
  ): Observable<Feedback> {
    return this.http.post<Feedback>(`${this.API_FEEDBACK}/${id}`, feedbackForm);
  }
}
