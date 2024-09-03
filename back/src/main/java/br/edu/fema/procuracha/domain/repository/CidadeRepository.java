package br.edu.fema.procuracha.domain.repository;

import br.edu.fema.procuracha.domain.entity.CidadeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CidadeRepository extends JpaRepository<CidadeEntity, Long> {

    List<CidadeEntity> findAllByUf(String uf);

    Optional<CidadeEntity> findByNomeIgnoreCaseAndUfIgnoreCase(String nome, String uf);

}
