package br.edu.fema.procuracha.application.controller;

import br.edu.fema.procuracha.application.dto.ProfissionalDTO;
import br.edu.fema.procuracha.application.form.ClienteForProfissionalForm;
import br.edu.fema.procuracha.application.form.ClienteForm;
import br.edu.fema.procuracha.application.form.ProfissionalForm;
import br.edu.fema.procuracha.application.form.ServicoForm;
import br.edu.fema.procuracha.domain.entity.CidadeEntity;
import br.edu.fema.procuracha.domain.entity.ClienteEntity;
import br.edu.fema.procuracha.domain.entity.ProfissionalEntity;
import br.edu.fema.procuracha.domain.entity.ServicoEntity;
import br.edu.fema.procuracha.domain.repository.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/profissional")
public class ProfissionalController {

    private FeedbackRepository feedbackRepository;

    private ProfissionalRepository profissionalRepository;

    private CidadeRepository cidadeRepository;

    private ClienteRepository clienteRepository;

    private ServicoRepository servicoRepository;

    private ServicoController servicoController;

    public ProfissionalController(ProfissionalRepository profissionalRepository,
                                  CidadeRepository cidadeRepository,
                                  ClienteRepository clienteRepository,
                                  FeedbackRepository feedbackRepository,
                                  ServicoRepository servicoRepository,
                                  ServicoController servicoController) {
        this.profissionalRepository = profissionalRepository;
        this.servicoController = servicoController;
        this.feedbackRepository = feedbackRepository;
        this.clienteRepository = clienteRepository;
        this.cidadeRepository = cidadeRepository;
        this.servicoRepository = servicoRepository;

    }

    @GetMapping("/{id}")
    public List<ProfissionalDTO> getProfissionais(@PathVariable Long id) {
        return ProfissionalDTO.converter(
                this.profissionalRepository.findAllByIdNot(id),
                this.feedbackRepository
        );
    }

    @GetMapping("/buscar/{cpf}")
    public ProfissionalDTO buscarProfissional(@PathVariable String cpf) {
        Optional<ProfissionalEntity> profissionalEntity = this.profissionalRepository.findByClienteEntity_Cpf(cpf);
        if(!profissionalEntity.isEmpty()) {
            return new ProfissionalDTO(
                    profissionalEntity.get(),
                    this.feedbackRepository.findAllByContratoEntity_ProfissionalEntity_Id(
                            profissionalEntity.get().getId()
                    )
            );
        }
        return null;
    }

    @PostMapping("/salvar/servico")
    public ProfissionalEntity addNewServicoToProfissional(@RequestBody ProfissionalForm profissionalForm) {
        ProfissionalEntity profissional = postProfissional(profissionalForm.getClienteForm());

        ServicoEntity novoServico = new ServicoEntity();
        novoServico.setDescricao(profissionalForm.getServicoForm().getDescricao());

        ServicoEntity servicoSalvo = this.servicoRepository.save(novoServico);

        if (profissional.getServicoEntities() == null) {
            profissional.setServicoEntities(new ArrayList<>());
        }

        if (profissional.getServicoEntities().stream()
                .noneMatch(servico -> servico.getDescricao().equalsIgnoreCase(servicoSalvo.getDescricao()))) {
            profissional.getServicoEntities().add(servicoSalvo);
        }



        // Salvar as mudanças no profissional
        return profissionalRepository.save(profissional);
    }

    public ProfissionalEntity postProfissional(ClienteForProfissionalForm clienteForm) {
        return this.profissionalRepository.findByClienteEntity_Cpf(clienteForm.getCpf())
                .orElseGet(() -> {
                    ProfissionalEntity novoProfissional = new ProfissionalEntity();
                    novoProfissional.setClienteEntity(this.clienteRepository.findByCpf(clienteForm.getCpf()).get());
                    return this.profissionalRepository.save(novoProfissional);
                });
    }
}