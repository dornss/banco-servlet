package br.com.ibm.bancoservlet.controllers;

import br.com.ibm.bancoservlet.GlobalExceptionHandler.ContaInvalidaException;
import br.com.ibm.bancoservlet.models.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/depositar")
public class DepositarServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String numeroConta = req.getParameter("numeroConta");
        double valorDeposito = Double.parseDouble(req.getParameter("valorDeposito"));

        ServletContext servletContext = getServletContext();
        List<ContaCorrente> contasCorrentes = (List<ContaCorrente>) servletContext.getAttribute("contasCorrentes");

        ContaCorrente contaDeposito = null;
        for (ContaCorrente conta : contasCorrentes) {
            if (conta.getNumeroDaConta().equals(numeroConta)) {
                contaDeposito = conta;
                break;
            }
        }

        try {
            if (contaDeposito != null) {
                double novoSaldo = contaDeposito.getSaldo() + valorDeposito;
                contaDeposito.setSaldo(novoSaldo);
                resp.sendRedirect("clientes.jsp");
            } else {
                throw new ContaInvalidaException("Conta inválida. Por favor, verifique o número da conta.");
            }
        } catch (ContaInvalidaException e) {
            req.setAttribute("mensagemErro", e.getMessage());
            RequestDispatcher rq = req.getRequestDispatcher("erro.jsp");
            rq.forward(req, resp);
        }
    }
}