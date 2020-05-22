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
import model.MdlCliente;
import model.MdlProduto;

public class DaoProduto {

    private EntityManager minhaConexao;

    public DaoProduto() {
        minhaConexao = FabricaConexao.getConexaoPADRAO();
    }

    public DaoProduto(EntityManager cnx) {
        minhaConexao = cnx;
    }

    public void inserir(MdlProduto produto) {
        try {
            minhaConexao.persist(produto);
            minhaConexao.getTransaction().commit();
        } catch (Exception e) {
            minhaConexao.getTransaction().rollback();
            System.err.println("Erro ao incluir produto: " + e.getMessage());
        }
    }

    public void atualizar(MdlProduto produto) {
        try {
            minhaConexao.persist(produto);
            minhaConexao.getTransaction().commit();
        } catch (Exception e) {
            minhaConexao.getTransaction().rollback();
            System.err.println("Erro ao atualizar produto: " + e.getMessage());
        }
    }

    public void excluir(int pk) {
        try {
            minhaConexao.remove(pk);
            minhaConexao.getTransaction().commit();
        } catch (Exception e) {
            minhaConexao.getTransaction().rollback();
            System.err.println("Erro ao deletar produto: " + e.getMessage());
        }
    }

    public void excluir(MdlProduto produto) {
        excluir(produto.getId());
    }

    public MdlProduto recuperar(int index) {
        try {
            minhaConexao.find(MdlProduto.class, index);
        } catch (Exception e) {
            Logger.getLogger(MdlProduto.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    public ArrayList<MdlProduto> recuperarTodos() {
        String sql = "select id_prod, descricao_prod, saldo_prod, unidade_prod, valor_prod from produto";
        return recuperar(sql);
    }
    
    public ArrayList<MdlProduto> recuperar(String SQL) {
        ArrayList<MdlProduto> ListaCliente = new ArrayList<>();
        try {
            Query minhaQuery = minhaConexao.createQuery(SQL);
            ListaCliente = (ArrayList<MdlProduto>) minhaQuery.getResultList();
        } catch (Exception e) {
            System.out.println("Erro ao recuperar produto: " + e);
            minhaConexao.getTransaction().rollback();
        }
        minhaConexao.getTransaction().commit();
        return ListaCliente;
    }
}
