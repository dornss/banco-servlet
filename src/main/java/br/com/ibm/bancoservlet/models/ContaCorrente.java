package br.com.ibm.bancoservlet.models;

import lombok.Data;

@Data
public class ContaCorrente extends ContaBancaria {
    private static final double TAXA_MANUTENCAO_CC = 0.01;
    private double taxaManutencao;

    public ContaCorrente(String numeroConta, double saldo, Cliente titular) {
        super(numeroConta, saldo, titular);
        this.taxaManutencao = TAXA_MANUTENCAO_CC;
    }

    @Override
    public void sacar(double valor) {
        double taxa = valor * taxaManutencao;
        super.sacar(valor + taxa);
    }


}