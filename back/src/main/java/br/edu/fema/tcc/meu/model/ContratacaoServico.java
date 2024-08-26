package br.edu.fema.tcc.meu.model;

import ch.qos.logback.core.net.server.Client;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "CONTRATACAO_SERVICO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContratacaoServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name="CLIENTE_ID", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name="CIDADE_ID",nullable = false)
    private Cidade cidade;

    @ManyToOne
    @JoinColumn(name="PROFISSIONAL_ID", nullable = false)
    private Profissional profissional;

    @ManyToOne
    @JoinColumn(name="SERVICO_ID", nullable = false)
    private Servico servico;

    @Column(name ="DT_INICIO_SERVICO")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataInicioServico;

    @Column(name ="DT_TERMINO_SERVICO")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataTerminoServico;

    @Column
    private BigDecimal valorTotalServico;

    @Column(name ="DT_CONTRATACAO_SERVICO", updatable = false)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataContratacaoServico;

    @PrePersist
    public void prePersist(){
        setDataContratacaoServico(LocalDateTime.now());
    }
}
