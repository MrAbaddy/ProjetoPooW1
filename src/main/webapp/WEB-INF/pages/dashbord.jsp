<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="br.mototech.oficina.model.Usuario" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    // Verifica se há usuário na sessão
    Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
    if (usuario == null) {
        response.sendRedirect("index.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8"/>
    <title>Dashboard - MotoTech</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"/>
    <style>
        body {
            display: flex;
            flex-direction: column;
            min-height: 100vh;
            margin: 0;
            background-color: #000080; /* azul marinho escuro */
            color: #fff;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }

        .navbar {
            background-color: #000 !important;
            position: fixed;
            top: 0;
            width: 100%;
            z-index: 1100;
        }

        .navbar-brand {
            font-weight: 700;
            color: #fff !important;
            font-size: 1.8rem;
        }

        .user-info {
            color: #fff;
            display: flex;
            align-items: center;
            gap: 1rem;
        }

        main {
            flex: 1;
            margin: 5.5rem auto 3rem auto;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 1rem;
            max-width: 900px;
            width: 90%;
        }

        .btn-group-custom {
            display: flex;
            justify-content: center;
            gap: 2rem;
            flex-wrap: nowrap;
        }

        .btn-group-custom .btn {
            min-width: 200px;
            padding: 1.5rem 2rem;
            font-size: 1.3rem;
            color: #fff;
            text-align: center;
            border-radius: 8px;
            border: none;
            transition: transform 0.2s ease-in-out, background-color 0.3s ease-in-out, color 0.3s ease-in-out;
        }

        .btn-verde {
            background-color: #28a745;
        }

        .btn-amarelo {
            background-color: #b8860b;
        }

        .btn-vermelho {
            background-color: #dc3545;
        }

        .btn-verde:hover {
            background-color: #fff;
            color: #28a745;
        }

        .btn-amarelo:hover {
            background-color: #fff;
            color: #b8860b;
        }

        .btn-vermelho:hover {
            background-color: #fff;
            color: #dc3545;
        }

        .btn-group-custom .btn:hover {
            transform: scale(1.05);
        }

        footer {
            background-color: #000 !important;
            color: #fff;
            text-align: center;
            padding: 1rem 0;
            font-size: 0.9rem;
            position: fixed;
            width: 100%;
            bottom: 0;
            z-index: 1100;
        }
    </style>
</head>
<body>

<!-- Navbar -->
<nav class="navbar navbar-expand-lg shadow-sm">
    <div class="container">
        <a class="navbar-brand" href="<%= request.getContextPath() %>/dashboard">MotoTech Motos</a>

        <div class="user-info ms-auto">
            <span>Bem-vindo, <strong><%= usuario.getEmail() %></strong></span>

            <!-- Botão Sair -->
            <a href="<%= request.getContextPath() %>/logout"
               class="btn btn-danger btn-sm ms-3"
               role="button"
               aria-label="Sair do sistema">Sair</a>
        </div>
    </div>
</nav>

<!-- Conteúdo principal -->
<main>
    <div class="btn-group-custom">
        <a href="<c:url value='/usuario' />" class="btn btn-verde">Cadastrar Usuários</a>
        <a href="<c:url value='/funcionario' />" class="btn btn-amarelo">Funcionários</a>
        <a href="<c:url value='/notaservico' />" class="btn btn-vermelho">Serviços</a>
    </div>
</main>

<!-- Rodapé -->
<footer>
    MotoTech — há 25 anos cuidando da sua Moto | Telefone e WhatsApp: (55) 99939-6619
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
