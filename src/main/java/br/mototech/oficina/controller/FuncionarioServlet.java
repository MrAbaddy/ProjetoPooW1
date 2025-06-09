package br.mototech.oficina.controller;

import br.mototech.oficina.model.Funcionario;
import br.mototech.oficina.service.FuncionarioService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/funcionario")
public class FuncionarioServlet extends HttpServlet {

    private static final FuncionarioService service = new FuncionarioService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession sessao = req.getSession(false);
        if (sessao == null || sessao.getAttribute("usuarioLogado") == null) {
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }

        String nome = req.getParameter("nome");
        String cnh = req.getParameter("cnh");
        String cargo = req.getParameter("cargo");
        String idStr = req.getParameter("id");

        Funcionario funcionario = new Funcionario();
        funcionario.setNome(nome);
        funcionario.setCnh(cnh);
        funcionario.setCargo(cargo);

        String retorno;

        if (idStr != null && !idStr.isEmpty()) {
            funcionario.setId(Integer.parseInt(idStr));
            retorno = service.alterar(funcionario);
        } else {
            retorno = service.inserir(funcionario);
        }

        req.setAttribute("retorno", retorno);
        req.setAttribute("funcionarios", service.listar());

        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/pages/funcionario.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession sessao = req.getSession(false);
        if (sessao == null || sessao.getAttribute("usuarioLogado") == null) {
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }

        String opcao = req.getParameter("opcao");
        String info = req.getParameter("info");

        if (opcao != null) {
            if (opcao.equals("editar")) {
                Funcionario funcionario = service.buscar(Integer.parseInt(info));
                req.setAttribute("funcionarioEditar", funcionario);
            } else if (opcao.equals("excluir")) {
                String valor = service.excluir(Integer.parseInt(info));
                req.setAttribute("msg", valor);
            }
        }

        ArrayList<Funcionario> funcionarios = service.listar();
        req.setAttribute("funcionarios", funcionarios);

        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/pages/funcionario.jsp");
        rd.forward(req, resp);
    }
}
