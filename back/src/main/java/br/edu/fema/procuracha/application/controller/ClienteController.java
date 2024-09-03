package br.edu.fema.procuracha.application.controller;

import br.edu.fema.procuracha.application.dto.ClienteDTO;
import br.edu.fema.procuracha.application.form.ClienteForm;
import br.edu.fema.procuracha.domain.entity.CidadeEntity;
import br.edu.fema.procuracha.domain.entity.ClienteEntity;
import br.edu.fema.procuracha.domain.repository.CidadeRepository;
import br.edu.fema.procuracha.domain.repository.ClienteRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private CidadeRepository cidadeRepository;
    private ClienteRepository clienteRepository;

    public ClienteController(ClienteRepository clienteRepository, CidadeRepository cidadeRepository) {
        this.clienteRepository = clienteRepository;
        this.cidadeRepository = cidadeRepository;
    }

    @PostMapping("/logar")
    public ClienteDTO logarCliente(@RequestBody @Valid ClienteForm clienteForm) {
        return new ClienteDTO(
                this.clienteRepository.findByEmailAndSenha(
                        clienteForm.getEmail().trim(),
                        clienteForm.getSenha().trim()
                )
        );
    }

    @PostMapping("/registro")
    public ClienteDTO registrarCliente(@RequestBody @Valid ClienteForm clienteForm) {
        Optional<ClienteEntity> cliente = this.clienteRepository.findByCpf(clienteForm.getCpf());
        if(cliente.isEmpty()) {
            Optional<CidadeEntity> cidade = this.cidadeRepository.findByNomeIgnoreCaseAndUfIgnoreCase
                    (clienteForm.getCidade().getNome(), clienteForm.getCidade().getUf());
            if (!cidade.isEmpty())
                clienteForm.setCidade(cidade.get());
            return new ClienteDTO(
                    this.clienteRepository.save(clienteForm.converterFormForEntity(new ClienteEntity()))
            );
        }
        return null;
    }

}
