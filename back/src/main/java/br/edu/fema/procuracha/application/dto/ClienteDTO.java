package br.edu.fema.procuracha.application.dto;

import br.edu.fema.procuracha.domain.entity.ClienteEntity;
import lombok.Getter;

import java.util.List;

@Getter
public class ClienteDTO {
    private Long id;

    private String nome;

    private String cpf;

    private String tel_cel;

    private CidadeDTO cidade;

    public ClienteDTO(ClienteEntity clienteEntity) {
        this.id = clienteEntity.getId();
        this.nome = clienteEntity.getNome();
        this.cpf = clienteEntity.getCpf();
        this.tel_cel = clienteEntity.getTel_cel();
        this.cidade = new CidadeDTO(clienteEntity.getCidadeEntity());
    }

    public static List<ClienteDTO> converterEntityForDTO(List<ClienteEntity> clienteEntityList) {
        return clienteEntityList.stream().map(ClienteDTO::new).toList();
    }

}
