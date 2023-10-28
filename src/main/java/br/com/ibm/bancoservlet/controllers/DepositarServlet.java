package br.com.ibm.bancoservlet.controllers;

import br.com.ibm.bancoservlet.GlobalExceptionHandler.ContaInvalidaException;
import br.com.ibm.bancoservlet.models.ContaCorrente;
import br.com.ibm.bancoservlet.services.ContaCorrenteService;
import br.com.ibm.bancoservlet.services.ContaCorrenteServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/depositar")
public class DepositarServlet extends HttpServlet {

    private final List<ContaCorrente> contasCorrentes = new ArrayList<>();
    private final ContaCorrenteService contaCorrenteService = new ContaCorrenteServiceImpl(contasCorrentes);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String numeroConta = req.getParameter("numeroConta");
        double valorDeposito = Double.parseDouble(req.getParameter("valorSaque"));

        try {
            contaCorrenteService.depositar(numeroConta, valorDeposito);
            resp.sendRedirect("index.jsp");
        } catch (ContaInvalidaException e) {
            req.setAttribute("mensagemErro", e.getMessage());
            RequestDispatcher rq = req.getRequestDispatcher("erro.jsp");
            rq.forward(req, resp);
        }

    }
}
