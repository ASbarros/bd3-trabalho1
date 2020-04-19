package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.MdlProduto;

public class DaoProduto {

    private Connection minhaConexao;

    public DaoProduto() {
        minhaConexao = FabricaConexao.getConexaoPADRAO();
    }

    public DaoProduto(Connection cnx) {
        minhaConexao = cnx;
    }

    public void inserir(MdlProduto produto) {
        PreparedStatement comandoSQL;

        String sql = "insert into produto (descricao_prod, saldo_prod, unidade_prod, valor_prod) values (?,?,?,?);";
        try {
            comandoSQL = minhaConexao.prepareStatement(sql);

            comandoSQL.setString(1, produto.getDescricao());
            comandoSQL.setDouble(2, produto.getSaldo());
            comandoSQL.setString(3, produto.getUnidade());
            comandoSQL.setDouble(4, produto.getValor());

            comandoSQL.executeUpdate();
            comandoSQL.close();
        } catch (SQLException e) {
            System.err.println("Erro ao incluir produto: " + e.getMessage());
        }
    }

    public void atualizar(MdlProduto produto) {
        PreparedStatement comandoSQL;

        String sql = "update produto set descricao_prod = ?, saldo_prod = ?, unidade_prod = ?, valor_prod = ? where id_prod = ?;";

        try {
            comandoSQL = minhaConexao.prepareStatement(sql);

            comandoSQL.setString(1, produto.getDescricao());
            comandoSQL.setDouble(2, produto.getSaldo());
            comandoSQL.setString(3, produto.getUnidade());
            comandoSQL.setDouble(4, produto.getValor());
            comandoSQL.setInt(5, produto.getId());

            comandoSQL.executeUpdate();
            comandoSQL.close();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar produto: " + e.getMessage());
        }
    }

    public void excluir(int pk) {
        PreparedStatement comandoSQL;

        String sql = "delete from produto where id_prod = ?";

        try {
            comandoSQL = minhaConexao.prepareStatement(sql);

            comandoSQL.setInt(1, pk);

            comandoSQL.executeUpdate();
            comandoSQL.close();
        } catch (SQLException e) {
            System.err.println("Erro ao deletar produto: " + e.getMessage());
        }
    }

    public void excluir(MdlProduto produto) {
        excluir(produto.getId());
    }

    public MdlProduto recuperar(int index) {
        String sql = "select id_prod, descricao_prod, saldo_prod, unidade_prod, valor_prod from produto where id_prod = ?";

        try {
            PreparedStatement stp = minhaConexao.prepareStatement(sql);
            stp.setInt(1, index);
            ResultSet resultado = stp.executeQuery();

            MdlProduto obj = new MdlProduto();

            if (resultado.next()) {
                obj.setId(Integer.parseInt(resultado.getString("id_prod")));
                obj.setDescricao(resultado.getString("descricao_prod"));
                obj.setSaldo(resultado.getDouble("saldo_prod"));
                obj.setUnidade(resultado.getString("unidade_prod"));
                obj.setValor(Double.parseDouble(resultado.getString("valor_prod")));

            }
            return obj;

        } catch (SQLException ex) {
            Logger.getLogger(MdlProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<MdlProduto> recuperarTodos() {
        String sql = "select id_prod, descricao_prod, saldo_prod, unidade_prod, valor_prod from produto";
        ArrayList<MdlProduto> lista = new ArrayList();
        try {
            PreparedStatement stp = minhaConexao.prepareStatement(sql);
            ResultSet resultado = stp.executeQuery();

            while (resultado.next()) {
                MdlProduto obj = new MdlProduto();
                obj.setId(Integer.parseInt(resultado.getString("id_prod")));
                obj.setDescricao(resultado.getString("descricao_prod"));
                obj.setSaldo(resultado.getDouble("saldo_prod"));
                obj.setUnidade(resultado.getString("unidade_prod"));
                obj.setValor(resultado.getDouble("valor_prod"));
                lista.add(obj);
            }
            return lista;

        } catch (SQLException ex) {
            Logger.getLogger(MdlProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
