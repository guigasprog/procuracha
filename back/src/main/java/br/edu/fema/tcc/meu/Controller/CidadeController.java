package br.edu.fema.tcc.meu.Controller;

import br.edu.fema.tcc.meu.Repository.CidadeRepository;
import br.edu.fema.tcc.meu.Service.CidadeService;
import br.edu.fema.tcc.meu.model.Cidade;
import br.edu.fema.tcc.meu.model.Cliente;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    private CidadeRepository repository;

    private CidadeService cidadeService;
    @Autowired
    public CidadeController(CidadeRepository repository){
        this.repository = repository;
    }

    @GetMapping
    public List<Cidade> acharTodos() {
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // aqui sempre aparece o status do postman
    public Cidade salvar (@RequestBody @Valid Cidade cidade){
        return repository.save(cidade);
    }

    public CidadeController( CidadeService cidadeService) {
        this.cidadeService= cidadeService;
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody @Valid Cidade cidadeAtualizada){
        repository.findById(id)
                .map(cidade -> {
                    cidade.setNome(cidadeAtualizada.getNome());
                    cidade.setUf(cidadeAtualizada.getUf());
                    return repository.save(cidade);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cidade nao encontrado"));
    }
}
