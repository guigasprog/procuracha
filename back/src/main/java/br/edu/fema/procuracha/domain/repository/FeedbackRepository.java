package br.edu.fema.procuracha.domain.repository;

import br.edu.fema.procuracha.domain.entity.FeedbackEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<FeedbackEntity, Long> {
}
