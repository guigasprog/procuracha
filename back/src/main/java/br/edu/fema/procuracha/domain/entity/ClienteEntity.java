package br.edu.fema.procuracha.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="cliente")
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome",nullable = false, length = 80)
    private String nome;

    @Column(name = "cpf",nullable = false, length = 15)
    private String cpf;

    @Column(name = "tel_cel",nullable = false, length = 15)
    private String tel_cel;

    @ManyToOne
    @JoinColumn(name = "cidade_id")
    private CidadeEntity cidadeEntity;

}
