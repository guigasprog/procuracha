package br.edu.fema.tcc.meu.Service;

import br.edu.fema.tcc.meu.Repository.CidadeRepository;
import br.edu.fema.tcc.meu.model.Cidade;
import br.edu.fema.tcc.meu.model.Cliente;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Service
public class CidadeService {
    private CidadeRepository repository;

	//injecao de dependencia
    public CidadeService(CidadeRepository repository) {
        this.repository = repository;
    }

    public List<Cidade> listaCidade(){
        List<Cidade> lista = repository.findAll();
        return lista;
    }

    public Cidade salvar(Cidade cidade){
        return repository.save(cidade);
    }
}
