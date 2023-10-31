package br.com.ibm.bancoservlet.controllers;

import br.com.ibm.bancoservlet.GlobalExceptionHandler.ContaInvalidaException;
import br.com.ibm.bancoservlet.GlobalExceptionHandler.SaldoInsuficienteException;
import br.com.ibm.bancoservlet.models.ContaCorrente;
import br.com.ibm.bancoservlet.services.ContaCorrenteService;
import br.com.ibm.bancoservlet.services.ContaCorrenteServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/transferir"})
public class TransferirServlet extends HttpServlet{

    private ContaCorrenteService contaService;

    @Override
    public void init() throws ServletException {
        super.init();
        List<ContaCorrente> contasCorrentes = (List<ContaCorrente>) getServletContext().getAttribute("contasCorrentes");
        contaService = new ContaCorrenteServiceImpl(contasCorrentes);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String contaOrigem = req.getParameter("contaOrigem");
        String contaDestino = req.getParameter("contaDestino");
        String valorTransferenciaStr = req.getParameter("valorTransferencia");

        try {
            double valorTransferencia = Double.parseDouble(valorTransferenciaStr);
            contaService.transferir(contaOrigem, contaDestino, valorTransferencia);
            RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
            dispatcher.forward(req, resp);
        } catch (NumberFormatException e) {
            req.setAttribute("mensagemErro", "Valor de transferência inválido.");
            RequestDispatcher rq = req.getRequestDispatcher("erro.jsp");
            rq.forward(req, resp);
        } catch (ContaInvalidaException | SaldoInsuficienteException e) {
            req.setAttribute("mensagemErro", e.getMessage());
            RequestDispatcher rq = req.getRequestDispatcher("erro.jsp");
            rq.forward(req, resp);
        }
    }
}
