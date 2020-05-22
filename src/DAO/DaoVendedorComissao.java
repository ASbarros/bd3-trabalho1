package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.MdlVendedorComissao;

public class DaoVendedorComissao {

    private EntityManager minhaConexao;

    public DaoVendedorComissao() {
        minhaConexao = FabricaConexao.getConexaoPADRAO();
    }

    public DaoVendedorComissao(EntityManager cnx) {
        minhaConexao = cnx;
    }

    public void inserir(MdlVendedorComissao comissao) {
        try {
            minhaConexao.persist(comissao);
            minhaConexao.getTransaction().commit();
        } catch (Exception e) {
            minhaConexao.getTransaction().rollback();
            System.err.println("Erro ao incluir pedido vendedor comissao: " + e.getMessage());
        }
    }

    public void atualizar(MdlVendedorComissao comissao) {
        try {
            minhaConexao.persist(comissao);
            minhaConexao.getTransaction().commit();
        } catch (Exception e) {
            minhaConexao.getTransaction().rollback();
            System.err.println("Erro ao atualizar vendedor comissao: " + e.getMessage());
        }
    }

    public void excluir(int pk) {
        try {
            minhaConexao.remove(pk);
            minhaConexao.getTransaction().commit();
        } catch (Exception e) {
            minhaConexao.getTransaction().rollback();
            System.err.println("Erro ao deletar vendedor comissao: " + e.getMessage());
        }
    }

    public void excluir(MdlVendedorComissao PedProd) {
        excluir(PedProd.getId());
    }

    public MdlVendedorComissao recuperar(int index) {
        try {
            return minhaConexao.find(MdlVendedorComissao.class, index);
        } catch (Exception ex) {
            Logger.getLogger(MdlVendedorComissao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<MdlVendedorComissao> recuperarTodos() {
        String sql = "select id_vendcom, percentual_vendcom, vlrcomissao_vendcom, id_vend_vendcom, id_ped_vendcom from vendedor_comissao";
        return recuperar(sql);
    }
    
    public ArrayList<MdlVendedorComissao> recuperar(String SQL) {
        ArrayList<MdlVendedorComissao> ListaCliente = new ArrayList<>();
        try {
            Query minhaQuery = minhaConexao.createQuery(SQL);
            ListaCliente = (ArrayList<MdlVendedorComissao>) minhaQuery.getResultList();
        } catch (Exception e) {
            System.out.println("Erro ao recuperar conta: " + e);
            minhaConexao.getTransaction().rollback();
        }
        minhaConexao.getTransaction().commit();
        return ListaCliente;
    }
}
