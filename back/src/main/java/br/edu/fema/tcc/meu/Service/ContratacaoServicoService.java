package br.edu.fema.tcc.meu.Service;

import br.edu.fema.tcc.meu.Repository.CidadeRepository;
import br.edu.fema.tcc.meu.Repository.ContratacaoServicoRepository;
import br.edu.fema.tcc.meu.model.Cidade;
import br.edu.fema.tcc.meu.model.ContratacaoServico;

import java.util.List;

public class ContratacaoServicoService {

    private ContratacaoServicoRepository repository;

    //injecao de dependencia
    public ContratacaoServicoService(ContratacaoServicoRepository repository) {
        this.repository = repository;
    }

    public List<ContratacaoServico> listaCidade(){
        List<ContratacaoServico> lista = repository.findAll();
        return lista;
    }

    public ContratacaoServico salvar(ContratacaoServico contratacaoServico){
        return repository.save(contratacaoServico);
    }
}

