package br.edu.fema.procuracha.domain.repository;

import br.edu.fema.procuracha.domain.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository  extends JpaRepository<ClienteEntity, Long> {
}
