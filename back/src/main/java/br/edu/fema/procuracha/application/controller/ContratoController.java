package br.edu.fema.procuracha.application.controller;

import br.edu.fema.procuracha.application.dto.ContratoDTO;
import br.edu.fema.procuracha.application.form.ContratoForm;
import br.edu.fema.procuracha.domain.entity.ContratoEntity;
import br.edu.fema.procuracha.domain.entity.FeedbackEntity;
import br.edu.fema.procuracha.domain.repository.ClienteRepository;
import br.edu.fema.procuracha.domain.repository.ContratoRepository;
import br.edu.fema.procuracha.domain.repository.FeedbackRepository;
import br.edu.fema.procuracha.domain.repository.ProfissionalRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/contrato")
public class ContratoController {

    private ContratoRepository contratoRepository;
    private ClienteRepository clienteRepository;
    private ProfissionalRepository profissionalRepository;
    private FeedbackRepository feedbackRepository;

    public ContratoController(ContratoRepository contratoRepository,
                              ClienteRepository clienteRepository,
                              ProfissionalRepository profissionalRepository,
                              FeedbackRepository feedbackRepository) {
        this.contratoRepository = contratoRepository;
        this.clienteRepository = clienteRepository;
        this.profissionalRepository = profissionalRepository;
        this.feedbackRepository = feedbackRepository;
    }

    @GetMapping("/profissional/{id}")
    public List<ContratoDTO> getContratosProfissional(@PathVariable Long id) {
        List<ContratoDTO> contratoDTO = ContratoDTO.converterEntityForDTO(
                this.contratoRepository.findAllByProfissionalEntity_Id(id)
        );
        if(!contratoDTO.isEmpty()) {
            contratoDTO = contratoDTO.stream()
                    .filter(contrato -> {
                        FeedbackEntity feedback = this.feedbackRepository
                                .findByContratoEntity_Id(contrato.getId());
                        return feedback == null &&
                                contrato.getData().isAfter(LocalDate.now());
                    })
                    .toList();
        }
        return contratoDTO;
    }

    @PostMapping("/criar")
    public ContratoEntity postContrato(@RequestBody @Valid ContratoForm contratoForm) {
        ContratoEntity contratoEntity = new ContratoEntity();
        contratoEntity.setClienteEntity(
                this.clienteRepository.findById(contratoForm.getIdCliente()).get()
        );
        contratoEntity.setProfissionalEntity(
                this.profissionalRepository.findById(contratoForm.getIdProfissional()).get()
        );
        contratoEntity.setData(contratoForm.getData());
        contratoEntity.setHora(contratoForm.getHora());
        contratoEntity.setDescricao(contratoForm.getDescricao());
        return this.contratoRepository.save(contratoEntity);
    }


}
