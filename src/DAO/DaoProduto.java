package dao;

import java.sql.Connection;
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
        minhaConexao.getTransaction().begin();
        try {
            minhaConexao.persist(produto);
            minhaConexao.getTransaction().commit();
        } catch (Exception e) {
            minhaConexao.getTransaction().rollback();
            System.err.println("Erro ao incluir produto: " + e.getMessage());
        }
        minhaConexao.close();
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
        minhaConexao.getTransaction().begin();
        try {
            minhaConexao.remove(this.recuperar(pk));
            minhaConexao.getTransaction().commit();
        } catch (Exception e) {
            minhaConexao.getTransaction().rollback();
            System.err.println("Erro ao deletar produto: " + e.getMessage());
        }
        minhaConexao.close();
    }

    public void excluir(MdlProduto produto) {
        excluir(produto.getId());
    }

    public MdlProduto recuperar(int index) {
        try {
            return minhaConexao.find(MdlProduto.class, index);
        } catch (Exception e) {
            Logger.getLogger(MdlProduto.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    public ArrayList<MdlProduto> recuperarTodos() {
        String sql = "select p.id, p.descricao, p.saldo, p.unidade, p.valor from MdlProduto p";
        return recuperar(sql);
    }

    public ArrayList<MdlProduto> recuperar(String SQL) {
        ArrayList<MdlProduto> ListaCliente = new ArrayList<>();
        minhaConexao.getTransaction().begin();
        try {
            Query minhaQuery = minhaConexao.createQuery(SQL);
            List<Object[]> retornoBD = minhaQuery.getResultList();
            retornoBD.stream().map((objects) -> {
                MdlProduto obj = new MdlProduto();
                obj.setId(Integer.parseInt(objects[0].toString()));
                obj.setDescricao(objects[1].toString());
                obj.setSaldo(Double.parseDouble(objects[2].toString()));
                obj.setUnidade(objects[3].toString());
                obj.setValor(Double.parseDouble(objects[4].toString()));
                return obj;
            }).forEachOrdered(ListaCliente::add);
        } catch (Exception e) {
            System.out.println("Erro ao recuperar produto: " + e);
            minhaConexao.getTransaction().rollback();
        }
        minhaConexao.getTransaction().commit();
        return ListaCliente;
    }
}
