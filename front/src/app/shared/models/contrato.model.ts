import { Profissional } from './profissional.model';
import { Cliente } from './cliente.model';

export class Contrato {
  id?: number;

  data!: Date;

  hora!: string;

  descricao!: string;

  cliente!: Cliente;

  profissional!: Profissional;
}
