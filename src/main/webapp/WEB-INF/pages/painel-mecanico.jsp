<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8"/>
    <title>Notas de Serviço - MotoTech</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"/>
    <style>
        body {
            display: flex;
            flex-direction: column;
            min-height: 100vh;
            margin: 0;
            background-color: #000080;
            color: #fff;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }

        main {
            flex: 1;
            margin: 5.5rem auto 6rem auto;
            max-width: 900px;
            width: 90%;
            padding-top: 0;
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

        .nav-link, .user-info span {
            color: #fff !important;
            font-weight: 600;
            font-size: 1.1rem;
        }

        .nav-link:hover {
            color: #b8860b !important;
        }

        .user-info {
            display: flex;
            align-items: center;
            gap: 1rem;
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

        table {
            background-color: #001a66;
            color: #fff;
            border-collapse: collapse;
            width: 100%;
            font-size: 1rem;
        }

        table th, table td {
            padding: 0.75rem 1rem;
            border: 1px solid #444;
            text-align: center;
        }

        table th {
            background-color: #003399;
            color: #fff;
            font-weight: 700;
        }

        .btn-sm {
            min-width: 70px;
        }

        .btn-primary {
            background-color: #004080;
            border-color: #004080;
            color: #fff;
            font-weight: 600;
        }

        .btn-primary:hover {
            background-color: #0066cc;
            border-color: #0066cc;
            color: #fff;
        }

        .alert-info {
            background-color: #0059b3;
            border-color: #004080;
            color: #fff;
            font-weight: 600;
        }
    </style>
</head>
<body>

<nav class="navbar navbar-expand-lg shadow-sm fixed-top">
    <div class="container">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/dashboard">MotoTech Motos</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Alternar navegação">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/usuario">Usuários</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/funcionario">Funcionários</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/notaservico">Serviços</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/painel-mecanico">Painel do Mecânico</a>
                </li>
            </ul>
            <div class="user-info ms-auto" role="navigation" aria-label="Informações do usuário">
                <span>Bem-vindo, <strong>${usuarioLogado.email}</strong></span>
                <a href="${pageContext.request.contextPath}/logout" class="btn btn-danger btn-sm ms-3"
                   aria-label="Sair do sistema">Sair</a>
            </div>
        </div>
    </div>
</nav>

<!-- Conteúdo principal -->
<main>
    <h1 class="mb-4">Notas de Serviço Cadastradas</h1>

    <c:if test="${not empty msg}">
        <div class="alert alert-info">${msg}</div>
    </c:if>

    <table class="table table-striped table-bordered table-hover">
        <thead>
        <tr>
            <th>Cliente</th>
            <th>Moto</th>
            <th>Cilindradas</th>
            <th>Descrição</th>
            <th>Material</th>
            <th>Funcionário</th>
            <th>Situação</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="nota" items="${notas}">
            <tr>
                <td>${nota.cliente}</td>
                <td>${nota.moto}</td>
                <td>${nota.cilindradas}</td>
                <td>${nota.descricao}</td>
                <td>${nota.material}</td>
                <td><c:out value="${nota.funcionario != null ? nota.funcionario.nome : ''}"/></td>
                <td>
                    <form action="${pageContext.request.contextPath}/painel-mecanico/alterar-situacao" method="post" style="margin:0; display:flex; gap:0.5rem; justify-content:center; align-items:center;">
                        <input type="hidden" name="notaId" value="${nota.id}"/>
                        <select name="novaSituacao" class="form-select form-select-sm" aria-label="Selecionar situação">
                            <option value="Em Aberto" ${nota.situacao == 'Em Aberto' ? 'selected' : ''}>Em Aberto</option>
                            <option value="Em Andamento" ${nota.situacao == 'Em Andamento' ? 'selected' : ''}>Em Andamento</option>
                            <option value="Finalizado" ${nota.situacao == 'Finalizado' ? 'selected' : ''}>Finalizado</option>
                        </select>
                        <button type="submit" class="btn btn-primary btn-sm" aria-label="Salvar situação">Salvar</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</main>

<footer>
    MotoTech — há 25 anos cuidando da sua Moto | Telefone e WhatsApp: (55) 99939-6619
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
