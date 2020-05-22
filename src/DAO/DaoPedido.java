package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.MdlPedido;

public class DaoPedido {

    private EntityManager minhaConexao;

    public DaoPedido() {
        minhaConexao = FabricaConexao.getConexaoPADRAO();
    }

    public DaoPedido(EntityManager cnx) {
        minhaConexao = cnx;
    }

    public void inserir(MdlPedido pedido) {
        try {
            minhaConexao.getTransaction().begin();
            minhaConexao.persist(pedido);
            minhaConexao.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Erro ao incluir pedido: " + e.getMessage());
            minhaConexao.getTransaction().rollback();
        }
    }

    public void atualizar(MdlPedido pedido) {
        try {
            minhaConexao.persist(pedido);
            minhaConexao.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Erro ao atualizar pedido: " + e.getMessage());
            minhaConexao.getTransaction().rollback();
        }
    }

    public void excluir(int pk) {
        try {
            minhaConexao.remove(pk);
            minhaConexao.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Erro ao deletar pedido: " + e.getMessage());
            minhaConexao.getTransaction().rollback();

        }
    }

    public void excluir(MdlPedido pedido) {
        excluir(pedido.getId());
    }

    public MdlPedido recuperar(int index) {
        String sql = "select p.id, p.data, p.observacao, p.cliente_id, p.vendedor_id from pedido p where p.id = ?";

        try {
            minhaConexao.getTransaction().begin();
            MdlPedido obj = minhaConexao.find(MdlPedido.class, index);
            minhaConexao.getTransaction().commit();
            return obj;
        } catch (Exception ex) {
            Logger.getLogger(MdlPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<MdlPedido> recuperarTodos() {
        String sql = "select p.id_ped, p.data_ped, p.observacao_pedido, p.id_cli_ped, p.id_vend_ped from pedido p";
        return recuperar(sql);
    }

    public String recuperarUltimo(int idCliente) {
        String sql = "select max(p.data_ped) as data_ped from pedido p where p.id_cli_ped = ?";

        try {
            Query minhaQuery = minhaConexao.createQuery(sql);
            ArrayList<MdlPedido> lista = (ArrayList<MdlPedido>) minhaQuery.getResultList();
            return lista.toString();

        } catch (Exception ex) {
            Logger.getLogger(MdlPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String recuperarUltimo() {
        String sql = "select max(p.id) as id from MdlPedido p";
        try {
            MdlPedido obj = new MdlPedido();
            Query minhaQuery = minhaConexao.createQuery(sql);
            //Object[] retornoBD = minhaQuery.getSingleResult();
            return String.valueOf(minhaQuery.getSingleResult());
            // obj.setId(Integer.parseInt(objects[0]);

        } catch (NumberFormatException e) {
            System.out.println("Erro ao recuperar pedido: " + e);
        }
        return null;
    }

    public ArrayList<MdlPedido> recuperar(String SQL) {
        ArrayList<MdlPedido> Lista = new ArrayList<>();
        try {
            Query minhaQuery = minhaConexao.createQuery(SQL);
            Lista = (ArrayList<MdlPedido>) minhaQuery.getResultList();
        } catch (Exception e) {
            System.out.println("Erro ao recuperar pedido: " + e);
        }
        return Lista;
    }
}
