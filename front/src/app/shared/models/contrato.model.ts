import { Profissional } from './profissional.model';
import { Cliente } from './cliente.model';

export class Contrato {
  data!: string;

  hora!: string;

  descricao!: string;

  cliente!: Cliente;

  profissional!: Profissional;
}
