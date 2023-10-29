package br.com.cg.projectAlpha.exceptions;

import br.com.cg.projectAlpha.enums.PessoaErrors;
import br.com.cg.projectAlpha.enums.ProjetoErrors;
import org.springframework.lang.Nullable;

public class PessoaException extends Exception{
    private final PessoaErrors pessoaErrors;
    @Nullable
    private final String projetoId;

    public PessoaException(PessoaErrors pessoaErrors, @Nullable String projetoId) {
        this.pessoaErrors = pessoaErrors;
        this.projetoId = projetoId;
    }

    public PessoaErrors getPessoaErrors() {
        return pessoaErrors;
    }

    @Nullable
    public String getPessoaId() {
        return projetoId;
    }
}
