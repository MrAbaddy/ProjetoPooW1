package br.mototech.oficina.service;

import br.mototech.oficina.dao.UsuarioDAO;
import br.mototech.oficina.model.Usuario;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final UsuarioDAO dao;

    public LoginService(UsuarioDAO dao) {
        this.dao = dao;
    }

    public Usuario autenticar(String email, String senha) {
        Usuario usuario = dao.buscar(email);

        if (usuario != null && usuario.getSenha() != null && usuario.getSenha().equals(senha)) {
            return usuario;
        }

        return null;
    }

    public boolean autenticarLoginSimples(String email, String senha) {
        return autenticar(email, senha) != null;
    }
}
