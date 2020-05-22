package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
        minhaConexao.getTransaction().begin();
        try {
            minhaConexao.persist(cliente);
            minhaConexao.getTransaction().commit();
        } catch (Exception e) {
            minhaConexao.getTransaction().rollback();
            System.err.println("Erro ao incluir cliente: " + e.getMessage());
        }
        minhaConexao.close();
    }

    public void atualizar(MdlCliente cliente) {
        try {
            minhaConexao.persist(cliente);
            minhaConexao.getTransaction().commit();
        } catch (Exception e) {
            minhaConexao.getTransaction().rollback();
            System.err.println("Erro ao atualizar cliente: " + e.getMessage());
        }
    }

    public void excluir(int pk) {
        minhaConexao.getTransaction().begin();
        try {
            minhaConexao.remove(this.recuperar(pk));
            minhaConexao.getTransaction().commit();
        } catch (Exception e) {
            minhaConexao.getTransaction().rollback();
            System.err.println("Erro ao deletar cliente: " + e.getMessage());
        }
        minhaConexao.close();
    }

    public void excluir(MdlCliente cliente) {
        excluir(cliente.getId());
    }

    public MdlCliente recuperar(int index) {
        try {
            return minhaConexao.find(MdlCliente.class, index);
        } catch (Exception ex) {
            Logger.getLogger(MdlCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<MdlCliente> recuperarTodos() {
        String sql = "select c.id, c.nome, c.cpf, c.ultimaCompra from MdlCliente c";
        return recuperar(sql);
    }

    public ArrayList<MdlCliente> recuperar(String SQL) {
        minhaConexao.getTransaction().begin();
        ArrayList<MdlCliente> ListaCliente = new ArrayList<>();
        try {
            Query minhaQuery = minhaConexao.createQuery(SQL);
            List<Object[]> retornoBD = minhaQuery.getResultList();
            retornoBD.stream().map((objects) -> {
                MdlCliente obj = new MdlCliente();
                obj.setId(Integer.parseInt(objects[0].toString()));
                obj.setNome(objects[1].toString());
                obj.setCpf(objects[2].toString());
                
                return obj;
            }).forEachOrdered(ListaCliente::add);
        } catch (Exception e) {
            System.out.println("Erro ao recuperar conta: " + e);
            minhaConexao.getTransaction().rollback();
        }
        minhaConexao.getTransaction().commit();
       // minhaConexao.close();
        return ListaCliente;
    }
}
