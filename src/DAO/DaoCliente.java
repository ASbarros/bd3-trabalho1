package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.MdlCliente;

public class DaoCliente {

    private EntityManager minhaConexao;
    // private String comandoSQL;

    public DaoCliente() {
        minhaConexao = FabricaConexao.getConexaoPADRAO();
    }

    public DaoCliente(EntityManager cnx) {
        minhaConexao = cnx;
    }

    public void inserir(MdlCliente cliente) {
        try {
            minhaConexao.persist(cliente);
            minhaConexao.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Erro ao incluir cliente: " + e.getMessage());
        }
    }

    public void atualizar(MdlCliente cliente) {
        try {
            minhaConexao.persist(cliente);
            minhaConexao.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Erro ao atualizar cliente: " + e.getMessage());
        }
    }

    public void excluir(int pk) {
        try {
            minhaConexao.remove(pk);
            minhaConexao.getTransaction().commit();

        } catch (Exception e) {
            System.err.println("Erro ao deletar cliente: " + e.getMessage());
        }

    }

    public void excluir(MdlCliente cliente) {
        excluir(cliente.getId());
    }

    public MdlCliente recuperar(int index) {
        String sql = "select c.id_cli, c.nome_cli, c.cpf_cli, c.ultcomp_cli from cliente c where c.id_cli = ?";

        try {
            return minhaConexao.find(MdlCliente.class, index);
        } catch (Exception ex) {
            Logger.getLogger(MdlCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<MdlCliente> recuperarTodos() {
        String sql = "select c.id_cli, c.nome_cli, c.cpf_cli, c.ultcomp_cli from cliente c";
        return recuperar(sql);
    }
    
    public ArrayList<MdlCliente> recuperar(String SQL) {
        ArrayList<MdlCliente> ListaCliente = new ArrayList<>();
        try {
            Query minhaQuery = minhaConexao.createQuery(SQL);
            ListaCliente = (ArrayList<MdlCliente>) minhaQuery.getResultList();
        } catch (Exception e) {
            System.out.println("Erro ao recuperar conta: " + e);
            minhaConexao.getTransaction().rollback();
        }
        minhaConexao.getTransaction().commit();
        return ListaCliente;
    }
}
