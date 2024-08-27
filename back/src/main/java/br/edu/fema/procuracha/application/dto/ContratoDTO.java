package br.edu.fema.procuracha.application.dto;

import br.edu.fema.procuracha.domain.entity.ClienteEntity;
import br.edu.fema.procuracha.domain.entity.ContratoEntity;
import br.edu.fema.procuracha.domain.entity.ProfissionalEntity;
import br.edu.fema.procuracha.domain.entity.ServicoEntity;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
public class ContratoDTO {

    private Long id;

    private LocalDate data;

    private LocalTime hora;

    private String descricao;

    private ClienteDTO cliente;

    private ProfissionalDTO profissional;

    public ContratoDTO(ContratoEntity contratoEntity) {
        this.cliente = new ClienteDTO(contratoEntity.getClienteEntity());
        if(!contratoEntity.getProfissionalEntity().getClienteEntity().equals(this.cliente)) {
            this.id = contratoEntity.getId();
            this.data = contratoEntity.getData();
            this.hora = contratoEntity.getHora();
            this.descricao = contratoEntity.getDescricao();
            this.profissional = new ProfissionalDTO(contratoEntity.getProfissionalEntity());
        }
    }

    public static List<ContratoDTO> converterEntityForDTO(List<ContratoEntity> contratoEntityList) {
        return contratoEntityList.stream().map(ContratoDTO::new).toList();
    }


}
