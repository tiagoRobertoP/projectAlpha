package br.com.cg.projectAlpha.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class ProjetoErrorResponse{
        String message;
        int statusCode;
        String requestId;

}