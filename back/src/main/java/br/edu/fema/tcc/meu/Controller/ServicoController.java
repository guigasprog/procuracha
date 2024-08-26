package br.edu.fema.tcc.meu.Controller;


import br.edu.fema.tcc.meu.Repository.ServicoRepository;
import br.edu.fema.tcc.meu.model.Cliente;
import br.edu.fema.tcc.meu.model.Servico;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/servicos")


public class ServicoController {

    private ServicoRepository repository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Servico salvar(@RequestBody @Valid Servico servico){
    return repository.save(servico);
    }

    @GetMapping
    public List<Servico> achartodos(){
        return repository.findAll();
    }

    @GetMapping("{id}")
    public Servico acharPorId(@PathVariable Integer id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/descricao/{descricao}")
    public Servico acharPorDescricao(@PathVariable String descricao){
        return repository.findByDescricao(descricao).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // sei fez o delete
    public void deletar(@PathVariable Integer id) {
        repository.findById(id)
                .map(servico -> {
                    repository.delete(servico); //poderia usar um modo mais simples porem aqui estou retornando se o servico nao existe
                    return Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody @Valid Servico servicoAtualizado) {
        repository.findById(id)
                .map(servico -> {
                    servico.setDescricao(servicoAtualizado.getDescricao());
                    return repository.save(servico);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}
