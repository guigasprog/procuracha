package br.edu.fema.procuracha.application.form;

import br.edu.fema.procuracha.domain.entity.ServicoEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ServicoForm {

    @JsonProperty("descricao")
    private String descricao;

    public ServicoForm() {}

    // Construtor com argumento, se necessário
    public ServicoForm(String descricao) {
        this.descricao = descricao;
    }

    public ServicoEntity converterFormForEntity(ServicoEntity servicoEntity) {
        servicoEntity.setDescricao(this.descricao);
        return servicoEntity;
    }

}
