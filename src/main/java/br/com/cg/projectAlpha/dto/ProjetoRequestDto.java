package br.com.cg.projectAlpha.dto;

import br.com.cg.projectAlpha.enums.Risco;
import br.com.cg.projectAlpha.enums.Status;
import br.com.cg.projectAlpha.model.Pessoa;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDate;

@Data
public class ProjetoRequestDto {

    private BigInteger id;

    private String nome;

    private LocalDate dataInicio;

    private LocalDate dataFim;

    private LocalDate dataPrevisaoFim;

    private String descricao;

    private Float orcamento;

    private Status status;

    private Risco risco;

    private BigInteger idgerente;
}
