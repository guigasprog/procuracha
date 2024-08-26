package br.edu.fema.tcc.meu.Controller;

import br.edu.fema.tcc.meu.Repository.ProfissionalRepository;
import br.edu.fema.tcc.meu.model.Profissional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/profissionais")
public class ProfissionalController {

    private final ProfissionalRepository repository;
    @Autowired
    public ProfissionalController(ProfissionalRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Profissional salvar (@RequestBody @Valid Profissional profissional) {
       return repository.save(profissional);
    }

    @GetMapping
    public List<Profissional> acharTodos(){   //buscar todos os profissionais
        return repository.findAll();
    }

    @GetMapping("/{id}")  //buscar o profissional por id
    public Profissional acharPorId(@PathVariable Integer id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Profissional nao encontrado"));
    }

    @GetMapping("/nome/{nome}")  // procurando o profissional pelo nome
    public Profissional acharPorNome(@PathVariable String nome){
        return repository.findByNome(nome).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Profissional nao encontrado"));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        repository.findById(id)
                .map(cliente -> {
                    repository.delete(cliente); //poderia usar um modo mais simples porem aqui estou retornando se o cliente nao existe
                    return Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Profissional nao encontrado"));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody @Valid Profissional profissionalAtualizado){
        repository.findById(id)
               .map(profissional -> {
                   profissional.setNome(profissionalAtualizado.getNome());
                   profissional.setCpf(profissionalAtualizado.getCpf());
                    return repository.save(profissional);
                })
               .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Profissional nao encontrado"));
    }

}
