package br.mototech.oficina.service;

import br.mototech.oficina.dao.UsuarioDAO;
import br.mototech.oficina.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioDAO dao;

    public UsuarioService(UsuarioDAO dao) {
        this.dao = dao;
    }

    public String excluir(int id) {
        if (dao.excluir(id)) {
            return "Sucesso ao excluir usuario";
        } else {
            return "Erro ao excluir usuario";
        }
    }

    public List<Usuario> listar() {
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
