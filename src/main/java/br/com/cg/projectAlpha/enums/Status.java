package br.com.cg.projectAlpha.enums;

import br.com.cg.projectAlpha.exceptions.StatusNotFoundException;

import java.util.Arrays;

public enum Status {
    EM_ANALISE("EM ANÁLISE"),
    ANALISE_REALIZADA("ANÁLISE REALIZADA"),
    AGENDADANALISE_APROVADA("ANÁLISSE APROVADA"),
    INICIADO("INICIADO"),
    PLANEJADO("PLANEJADO"),
    EM_ANDAMENTO("EM ANDAMENTO"),
    ENCERRADO("ENCERRADO"),
    CANCELADO("CANCELADO");

    private final String label;
    Status(String label) { this.label = label; }
    public static Status findByLabelOrElseThrow(String label) throws Exception {
        return Arrays.stream(Status.values())
                .filter(exercise -> exercise.name().equals(label))
                .findFirst()
                .orElseThrow(StatusNotFoundException::new);
    }
    @Override
    public String toString() { return label; }
}
