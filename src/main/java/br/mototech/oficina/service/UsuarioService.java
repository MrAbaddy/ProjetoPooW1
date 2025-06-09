package br.mototech.oficina.service;

import br.mototech.oficina.dao.UsuarioDAO;
import br.mototech.oficina.model.Usuario;

import java.util.ArrayList;

public class UsuarioService {

    private static UsuarioDAO dao = new UsuarioDAO();

    public String excluir(int id) {

        if (dao.excluir(id)) {
            return "Sucesso ao excluir usuario";
        } else {
            return "Erro ao excluir usuario";
        }

    }

    public ArrayList<Usuario> listar() {

        //o usuário q está solicitando
        // a requisição pode acessar essa lista?
        return dao.listar();
    }

    public Usuario buscar(int usuarioId) {
        return dao.buscar(usuarioId);
    }

    public String inserir(Usuario usuario) {
        return dao.inserir(usuario);
    }

    public String alterar(Usuario usuario) {
        return dao.alterar(usuario);
    }
}
