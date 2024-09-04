package br.edu.fema.procuracha.domain.repository;

import br.edu.fema.procuracha.domain.entity.ContratoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContratoRepository extends JpaRepository<ContratoEntity, Long> {

    List<ContratoEntity> findAllByProfissionalEntity_Id(Long id);

}
