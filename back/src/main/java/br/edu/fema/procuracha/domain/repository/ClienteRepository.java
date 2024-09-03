package br.edu.fema.procuracha.domain.repository;

import br.edu.fema.procuracha.domain.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository  extends JpaRepository<ClienteEntity, Long> {

    ClienteEntity findByEmailAndSenha(String email, String senha);

    Optional<ClienteEntity> findByCpf(String cpf);

}
