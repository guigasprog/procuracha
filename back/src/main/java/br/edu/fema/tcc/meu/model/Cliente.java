package br.edu.fema.tcc.meu.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Entity
@Table(name = "CLIENTES")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name="NOME",nullable = false, length = 150)
    @NotEmpty(message = "{campo.nome.obrigatorio}") //valida para nao ser nulo e nem fazia
    private String nome;
    @Column(name="CPF",nullable = false, length = 11)
    @NotEmpty(message = "{campo.cpf.obrigatorio}")
    @CPF(message = "{campo.cpf.invalido}")
    private String cpf;
    @Column(name="TELEFONE",nullable = false, length =11)
    @NotEmpty(message = "{campo.telefone.obrigatorio}")
    private String telefone;
    @Column(name="ENDERECO",nullable = false, length =100)
    @NotEmpty(message = "{campo.endereço.obrigatorio}")
    private String endereco;

    @ManyToOne
    @JoinColumn(name="CIDADE_ID",nullable = false)
    private Cidade cidade;

    @Column(name ="DT_CADASTRO", updatable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;

    @PrePersist
    public void prePersist(){
        setDataCadastro(LocalDate.now());
    }




    public Cliente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }


}