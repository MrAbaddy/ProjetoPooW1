package br.mototech.oficina.dao;

import br.mototech.oficina.model.Funcionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FuncionarioDAO {

    public ArrayList<Funcionario> listar() {
        ArrayList<Funcionario> funcionarios = new ArrayList<>();

        String sql = "SELECT * FROM Funcionario";

        try (
                Connection conn = ConexaoDB.conectarBancoPostgres();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {
            while (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setId(rs.getInt("id"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setCnh(rs.getString("cnh"));
                funcionario.setCargo(rs.getString("cargo"));

                funcionarios.add(funcionario);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao listar funcionários: " + e.getMessage());
            e.printStackTrace();
        }

        return funcionarios;
    }

    public String inserir(Funcionario funcionario) {

        String sql = "INSERT INTO Funcionario(nome, cnh, cargo) VALUES (?, ?, ?)";

        try (Connection conn = ConexaoDB.conectarBancoPostgres();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getCnh());
            stmt.setString(3, funcionario.getCargo());

            stmt.execute();

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao inserir funcionário: " + e.getMessage());
            e.printStackTrace();
            return "Erro ao inserir funcionário.";
        }

        return "Funcionário inserido com sucesso!";
    }

    public String alterar(Funcionario funcionario) {

        String sql = "UPDATE Funcionario SET nome = ?, cnh = ?, cargo = ? WHERE id = ?";

        try (
                Connection conn = ConexaoDB.conectarBancoPostgres();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getCnh());
            stmt.setString(3, funcionario.getCargo());
            stmt.setInt(4, funcionario.getId());

            stmt.execute();

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao alterar funcionário: " + e.getMessage());
            e.printStackTrace();
            return "Erro ao alterar funcionário.";
        }

        return "Funcionário alterado com sucesso!";
    }

    public boolean excluir(int id) {

        String sql = "DELETE FROM Funcionario WHERE id = ?";
        try (
                Connection conn = ConexaoDB.conectarBancoPostgres();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, id);
            stmt.execute();
            return stmt.getUpdateCount() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao excluir funcionário: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public Funcionario buscar(int id) {
        Funcionario funcionario = null;
        String sql = "SELECT * FROM Funcionario WHERE id = ?";

        try (
                Connection conn = ConexaoDB.conectarBancoPostgres();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    funcionario = new Funcionario();
                    funcionario.setId(rs.getInt("id"));
                    funcionario.setNome(rs.getString("nome"));
                    funcionario.setCnh(rs.getString("cnh"));
                    funcionario.setCargo(rs.getString("cargo"));
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao buscar funcionário: " + e.getMessage());
            e.printStackTrace();
        }

        return funcionario;
    }
}
