package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.MdlProdutoMovimento;

public class DaoProdutoMovimento {
    private Connection minhaConexao;
    private String comandoSQL;

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
            comandoSQL.setDate(2, new java.sql.Date(movimento.getData().getTime().getTime()));
            comandoSQL.setString(3, movimento.getDescricao());
            comandoSQL.setInt(4, movimento.getProduto().getId());
            
            comandoSQL.executeUpdate();
            comandoSQL.close();
        } catch (SQLException e) {
            System.err.println("Erro ao incluir produto movimento: " + e.getMessage());
        }
    }

    public void atualizar(MdlProdutoMovimento movimento) {
        PreparedStatement comandoSQL;

        String sql = "update produto_movimento set tipo_prodmov = ?, data_prodmov = ?, descricao_prodmov = ?, id_prod_prodmov = ? where id_prodmov = ?";

        try {
            comandoSQL = minhaConexao.prepareStatement(sql);

            comandoSQL.setString(1, movimento.getTipo());
            comandoSQL.setDate(2, new java.sql.Date(movimento.getData().getTime().getTime()));
            comandoSQL.setString(3, movimento.getDescricao());
            comandoSQL.setInt(4, movimento.getProduto().getId());
            comandoSQL.setInt(5, movimento.getId());

            comandoSQL.executeUpdate();
            comandoSQL.close();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar produto movimento: " + e.getMessage());
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

    public ArrayList<MdlProdutoMovimento> recuperar(String SQL) {
        ArrayList<MdlProdutoMovimento> listaMovimento = new ArrayList<>();

        try {
            Statement objSTM = minhaConexao.createStatement();
            objSTM.executeQuery(SQL);

            ResultSet objResultSet = objSTM.getResultSet();
            while (objResultSet.next()) {
                            
                int idMovimento = objResultSet.getInt("id_prodmov");   
                DaoProdutoMovimento daoMovimento = new DaoProdutoMovimento(minhaConexao);
                MdlProdutoMovimento objMovimento = daoMovimento.Recupera(idMovimento);
                
                int id = objResultSet.getInt("id_prodmov");
                String tipo = objResultSet.getString("tipo_prodmov");
                java.util.Date data = objResultSet.getDate("data_prodmov");
                String descricao = objResultSet.getString("descricao_prodmov");
                int idProduto = objResultSet.getInt("id_prod_prodmov");
                
                MdlProdutoMovimento obj = new MdlProdutoMovimento(id, tipo, data, descricao, idProduto);
                                    
                listaMovimento.add(obj);
            }

            objResultSet.close();
            objSTM.close();
        } catch (NumberFormatException | SQLException erro) {
            System.err.println("Erro ao Recuperar Objetos produto movimento: " + erro.getMessage());
           
        }
        return listaMovimento;
    }

    public ArrayList<MdlProdutoMovimento> recuperaTodos() {
        return recuperar("select * from produto_movimento");
    }

    public MdlProdutoMovimento Recupera(int pk) {
        ArrayList<MdlProdutoMovimento> listaPedidos = recuperar("select * from produto_movimento where id_prodmov = " + pk);
        if (listaPedidos.size() > 0) {
            return listaPedidos.get(0);
        } else {
            return new MdlProdutoMovimento();
        }
    }
}
