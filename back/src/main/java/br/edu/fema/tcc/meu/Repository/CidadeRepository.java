package br.edu.fema.tcc.meu.Repository;

import br.edu.fema.tcc.meu.model.Cidade;
import br.edu.fema.tcc.meu.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

    Optional<Cidade> findByNome(String nome);

}
