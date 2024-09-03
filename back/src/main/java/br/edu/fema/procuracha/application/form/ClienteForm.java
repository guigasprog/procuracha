package br.edu.fema.procuracha.application.form;

import br.edu.fema.procuracha.domain.entity.CidadeEntity;
import br.edu.fema.procuracha.domain.entity.ClienteEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ClienteForm {

    private String nome;

    private String cpf;

    private String email;

    private String senha;

    private String tel_cel;

    private CidadeEntity cidade;

    public ClienteEntity converterFormForEntity(ClienteEntity clienteEntity) {
        clienteEntity.setCpf(this.cpf);
        clienteEntity.setNome(this.nome);
        clienteEntity.setEmail(this.email);
        clienteEntity.setSenha(this.senha);
        clienteEntity.setTel_cel(this.tel_cel);
        clienteEntity.setCidadeEntity(this.cidade);

        return clienteEntity;
    }

}
