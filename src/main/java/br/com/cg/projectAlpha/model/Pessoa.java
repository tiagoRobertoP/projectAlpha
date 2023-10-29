package br.com.cg.projectAlpha.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Data
@Table(name="pessoa")
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private BigInteger idPessoa;

    @Column(name="cpf")
    private Integer cpf;

    @Column(name="nome")
    private String nome;

    @Column(name="datanascimento")
    private LocalDate datanascimento;

}
