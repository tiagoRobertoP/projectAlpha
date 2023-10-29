package br.com.cg.projectAlpha.model;

import br.com.cg.projectAlpha.enums.Risco;
import br.com.cg.projectAlpha.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Data
@Table(name="projeto")
public class Projeto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private BigInteger id;

    @Column(name="nome")
    private String nome;

    @Column(name="data_inicio")
    private LocalDate dataInicio;

    @Column(name="data_fim")
    private LocalDate dataFim;

    @Column(name="data_previsao_fim")
    private LocalDate dataPrevisaoFim;

    @Column(name="descricao")
    private String descricao;

    @Column(name="orcamento")
    private Float orcamento;

    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private Status status;

    @Enumerated(EnumType.STRING)
    @Column(name="risco")
    private Risco risco;

    @ManyToOne
    @JoinColumn(name="idgerente", referencedColumnName="id")
    private Pessoa gerente;
}
