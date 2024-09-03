package br.edu.fema.procuracha.application.controller;

import br.edu.fema.procuracha.application.dto.ServicoDTO;
import br.edu.fema.procuracha.application.form.ProfissionalForm;
import br.edu.fema.procuracha.application.form.ServicoForm;
import br.edu.fema.procuracha.domain.entity.ClienteEntity;
import br.edu.fema.procuracha.domain.entity.ServicoEntity;
import br.edu.fema.procuracha.domain.repository.ServicoRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/servico")
public class ServicoController {

    private ServicoRepository servicoRepository;

    public ServicoController(ServicoRepository servicoRepository) {
        this.servicoRepository = servicoRepository;
    }

    @GetMapping
    public List<ServicoDTO> getServicos() {
        return ServicoDTO.converterEntityForDTO(this.servicoRepository.findAll());
    }
    
}
