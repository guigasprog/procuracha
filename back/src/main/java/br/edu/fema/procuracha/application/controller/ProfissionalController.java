package br.edu.fema.procuracha.application.controller;

import br.edu.fema.procuracha.application.dto.ProfissionalDTO;
import br.edu.fema.procuracha.application.form.ClienteForm;
import br.edu.fema.procuracha.domain.entity.ProfissionalEntity;
import br.edu.fema.procuracha.domain.repository.FeedbackRepository;
import br.edu.fema.procuracha.domain.repository.ProfissionalRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profissional")
public class ProfissionalController {

    private FeedbackRepository feedbackRepository;

    private ProfissionalRepository profissionalRepository;

    private ClienteController clienteController;

    public ProfissionalController(ProfissionalRepository profissionalRepository,
                                  ClienteController clienteController,
                                  FeedbackRepository feedbackRepository) {
        this.profissionalRepository = profissionalRepository;
        this.clienteController = clienteController;
        this.feedbackRepository = feedbackRepository;
    }

    @GetMapping("/{id}")
    public List<ProfissionalDTO> getClientes(@PathVariable Long id) {
        return ProfissionalDTO.converter(
                this.profissionalRepository.findAllByIdNot(id),
                this.feedbackRepository
        );
    }

    @PostMapping("/logar")
    public ProfissionalDTO logarCliente(@RequestBody @Valid ClienteForm clienteForm) {
        ProfissionalEntity profissionalEntity = this.profissionalRepository.findByClienteEntity_Id(
                this.clienteController.logarCliente(clienteForm).getId()
        );
        return new ProfissionalDTO(
                profissionalEntity,
                this.feedbackRepository.findAllByContratoEntity_ProfissionalEntity_Id(
                        profissionalEntity.getId()
                )
        );
    }
}
