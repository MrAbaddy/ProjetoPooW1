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
        <a class="navbar-brand" href="<%= request.getContextPath() %>/dashboard">MotoTech Motos</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Alternar navegação">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link" href="/Mototech-1.0-SNAPSHOT/usuario">Usuários</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/Mototech-1.0-SNAPSHOT/funcionario">Funcionários</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/Mototech-1.0-SNAPSHOT/notaservico">Serviços</a>
                </li>
            </ul>
            <div class="user-info ms-auto" role="navigation" aria-label="Informações do usuário">
                <a href="/Mototech-1.0-SNAPSHOT/" class="btn btn-danger btn-sm ms-3"
                   aria-label="Sair do sistema">Sair</a>
            </div>
        </div>
    </div>
</nav>

<!-- Conteúdo principal -->
<main>
    <h1 class="mb-4">${notaEditar != null ? 'Editar Nota de Serviço' : 'Cadastro de Notas de Serviço'}</h1>

    <form action="notaservico" method="post" class="mb-5">
        <c:if test="${notaEditar != null}">
            <input type="hidden" name="id" value="${notaEditar.id}"/>
        </c:if>

        <div class="mb-3">
            <label for="cliente" class="form-label">Cliente</label>
            <input type="text" id="cliente" name="cliente" placeholder="Nome do Cliente"
                   class="form-control"
                   value="${notaEditar != null ? notaEditar.cliente : ''}" required>
        </div>

        <div class="mb-3">
            <label for="moto" class="form-label">Moto</label>
            <input type="text" id="moto" name="moto" placeholder="Modelo da Moto"
                   class="form-control"
                   value="${notaEditar != null ? notaEditar.moto : ''}" required>
        </div>

        <div class="mb-3">
            <label for="cilindradas" class="form-label">Cilindradas</label>
            <input type="number" id="cilindradas" name="cilindradas" placeholder="Cilindradas"
                   class="form-control"
                   value="${notaEditar != null ? notaEditar.cilindradas : ''}" min="0" required>
        </div>

        <div class="mb-3">
            <label for="descricao" class="form-label">Descrição</label>
            <textarea id="descricao" name="descricao" rows="3" placeholder="Descrição do serviço"
                      class="form-control" required>${notaEditar != null ? notaEditar.descricao : ''}</textarea>
        </div>

        <div class="mb-3">
            <label for="material" class="form-label">Material</label>
            <input type="text" id="material" name="material" placeholder="Material utilizado"
                   class="form-control"
                   value="${notaEditar != null ? notaEditar.material : ''}">
        </div>

        <div class="mb-3">
            <label for="funcionarioId" class="form-label">Funcionário</label>
            <select id="funcionarioId" name="funcionarioId" class="form-select" required>
                <option value="">Selecione o funcionário</option>
                <c:forEach var="func" items="${funcionarios}">
                    <option value="${func.id}"
                            <c:if test="${notaEditar != null && notaEditar.funcionario != null && notaEditar.funcionario.id == func.id}">
                                selected
                            </c:if>>
                            ${func.nome} - ${func.cargo}
                    </option>
                </c:forEach>
            </select>
        </div>

        <div class="mb-3">
            <label for="situacao" class="form-label">Situação</label>
            <select id="situacao" name="situacao" class="form-select" required>
                <option value="">Selecione a situação</option>
                <option value="Aberto"
                        <c:if test="${notaEditar != null && notaEditar.situacao == 'Aberto'}">selected</c:if>>Aberto
                </option>
                <option value="Em Andamento"
                        <c:if test="${notaEditar != null && notaEditar.situacao == 'Em Andamento'}">selected</c:if>>Em
                    Andamento
                </option>
                <option value="Finalizado"
                        <c:if test="${notaEditar != null && notaEditar.situacao == 'Finalizado'}">selected</c:if>>
                    Finalizado
                </option>
            </select>
        </div>

        <button type="submit" class="btn btn-success">
            ${notaEditar != null ? 'Salvar Alterações' : 'Cadastrar'}
        </button>
    </form>

    <c:if test="${not empty msg}">
        <div class="alert alert-info">${msg}</div>
    </c:if>

    <h2 class="mb-3">Lista de Notas de Serviço</h2>
    <table>
        <thead>
        <tr>
            <th>Cliente</th>
            <th>Moto</th>
            <th>Cilindradas</th>
            <th>Descrição</th>
            <th>Material</th>
            <th>Funcionário</th>
            <th>Situação</th>
            <th colspan="2">Ações</th>
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
                <td>${nota.funcionario != null ? nota.funcionario.nome : ''}</td>
                <td>${nota.situacao}</td>
                <td>
                    <a href="notaservico?opcao=editar&&info=${nota.id}" class="btn btn-sm btn-warning">Editar</a>
                </td>
                <td>
                    <a href="notaservico?opcao=excluir&&info=${nota.id}" class="btn btn-sm btn-danger">Excluir</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</main>

<!-- Rodapé -->
<footer>
    MotoTech — há 25 anos cuidando da sua Moto | Telefone e WhatsApp: (55) 99939-6619
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
