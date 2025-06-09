package br.mototech.oficina.controller;

import br.mototech.oficina.model.Funcionario;
import br.mototech.oficina.model.NotaServico;
import br.mototech.oficina.service.FuncionarioService;
import br.mototech.oficina.service.NotaServicoService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

@WebServlet("/notaservico")
public class NotaServicoServlet extends HttpServlet {

    private static final NotaServicoService service = new NotaServicoService();
    private static final FuncionarioService funcionarioService = new FuncionarioService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession sessao = req.getSession(false);
        if (sessao == null || sessao.getAttribute("usuarioLogado") == null) {
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }

        String idStr = req.getParameter("id");
        String cliente = req.getParameter("cliente");
        String moto = req.getParameter("moto");
        String cilindradasStr = req.getParameter("cilindradas");
        String descricao = req.getParameter("descricao");
        String material = req.getParameter("material");
        String idFuncionarioStr = req.getParameter("funcionarioId");
        String situacao = req.getParameter("situacao");

        Integer id = parseInteger(idStr);
        Integer cilindradas = parseInteger(cilindradasStr);
        Integer idFuncionario = parseInteger(idFuncionarioStr);

        Funcionario funcionario = null;
        if (idFuncionario != null) {
            funcionario = funcionarioService.buscar(idFuncionario);
            if (funcionario == null) {

                req.setAttribute("msg", "Funcionário inválido.");
                doGet(req, resp); // Reexibe página com mensagem
                return;
            }
        }

        NotaServico nota = new NotaServico();
        if (id != null) nota.setId(id);
        nota.setCliente(cliente);
        nota.setMoto(moto);
        nota.setCilindradas(cilindradas);
        nota.setDescricao(descricao);
        nota.setMaterial(material);
        nota.setFuncionario(funcionario);
        nota.setSituacao(situacao);

        String mensagem;
        if (id != null) {
            mensagem = service.alterar(nota);
        } else {
            mensagem = service.inserir(nota);
        }


        resp.sendRedirect(req.getContextPath() + "/notaservico?msg=" + URLEncoder.encode(mensagem, StandardCharsets.UTF_8));
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
        String msg = req.getParameter("msg");

        if (msg != null && !msg.isEmpty()) {
            req.setAttribute("msg", msg);
        }

        if ("editar".equalsIgnoreCase(opcao) && info != null) {
            try {
                int id = Integer.parseInt(info);
                NotaServico nota = service.buscar(id);
                if (nota != null) {
                    req.setAttribute("notaEditar", nota);
                } else {
                    req.setAttribute("msg", "Nota de serviço não encontrada.");
                }
            } catch (NumberFormatException e) {
                req.setAttribute("msg", "ID inválido para edição.");
            }
        } else if ("excluir".equalsIgnoreCase(opcao) && info != null) {
            try {
                int id = Integer.parseInt(info);
                String resultado = service.excluir(id);
                req.setAttribute("msg", resultado);
            } catch (NumberFormatException e) {
                req.setAttribute("msg", "ID inválido para exclusão.");
            }
        }

        ArrayList<NotaServico> notas = service.listar();
        ArrayList<Funcionario> funcionarios = funcionarioService.listar();

        req.setAttribute("notas", notas);
        req.setAttribute("funcionarios", funcionarios);

        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/pages/notaservico.jsp");
        rd.forward(req, resp);
    }

    private Integer parseInteger(String valor) {
        if (valor == null || valor.trim().isEmpty()) return null;
        try {
            return Integer.parseInt(valor.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
