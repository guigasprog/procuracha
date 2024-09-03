package br.edu.fema.procuracha.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name="profissional")
public class ProfissionalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = { CascadeType.ALL })
    @JoinColumn(name = "cliente_id")
    private ClienteEntity clienteEntity;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(name="profissional_servicos",
            joinColumns= {@JoinColumn(name="profissional_id")},
            inverseJoinColumns= {@JoinColumn(name="servico_id")})
    private List<ServicoEntity> servicoEntities;

}
