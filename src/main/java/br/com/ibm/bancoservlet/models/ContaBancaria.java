package br.com.ibm.bancoservlet.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContaBancaria {
    private String numeroDaConta;
    private Double saldo;
    private Cliente titular;

}
