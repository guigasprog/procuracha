package br.edu.fema.procuracha.application.form;

import br.edu.fema.procuracha.domain.entity.ClienteEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteForProfissionalForm {

    private String cpf;

    public ClienteEntity converterFormForEntity(ClienteEntity clienteEntity) {
        clienteEntity.setCpf(this.cpf);

        return clienteEntity;
    }

}
