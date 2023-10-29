package br.com.cg.projectAlpha.exceptions;

import br.com.cg.projectAlpha.dto.PessoaErrorResponse;
import br.com.cg.projectAlpha.dto.ProjetoErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class PessoaExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {PessoaException.class})
    protected ResponseEntity<Object> handleOrderErrors(PessoaException pessoaException, WebRequest webRequest) {
        PessoaErrorResponse pessoaErrorResponse = new PessoaErrorResponse();
        pessoaErrorResponse.setMessage(pessoaException.getPessoaErrors().getMessage());
        pessoaErrorResponse.setStatusCode(pessoaException.getPessoaErrors().getHttpStatus().value());
        pessoaErrorResponse.setRequestId(pessoaException.getPessoaId());

        return handleExceptionInternal(
                pessoaException,
                pessoaErrorResponse,
                new HttpHeaders(),
                pessoaException.getPessoaErrors().getHttpStatus(),
                webRequest
        );
    }
}