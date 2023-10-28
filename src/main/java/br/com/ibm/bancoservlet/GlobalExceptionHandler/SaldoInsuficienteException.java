package br.com.ibm.bancoservlet.GlobalExceptionHandler;

public class SaldoInsuficienteException extends Exception {
    public SaldoInsuficienteException(String mensagem) {
        super(mensagem);
    }
}
