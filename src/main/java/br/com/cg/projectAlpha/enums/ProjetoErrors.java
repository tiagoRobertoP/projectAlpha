package br.com.cg.projectAlpha.enums;

import org.springframework.http.HttpStatus;

public enum ProjetoErrors {
    PROJETO_NOT_FOUND("Projeto não encontrado", HttpStatus.NOT_FOUND)
    ;

    private final String message;
    private final HttpStatus httpStatus;

    ProjetoErrors(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
