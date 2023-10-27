<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista de Contas Correntes</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h1 class="mb-4">Lista de Contas Correntes</h1>
    <table class="table">
        <thead class="thead-dark">
        <tr>
            <th scope="col">Número da Conta</th>
            <th scope="col">Saldo</th>
            <th scope="col">Titular</th>
            <th scope="col">Operações</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="conta" items="${contasCorrentes}">
            <tr>
                <td>${conta.numeroDaConta}</td>
                <td>R$${conta.saldo}</td>
                <td>${conta.titular.nome}</td>
                <td>
                    <form action="deposito.jsp" method="post">
                        <input type="hidden" name="numeroConta" value="${conta.numeroDaConta}">
                        <button class="btn btn-success btn-sm" type="submit">Depositar</button>
                    </form>
                </td>
                <td>
                    <form action="saque.jsp" method="post">
                        <input type="hidden" name="numeroConta" value="${conta.numeroConta}">
                        <button class="btn btn-success btn-sm" type="submit">Sacar</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
