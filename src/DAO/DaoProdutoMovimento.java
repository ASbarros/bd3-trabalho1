package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.MdlProdutoMovimento;

public class DaoProdutoMovimento {

    private EntityManager minhaConexao;

    public DaoProdutoMovimento() {
        minhaConexao = FabricaConexao.getConexaoPADRAO();
    }

    public DaoProdutoMovimento(EntityManager cnx) {
        minhaConexao = cnx;
    }

    public void inserir(MdlProdutoMovimento movimento) {
        try {
            minhaConexao.persist(movimento);
            minhaConexao.getTransaction().commit();
        } catch (Exception e) {
            minhaConexao.getTransaction().rollback();
            System.err.println("Erro ao incluir produto movimento: " + e.getMessage());
        }
    }

    public void atualizar(MdlProdutoMovimento movimento) {
        try {
            minhaConexao.persist(movimento);
            minhaConexao.getTransaction().commit();
        } catch (Exception e) {
            minhaConexao.getTransaction().rollback();
            System.err.println("Erro ao atualizar produto movimento: " + e.getMessage());
        }
    }

    public void excluir(int pk) {
        try {
            minhaConexao.remove(pk);
            minhaConexao.getTransaction().commit();
        } catch (Exception e) {
            minhaConexao.getTransaction().rollback();
            System.err.println("Erro ao deletar produto movimento: " + e.getMessage());
        }
    }

    public void excluir(MdlProdutoMovimento movimento) {
        excluir(movimento.getId());
    }

    public MdlProdutoMovimento recuperar(int index) {
        try {
            return minhaConexao.find(MdlProdutoMovimento.class, index);
        } catch (Exception ex) {
            Logger.getLogger(MdlProdutoMovimento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<MdlProdutoMovimento> recuperarTodos() {
        String sql = "select id_prodmov, tipo_prodmov, data_prodmov, descricao_prodmov, id_prod_prodmov from produto_movimento";
        return null;
    }
    
    public ArrayList<MdlProdutoMovimento> recuperar(String SQL) {
        ArrayList<MdlProdutoMovimento> ListaCliente = new ArrayList<>();
        try {
            Query minhaQuery = minhaConexao.createQuery(SQL);
            ListaCliente = (ArrayList<MdlProdutoMovimento>) minhaQuery.getResultList();
        } catch (Exception e) {
            System.out.println("Erro ao recuperar MdlProdutoMovimento: " + e);
            minhaConexao.getTransaction().rollback();
        }
        minhaConexao.getTransaction().commit();
        return ListaCliente;
    }
}
