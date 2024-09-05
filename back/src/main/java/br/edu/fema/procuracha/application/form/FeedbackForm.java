package br.edu.fema.procuracha.application.form;

import br.edu.fema.procuracha.domain.entity.FeedbackEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedbackForm {

    private boolean resolvido;

    private int nota;

    private String descricao;

    public FeedbackEntity converterFormForEntity(FeedbackEntity feedbackEntity) {
        if(feedbackEntity.isAceito()) {
            feedbackEntity.setResolvido(this.resolvido);
            feedbackEntity.setDescricao(this.descricao);
            feedbackEntity.setNota(this.nota);
            return feedbackEntity;
        }
        return null;
    }


}
