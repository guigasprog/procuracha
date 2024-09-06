package br.edu.fema.procuracha.domain.repository;

import br.edu.fema.procuracha.domain.entity.FeedbackEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FeedbackRepository extends JpaRepository<FeedbackEntity, Long> {


    List<FeedbackEntity> findAllByContratoEntity_ClienteEntity_IdAndAceitoTrueAndResolvidoFalseAndNotaAndDescricao(
            Long id, Long nota, String descricao
    );

    Optional<FeedbackEntity> findByContratoEntity_Id(Long id);

    List<FeedbackEntity> findAllByContratoEntity_ProfissionalEntity_Id(Long id);

}
