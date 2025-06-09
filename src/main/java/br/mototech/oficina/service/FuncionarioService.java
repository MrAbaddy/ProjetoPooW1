package br.mototech.oficina.service;

import br.mototech.oficina.dao.FuncionarioDAO;
import br.mototech.oficina.model.Funcionario;

import java.util.ArrayList;

public class FuncionarioService {

    private static final FuncionarioDAO dao = new FuncionarioDAO();

    public ArrayList<Funcionario> listar() {
        return dao.listar();
    }

    public Funcionario buscar(int id) {
        return dao.buscar(id);
    }

    public String inserir(Funcionario funcionario) {
        return dao.inserir(funcionario);
    }

    public String alterar(Funcionario funcionario) {
        return dao.alterar(funcionario);
    }

    public String excluir(int id) {
        if (dao.excluir(id)) {
            return "Funcionário excluído com sucesso!";
        } else {
            return "Erro ao excluir funcionário.";
        }
    }

}
