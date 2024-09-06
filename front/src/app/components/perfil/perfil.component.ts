import { FeedbackService } from './../../shared/services/feedback.service';
import { Component, OnInit } from '@angular/core';
import { Cliente } from '../../shared/models/cliente.model';
import {
  FormBuilder,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
} from '@angular/forms';
import { Profissional } from '../../shared/models/profissional.model';
import { ActivatedRoute, Router } from '@angular/router';
import { ProfissionalForm } from '../../shared/models/profissionalForm.form';
import { Servico } from '../../shared/models/servico.model';
import { Contrato } from '../../shared/models/contrato.model';
import { ProfissionalService } from '../../shared/services/profissional.service';
import { ContratoService } from '../../shared/services/contrato.service';
import { ContratoForm } from '../../shared/models/contrato.form';
import { DatePipe } from '@angular/common';
import { Feedback } from '../../shared/models/feedback.model';

@Component({
  selector: 'USER',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule, DatePipe],
  template: ` <div class="bg">
      <header class="center">
        <img
          src="img/icon/procuracha.png"
          style="height: 100%; scale: 1.3; cursor: pointer"
          (click)="voltarMenu()"
        />
        <div class="margin" style="width: 40%;"></div>
        <input type="text" placeholder="PESQUISAR" list="profissionais" />
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
          <button (click)="contratacao = true">Contratar</button>
          }
        </div>
      </div>
      @if(profissional) { @if(feedbacks.length > 0) {
      <div
        class="main center"
        style="width: 100%; height: auto; padding: 1% 0;"
      >
        <div
          class="content center"
          style="width: 80%; flex-direction: column; border-bottom: 2px solid #ffffff; padding: 1%;"
        >
          <h1>DEIXE SEU FEEDBACK AQUI APÓS O SERVIÇO</h1>
          @for(feedback of feedbacks; track $index) {
          <h2>{{ feedback.contratoEntity.descricao }}</h2>
          <div
            class="content center"
            style="width: 80%; flex-direction: column; gap: 15px;"
          >
            <label for="resolvido" class="center" style="width: 100%"
              ><input
                type="checkbox"
                name="resolvido"
                [(ngModel)]="feedbackForm!.resolvido"
                style="border-radius: 10px; width: 20px; height: 20px; margin-top: -2px"
              />
              Resolveu?
            </label>
            <label class="center" style="width: 100%; flex-direction: column;">
              Descreva como foi o desempenho do profissional
              <input
                style="border: 2px solid #ffffff; color: #ffffff"
                type="text"
                [(ngModel)]="feedbackForm!.descricao"
                placeholder="Ex: O corte de cabelo foi muito bom"
              />
            </label>
            <label class="center" style="width: 100%; flex-direction: column;">
              Que nota você daria?
              <input
                [(ngModel)]="feedbackForm!.nota"
                style="border: 2px solid #ffffff; color: #ffffff"
                type="number"
              />
            </label>
            <button
              style="background-color: #3c9966"
              (click)="postFeedback(feedback)"
            >
              ENVIAR
            </button>
          </div>
          }
        </div>
      </div>
      }
      <div
        class="main"
        style="width: 100%; height: auto; display: flex; padding: 3% 0;"
      >
        <div class="feedbacks">
          <h1>FEEDBACK</h1>
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
        @if(profissional.cliente.id == user.id) {
        <div class="contratos">
          <h1>CONTRATOS</h1>
          @for(contrato of contratos; track $index) {
          <div class="card">
            <div>
              <h2 style="margin: 0 0 0 5px; font-weight: 500">
                {{ contrato.descricao }} •
                {{ contrato.data | date : 'dd/MM/yyyy' }} •
                {{ contrato.hora }}
              </h2>
            </div>
            <div>
              <h4 style="margin: 0 0 0 5px; font-weight: 500">
                {{ contrato.cliente.nome }} • {{ contrato.cliente.email }}
              </h4>
              <h4 style="margin: 0 0 0 5px; font-weight: 500">
                {{ contrato.cliente.cidade.nome }}/{{
                  contrato.cliente.cidade.uf
                }}
              </h4>
            </div>
            <div class="center" style="width: 100%; padding: 5px;">
              <button
                class="center"
                style="text-warp: warp; gap: 10px; background-color: #3c9966"
                (click)="aceitarContrato(contrato.id!)"
              >
                ACEITAR
              </button>
            </div>
          </div>
          }
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
              (click)="postprofissionalService()"
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
              (click)="postprofissionalService(formServico)"
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
            (click)="postprofissionalService()"
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

      <div
        class="modal"
        style="{{
          contratacao
            ? 'opacity: 1; z-index: 5000;'
            : 'opacity: 0; z-index: -5000;'
        }}"
      >
        <div class="modal-content center">
          <span class="close" (click)="contratacao = false">&times;</span>
          <h1>CONTRATAR</h1>
          <form class="center" [formGroup]="formulario">
            <div>
              <label for="data">Data:</label>
              <input id="data" type="date" formControlName="data" />
            </div>

            <div>
              <label for="hora">Hora:</label>
              <input id="hora" type="time" formControlName="hora" />
            </div>

            <div>
              <label for="descricao">Descrição:</label>
              <input id="descricao" type="text" formControlName="descricao" />
            </div>
          </form>

          <div class="center" style="gap: 30px;">
            <button
              class="center"
              style="text-warp: warp; gap: 10px; background-color: #c45b52"
              (click)="postprofissionalService()"
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
              (click)="onSubmit()"
            >
              <img
                src="img/icon/plus.png"
                style="height: 70%; filter: invert(100%)"
              />
              CONTRATAR
            </button>
          </div>
        </div>
      </div>
    </div>
    @for(profissional of profissionais; track $index){
    <option value="{{ profissional.cliente.nome }}"></option>
    }`,
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
    .contratos {
      width: 45%;
      padding: 3%;
      border-left: 2px solid #ffffff;
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
        form {
          margin: 1%;
          gap: 15px;
          div {
            display: flex;
            flex-direction: column;
            input[type="date"]{
              background-color: #ffffff;
              border: 2px solid #ffffff;
              color: #000000;
              padding: 0 10px;
            }
            input[type="time"]{
              background-color: #ffffff;
              border: 2px solid #ffffff;
              color: #000000;
              padding: 0 10px;
            }
            input{
              background-color: #ffffff;
              border: 2px solid #ffffff;
              color: #000000;
              padding: 0 10px;
            }
            label {
              font-weight: 500;
              margin: 0 0 0 10px;
            }
          }
        }

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
  contratacao: boolean = false;
  contrato: ContratoForm = new ContratoForm();
  formulario: FormGroup;
  contratos: Array<Contrato> = [];
  feedbacks: Array<Feedback> = [];
  feedbackForm?: Feedback = new Feedback();
  profissionais: Array<Profissional> = [];

  constructor(
    private profissionalService: ProfissionalService,
    private contratoService: ContratoService,
    private feedbackService: FeedbackService,
    private router: Router,
    private _route: ActivatedRoute,
    private fb: FormBuilder
  ) {
    this.formulario = this.fb.group({
      data: [new Date().toISOString().substring(0, 10)], // Inicializa com a data atual
      hora: [new Date().toISOString().substring(11, 16)], // Inicializa com a hora atual
      descricao: [''],
    });
  }

  ngOnInit(): void {
    if (localStorage.getItem(`token`) != null)
      this.user = JSON.parse(localStorage.getItem(`token`)!);
    this._route.params.subscribe((params) => {
      this.getProfissional(params['cpf']);
    });
    this.profissionais = JSON.parse(localStorage.getItem('profissionais')!);
  }

  getProfissional(cpf: string) {
    this.profissionalService.getProfissional(cpf).subscribe((success) => {
      this.profissional = success;
      console.log(this.user.id + ' ' + this.profissional?.cliente.id);
      if (this.profissional)
        if (this.user.id == this.profissional?.cliente.id) {
          this.getContratos();
          this.getFeedback();
        }
    });
  }

  getContratos() {
    this.contratoService
      .getContratosProfissional(this.user.id!)
      .subscribe((success) => (this.contratos = success));
  }

  getFeedback() {
    this.feedbackService
      .getFeedback(this.profissional!.id!)
      .subscribe((success) => {
        this.feedbacks = success;
        console.log(this.feedbacks);
      });
  }

  postFeedback(feedback: Feedback) {
    console.log(this.feedbackForm);
    this.feedbackService
      .postFeedback(feedback.id!, this.feedbackForm!)
      .subscribe((success) =>
        this.router
          .navigateByUrl('/', { skipLocationChange: true })
          .then(() => {
            this.router.navigate(['/user', this.user.cpf]);
          })
      );
  }

  postprofissionalService(formServico?: string) {
    if (formServico != undefined) {
      this.profissionalForm.clienteForm = new Cliente();
      this.profissionalForm.clienteForm.cpf = this.user.cpf;
      this.profissionalForm.servicoForm = new Servico();
      this.profissionalForm.servicoForm.descricao = formServico;
      this.profissionalService
        .postProfissional(this.profissionalForm)
        .subscribe((success) => {
          this.router
            .navigateByUrl('/', { skipLocationChange: true })
            .then(() => {
              this.router.navigate(['/user', this.user.cpf]);
            });
        });
    } else {
      this.formServico = '';
      this.forms = !this.forms;
    }
  }

  aceitarContrato(idContrato: number) {
    this.feedbackService.aceitarFeedback(idContrato).subscribe((success) =>
      this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
        this.router.navigate(['/user', this.user.cpf]);
      })
    );
  }

  onSubmit(): void {
    this.contratacao = false;
    let contrato: ContratoForm = new ContratoForm();
    contrato.data = this.formulario.get('data')!.value;
    contrato.hora = this.formulario.get('hora')!.value;
    contrato.descricao = this.formulario.get('descricao')!.value;
    contrato.idCliente = this.user.id!;
    contrato.idProfissional = this.profissional!.id!;
    this.contratoService
      .postContrato(contrato)
      .subscribe((success) => console.log(success));
  }

  voltarMenu() {
    this.router.navigate(['']);
  }
}
