package br.com.ibm.bancoservlet.models;

import br.com.ibm.bancoservlet.GlobalExceptionHandler.SaldoInsuficienteException;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class ContaCorrente extends ContaBancaria {
    private static final double TAXA_MANUTENCAO_CC = 0.01;
    private final double taxaManutencao;

    public ContaCorrente(String numeroConta, double saldo, Cliente titular) {
        super(numeroConta, saldo, titular);
        this.taxaManutencao = TAXA_MANUTENCAO_CC;
    }

    @Override
    public void sacar(double valor) throws SaldoInsuficienteException {
        double taxa = valor * taxaManutencao;
        super.sacar(valor + taxa);
    }
}