package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.MdlProduto;

public class DaoProduto {
    
    private Connection minhaConexao;
    private String comandoSQL;

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

    public ArrayList<MdlProduto> recuperar(String SQL){
        ArrayList<MdlProduto> listaProduto = new ArrayList<>();

        try {
            Statement objSTM = minhaConexao.createStatement();
            objSTM.executeQuery(SQL);

            ResultSet objResultSet = objSTM.getResultSet();
            while (objResultSet.next()) {
                            
                int idProduto = objResultSet.getInt("id_prod");   
                DaoProduto daoProduto = new DaoProduto(minhaConexao);
                MdlProduto objProduto = daoProduto.Recupera(idProduto);
                
                int id = objResultSet.getInt("id_prod");
                String descricao = objResultSet.getString("descricao_prod");
                double saldo = objResultSet.getDouble("saldo_prod");
                String unidade = objResultSet.getString("unidade_prod");
                double valor = objResultSet.getDouble("valor_prod");
              
                MdlProduto obj = new MdlProduto(id, descricao, saldo, unidade, valor);
                                    
                listaProduto.add(obj);
            }

            objResultSet.close();
            objSTM.close();
        } catch (NumberFormatException | SQLException erro) {
            System.err.println("Erro ao Recuperar Objetos cliente: " + erro.getMessage());
           
        }
        return listaProduto;
    }

    public ArrayList<MdlProduto> recuperaTodos() {
        return recuperar("select * from produto");
    }

    public MdlProduto Recupera(int pk) {
        ArrayList<MdlProduto> listaDeContas = recuperar("select * from produto where id_prod = " + pk);
        if (listaDeContas.size() > 0) {
            return listaDeContas.get(0);
        } else {
            return new MdlProduto();
        }
    }
}
