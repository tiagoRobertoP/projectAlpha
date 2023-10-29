package br.com.cg.projectAlpha.exceptions;

import br.com.cg.projectAlpha.dto.ProjetoErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ProjetoExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {ProjetoException.class})
    protected ResponseEntity<Object> handleOrderErrors(ProjetoException projetoException, WebRequest webRequest) {
        ProjetoErrorResponse projetoErrorResponse = new ProjetoErrorResponse();
        projetoErrorResponse.setMessage(projetoException.getProjetoErrors().getMessage());
        projetoErrorResponse.setStatusCode(projetoException.getProjetoErrors().getHttpStatus().value());
        projetoErrorResponse.setRequestId(projetoException.getProjetoId());

        return handleExceptionInternal(
                projetoException,
                projetoErrorResponse,
                new HttpHeaders(),
                projetoException.getProjetoErrors().getHttpStatus(),
                webRequest
        );
    }
}