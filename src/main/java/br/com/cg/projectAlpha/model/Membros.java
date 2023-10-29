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
    @EmbeddedId
    private MembrosId membrosId;


}
