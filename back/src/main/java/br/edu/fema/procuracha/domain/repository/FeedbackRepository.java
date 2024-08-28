package br.edu.fema.procuracha.domain.repository;

import br.edu.fema.procuracha.domain.entity.FeedbackEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<FeedbackEntity, Long> {

    List<FeedbackEntity> findAllByContratoEntity_ProfissionalEntity_Id(Long id);

}
