package br.edu.fema.tcc.meu.Controller;

import br.edu.fema.tcc.meu.Repository.ClienteRepository;
//import br.edu.fema.tcc.meu.Service.ClienteService;
import br.edu.fema.tcc.meu.model.Cliente;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteRepository repository;

    @Autowired
    public ClienteController(ClienteRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // aqui sempre aparece o status do postman
    public Cliente salvar (@RequestBody @Valid Cliente cliente){
     return repository.save(cliente);
    }

    @GetMapping
    public List<Cliente> acharTodos() {
        return repository.findAll();   //listando todos os clientes existentes na lista
    }

    @GetMapping("{id}") // procurando pelo id do cliente
    public Cliente acharPorId( @PathVariable Integer id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Cliente nao encontrado"));
    }

     @GetMapping("/nome/{nome}")  //procurando por um nome especifico
    public Cliente acharPorNome( @PathVariable String nome){
      return repository.findByNome(nome).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Cliente nao encontrado"));
    }
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // sei fez o delete
    public void deletar(@PathVariable Integer id){
        repository.findById(id)
                .map(cliente -> {
                    repository.delete(cliente); //poderia usar um modo mais simples porem aqui estou retornando se o cliente nao existe
                    return Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente nao encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody @Valid Cliente clienteAtualizado){
        repository.findById(id)
                .map(cliente -> {
                    cliente.setNome(clienteAtualizado.getNome());
                    cliente.setCpf(clienteAtualizado.getCpf());
                   return repository.save(cliente);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente nao encontrado"));
    }
}
