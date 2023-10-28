package br.com.ibm.bancoservlet.controllers;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


import br.com.ibm.bancoservlet.GlobalExceptionHandler.ContaInvalidaException;
import br.com.ibm.bancoservlet.models.ContaCorrente;
import br.com.ibm.bancoservlet.services.ContaCorrenteService;
import br.com.ibm.bancoservlet.services.ContaCorrenteServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(urlPatterns = {"/cadastrar"})
public class CadastroServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
        List<ContaCorrente> contasCorrentes = new ArrayList<>();
        getServletContext().setAttribute("contasCorrentes", contasCorrentes);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nome = req.getParameter("nome");
        String cpf = req.getParameter("cpf");

        if (nome == null || cpf == null || nome.isEmpty() || cpf.isEmpty()) {
            req.setAttribute("mensagemErro", "Nome e CPF são obrigatórios.");
            RequestDispatcher dispatcher = req.getRequestDispatcher("erro.jsp");
            dispatcher.forward(req, resp);
            return;
        }

        List<ContaCorrente> contasCorrentes = (List<ContaCorrente>) getServletContext().getAttribute("contasCorrentes");

        try {
            ContaCorrenteService contaService = new ContaCorrenteServiceImpl(contasCorrentes);
            ContaCorrente contaCorrente = contaService.criarConta(nome, cpf);
            contasCorrentes.add(contaCorrente);
            resp.getWriter().println("Cadastro realizado com sucesso");
        } catch (ContaInvalidaException e) {
            req.setAttribute("mensagemErro", e.getMessage());
            RequestDispatcher dispatcher = req.getRequestDispatcher("erro.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
