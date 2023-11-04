package br.com.cg.projectAlpha.dto;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDate;
@Builder
@Data
public class PessoaRequestDto {
    private BigInteger id;

    private Integer cpf;

    private String nome;

    private LocalDate datanascimento;

    private Boolean funcionario;
}
