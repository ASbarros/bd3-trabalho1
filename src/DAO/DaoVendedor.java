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
import model.MdlVendedor;

public class DaoVendedor {

    private EntityManager minhaConexao;

    public DaoVendedor() {
        minhaConexao = FabricaConexao.getConexaoPADRAO();
    }

    public DaoVendedor(EntityManager cnx) {
        minhaConexao = cnx;
    }

    public void inserir(MdlVendedor vendedor) {
        try {
            minhaConexao.persist(vendedor);
            minhaConexao.getTransaction().commit();
        } catch (Exception e) {
            minhaConexao.getTransaction().rollback();
            System.err.println("Erro ao incluir vendedor: " + e.getMessage());
        }
    }

    public void atualizar(MdlVendedor vendedor) {
         try {
            minhaConexao.persist(vendedor);
            minhaConexao.getTransaction().commit();
        } catch (Exception e) {
            minhaConexao.getTransaction().rollback();
            System.err.println("Erro ao atualizar vendedor: " + e.getMessage());
        }
    }

    public void excluir(int pk) {
        try {
            minhaConexao.remove(pk);
            minhaConexao.getTransaction().commit();
        } catch (Exception e) {
            minhaConexao.getTransaction().rollback();
            System.err.println("Erro ao deletar vendedor: " + e.getMessage());
        }
    }

    public void excluir(MdlVendedor vendedor) {
        excluir(vendedor.getId());
    }

    public MdlVendedor recuperar(int index) {
        try {
            return minhaConexao.find(MdlVendedor.class, index);
        } catch (Exception ex) {
            Logger.getLogger(MdlVendedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<MdlVendedor> recuperarTodos() {
        String sql = "select id_vend, nome_vend, percentual_vend from vendedor";
        return recuperar(sql);
    }
    
    public ArrayList<MdlVendedor> recuperar(String SQL) {
        ArrayList<MdlVendedor> ListaCliente = new ArrayList<>();
        try {
            Query minhaQuery = minhaConexao.createQuery(SQL);
            ListaCliente = (ArrayList<MdlVendedor>) minhaQuery.getResultList();
        } catch (Exception e) {
            System.out.println("Erro ao recuperar conta: " + e);
            minhaConexao.getTransaction().rollback();
        }
        minhaConexao.getTransaction().commit();
        return ListaCliente;
    }
}
