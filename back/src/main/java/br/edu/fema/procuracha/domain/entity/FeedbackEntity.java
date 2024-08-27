package br.edu.fema.procuracha.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "feedback")
public class FeedbackEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "aceito",nullable = false)
    private boolean aceito;

    @Column(name = "resolvido",nullable = false)
    private boolean resolvido;

    @Column(name = "nota",nullable = false)
    private int nota;

    @Column(name = "descricao",nullable = false)
    private String descricao;

    @OneToOne
    @JoinColumn(name = "contrato_id")
    private ContratoEntity contratoEntity;

}
