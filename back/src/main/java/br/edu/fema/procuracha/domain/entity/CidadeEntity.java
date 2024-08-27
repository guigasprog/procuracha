package br.edu.fema.procuracha.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="cidade")
public class CidadeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome",nullable = false, length = 80)
    private String nome;

    @Column(name = "uf",nullable = false, length = 2)
    private String uf;
}
