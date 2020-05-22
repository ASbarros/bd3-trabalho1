package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.MdlPedidoProduto;

public class DaoPedidoProduto {

    private EntityManager minhaConexao;

    public DaoPedidoProduto() {
        minhaConexao = FabricaConexao.getConexaoPADRAO();
    }

    public DaoPedidoProduto(EntityManager cnx) {
        minhaConexao = cnx;
    }

    public void inserir(MdlPedidoProduto PedProd) {
        try {
            minhaConexao.persist(PedProd);
            minhaConexao.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Erro ao incluir pedido produto: " + e.getMessage());
            minhaConexao.getTransaction().rollback();
        }
    }

    public void atualizar(MdlPedidoProduto PedProd) {
        try {
            minhaConexao.persist(PedProd);
            minhaConexao.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Erro ao atualizar pedido produto: " + e.getMessage());
            minhaConexao.getTransaction().rollback();
        }
    }

    public void excluir(int pk) {
        try {
            minhaConexao.remove(pk);
            minhaConexao.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Erro ao deletar pedido produto: " + e.getMessage());
            minhaConexao.getTransaction().rollback();
        }
    }

    public void excluir(MdlPedidoProduto PedProd) {
        excluir(PedProd.getId());
    }

    public MdlPedidoProduto recuperar(int index) {
        try {
            return minhaConexao.find(MdlPedidoProduto.class,index);
        } catch (Exception ex) {
            Logger.getLogger(MdlPedidoProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<MdlPedidoProduto> recuperarTodos() {
        String sql = "select id_pedprod, quantidade_pedprod, valor_pedprod, total_pedprod, id_prod_pedprod, id_ped_pedprod from pedido_produto";
        
        return recuperar(sql);
    }
    
    public ArrayList<MdlPedidoProduto> recuperar(String SQL) {
        ArrayList<MdlPedidoProduto> ListaCliente = new ArrayList<>();
        try {
            Query minhaQuery = minhaConexao.createQuery(SQL);
            ListaCliente = (ArrayList<MdlPedidoProduto>) minhaQuery.getResultList();
        } catch (Exception e) {
            System.out.println("Erro ao recuperar Pedido Produto: " + e);
            minhaConexao.getTransaction().rollback();
        }
        minhaConexao.getTransaction().commit();
        return ListaCliente;
    }
}
