package br.mototech.oficina.dao;

import br.mototech.oficina.model.Funcionario;
import br.mototech.oficina.model.NotaServico;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NotaServicoDAO {

    private final JdbcTemplate jdbcTemplate;
    private final FuncionarioDAO funcionarioDAO;

    private final RowMapper<NotaServico> rowMapper;

    public NotaServicoDAO(JdbcTemplate jdbcTemplate, FuncionarioDAO funcionarioDAO) {
        this.jdbcTemplate = jdbcTemplate;
        this.funcionarioDAO = funcionarioDAO;

        this.rowMapper = (rs, rowNum) -> {
            NotaServico nota = new NotaServico();
            nota.setId(rs.getInt("id"));
            nota.setCliente(rs.getString("cliente"));
            nota.setMoto(rs.getString("moto"));
            nota.setCilindradas(rs.getInt("cilindradas"));
            nota.setDescricao(rs.getString("descricao"));
            nota.setMaterial(rs.getString("material"));
            nota.setSituacao(rs.getString("situacao"));

            int funcId = rs.getInt("funcionario_id");
            Funcionario func = this.funcionarioDAO.buscar(funcId);
            nota.setFuncionario(func);

            return nota;
        };
    }

    public List<NotaServico> listar() {
        String sql = "SELECT * FROM nota_servico";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public NotaServico buscar(int id) {
        String sql = "SELECT * FROM nota_servico WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, rowMapper, id);
        } catch (Exception e) {
            System.out.println("Erro ao buscar nota de serviço: " + e.getMessage());
            return null;
        }
    }

    public String inserir(NotaServico nota) {
        String sql = "INSERT INTO nota_servico (cliente, moto, cilindradas, descricao, material, funcionario_id, situacao) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            int rows = jdbcTemplate.update(sql,
                    nota.getCliente(),
                    nota.getMoto(),
                    nota.getCilindradas(),
                    nota.getDescricao(),
                    nota.getMaterial(),
                    nota.getFuncionario().getId(),
                    nota.getSituacao());
            return rows > 0 ? "Inserido com sucesso!" : "Erro ao inserir.";
        } catch (Exception e) {
            System.out.println("Erro ao inserir nota de serviço: " + e.getMessage());
            return "Erro ao inserir: " + e.getMessage();
        }
    }

    public String alterar(NotaServico nota) {
        String sql = "UPDATE nota_servico SET cliente = ?, moto = ?, cilindradas = ?, descricao = ?, material = ?, funcionario_id = ?, situacao = ? WHERE id = ?";
        try {
            int rows = jdbcTemplate.update(sql,
                    nota.getCliente(),
                    nota.getMoto(),
                    nota.getCilindradas(),
                    nota.getDescricao(),
                    nota.getMaterial(),
                    nota.getFuncionario().getId(),
                    nota.getSituacao(),
                    nota.getId());
            return rows > 0 ? "Alterado com sucesso!" : "Erro ao alterar.";
        } catch (Exception e) {
            System.out.println("Erro ao alterar nota de serviço: " + e.getMessage());
            return "Erro ao alterar: " + e.getMessage();
        }
    }

    public String excluir(int id) {
        String sql = "DELETE FROM nota_servico WHERE id = ?";
        try {
            int rows = jdbcTemplate.update(sql, id);
            return rows > 0 ? "Excluído com sucesso!" : "Erro ao excluir.";
        } catch (Exception e) {
            System.out.println("Erro ao excluir nota de serviço: " + e.getMessage());
            return "Erro ao excluir: " + e.getMessage();
        }
    }
}
