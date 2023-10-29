package br.com.cg.projectAlpha.dto;

import lombok.Data;

@Data
public class PessoaErrorResponse {
        String message;
        int statusCode;
        String requestId;

}