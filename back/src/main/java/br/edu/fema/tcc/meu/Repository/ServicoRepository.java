package br.edu.fema.tcc.meu.Repository;

import br.edu.fema.tcc.meu.model.Cliente;
import br.edu.fema.tcc.meu.model.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ServicoRepository extends JpaRepository <Servico, Integer> {

  Optional<Servico> findByDescricao (String descricao);


}
