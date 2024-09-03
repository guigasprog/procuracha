import { Component, OnInit } from '@angular/core';
import { Cliente } from '../../shared/models/cliente.model';
import { FormsModule } from '@angular/forms';
import { Profissional } from '../../shared/models/profissional.model';
import { ProfissionalService } from '../../shared/services/profissional.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ProfissionalForm } from '../../shared/models/profissionalForm.form';
import { Servico } from '../../shared/models/servico.model';

@Component({
  selector: 'USER',
  standalone: true,
  imports: [FormsModule],
  template: ` <div class="bg">
    <header class="center">
      <img
        src="img/icon/procuracha.png"
        style="height: 100%; scale: 1.3; cursor: pointer"
        (click)="voltarMenu()"
      />
      <div class="margin" style="width: 40%;"></div>
      <input type="text" placeholder="PESQUISAR" />
    </header>
    <div class="perfil center">
      <div class="icon center">
        <img src="img/icon/perfil.png" style="height: 70%;" />
      </div>
      <div class="dados">
        <div class="title">
          <h2 style="margin: 40px 0 0 0;">
            @if(profissional) {
            {{ profissional.cliente.nome }}
            } @else {
            {{ user.nome }}
            }
          </h2>
        </div>
        <div class="sub-title" style="height: 35%; width: 35%;">
          @if(profissional) {
          <div class="servico" style="height: 100%; overflow-y: auto;">
            @for (servico of profissional.servicos; track $index) {
            <h5 style="margin: 5px 0 0 0; font-warp: warp">
              {{ servico.descricao.toUpperCase() }}
            </h5>
            }
          </div>
          }

          <h5 style="margin: 5px 0 0 0;">
            @if(profissional) {
            {{ profissional.cliente.cidade.nome.toUpperCase() }}
            } @else {
            {{ user.cidade.nome.toUpperCase() }}
            } / @if(profissional) {
            {{ profissional.cliente.cidade.uf.toUpperCase() }}
            } @else {
            {{ user.cidade.uf.toUpperCase() }}
            }
          </h5>
        </div>
      </div>
      <div class="button center">
        @if(!profissional || user.id! == profissional.cliente.id) {
        <h3 id="edit" (click)="editPerfil = true">Editar perfil</h3>
        } @else {
        <button>Contratar</button>
        }
      </div>
    </div>
    @if(profissional) {
    <div class="feedbacks">
      @for(feedback of profissional.feedbacksProfissional; track $index) {
      <div class="card">
        <div class="title">
          <h6 style="margin: 0 0 0 5px; font-weight: 500">
            {{ feedback.descricaoContrato }} • {{ feedback.nota }}/10
          </h6>
        </div>
        <div>
          <h4 style="margin: 0 0 0 5px; font-weight: 500">
            {{ feedback.descricao }}
          </h4>
        </div>
      </div>
      }
    </div>
    }
    <div
      class="modal"
      style="{{
        editPerfil
          ? 'opacity: 1; z-index: 5000;'
          : 'opacity: 0; z-index: -5000;'
      }}"
    >
      <div class="modal-content center">
        <span class="close" (click)="editPerfil = false">&times;</span>
        <h3>Adicione seus serviços, se for profissional.</h3>
        @if(forms) {
        <input
          type="text"
          [(ngModel)]="formServico"
          placeholder="Informe o serviço"
          style="margin-bottom: 10px; border: 2px solid #ffffff; color: #ffffff"
        />
        } @if(forms) {
        <div class="center" style="gap: 30px;">
          <button
            class="center"
            style="text-warp: warp; gap: 10px; background-color: #c45b52"
            (click)="postService()"
          >
            <img
              src="img/icon/plus.png"
              style="height: 70%; filter: invert(100%); rotate: 45deg"
            />
            CANCELAR
          </button>
          <button
            class="center"
            style="text-warp: warp; gap: 10px; background-color: #3c9966"
            (click)="postService(formServico)"
          >
            <img
              src="img/icon/plus.png"
              style="height: 70%; filter: invert(100%)"
            />
            ADICIONAR
          </button>
        </div>

        } @else {
        <button
          class="center"
          style="text-warp: warp; height: 80px; gap: 10px"
          (click)="postService()"
        >
          <img
            src="img/icon/plus.png"
            style="height: 50%; filter: invert(100%)"
          />
          Adicionar Serviço
        </button>
        }
      </div>
    </div>
  </div>`,
  styles: `
  .bg {
    overflow: hidden;
    overflow-y: auto;
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
    .perfil {
      width: 100%;
      height: 250px;
      padding: 20px 0 20px 20px;
      justify-content: start;
      border-bottom: 2px solid #ffffff;
      .icon {
        width: 300px;
        height: 100%;
      }
      .dados {
        width: 60%;
        height: 100%;
        .sub-title {
          h5 {
            font-weight: 400;
          }
        }
      }
      .button {
        width: 14%;
        height: 100%;
        justify-content: end;
        align-items: end;
        #edit:hover {
          cursor: pointer;
          border-bottom: 1px solid #ffffff;
        }
      }
    }
    .feedbacks {
      width: 55%;
      padding: 3%;
      .card {
        width: 100%;
        height: auto;
        padding-top: 20px;
        padding-bottom: 5px;
        border-bottom: 2px solid #ffffff;
      }
    }
    .modal {
      transition: 1000ms;
      opacity: 0;
      position: fixed;
      z-index: -5000;
      left: 0;
      top: 0;
      width: 100%;
      height: 100%;
      overflow: auto;
      background-color: rgb(0,0,0);
      background-color: rgba(0,0,0,0.4);
      .modal-content {
        border-radius: 10px;
        background-color: #141414;
        margin: 15% auto;
        flex-direction: column;
        padding: 20px;
        border: 1px solid #888;
        width: 80%;
        position: relative;
        overflow-y: auto;
      }
      .close {
        position: absolute;
        top: 15px;
        right: 20px;
        color: #aaa;
        float: right;
        font-size: 28px;
        font-weight: bold;
        transition: 800ms;
      }

      .close:hover,
      .close:focus {
        color: red;
        text-decoration: none;
        cursor: pointer;
      }
    }
  }

  `,
})
export class PerfilComponent implements OnInit {
  user: Cliente = new Cliente();
  profissional?: Profissional;
  editPerfil: boolean = false;
  forms: boolean = false;
  formServico: string = '';
  profissionalForm: ProfissionalForm = new ProfissionalForm();

  constructor(
    private service: ProfissionalService,
    private router: Router,
    private _route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    if (localStorage.getItem(`token`) != null)
      this.user = JSON.parse(localStorage.getItem(`token`)!);
    this._route.params.subscribe((params) => {
      this.getProfissional(params['cpf']);
    });
  }

  getProfissional(cpf: string) {
    this.service
      .getProfissional(cpf)
      .subscribe((success) => (this.profissional = success));
  }

  postService(formServico?: string) {
    if (formServico != undefined) {
      this.profissionalForm.clienteForm = new Cliente();
      this.profissionalForm.clienteForm = this.user;
      this.profissionalForm.servicoForm = new Servico();
      this.profissionalForm.servicoForm.descricao = formServico;
      this.service
        .postProfissional(this.profissionalForm)
        .subscribe((success) => console.log(success));
    } else {
      this.formServico = '';
      this.forms = !this.forms;
    }
  }

  voltarMenu() {
    this.router.navigate(['']);
  }
}
