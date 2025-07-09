package br.mototech.oficina.dao;

import br.mototech.oficina.model.Funcionario;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FuncionarioDAO {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Funcionario> rowMapper = new BeanPropertyRowMapper<>(Funcionario.class);

    public FuncionarioDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Funcionario> listar() {
        String sql = "SELECT * FROM Funcionario";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public String inserir(Funcionario funcionario) {
        String sql = "INSERT INTO Funcionario(nome, cnh, cargo) VALUES (?, ?, ?)";
        try {
            jdbcTemplate.update(sql, funcionario.getNome(), funcionario.getCnh(), funcionario.getCargo());
            return "Funcionário inserido com sucesso!";
        } catch (Exception e) {
            System.out.println("Erro ao inserir funcionário: " + e.getMessage());
            return "Erro ao inserir funcionário.";
        }
    }

    public String alterar(Funcionario funcionario) {
        String sql = "UPDATE Funcionario SET nome = ?, cnh = ?, cargo = ? WHERE id = ?";
        try {
            jdbcTemplate.update(sql, funcionario.getNome(), funcionario.getCnh(), funcionario.getCargo(), funcionario.getId());
            return "Funcionário alterado com sucesso!";
        } catch (Exception e) {
            System.out.println("Erro ao alterar funcionário: " + e.getMessage());
            return "Erro ao alterar funcionário.";
        }
    }

    public boolean excluir(int id) {
        String sql = "DELETE FROM Funcionario WHERE id = ?";
        try {
            int rows = jdbcTemplate.update(sql, id);
            return rows > 0;
        } catch (Exception e) {
            System.out.println("Erro ao excluir funcionário: " + e.getMessage());
            return false;
        }
    }

    public Funcionario buscar(int id) {
        String sql = "SELECT * FROM Funcionario WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, rowMapper, id);
        } catch (Exception e) {
            System.out.println("Erro ao buscar funcionário: " + e.getMessage());
            return null;
        }
    }
}
