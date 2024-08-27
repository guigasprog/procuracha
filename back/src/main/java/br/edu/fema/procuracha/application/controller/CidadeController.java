package br.edu.fema.procuracha.application.controller;

import br.edu.fema.procuracha.application.dto.CidadeDTO;
import br.edu.fema.procuracha.domain.entity.CidadeEntity;
import br.edu.fema.procuracha.domain.repository.CidadeRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/cidade")
public class CidadeController {

    private CidadeRepository cidadeRepository;

    public CidadeController(CidadeRepository cidadeRepository) {
        this.cidadeRepository = cidadeRepository;
    }

    @GetMapping
    public List<CidadeDTO> getCidades() {
        return CidadeDTO.converterEntityForDTO(this.cidadeRepository.findAll());
    }

    @GetMapping("/uf")
    public Set<String> getUfs() {
        return CidadeDTO.findByAllUF(
                getCidades()
        );
    }

    @GetMapping("/{uf}")
    public List<CidadeDTO> getCidadesDTOByUf(@PathVariable String uf) {
        return CidadeDTO.converterEntityForDTO(this.cidadeRepository.findAllByUf(uf));
    }

}
