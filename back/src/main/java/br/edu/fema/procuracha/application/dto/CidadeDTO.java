package br.edu.fema.procuracha.application.dto;

import br.edu.fema.procuracha.domain.entity.CidadeEntity;
import lombok.Getter;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class CidadeDTO {

    private Long id;

    private String nome;

    private String uf;

    public CidadeDTO(CidadeEntity cidadeEntity) {
        this.id = cidadeEntity.getId();
        this.nome = cidadeEntity.getNome();
        this.uf = cidadeEntity.getUf();
    }

    public static List<CidadeDTO> converterEntityForDTO(List<CidadeEntity> cidadeEntityList) {
        return cidadeEntityList.stream().map(CidadeDTO::new).toList();
    }

    public static Set<String> findByAllUF(List<CidadeDTO> cidadesDTO) {
        return cidadesDTO.stream()
                .collect(Collectors.groupingBy(CidadeDTO::getUf, Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }
}
