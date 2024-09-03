import { ProfissionalService } from './../../shared/services/profissional.service';
import { ClienteService } from './../../shared/services/cliente.service';
import { Component } from '@angular/core';
import { Cliente } from '../../shared/models/cliente.model';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { timeout } from 'rxjs';

@Component({
  selector: 'LOGIN',
  standalone: true,
  imports: [FormsModule],
  template: ` <div class="bg" style="position: absolute; top: 0;">
      <img
        style="width: 100%; transform: rotateX(0deg) rotateY(180deg); z-index: 1;"
        src="img/background.jpg"
      />
    </div>
    <div
      class="bg center"
      style="position: absolute; top: 0;
    background-image: linear-gradient(to right, #0c0c0c 25%, #737373, transparent);"
    >
      <main class="center">
        <aside>
          <img src="img/icon/procuracha.png" style="width: 100%;" />
          <img src="img/loginImage.png" style="width: 65%;" />
        </aside>
        <div class="margin"></div>
        <div class="flip">
          <div
            class="card"
            style="transform: rotateY({{ reg ? '180deg' : '0deg' }});"
          >
            <div class="formLogin">
              <div class="title campo">
                <h3 style="color: #000000">LOGIN</h3>
              </div>
              <div class="campo">
                <h4>EMAIL</h4>
                <input
                  type="text"
                  [(ngModel)]="login.email"
                  placeholder="exemplo@email.com"
                />
              </div>
              <div class="campo">
                <h4>SENHA</h4>
                <input type="password" [(ngModel)]="login.senha" />
              </div>
              <div
                class="campo center"
                style="height: 10%;
              margin: 0 0 0 -25px; cursor: pointer;"
                (click)="clickou()"
              >
                <h5 style="color: #000000">NÃO POSSUI CONTA - REGISTRAR</h5>
              </div>
              <div
                class="campo center"
                style="height: 15%;
              margin: 0 0 0 -25px;"
              >
                <button
                  style="background-color: #000000;"
                  (click)="logar(login)"
                >
                  LOGAR
                </button>
              </div>
            </div>

            <div class="formRegister">
              <div class="title" style="height: 10%;">
                <h3 style="color: #000000">REGISTRAR</h3>
              </div>
              <div
                class="campo center"
                style="height: 10%;
              margin: 10px 0 0 -25px; cursor: pointer;"
                (click)="clickou()"
              >
                <h5 style="color: #000000">JÁ POSSUI CONTA - LOGAR</h5>
              </div>
              <div class="campo" style="height: 10%;">
                <h4>CPF</h4>
                <input
                  type="text"
                  [(ngModel)]="registro.cpf"
                  placeholder="999.999.999-XX"
                />
              </div>
              <div class="campo" style="height: 10%;">
                <h4>NOME COMPLETO</h4>
                <input type="text" [(ngModel)]="registro.nome" />
              </div>
              <div class="campo" style="height: 10%;">
                <h4>EMAIL</h4>
                <input
                  type="text"
                  [(ngModel)]="registro.email"
                  placeholder="exemplo@email.com"
                />
              </div>
              <div class="campo" style="height: 10%;">
                <h4>SENHA</h4>
                <input type="password" [(ngModel)]="registro.senha" />
              </div>
              <div class="campo" style="height: 10%;">
                <h4>TELEFONE-CELULAR</h4>
                <input
                  type="text"
                  [(ngModel)]="registro.tel_cel"
                  placeholder="(99)99999-9999"
                />
              </div>
              <div
                class="campo center"
                style="height: 5%;
              margin: 30px 0 0 -25px;"
              >
                <h5 style="color: #000000">ENDEREÇO</h5>
              </div>
              <div class="campo" style="height: 10%;">
                <h4>ESTADO</h4>
                <input
                  type="text"
                  [(ngModel)]="registro.cidade.uf"
                  placeholder="SP"
                />
              </div>
              <div class="campo" style="height: 10%;">
                <h4>CIDADE</h4>
                <input
                  type="text"
                  [(ngModel)]="registro.cidade.nome"
                  placeholder="Assis"
                />
              </div>
              <div
                class="campo center"
                style="height: 20%;
              margin: 0 0 0 -25px;"
              >
                <button
                  style="background-color: #000000;"
                  (click)="registrar(registro)"
                >
                  REGISTRAR
                </button>
              </div>
            </div>
          </div>
        </div>
      </main>
    </div>`,
  styles: `
  .bg {
    background-color: transparent;
    main {
      width: 95%;
      height: 90%;
      aside {
        width: 30%;
        height: 100%;
        display: flex;
        align-items: center;
        flex-direction: column;
      }
      .margin {
        width: 39%;
      }
      .flip {
        width: 20%;
        height: 80%;
        padding: 25px;
        perspective: 1000px;
        backdrop-filter: blur(3px);
        .card {
          position: relative;
          width: 100%;
          height: 100%;
          margin: -25px 0 0 -25px;
          padding: 25px;
          background-color: transparent;
          border-radius: 20px;
          border: 2px solid #000000;
          transition: transform 0.8s;
          transform-style: preserve-3d;
          .formLogin, .formRegister {
            position: absolute;
            margin: -25px 0 0 0px;
            width: 100%;
            height: 100%;
            -webkit-backface-visibility: hidden;
            backface-visibility: hidden;
            .campo {
              width: 100%;
              height: 15%;
              input {
                color: #000000;
              }
              h4 {
                color: #000000;
                margin: 15px 0 0 10px;
              }
            }
            overflow-y: auto;
          }
          .formRegister {
            margin: -25px 0 0 -50px;
            transform: rotateY(180deg);
          }
        }
      }
      // .flip:hover .card {
      //   transform: rotateY(180deg);
      // }
    }
  }

  `,
})
export class LogInComponent {
  login: Cliente = new Cliente();
  registro: Cliente = new Cliente();

  reg: boolean = false;

  constructor(
    private clienteService: ClienteService,
    private profissionalService: ProfissionalService,
    private router: Router
  ) {}

  clickou() {
    this.reg = !this.reg;
    this.login = new Cliente();
    this.registro = new Cliente();
  }

  logar(login: Cliente) {
    this.clienteService.getClienteLogin(login).subscribe((success) => {
      localStorage.clear();
      localStorage.setItem('token', JSON.stringify(success));
      this.profissionalService
        .getProfissionais(success.id)
        .subscribe((success) => {
          localStorage.setItem('profissionais', JSON.stringify(success));
        });
      this.profissionalService
        .getProfissional(success.cpf)
        .subscribe((success) =>
          this.profissionalService
            .getProfissionais(success.id)
            .subscribe((success) => {
              localStorage.setItem('profissionais', JSON.stringify(success));
            })
        );
      setTimeout(() => this.router.navigate(['']), 1000);
    });
  }

  registrar(registro: Cliente) {
    this.clienteService.postClienteRegistro(registro).subscribe((success) => {
      this.logar(success);
    });
  }
}
