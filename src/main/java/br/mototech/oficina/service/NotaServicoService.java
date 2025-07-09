package br.mototech.oficina.service;

import br.mototech.oficina.dao.FuncionarioDAO;
import br.mototech.oficina.dao.NotaServicoDAO;
import br.mototech.oficina.model.Funcionario;
import br.mototech.oficina.model.NotaServico;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotaServicoService {

    private final NotaServicoDAO dao;
    private final FuncionarioDAO funcionarioDAO;

    public NotaServicoService(NotaServicoDAO dao, FuncionarioDAO funcionarioDAO) {
        this.dao = dao;
        this.funcionarioDAO = funcionarioDAO;
    }

    public List<NotaServico> listar() {
        return dao.listar();
    }

    public NotaServico buscar(int id) {
        return dao.buscar(id);
    }

    public String inserir(NotaServico nota) {
        Funcionario funcionario = funcionarioDAO.buscar(nota.getFuncionario().getId());

        if (funcionario == null) {
            return "Erro: Funcionário não encontrado.";
        }

        return dao.inserir(nota);
    }

    public String alterar(NotaServico nota) {
        Funcionario funcionario = funcionarioDAO.buscar(nota.getFuncionario().getId());

        if (funcionario == null) {
            return "Erro: Funcionário não encontrado.";
        }

        return dao.alterar(nota);
    }

    public String excluir(int id) {
        return dao.excluir(id);
    }
}