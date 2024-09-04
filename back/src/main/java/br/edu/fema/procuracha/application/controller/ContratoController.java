package br.edu.fema.procuracha.application.controller;

import br.edu.fema.procuracha.application.dto.ContratoDTO;
import br.edu.fema.procuracha.application.form.ContratoForm;
import br.edu.fema.procuracha.domain.entity.ContratoEntity;
import br.edu.fema.procuracha.domain.repository.ClienteRepository;
import br.edu.fema.procuracha.domain.repository.ContratoRepository;
import br.edu.fema.procuracha.domain.repository.ProfissionalRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contrato")
public class ContratoController {

    private ContratoRepository contratoRepository;
    private ClienteRepository clienteRepository;
    private ProfissionalRepository profissionalRepository;

    public ContratoController(ContratoRepository contratoRepository,
                              ClienteRepository clienteRepository,
                              ProfissionalRepository profissionalRepository) {
        this.contratoRepository = contratoRepository;
        this.clienteRepository = clienteRepository;
        this.profissionalRepository = profissionalRepository;
    }

    @GetMapping("/profissional/{id}")
    public List<ContratoDTO> getContratosProfissional(@PathVariable Long idProfissional) {
        return ContratoDTO.converterEntityForDTO(
                this.contratoRepository.findAllByProfissionalEntity_Id(idProfissional)
        );
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
