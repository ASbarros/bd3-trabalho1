package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.MdlProdutoMovimento;

public class DaoProdutoMovimento {
    private Connection minhaConexao;

    public DaoProdutoMovimento() {
        minhaConexao = FabricaConexao.getConexaoPADRAO();
    }

    public DaoProdutoMovimento(Connection cnx) {
        minhaConexao = cnx;
    }

    public void inserir(MdlProdutoMovimento movimento) {
        PreparedStatement comandoSQL;

        String sql = "insert into produto_movimento (tipo_prodmov, data_prodmov, descricao_prodmov, id_prod_prodmov) values (?, ?, ?, ?)";

        try {
            comandoSQL = minhaConexao.prepareStatement(sql);

            
            comandoSQL.setString(1, movimento.getTipo());
            comandoSQL.setDate(2, new java.sql.Date(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(movimento.getData()).getTime()));
            comandoSQL.setString(3, movimento.getDescricao());
            comandoSQL.setInt(4, movimento.getProduto().getId());
            
            comandoSQL.executeUpdate();
            comandoSQL.close();
        } catch (SQLException e) {
            System.err.println("Erro ao incluir produto movimento: " + e.getMessage());
        } catch (ParseException ex) {
            Logger.getLogger(DaoProdutoMovimento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void atualizar(MdlProdutoMovimento movimento) {
        PreparedStatement comandoSQL;

        String sql = "update produto_movimento set tipo_prodmov = ?, data_prodmov = ?, descricao_prodmov = ?, id_prod_prodmov = ? where id_prodmov = ?";

        try {
            comandoSQL = minhaConexao.prepareStatement(sql);

            comandoSQL.setString(1, movimento.getTipo());
            comandoSQL.setDate(2, new java.sql.Date(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(movimento.getData()).getTime()));
            comandoSQL.setString(3, movimento.getDescricao());
            comandoSQL.setInt(4, movimento.getProduto().getId());
            comandoSQL.setInt(5, movimento.getId());

            comandoSQL.executeUpdate();
            comandoSQL.close();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar produto movimento: " + e.getMessage());
        } catch (ParseException ex) {
            Logger.getLogger(DaoProdutoMovimento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void excluir(int pk) {
        PreparedStatement comandoSQL;

        String sql = "delete from produto_movimento where id_prodmov = ?";

        try {
            comandoSQL = minhaConexao.prepareStatement(sql);

            comandoSQL.setInt(1, pk);

            comandoSQL.executeUpdate();
            comandoSQL.close();
        } catch (SQLException e) {
            System.err.println("Erro ao deletar produto movimento: " + e.getMessage());
        }
    }

    public void excluir(MdlProdutoMovimento movimento) {
        excluir(movimento.getId());
    }
     public MdlProdutoMovimento recuperar(int index) {
        String sql = "select id_prodmov, tipo_prodmov, data_prodmov, descricao_prodmov, id_prod_prodmov from produto_movimento where id_prodmov = ?";

        try {
            PreparedStatement stp = minhaConexao.prepareStatement(sql);
            stp.setInt(1, index);
            ResultSet resultado = stp.executeQuery();

            MdlProdutoMovimento obj = new MdlProdutoMovimento();

            if (resultado.next()) {
                obj.setId(resultado.getInt("id_prodmov"));
                obj.setTipo(resultado.getString("tipo_prodmov"));
                obj.setData(resultado.getDate("data_prodmov"));
                obj.setDescricao(resultado.getString("descricao_prodmov"));
                obj.setProduto(new DaoProduto().recuperar(resultado.getInt("id_prod_prodmov")));
            }
            return obj;

        } catch (SQLException ex) {
            Logger.getLogger(MdlProdutoMovimento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<MdlProdutoMovimento> recuperarTodos() {
        String sql = "select id_prodmov, tipo_prodmov, data_prodmov, descricao_prodmov, id_prod_prodmov from produto_movimento";
        ArrayList<MdlProdutoMovimento> lista = new ArrayList();
        try {
            PreparedStatement stp = minhaConexao.prepareStatement(sql);
            ResultSet resultado = stp.executeQuery();

            if (resultado.next()) {
                MdlProdutoMovimento obj = new MdlProdutoMovimento();
                obj.setId(resultado.getInt("id_prodmov"));
                obj.setTipo(resultado.getString("tipo_prodmov"));
                obj.setData(resultado.getDate("data_prodmov"));
                obj.setDescricao(resultado.getString("descricao_prodmov"));
                obj.setProduto(new DaoProduto().recuperar(resultado.getInt("id_prod_prodmov")));

                lista.add(obj);
            }
            return lista;

        } catch (SQLException ex) {
            Logger.getLogger(MdlProdutoMovimento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}