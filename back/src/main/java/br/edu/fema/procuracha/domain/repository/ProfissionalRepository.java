package br.edu.fema.procuracha.domain.repository;

import br.edu.fema.procuracha.domain.entity.ProfissionalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfissionalRepository extends JpaRepository<ProfissionalEntity, Long> {

    ProfissionalEntity findByClienteEntity_Id(Long id);

    List<ProfissionalEntity> findAllByIdNot(Long id);
}
