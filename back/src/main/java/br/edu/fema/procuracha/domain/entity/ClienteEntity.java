package br.edu.fema.procuracha.domain.entity;

import jakarta.persistence.*;
import lombok.Builder;
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

    @Column(name = "cpf",nullable = false, length = 15, unique = true)
    private String cpf;

    @Column(name = "email",nullable = false, length = 250, unique = true)
    private String email;

    @Column(name = "senha",nullable = false, length = 250)
    private String senha;

    @Column(name = "tel_cel",nullable = false, length = 15)
    private String tel_cel;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cidade_id")
    private CidadeEntity cidadeEntity;

}
