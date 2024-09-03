import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Profissional } from '../../shared/models/profissional.model';
import { Router, RouterLink } from '@angular/router';
import { Cliente } from '../../shared/models/cliente.model';

@Component({
  selector: 'MENU',
  standalone: true,
  imports: [FormsModule, RouterLink],
  template: ` <div class="bg">
    <header class="center">
      <div class="logo center" style="width: 30%; height: 100%;">
        <img src="img/icon/procuracha.png" style="height: 100%; scale: 1.3;" />
      </div>

      <div class="margin" style="width: 10%;"></div>
      <input type="text" placeholder="PESQUISAR" />
      <div
        class="user center"
        style="height: 100%; width: 25%; justify-content: end;"
      >
        <img src="img/icon/perfil.png" style="height: 100%; scale: 0.6" />
        <div class="edit" style="width: 50%">
          <h3 style="font-size: 23px; margin: 0 0 3px 0; text-wrap: wrap;">
            {{ user.nome }}
          </h3>
          <h4
            (click)="perfil(user.cpf)"
            style="font-weight: 500; font-size: 13px; margin: 0 0 0 0; cursor: pointer"
          >
            Perfil
          </h4>
        </div>
      </div>
    </header>
    <header
      class="center"
      style="gap: 10px; background-color: transparent; border-bottom: 2px solid #ffffff"
    >
      <input
        type="text"
        placeholder="UF"
        style="width: 5%"
        maxlength="2"
        [(ngModel)]="uf"
        (change)="aplicarFiltro()"
      />
      <input
        type="text"
        placeholder="CIDADE"
        style="width: 15%"
        [(ngModel)]="cid"
        (change)="aplicarFiltro()"
      />
      <input
        type="text"
        placeholder="FUNÇÃO"
        style="width: 15%"
        [(ngModel)]="func"
        (change)="aplicarFiltro()"
      />
      <input
        type="number"
        placeholder="RELEVANCIA"
        style="width: 15%"
        min="0"
        max="10"
        [(ngModel)]="rev"
        (change)="aplicarFiltro()"
      />
      <div class="margin" style="width: 35%;"></div>
    </header>
    <main>
      <div class="feedback">
        @if(profissionais) { @for(profissional of profissionais; track $index) {
        <div class="feedbacks">
          <div
            (click)="perfil(profissional.cliente.cpf)"
            class="card"
            style=" cursor: pointer;
        padding: 2%;"
          >
            <h2 style="margin: 0">
              {{ profissional.cliente.nome }} •
              {{ profissional.cliente.cidade.nome }}/{{
                profissional.cliente.cidade.uf
              }}
              • {{ profissional.mediaFeedback }}/10
            </h2>
            @for(feedback of profissional.feedbacksProfissional; track $index) {
            @if(feedback.resolvido) {
            <p style="margin: 10px 0 10px 0;">
              {{ feedback.descricaoContrato }} • {{ feedback.nota }}/10
            </p>
            } }
          </div>
        </div>
        } }
      </div>

      <div class="maiorRelevancia">
        <div class="card">
          <div
            class="dados"
            style="cursor: pointer"
            (click)="perfil(profissionalMaisRelevante.cliente.cpf)"
          >
            <h1 style="margin: 0">Mais Relevante</h1>
            <h2>
              {{ profissionalMaisRelevante.cliente.nome }} •
              {{ profissionalMaisRelevante.cliente.cidade.nome }}/{{
                profissionalMaisRelevante.cliente.cidade.uf
              }}
              • {{ profissionalMaisRelevante.mediaFeedback }}/10
            </h2>
            @for(feedback of profissionalMaisRelevante.feedbacksProfissional;
            track $index) { @if(feedback.resolvido) {
            <p style="margin: 0 0 15px 0;">
              {{ feedback.descricaoContrato }} • {{ feedback.nota }}/10
            </p>
            } }
          </div>
        </div>
      </div>
    </main>
  </div>`,
  styles: `
  .bg {
    overflow: hidden;
    overflow-y: auto;
    position: absolute;
    top: 0;
    left: 0;
    header {
      width: 100%;
      height: 5rem;
      background-color: #000000;
      input {
        margin: 0;
        padding: 5px 0 0 15px;
        border-radius: 15px;
        background-color: #ffffff;
        font-weight: 700;
        font-size: 14px;
        width: 30%;
        font-family: "League Spartan", sans-serif;
        color: #000000;
      }
    }
    main {
      width: 100%;
      height: 76%;
      display: flex;
      .feedback {
        width: 55%;
        height: auto;
        .feedbacks {
          width: 90%;
          padding: 3%;
          padding-bottom: 0.1%;
          .card {
            width: 100%;
            height: auto;
            padding: 20px;
            border-bottom: 2px solid #ffffff;
          }
        }

      }
      .maiorRelevancia {
        width: 45%;
        padding-top: 3%;
        padding-bottom: 3%;
        .card {
          width: 100%;
          height: 100%;
          padding-left: 3%;
          padding-top: 2%;
          border-left: 2px solid #ffffff;
          .dados {
            width: 100%;
            height: 100%;
            .card {
              border-left: none;
            }
          }
        }
      }
    }

  }

  `,
})
export class MenuComponent implements OnInit {
  user: Cliente = new Cliente();
  rawProfissionais: Array<Profissional> = [];
  profissionais: Array<Profissional> = [];
  profissionalMaisRelevante: Profissional = new Profissional();
  uf?: string;
  cid?: string;
  func?: string;
  rev?: number;

  constructor(private router: Router) {}

  ngOnInit(): void {
    this.rawProfissionais = JSON.parse(localStorage.getItem('profissionais')!);
    console.log(this.rawProfissionais);
    this.profissionais = this.rawProfissionais;
    this.user = JSON.parse(localStorage.getItem('token')!);
    this.profissionalMaisRelevante = this.rawProfissionais
      .map((prof) => ({
        ...prof,
        totalFeedbacks: prof.feedbacksProfissional.length,
        mediaFeedback: prof.mediaFeedback || 0,
      }))
      .reduce((maisRelevante, atual) => {
        if (!maisRelevante) {
          return atual;
        }

        if (
          atual.totalFeedbacks > maisRelevante.totalFeedbacks ||
          (atual.totalFeedbacks === maisRelevante.totalFeedbacks &&
            atual.mediaFeedback > maisRelevante.mediaFeedback)
        ) {
          return atual;
        }
        return maisRelevante;
      }, null as any);
  }

  aplicarFiltro() {
    this.aplicarFiltros({
      uf: this.uf,
      cidade: this.cid,
      funcao: this.func,
      relevancia: this.rev == null ? undefined : this.rev,
    });
  }

  aplicarFiltros(filtros: {
    uf?: string;
    cidade?: string;
    funcao?: string;
    relevancia?: number;
  }) {
    this.profissionais = this.rawProfissionais.filter((prof) => {
      const ufMatches = filtros.uf
        ? prof.cliente.cidade.uf
            .toLowerCase()
            .includes(filtros.uf.toLowerCase())
        : true;
      const cidadeMatches = filtros.cidade
        ? prof.cliente.cidade.nome
            .toLowerCase()
            .includes(filtros.cidade.toLowerCase())
        : true;
      const funcaoMatches = filtros.funcao
        ? prof.servicos.some((servico) =>
            servico.descricao
              .toLowerCase()
              .includes(filtros.funcao?.toLowerCase() ?? '')
          )
        : true;
      const relevanciaMatches =
        filtros.relevancia !== undefined
          ? prof.mediaFeedback === filtros.relevancia
          : true;

      return ufMatches && cidadeMatches && funcaoMatches && relevanciaMatches;
    });
  }

  perfil(cpf: string) {
    this.router.navigate(['/user', cpf]);
  }
}
