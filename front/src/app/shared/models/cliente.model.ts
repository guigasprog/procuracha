import { Cidade } from './cidade.model';

export class Cliente {
  id?: number;

  nome!: string;

  cpf!: string;

  email!: string;

  senha!: string;

  tel_cel!: string;

  cidade: Cidade = new Cidade();
}
