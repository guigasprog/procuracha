package br.edu.fema.tcc.meu.Repository;

import br.edu.fema.tcc.meu.model.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfissionalRepository extends JpaRepository<Profissional, Integer>{

    Optional <Profissional> findByNome(String nome);
}
