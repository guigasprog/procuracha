package br.edu.fema.procuracha.domain.repository;

import br.edu.fema.procuracha.domain.entity.ProfissionalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfissionalRepository extends JpaRepository<ProfissionalEntity, Long> {
}
