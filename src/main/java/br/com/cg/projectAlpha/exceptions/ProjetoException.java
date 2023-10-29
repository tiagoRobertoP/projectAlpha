package br.com.cg.projectAlpha.exceptions;

import br.com.cg.projectAlpha.enums.ProjetoErrors;
import org.springframework.lang.Nullable;

public class ProjetoException extends Exception{
    private final ProjetoErrors projetoErrors;
    @Nullable
    private final String projetoId;

    public ProjetoException(ProjetoErrors projetoErrors, @Nullable String projetoId) {
        this.projetoErrors = projetoErrors;
        this.projetoId = projetoId;
    }

    public ProjetoErrors getProjetoErrors() {
        return projetoErrors;
    }

    @Nullable
    public String getProjetoId() {
        return projetoId;
    }
}
