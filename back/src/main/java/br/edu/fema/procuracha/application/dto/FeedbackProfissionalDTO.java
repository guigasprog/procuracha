package br.edu.fema.procuracha.application.dto;

import br.edu.fema.procuracha.domain.entity.FeedbackEntity;
import lombok.Getter;

import java.util.List;

@Getter
public class FeedbackProfissionalDTO {

    private Long id;

    private boolean resolvido;

    private int nota;

    private String descricao;

    private String descricaoContrato;

    private String nomeCliente;

    public FeedbackProfissionalDTO(FeedbackEntity feedbackEntity) {
        if(feedbackEntity.isAceito()) {
            this.id = feedbackEntity.getId();
            this.descricao = feedbackEntity.getDescricao();
            this.resolvido = feedbackEntity.isResolvido();
            this.nota = feedbackEntity.getNota();
            this.descricaoContrato = feedbackEntity.getContratoEntity().getDescricao();
            this.nomeCliente = feedbackEntity.getContratoEntity().getClienteEntity().getNome();
        }
    }

    public static List<FeedbackProfissionalDTO> converterEntityForDto(List<FeedbackEntity> feedbackEntityList) {
        return feedbackEntityList.stream().filter(FeedbackEntity::isAceito).map(FeedbackProfissionalDTO::new).toList();
    }
}
