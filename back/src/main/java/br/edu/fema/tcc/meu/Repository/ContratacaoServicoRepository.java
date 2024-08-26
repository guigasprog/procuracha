package br.edu.fema.tcc.meu.Repository;

import br.edu.fema.tcc.meu.model.Cliente;
import br.edu.fema.tcc.meu.model.ContratacaoServico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContratacaoServicoRepository extends JpaRepository<ContratacaoServico, Integer> {
}
