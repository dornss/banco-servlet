package br.com.ibm.bancoservlet.controllers;

import br.com.ibm.bancoservlet.models.*;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/depositar")
public class DepositarServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String numeroConta = req.getParameter("numeroConta");
        double valorDeposito = Double.parseDouble(req.getParameter("valorDeposito"));

        // Recuperar a lista de contas correntes do contexto de servlet
        ServletContext servletContext = getServletContext();
        List<ContaCorrente> contasCorrentes = (List<ContaCorrente>) servletContext.getAttribute("contasCorrentes");

        // Encontrar a conta corrente com o número informado
        ContaCorrente contaDeposito = null;
        for (ContaCorrente conta : contasCorrentes) {
            if (conta.getNumeroDaConta().equals(numeroConta)) {
                contaDeposito = conta;
                break;
            }
        }

        if (contaDeposito != null) {
            double novoSaldo = contaDeposito.getSaldo() + valorDeposito;
            contaDeposito.setSaldo(novoSaldo);
            resp.sendRedirect("clientes.jsp");
        } else {
            resp.sendRedirect("erro.jsp"); //
        }
    }
}