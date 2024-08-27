package br.edu.fema.procuracha.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "contrato")
public class ContratoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "data",nullable = false)
    private LocalDate data;

    @Column(name = "hora",nullable = false)
    private LocalTime hora;

    @Column(name = "problema",nullable = false)
    private String descricao;

    @OneToOne
    @JoinColumn(name = "cliente_id")
    private ClienteEntity clienteEntity;

    @OneToOne
    @JoinColumn(name = "profissional_id")
    private ProfissionalEntity profissionalEntity;

}
