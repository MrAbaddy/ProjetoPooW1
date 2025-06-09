package br.mototech.oficina.dao;

import br.mototech.oficina.model.Funcionario;
import br.mototech.oficina.model.NotaServico;

import java.sql.*;
import java.util.ArrayList;

public class NotaServicoDAO {

    private FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

    private Connection conectar() throws SQLException, ClassNotFoundException {
        return ConexaoDB.conectarBancoPostgres();
    }

    public ArrayList<NotaServico> listar() {
        ArrayList<NotaServico> lista = new ArrayList<>();
        String sql = "SELECT * FROM nota_servico";

        try (Connection con = conectar();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                NotaServico nota = new NotaServico();
                nota.setId(rs.getInt("id"));
                nota.setCliente(rs.getString("cliente"));
                nota.setMoto(rs.getString("moto"));
                nota.setCilindradas(rs.getInt("cilindradas"));
                nota.setDescricao(rs.getString("descricao"));
                nota.setMaterial(rs.getString("material"));
                nota.setSituacao(rs.getString("situacao"));

                int funcId = rs.getInt("funcionario_id");
                Funcionario func = funcionarioDAO.buscar(funcId);
                nota.setFuncionario(func);

                lista.add(nota);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public NotaServico buscar(int id) {
        String sql = "SELECT * FROM nota_servico WHERE id = ?";
        NotaServico nota = null;

        try (Connection con = conectar();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    nota = new NotaServico();
                    nota.setId(rs.getInt("id"));
                    nota.setCliente(rs.getString("cliente"));
                    nota.setMoto(rs.getString("moto"));
                    nota.setCilindradas(rs.getInt("cilindradas"));
                    nota.setDescricao(rs.getString("descricao"));
                    nota.setMaterial(rs.getString("material"));
                    nota.setSituacao(rs.getString("situacao"));

                    int funcId = rs.getInt("funcionario_id");
                    Funcionario func = funcionarioDAO.buscar(funcId);
                    nota.setFuncionario(func);
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return nota;
    }

    public String inserir(NotaServico nota) {
        String sql = "INSERT INTO nota_servico (cliente, moto, cilindradas, descricao, material, funcionario_id, situacao) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = conectar();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, nota.getCliente());
            pst.setString(2, nota.getMoto());
            pst.setInt(3, nota.getCilindradas());
            pst.setString(4, nota.getDescricao());
            pst.setString(5, nota.getMaterial());
            pst.setInt(6, nota.getFuncionario().getId());
            pst.setString(7, nota.getSituacao());

            int rows = pst.executeUpdate();
            return (rows > 0) ? "Inserido com sucesso!" : "Erro ao inserir.";

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return "Erro ao inserir: " + e.getMessage();
        }
    }

    public String alterar(NotaServico nota) {
        String sql = "UPDATE nota_servico SET cliente = ?, moto = ?, cilindradas = ?, descricao = ?, material = ?, funcionario_id = ?, situacao = ? WHERE id = ?";

        try (Connection con = conectar();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, nota.getCliente());
            pst.setString(2, nota.getMoto());
            pst.setInt(3, nota.getCilindradas());
            pst.setString(4, nota.getDescricao());
            pst.setString(5, nota.getMaterial());
            pst.setInt(6, nota.getFuncionario().getId());
            pst.setString(7, nota.getSituacao());
            pst.setInt(8, nota.getId());

            int rows = pst.executeUpdate();
            return (rows > 0) ? "Alterado com sucesso!" : "Erro ao alterar.";

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return "Erro ao alterar: " + e.getMessage();
        }
    }

    public String excluir(int id) {
        String sql = "DELETE FROM nota_servico WHERE id = ?";

        try (Connection con = conectar();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);
            int rows = pst.executeUpdate();
            return (rows > 0) ? "Excluído com sucesso!" : "Erro ao excluir.";

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return "Erro ao excluir: " + e.getMessage();
        }
    }
}
