package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.MdlPedidoProduto;

public class DaoPedidoProduto {

    private Connection minhaConexao;

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

    public MdlPedidoProduto recuperar(int index) {
        String sql = "select id_pedprod, quantidade_pedprod, valor_pedprod, total_pedprod, id_prod_pedprod, id_ped_pedprod from pedido_produto where id_pedprod = ?";

        try {
            PreparedStatement stp = minhaConexao.prepareStatement(sql);
            stp.setInt(1, index);
            ResultSet resultado = stp.executeQuery();

            MdlPedidoProduto obj = new MdlPedidoProduto();

            if (resultado.next()) {
                obj.setId(resultado.getInt("id_pedprod"));
                obj.setQuantidade(resultado.getDouble("quantidade_pedprod"));
                obj.setValor(resultado.getDouble("valor_pedprod"));
                obj.setTotal(resultado.getDouble("total_pedprod"));
                obj.setProduto(new DaoProduto().recuperar(resultado.getInt("id_prod_pedprod")));
                obj.setPedido(new DaoPedido().recuperar(resultado.getInt("id_ped_pedprod")));
            }
            return obj;

        } catch (SQLException ex) {
            Logger.getLogger(MdlPedidoProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<MdlPedidoProduto> recuperarTodos() {
        String sql = "select id_pedprod, quantidade_pedprod, valor_pedprod, total_pedprod, id_prod_pedprod, id_ped_pedprod from pedido_produto";
        ArrayList<MdlPedidoProduto> lista = new ArrayList();
        try {
            PreparedStatement stp = minhaConexao.prepareStatement(sql);
            ResultSet resultado = stp.executeQuery();

            if (resultado.next()) {
                MdlPedidoProduto obj = new MdlPedidoProduto();
                obj.setId(resultado.getInt("id_pedprod"));
                obj.setQuantidade(resultado.getDouble("quantidade_pedprod"));
                obj.setValor(resultado.getDouble("valor_pedprod"));
                obj.setTotal(resultado.getDouble("total_pedprod"));
                obj.setProduto(new DaoProduto().recuperar(resultado.getInt("id_prod_pedprod")));
                obj.setPedido(new DaoPedido().recuperar(resultado.getInt("id_ped_pedprod")));

                lista.add(obj);
            }
            return lista;

        } catch (SQLException ex) {
            Logger.getLogger(MdlPedidoProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
