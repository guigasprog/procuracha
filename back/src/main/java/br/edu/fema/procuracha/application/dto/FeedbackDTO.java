package br.edu.fema.procuracha.application.dto;

import br.edu.fema.procuracha.domain.entity.FeedbackEntity;
import lombok.Getter;

import java.util.List;

@Getter
public class FeedbackDTO {

    private Long id;

    private boolean resolvido;

    private int nota;

    private String descricao;

    private ContratoDTO contrato;

    public FeedbackDTO(FeedbackEntity feedbackEntity) {
        if(feedbackEntity.isAceito()) {
            this.id = feedbackEntity.getId();
            this.descricao = feedbackEntity.getDescricao();
            this.resolvido = feedbackEntity.isResolvido();
            this.nota = feedbackEntity.getNota();
            this.contrato = new ContratoDTO(feedbackEntity.getContratoEntity());
        }
    }

    public List<FeedbackDTO> converterEntityForDto(List<FeedbackEntity> feedbackEntityList) {
        return feedbackEntityList.stream().map(FeedbackDTO::new).toList();
    }

}
