package br.edu.fema.procuracha.application.dto;

import br.edu.fema.procuracha.domain.entity.ServicoEntity;
import lombok.Getter;

import java.util.List;

@Getter
public class ServicoDTO {

    private Long id;

    private String descricao;

    public ServicoDTO(ServicoEntity servicoEntity) {
        this.id = servicoEntity.getId();
        this.descricao = servicoEntity.getDescricao();
    }

    public static List<ServicoDTO> converterEntityForDTO(List<ServicoEntity> servicoEntityList) {
        return servicoEntityList.stream().map(ServicoDTO::new).toList();
    }

}
