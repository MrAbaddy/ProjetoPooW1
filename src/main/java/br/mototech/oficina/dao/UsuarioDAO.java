package br.mototech.oficina.dao;

import br.mototech.oficina.model.Usuario;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UsuarioDAO {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Usuario> rowMapper = new BeanPropertyRowMapper<>(Usuario.class);

    public UsuarioDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Usuario> listar() {
        String sql = "SELECT * FROM Usuario";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public String inserir(Usuario usuario) {
        String sql = "INSERT INTO Usuario (email, senha) VALUES (?, ?)";
        try {
            jdbcTemplate.update(sql, usuario.getEmail(), usuario.getSenha());
            return "Usuario inserido com sucesso";
        } catch (Exception e) {
            System.out.println("Erro ao inserir usuario: " + e.getMessage());
            return "Erro ao inserir usuario";
        }
    }

    public String alterar(Usuario usuario) {
        String sql = "UPDATE Usuario SET email = ?, senha = ? WHERE id = ?";
        try {
            jdbcTemplate.update(sql, usuario.getEmail(), usuario.getSenha(), usuario.getId());
            return "Usuario alterado com sucesso";
        } catch (Exception e) {
            System.out.println("Erro ao alterar usuario: " + e.getMessage());
            return "Erro ao alterar usuario";
        }
    }

    public boolean excluir(int id) {
        String sql = "DELETE FROM Usuario WHERE id = ?";
        try {
            int rows = jdbcTemplate.update(sql, id);
            return rows > 0;
        } catch (Exception e) {
            System.out.println("Erro ao excluir usuario: " + e.getMessage());
            return false;
        }
    }

    public Usuario buscar(int id) {
        String sql = "SELECT * FROM Usuario WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, rowMapper, id);
        } catch (Exception e) {
            System.out.println("Erro ao buscar usuario: " + e.getMessage());
            return null;
        }
    }

    public Usuario buscar(String email) {
        String sql = "SELECT * FROM Usuario WHERE email = ?";
        try {
            return jdbcTemplate.queryForObject(sql, rowMapper, email);
        } catch (Exception e) {
            System.out.println("Erro ao buscar usuario: " + e.getMessage());
            return null;
        }
    }
}
