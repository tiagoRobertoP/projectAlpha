package br.com.cg.projectAlpha.model;


import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigInteger;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Embeddable
public class MembrosId implements Serializable {
    private BigInteger idprojeto;

    @ManyToOne
    @JoinColumn(name="idpessoa")
    private Pessoa pessoa;
}
