package br.edu.fema.tcc.meu.Repository;

import br.edu.fema.tcc.meu.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    Optional<Cliente> findByNome(String nome);
}
