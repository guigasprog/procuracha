package br.edu.fema.tcc.meu.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Entity
@Table(name="SERVICOS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Servico {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name="DESCRICAO",nullable = false, length= 150)
    // @NotEmpty(message="{campo.descricao.obrigatorio}")
    private String descricao;


    @Column(name="VALOR BASE POR HORA")
    private BigDecimal valor;
}
