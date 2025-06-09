package br.mototech.oficina.dao;

import br.mototech.oficina.model.Usuario;

import java.sql.*;
import java.util.ArrayList;

public class UsuarioDAO {

    public ArrayList<Usuario> listar() {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM Usuario";

        try (
                Connection conn = ConexaoDB.conectarBancoPostgres();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)
        ) {
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setEmail(rs.getString("email"));
                u.setSenha(rs.getString("senha"));
                usuarios.add(u);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao listar usuarios: " + e.getMessage());
        }

        return usuarios;
    }

    public String inserir(Usuario usuario) {
        String sql = "INSERT INTO Usuario (email, senha) VALUES (?, ?)";

        try (
                Connection conn = ConexaoDB.conectarBancoPostgres();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, usuario.getEmail());
            stmt.setString(2, usuario.getSenha());
            stmt.execute();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao inserir usuario: " + e.getMessage());
            return "Erro ao inserir usuario";
        }

        return "Usuario inserido com sucesso";
    }

    public String alterar(Usuario usuario) {
        String sql = "UPDATE Usuario SET email = ?, senha = ? WHERE id = ?";

        try (
                Connection conn = ConexaoDB.conectarBancoPostgres();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, usuario.getEmail());
            stmt.setString(2, usuario.getSenha());
            stmt.setInt(3, usuario.getId());
            stmt.execute();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao alterar usuario: " + e.getMessage());
            return "Erro ao alterar usuario";
        }

        return "Usuario alterado com sucesso";
    }

    public boolean excluir(int id) {
        String sql = "DELETE FROM Usuario WHERE id = ?";

        try (
                Connection conn = ConexaoDB.conectarBancoPostgres();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, id);
            stmt.execute();
            return stmt.getUpdateCount() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao excluir usuario: " + e.getMessage());
            return false;
        }
    }

    public Usuario buscar(int id) {
        String sql = "SELECT * FROM Usuario WHERE id = ?";

        try (
                Connection conn = ConexaoDB.conectarBancoPostgres();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setId(rs.getInt("id"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setSenha(rs.getString("senha"));
                    return usuario;
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao buscar usuario: " + e.getMessage());
        }

        return null; // Retorna null se não encontrar
    }

    public Usuario buscar(String email) {
        String sql = "SELECT * FROM Usuario WHERE email = ?";

        try (
                Connection conn = ConexaoDB.conectarBancoPostgres();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setId(rs.getInt("id"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setSenha(rs.getString("senha"));
                    return usuario;
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao buscar usuario: " + e.getMessage());
        }

        return null; // Retorna null se não encontrar
    }
}
