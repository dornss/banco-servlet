package br.com.ibm.bancoservlet.GlobalExceptionHandler;

public class ContaInvalidaException extends Exception{
    public ContaInvalidaException(String mensagem) {super(mensagem);}
}
