package br.com.cg.projectAlpha.exceptions;

public class StatusNotFoundException extends RuntimeException{

    public StatusNotFoundException(){
        super("Status não encontrado ");
    }
}
