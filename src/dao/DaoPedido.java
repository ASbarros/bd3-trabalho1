package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.MdlPedido;

public class DaoPedido {
    
    private Connection minhaConexao;
    private String comandoSQL;

    public DaoPedido() {
        minhaConexao = FabricaConexao.getConexaoPADRAO();
    }

    public DaoPedido(Connection cnx) {
        minhaConexao = cnx;
    }

    public void inserir(MdlPedido pedido) {
        PreparedStatement comandoSQL;

        String sql = "insert into pedido (data_ped, observacao_pedido, id_cli_ped, id_vend_ped) values (?, ?, ?, ?)";

        try {
            comandoSQL = minhaConexao.prepareStatement(sql);

            
            comandoSQL.setDate(1, new java.sql.Date(pedido.getData().getTime().getTime()));
            comandoSQL.setString(2, pedido.getObservacao());
            comandoSQL.setInt(3, pedido.getCliente().getId());
            comandoSQL.setInt(4, pedido.getVendedor().getId());
            
            comandoSQL.executeUpdate();
            comandoSQL.close();
        } catch (SQLException e) {
            System.err.println("Erro ao incluir pedido: " + e.getMessage());
        }
    }

    public void atualizar(MdlPedido pedido) {
        PreparedStatement comandoSQL;

        String sql = "update pedido set data_ped = ?, observacao_pedido = ?, id_cli_ped = ?, id_vend_ped = ? where id_ped = ?";

        try {
            comandoSQL = minhaConexao.prepareStatement(sql);

            comandoSQL.setDate(1, new java.sql.Date(pedido.getData().getTime().getTime()));
            comandoSQL.setString(2, pedido.getObservacao());
            comandoSQL.setInt(3, pedido.getCliente().getId());
            comandoSQL.setInt(4, pedido.getVendedor().getId());
            comandoSQL.setInt(5, pedido.getId());

            comandoSQL.executeUpdate();
            comandoSQL.close();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar pedido: " + e.getMessage());
        }
    }

    public void excluir(int pk) {
        PreparedStatement comandoSQL;

        String sql = "delete from pedido where id_ped = ?";

        try {
            comandoSQL = minhaConexao.prepareStatement(sql);

            comandoSQL.setInt(1, pk);

            comandoSQL.executeUpdate();
            comandoSQL.close();
        } catch (SQLException e) {
            System.err.println("Erro ao deletar pedido: " + e.getMessage());
        }
    }

    public void excluir(MdlPedido pedido) {
        excluir(pedido.getId());
    }

    public ArrayList<MdlPedido> recuperar(String SQL) {
        ArrayList<MdlPedido> listaCliente = new ArrayList<>();

        try {
            Statement objSTM = minhaConexao.createStatement();
            objSTM.executeQuery(SQL);

            ResultSet objResultSet = objSTM.getResultSet();
            while (objResultSet.next()) {
                            
                int idPedido = objResultSet.getInt("id_ped");   
                DaoPedido daoPedido = new DaoPedido(minhaConexao);
                MdlPedido objpedido = daoPedido.Recupera(idPedido);
                
                int id = objResultSet.getInt("id_ped");
                java.util.Date data = objResultSet.getDate("data_ped");
                String observacao = objResultSet.getString("observacao_pedido");
                int id_cliente = objResultSet.getInt("id_cli_ped");
                int id_pedido = objResultSet.getInt("id_vend_ped");
                
                MdlPedido obj = new MdlPedido(id, data, observacao, id_cliente, id_pedido);
                                    
                listaCliente.add(obj);
            }

            objResultSet.close();
            objSTM.close();
        } catch (NumberFormatException | SQLException erro) {
            System.err.println("Erro ao Recuperar Objetos pedido: " + erro.getMessage());
           
        }
        return listaCliente;
    }

    public ArrayList<MdlPedido> recuperaTodos() {
        return recuperar("select * from pedido");
    }

    public MdlPedido Recupera(int pk) {
        ArrayList<MdlPedido> listaPedidos = recuperar("select * from pedido where id_ped = " + pk);
        if (listaPedidos.size() > 0) {
            return listaPedidos.get(0);
        } else {
            return new MdlPedido();
        }
    }
}
