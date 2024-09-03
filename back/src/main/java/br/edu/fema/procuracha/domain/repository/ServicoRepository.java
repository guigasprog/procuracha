package br.edu.fema.procuracha.domain.repository;

import br.edu.fema.procuracha.domain.entity.ServicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ServicoRepository extends JpaRepository<ServicoEntity, Long> {
    
    Optional<ServicoEntity> findByDescricao(String descricao);
}
