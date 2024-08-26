package br.edu.fema.tcc.meu.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Entity
@Table(name = "Cidade")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class  Cidade implements Serializable {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Nome",nullable = false, length = 100)
    // @NotEmpty(message = "{campo.nome.obrigatorio}") //valida para nao ser nulo e nem fazia
    private String nome;

    @Column(name = "UF", nullable = false, length = 2)
    // @NotEmpty(message = "{campo.uf.obrigatorio}")
    private String uf;

    @Column(name = "ibge")
    private int ibge;

public Cidade(String id, String nome, String uf){
    this.nome = nome;
    this.uf = uf;
}
}
