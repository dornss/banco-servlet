<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8"%>
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
    <h1 class="mb-4">Administrador Banco IBM</h1>
    <!-- Botões de sacar e depositar -->
    <div class="mb-3">
        <form action="saque.jsp" method="post" class="d-inline-block mr-2">
            <button class="btn btn-success" type="submit">Sacar</button>
        </form>
        <form action="deposito.jsp" method="post" class="d-inline-block">
            <button class="btn btn-success" type="submit">Depositar</button>
        </form>
        <form action="cadastro.jsp" method="post" class="d-inline-block">
            <button class="btn btn-dark" type="submit">Cadastrar</button>
        </form>
    </div>
    <h1 class="mb-4">Contas correntes</h1>
    <table class="table">
        <thead class="thead-dark">
        <tr>
            <th scope="col">Número da Conta</th>
            <th scope="col">Saldo</th>
            <th scope="col">Titular</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="conta" items="${contasCorrentes}">
            <tr>
                <td>${conta.numeroConta}</td>
                <td><fmt:formatNumber value="${conta.saldo}" type="currency" currencyCode="BRL" maxFractionDigits="2" /></td>
                <td>${conta.titular.nome}</td>
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
