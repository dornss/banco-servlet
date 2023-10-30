package br.com.ibm.bancoservlet.controllers;

import br.com.ibm.bancoservlet.GlobalExceptionHandler.ContaInvalidaException;
import br.com.ibm.bancoservlet.GlobalExceptionHandler.SaldoInsuficienteException;
import br.com.ibm.bancoservlet.models.ContaCorrente;
import br.com.ibm.bancoservlet.services.ContaCorrenteService;
import br.com.ibm.bancoservlet.services.ContaCorrenteServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/sacar")
public class SacarServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String numeroConta = req.getParameter("numeroConta");
        double valorSaque = Double.parseDouble(req.getParameter("valorSaque"));

        List<ContaCorrente> contasCorrentes = (List<ContaCorrente>) getServletContext().getAttribute("contasCorrentes");

        try {
            ContaCorrenteService contaService = new ContaCorrenteServiceImpl(contasCorrentes);
            contaService.sacar(numeroConta, valorSaque);
            resp.sendRedirect("index.jsp");
        } catch (ContaInvalidaException | SaldoInsuficienteException e) {
            req.setAttribute("mensagemErro", e.getMessage());
            RequestDispatcher rq = req.getRequestDispatcher("erro.jsp");
            rq.forward(req, resp);
        }
    }
}
