package br.edu.fema.procuracha.application.controller;

import br.edu.fema.procuracha.application.dto.ClienteDTO;
import br.edu.fema.procuracha.application.form.ClienteForm;
import br.edu.fema.procuracha.domain.repository.ClienteRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private ClienteRepository clienteRepository;

    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @PostMapping("/logar")
    public ClienteDTO logarCliente(@RequestBody @Valid ClienteForm clienteForm) {
        return new ClienteDTO(
                this.clienteRepository.findByEmailAndSenha(
                        clienteForm.getEmail(),
                        clienteForm.getSenha()
                )
        );
    }

}
