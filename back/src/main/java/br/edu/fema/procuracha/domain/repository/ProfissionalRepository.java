package br.edu.fema.procuracha.domain.repository;

import br.edu.fema.procuracha.domain.entity.ProfissionalEntity;
import br.edu.fema.procuracha.domain.entity.ServicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProfissionalRepository extends JpaRepository<ProfissionalEntity, Long> {
    
    List<ProfissionalEntity> findAllByIdNot(Long id);

    Optional<ProfissionalEntity> findByClienteEntity_Cpf(String cpf);
}
