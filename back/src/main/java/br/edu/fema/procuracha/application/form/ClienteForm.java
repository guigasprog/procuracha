package br.edu.fema.procuracha.application.form;

import br.edu.fema.procuracha.application.dto.CidadeDTO;
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

    private CidadeDTO cidade;

}
