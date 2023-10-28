package br.com.cg.projectAlpha.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Data
@Table(name="membros")
public class Membros {
    @Id
    @Column(name="idprojeto")
    private BigInteger idprojeto;

    @Id
    @Column(name="idpessoas")
    private BigInteger idpessoas;


}
