package br.com.cg.projectAlpha.enums;

import org.springframework.http.HttpStatus;

public enum PessoaErrors {
    PESSOA_NOT_FOUND("Pessoa não encontrada", HttpStatus.NOT_FOUND)
    ;

    private final String message;
    private final HttpStatus httpStatus;

    PessoaErrors(String message, HttpStatus httpStatus) {
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
