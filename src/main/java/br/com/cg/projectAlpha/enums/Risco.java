package br.com.cg.projectAlpha.enums;

import br.com.cg.projectAlpha.exceptions.StatusNotFoundException;

import java.util.Arrays;

public enum Risco {
    BAIXO_RISCO("BAIXO RISCO"),
    MEDIO_RISCO("MÉDIO RISCO"),
    ALTO_RISCO("ALTO RISCO");

    private final String label;
    Risco(String label) { this.label = label; }
    public static Risco findByLabelOrElseThrow(String label) throws Exception {
        return Arrays.stream(Risco.values())
                .filter(exercise -> exercise.name().equals(label))
                .findFirst()
                .orElseThrow(StatusNotFoundException::new);
    }
    @Override
    public String toString() { return label; }
}
