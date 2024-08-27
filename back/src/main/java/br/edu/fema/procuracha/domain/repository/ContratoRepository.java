package br.edu.fema.procuracha.domain.repository;

import br.edu.fema.procuracha.domain.entity.ContratoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContratoRepository extends JpaRepository<ContratoEntity, Long> {
}
