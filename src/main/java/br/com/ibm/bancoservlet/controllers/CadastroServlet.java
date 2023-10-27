package br.com.ibm.bancoservlet.controllers;

import java.io.*;
import java.util.List;
import java.util.ArrayList;

import br.com.ibm.bancoservlet.models.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/cadastrar")
public class CadastroServlet extends HttpServlet {
    private final List<Cliente> clientes = new ArrayList<>();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nome = req.getParameter("nome");
        String cpf  = req.getParameter("cpf");
        ServletContext servletContext = getServletContext();

        Integer contadorContas = (Integer) servletContext.getAttribute("contadorContas");

        if (contadorContas == null) {
            contadorContas = 1;
            servletContext.setAttribute("contadorContas", contadorContas);
        } else {
            contadorContas++;
            servletContext.setAttribute("contadorContas", contadorContas);
        }

        String numeroConta = String.format("%05d", contadorContas);

        Cliente cliente = new Cliente(nome, cpf);
        clientes.add(cliente);

        ContaCorrente contaCorrente = new ContaCorrente(String.valueOf(numeroConta), 0.0, cliente);

        List<ContaCorrente> contasCorrentes = (List<ContaCorrente>)servletContext.getAttribute("contasCorrentes");

        if (contasCorrentes == null) {
            contasCorrentes = new ArrayList<>();
        }

        contasCorrentes.add(contaCorrente);
        servletContext.setAttribute("contasCorrentes", contasCorrentes);

        RequestDispatcher rq = req.getRequestDispatcher("sucesso.jsp");
        rq.forward(req,resp);
    }
}