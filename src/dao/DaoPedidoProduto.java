package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.MdlPedidoProduto;

public class DaoPedidoProduto {
    
    private Connection minhaConexao;
    private String comandoSQL;

    public DaoPedidoProduto() {
        minhaConexao = FabricaConexao.getConexaoPADRAO();
    }

    public DaoPedidoProduto(Connection cnx) {
        minhaConexao = cnx;
    }

    public void inserir(MdlPedidoProduto PedProd) {
        PreparedStatement comandoSQL;

        String sql = "insert into pedido_produto (quantidade_pedprod, valor_pedprod, total_pedprod, id_prod_pedprod, id_ped_pedprod) values (?, ?, ?, ?, ?);";

        try {
            comandoSQL = minhaConexao.prepareStatement(sql);

            comandoSQL.setDouble(1, PedProd.getQuantidade());
            comandoSQL.setDouble(2, PedProd.getValor());
            comandoSQL.setDouble(3, PedProd.getTotal());
            comandoSQL.setInt(4, PedProd.getProduto().getId());
            comandoSQL.setInt(5, PedProd.getPedido().getId());
           
            comandoSQL.executeUpdate();
            comandoSQL.close();
        } catch (SQLException e) {
            System.err.println("Erro ao incluir pedido produto: " + e.getMessage());
        }
    }

    public void atualizar(MdlPedidoProduto PedProd) {
        PreparedStatement comandoSQL;

        String sql = "update pedido_produto set quantidade_pedprod = ?, valor_pedprod = ?, total_pedprod = ?, id_prod_pedprod = ?, id_ped_pedprod  = ? where id_pedprod = ?";

        try {
            comandoSQL = minhaConexao.prepareStatement(sql);

            comandoSQL.setDouble(1, PedProd.getQuantidade());
            comandoSQL.setDouble(2, PedProd.getValor());
            comandoSQL.setDouble(3, PedProd.getTotal());
            comandoSQL.setInt(4, PedProd.getProduto().getId());
            comandoSQL.setInt(5, PedProd.getPedido().getId());
            comandoSQL.setInt(6, PedProd.getId());

            comandoSQL.executeUpdate();
            comandoSQL.close();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar pedido produto: " + e.getMessage());
        }
    }

    public void excluir(int pk) {
        PreparedStatement comandoSQL;

        String sql = "delete from pedido_produto where id_pedprod = ?";

        try {
            comandoSQL = minhaConexao.prepareStatement(sql);

            comandoSQL.setInt(1, pk);

            comandoSQL.executeUpdate();
            comandoSQL.close();
        } catch (SQLException e) {
            System.err.println("Erro ao deletar pedido produto: " + e.getMessage());
        }
    }

    public void excluir(MdlPedidoProduto PedProd) {
        excluir(PedProd.getId());
    }

    public ArrayList<MdlPedidoProduto> recuperar(String SQL) {
        ArrayList<MdlPedidoProduto> listaVendedor = new ArrayList<>();

        try {
            Statement objSTM = minhaConexao.createStatement();
            objSTM.executeQuery(SQL);

            ResultSet objResultSet = objSTM.getResultSet();
            while (objResultSet.next()) {

                
                //, , , , 
                
                int idPedProd = objResultSet.getInt("id_pedprod");   
                DaoPedidoProduto daoPedidoProduto = new DaoPedidoProduto(minhaConexao);
                MdlPedidoProduto objCliente = daoPedidoProduto.Recupera(idPedProd);
                                
                int id = objResultSet.getInt("id_pedprod");
                double quantidade = objResultSet.getDouble("quantidade_pedprod");
                double valor = objResultSet.getDouble("valor_pedprod");
                double total = objResultSet.getDouble("total_pedprod");
                int idProduto = objResultSet.getInt("id_prod_pedprod");
                int idPedido = objResultSet.getInt("id_ped_pedprod");
              
                MdlPedidoProduto obj = new MdlPedidoProduto(id, quantidade, valor, total, idProduto, idPedido);
                                    
                listaVendedor.add(obj);
            }

            objResultSet.close();
            objSTM.close();
        } catch (NumberFormatException | SQLException erro) {
            System.err.println("Erro ao Recuperar Objetos pedido produto: " + erro.getMessage());
           
        }
        return listaVendedor;
    }

    public ArrayList<MdlPedidoProduto> recuperaTodos() {
        return recuperar("select * from pedido_produto");
    }

    public MdlPedidoProduto Recupera(int pk) {
        ArrayList<MdlPedidoProduto> listaDeContas = recuperar("select * from pedido_produto where id_pedprod = " + pk);
        if (listaDeContas.size() > 0) {
            return listaDeContas.get(0);
        } else {
            return new MdlPedidoProduto();
        }
    }
}
