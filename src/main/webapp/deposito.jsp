<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Depósito Geral</title>
    <!-- Bootstrap CSS Link -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="assets/deposito.css">
</head>

<body>
<div class="container mt-5">
    <div class="row">
        <div class="col-md-6">
            <h1 class="mb-4">Depósito Geral</h1>
            <form action="depositar" method="post">
                <div class="form-group">
                    <label for="numeroConta">Número da Conta:</label>
                    <input type="text" class="form-control" id="numeroConta" name="numeroConta" required>
                </div>
                <div class="form-group">
                    <label for="valorDeposito">Valor do Depósito:</label>
                    <input type="text" class="form-control" id="valorDeposito" name="valorDeposito" required>
                </div>
                <button type="submit" class="btn btn-primary">Depositar</button>
            </form>
        </div>
        <div class="col-md-6 right-side">
            <!-- Imagem de depósito -->
            <img src="./assets/deposit-image.png" alt="Depósito" class="deposit-image">
        </div>
    </div>
</div>

<!-- Bootstrap JS and dependencies -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>

</html>
