import { Contrato } from './contrato.model';

export class Feedback {
  id?: number;

  aceito!: boolean;

  resolvido: boolean = false;

  nota: number = 0;

  descricao: string = '';

  contratoEntity!: Contrato;
}
