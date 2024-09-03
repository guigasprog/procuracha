import { Cliente } from './cliente.model';
import { FeedbackProfissional } from './feedback-profissional.model';
import { Servico } from './servico.model';

export class Profissional {
  id?: number;
  mediaFeedback!: number;
  feedbacksProfissional!: Array<FeedbackProfissional>;
  cliente!: Cliente;
  servicos!: Array<Servico>;
}
