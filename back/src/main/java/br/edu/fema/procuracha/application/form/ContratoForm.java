package br.edu.fema.procuracha.application.form;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class ContratoForm {

    private LocalDate data;
    private LocalTime hora;
    private String descricao;
    private Long idCliente;
    private Long idProfissional;

}
