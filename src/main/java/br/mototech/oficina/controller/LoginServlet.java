package br.mototech.oficina.controller;

import br.mototech.oficina.model.Usuario;
import br.mototech.oficina.service.LoginService;
import br.mototech.oficina.service.UsuarioService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private LoginService loginService = new LoginService();
    private UsuarioService usuarioService = new UsuarioService();

    // GET /login → exibe a página de login (index.jsp)
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
        dispatcher.forward(req, resp);
    }

    // POST /login → processa o envio do formulário de login
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("email");
        String senha = req.getParameter("senha");

        System.out.println("Email: " + email + " - senha: " + senha);

        // Chama o serviço que retorna o Usuario ou null
        Usuario usuario = loginService.autenticar(email, senha);

        if (usuario != null) {
            // Cria a sessão e armazena o usuário autenticado
            HttpSession session = req.getSession();
            session.setAttribute("usuarioLogado", usuario);

            // Encaminha para o dashboard
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/pages/dashbord.jsp");
            dispatcher.forward(req, resp);
        } else {
            // Se falhar, retorna para a própria index.jsp com mensagem de erro
            req.setAttribute("msg", "Login ou senha incorretos!");
            RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
