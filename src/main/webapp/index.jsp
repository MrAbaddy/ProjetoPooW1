<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8"/>
    <title>Login - MotoTech</title>

    <!-- Bootstrap 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"/>

    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css"/>

    <!-- CSS externo para login -->
    <link rel="stylesheet" href="css/Geral.Css"/>
</head>
<body>

<div class="login-box" role="main" aria-label="Área de login do sistema MotoTech">
    <div class="logo">
        <img src="image/LoginLogo.jpg" alt="Logo MotoTech"/>
    </div>
    <h2>Olá,</h2>
    <p>Por gentileza, insira seu email e senha!</p>

    <form action="login" method="post" novalidate>
        <div class="input-group mb-3">
            <span class="input-group-text" id="email-addon"><i class="fa fa-user"></i></span>
            <input type="email" class="form-control" name="email" placeholder="E-mail" aria-label="E-mail"
                   aria-describedby="email-addon" required/>
        </div>

        <div class="input-group mb-4">
            <span class="input-group-text" id="senha-addon"><i class="fa fa-lock"></i></span>
            <input type="password" class="form-control" name="senha" placeholder="Senha" aria-label="Senha"
                   aria-describedby="senha-addon" required/>
        </div>

        <button type="submit" class="btn btn-success btn-lg w-100" aria-label="Botão para entrar no sistema">Logar
        </button>
    </form>

    <c:if test="${not empty msg}">
        <div class="alert alert-danger" role="alert" aria-live="assertive" aria-atomic="true">
                ${msg}
        </div>
    </c:if>
</div>

<!-- Bootstrap JS Bundle -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
