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
        PreparedStatement comandoSQL;

        String sql = "insert into vendedor_comissao (percentual_vendcom, vlrcomissao_vendcom, id_vend_vendcom, id_ped_vendcom) values (?, ?, ?, ?)";

        try {
            comandoSQL = minhaConexao.prepareStatement(sql);

            comandoSQL.setDouble(1, comissao.getPercentual());
            comandoSQL.setDouble(2, comissao.getValor());
            comandoSQL.setInt(3, comissao.getVendedor().getId());
            comandoSQL.setInt(4, comissao.getPedido().getId());

            comandoSQL.executeUpdate();
            comandoSQL.close();
        } catch (SQLException e) {
            System.err.println("Erro ao incluir pedido vendedor comissao: " + e.getMessage());
        }
    }

    public void atualizar(MdlVendedorComissao comissao) {
        PreparedStatement comandoSQL;

        String sql = "update vendedor_comissao set percentual_vendcom = ?, vlrcomissao_vendcom = ?, id_vend_vendcom = ?, id_ped_vendcom = ? where id_vendcom = ?";

        try {
            comandoSQL = minhaConexao.prepareStatement(sql);

            comandoSQL.setDouble(1, comissao.getPercentual());
            comandoSQL.setDouble(2, comissao.getValor());
            comandoSQL.setInt(3, comissao.getVendedor().getId());
            comandoSQL.setInt(4, comissao.getPedido().getId());
            comandoSQL.setInt(5, comissao.getId());

            comandoSQL.executeUpdate();
            comandoSQL.close();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar vendedor comissao: " + e.getMessage());
        }
    }

    public void excluir(int pk) {
        PreparedStatement comandoSQL;

        String sql = "delete from vendedor_comissao where id_vendcom = ?";

        try {
            comandoSQL = minhaConexao.prepareStatement(sql);

            comandoSQL.setInt(1, pk);

            comandoSQL.executeUpdate();
            comandoSQL.close();
        } catch (SQLException e) {
            System.err.println("Erro ao deletar vendedor comissao: " + e.getMessage());
        }
    }

    public void excluir(MdlVendedorComissao PedProd) {
        excluir(PedProd.getId());
    }

    public MdlVendedorComissao recuperar(int index) {
        String sql = "select id_vendcom, percentual_vendcom, vlrcomissao_vendcom, id_vend_vendcom, id_ped_vendcom from vendedor_comissao where id_vendcom = ?";

        try {
            PreparedStatement stp = minhaConexao.prepareStatement(sql);
            stp.setInt(1, index);
            ResultSet resultado = stp.executeQuery();

            MdlVendedorComissao obj = new MdlVendedorComissao();

            if (resultado.next()) {
                obj.setId(Integer.parseInt(resultado.getString("id_vendcom")));
                obj.setPercentual(resultado.getDouble("percentual_vendcom"));
                obj.setValor(resultado.getDouble("vlrcomissao_vendcom"));
                obj.setVendedor(new DaoVendedor().recuperar(resultado.getInt("id_vend_vendcom")));
                obj.setPedido(new DaoPedido().recuperar(resultado.getInt("id_ped_vendcom")));
            }
            return obj;

        } catch (SQLException ex) {
            Logger.getLogger(MdlVendedorComissao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<MdlVendedorComissao> recuperarTodos() {
        String sql = "select id_vendcom, percentual_vendcom, vlrcomissao_vendcom, id_vend_vendcom, id_ped_vendcom from vendedor_comissao";
        ArrayList<MdlVendedorComissao> lista = new ArrayList();
        try {
            PreparedStatement stp = minhaConexao.prepareStatement(sql);
            ResultSet resultado = stp.executeQuery();

            if (resultado.next()) {
                MdlVendedorComissao obj = new MdlVendedorComissao();
                obj.setId(Integer.parseInt(resultado.getString("id_vendcom")));
                obj.setPercentual(resultado.getDouble("percentual_vendcom"));
                obj.setValor(resultado.getDouble("vlrcomissao_vendcom"));
                obj.setVendedor(new DaoVendedor().recuperar(resultado.getInt("id_vend_vendcom")));
                obj.setPedido(new DaoPedido().recuperar(resultado.getInt("id_ped_vendcom")));
                lista.add(obj);
            }
            return lista;

        } catch (SQLException ex) {
            Logger.getLogger(MdlVendedorComissao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
