package br.edu.fema.tcc.meu.Controller;

import br.edu.fema.tcc.meu.Repository.CidadeRepository;
import br.edu.fema.tcc.meu.Repository.ContratacaoServicoRepository;
import br.edu.fema.tcc.meu.Service.CidadeService;
import br.edu.fema.tcc.meu.Service.ContratacaoServicoService;
import br.edu.fema.tcc.meu.model.Cliente;
import br.edu.fema.tcc.meu.model.ContratacaoServico;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/contratacoes")
public class ContratacaoServicoController {


    @Autowired
    private ContratacaoServicoRepository repository;

   private ContratacaoServicoService contratacaoServicoService;

    @Autowired
    public ContratacaoServicoController(ContratacaoServicoRepository repository){
        this.repository = repository;
    }



    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContratacaoServico salvar (@RequestBody @Valid ContratacaoServico contratacaoServico){
        return repository.save(contratacaoServico);
    }

    @GetMapping
    public List<ContratacaoServico> achartodos(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ContratacaoServico acharPorId(@PathVariable Integer id){
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Contratacao não encontrada"));
    }


    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // sei fez o delete
    public void deletar(@PathVariable Integer id){
        repository.findById(id)
                .map(contratacaoServico -> {
                    repository.delete(contratacaoServico); //poderia usar um modo mais simples porem aqui estou retornando se o cliente nao existe
                    return Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

   // @PutMapping("{id}")
   // @ResponseStatus(HttpStatus.NO_CONTENT)
   // public void atualizar(@PathVariable Integer id, @RequestBody @Valid ContratacaoServico contratacaoServicoAtualizado){
   //     repository.findById(id)
   //             .map(contratacaoServico -> {
   //                 contratacaoServico.set(.getNome());
   //                 cliente.setCpf(clienteAtualizado.getCpf());
   //                 return repository.save(cliente);
   //             })
   //             .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
   // }

}
