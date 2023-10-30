package br.com.ibm.bancoservlet.services;

import br.com.ibm.bancoservlet.GlobalExceptionHandler.ContaInvalidaException;
import br.com.ibm.bancoservlet.GlobalExceptionHandler.SaldoInsuficienteException;
import br.com.ibm.bancoservlet.models.ContaCorrente;

public interface ContaCorrenteService {
    ContaCorrente getContaPorNumero(String numeroConta);
    void depositar(String numeroConta, double valor) throws ContaInvalidaException;
    void sacar(String numeroConta, double valor) throws ContaInvalidaException, SaldoInsuficienteException;
    void transferir(String contaOrigem, String contaDestino, double valor) throws ContaInvalidaException, SaldoInsuficienteException;
    ContaCorrente criarConta(String nome, String cpf) throws ContaInvalidaException;

}
