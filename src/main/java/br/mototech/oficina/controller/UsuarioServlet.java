package br.mototech.oficina.controller;

import br.mototech.oficina.model.Usuario;
import br.mototech.oficina.service.UsuarioService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/usuario")
public class UsuarioServlet extends HttpServlet {

    private static final UsuarioService service = new UsuarioService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession sessao = req.getSession(false);
        if (sessao == null || sessao.getAttribute("usuarioLogado") == null) {
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }

        String email = req.getParameter("email");
        String senha = req.getParameter("senha");
        String idStr = req.getParameter("id");

        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setSenha(senha);

        String retorno;

        if (idStr != null && !idStr.isEmpty()) {
            usuario.setId(Integer.parseInt(idStr));
            retorno = service.alterar(usuario);
        } else {
            retorno = service.inserir(usuario);
        }

        req.setAttribute("retorno", retorno);
        req.setAttribute("usuarios", service.listar());

        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/pages/usuario.jsp");
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
                Usuario usuario = service.buscar(Integer.parseInt(info));
                req.setAttribute("usuarioEditar", usuario);
            } else if (opcao.equals("excluir")) {
                String valor = service.excluir(Integer.parseInt(info));
                req.setAttribute("msg", valor);
            }
        }

        ArrayList<Usuario> usuarios = service.listar();
        req.setAttribute("usuarios", usuarios);

        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/pages/usuario.jsp");
        rd.forward(req, resp);
    }
}
