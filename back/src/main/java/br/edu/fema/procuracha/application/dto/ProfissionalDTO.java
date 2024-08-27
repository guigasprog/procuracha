package br.edu.fema.procuracha.application.dto;

import br.edu.fema.procuracha.domain.entity.ProfissionalEntity;
import lombok.Getter;

import java.util.List;

@Getter
public class ProfissionalDTO {

    private Long id;

    private ClienteDTO cliente;

    private List<ServicoDTO> servicos;

    public ProfissionalDTO(ProfissionalEntity profissionalEntity) {
        this.id = profissionalEntity.getId();
        this.cliente = new ClienteDTO(profissionalEntity.getClienteEntity());
        this.servicos = ServicoDTO.converterEntityForDTO(profissionalEntity.getServicoEntities());
    }

    public static List<ProfissionalDTO> converterEntityForDTO(List<ProfissionalEntity> profissionalEntityList) {
        return profissionalEntityList.stream().map(ProfissionalDTO::new).toList();
    }

}
