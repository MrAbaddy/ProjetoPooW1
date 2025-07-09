<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="br.mototech.oficina.model.Usuario" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8"/>
    <title>Funcionários - MotoTech</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"/>
    <style>
        body {
            display: flex;
            flex-direction: column;
            min-height: 100vh;
            margin: 0;
            background-color: #000080;
            color: #fff;
        }

        main {
            flex: 1;
            margin: 2rem auto 6rem auto;
            max-width: 900px;
            width: 90%;
            padding-top: 70px;
        }

        form > div.mb-3 {
            margin-bottom: 0.75rem;
        }

        form button {
            padding: 0.5rem 1.25rem;
            font-size: 1rem;
        }

        .navbar {
            background-color: #000 !important;
        }

        .navbar-brand {
            font-weight: 700;
            color: #fff !important;
            font-size: 1.5rem;
        }

        .nav-link, .user-info span {
            color: #fff !important;
        }

        .user-info {
            display: flex;
            align-items: center;
            gap: 1rem;
        }

        footer {
            background-color: #000;
            color: #fff;
            text-align: center;
            padding: 1rem 0;
            font-size: 0.9rem;
            position: fixed;
            width: 100%;
            bottom: 0;
        }

        label {
            font-weight: 600;
        }

        input, select, textarea {
            background-color: #001a66;
            border: 1px solid #444;
            color: #fff;
        }

        input::placeholder {
            color: #bbb;
        }

        input:focus, select:focus, textarea:focus {
            background-color: #003399;
            border-color: #b8860b;
            outline: none;
            color: #fff;
        }

        table {
            background-color: #001a66;
            color: #fff;
            border-collapse: collapse;
            width: 100%;
        }

        table th, table td {
            padding: 0.75rem 1rem;
            border: 1px solid #444;
        }

        table th {
            background-color: #003399;
        }

        a.btn-sm {
            min-width: 70px;
        }
    </style>
</head>
<body>

<!-- Navbar -->
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

<main>
    <h1 class="mb-4">${funcionarioEditar != null ? 'Editar Funcionário' : 'Cadastro de Funcionários'}</h1>

    <form action="${pageContext.request.contextPath}/funcionario" method="post" class="mb-5">
        <c:if test="${funcionarioEditar != null}">
            <input type="hidden" name="id" value="${funcionarioEditar.id}"/>
        </c:if>

        <div class="mb-3">
            <label for="nome" class="form-label">Nome</label>
            <input type="text" id="nome" name="nome" placeholder="Nome"
                   class="form-control"
                   value="${funcionarioEditar != null ? funcionarioEditar.nome : ''}" required>
        </div>

        <div class="mb-3">
            <label for="cnh" class="form-label">CNH</label>
            <input type="text" id="cnh" name="cnh" placeholder="CNH"
                   class="form-control"
                   value="${funcionarioEditar != null ? funcionarioEditar.cnh : ''}" required>
        </div>

        <div class="mb-3">
            <label for="cargo" class="form-label">Cargo</label>
            <input type="text" id="cargo" name="cargo" placeholder="Cargo"
                   class="form-control"
                   value="${funcionarioEditar != null ? funcionarioEditar.cargo : ''}" required>
        </div>

        <button type="submit" class="btn btn-success">
            ${funcionarioEditar != null ? 'Salvar Alterações' : 'Cadastrar'}
        </button>
    </form>

    <c:if test="${not empty msg}">
        <div class="alert alert-info">${msg}</div>
    </c:if>

    <h2 class="mb-3">Lista de Funcionários</h2>
    <table>
        <thead>
        <tr>
            <th>Nome</th>
            <th>CNH</th>
            <th>Cargo</th>
            <th colspan="2">Ações</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="funcionario" items="${funcionarios}">
            <tr>
                <td>${funcionario.nome}</td>
                <td>${funcionario.cnh}</td>
                <td>${funcionario.cargo}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/funcionario?opcao=editar&&info=${funcionario.id}" class="btn btn-sm btn-warning">Editar</a>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/funcionario?opcao=excluir&&info=${funcionario.id}"
                       class="btn btn-sm btn-danger">Excluir</a>
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
