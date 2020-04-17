package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.MdlVendedor;

public class DaoVendedor {

    private Connection minhaConexao;

    public DaoVendedor() {
        minhaConexao = FabricaConexao.getConexaoPADRAO();
    }

    public DaoVendedor(Connection cnx) {
        minhaConexao = cnx;
    }

    public void inserir(MdlVendedor vendedor) {
        PreparedStatement comandoSQL;

        String sql = "insert into vendedor (nome_vend, percentual_vend) values (?,?);";

        try {
            comandoSQL = minhaConexao.prepareStatement(sql);

            comandoSQL.setString(1, vendedor.getNome());
            comandoSQL.setDouble(2, vendedor.getPercentual());

            comandoSQL.executeUpdate();
            comandoSQL.close();
        } catch (SQLException e) {
            System.err.println("Erro ao incluir vendedor: " + e.getMessage());
        }
    }

    public void atualizar(MdlVendedor vendedor) {
        PreparedStatement comandoSQL;

        String sql = "update vendedor set nome_vend = ?, percentual_vend = ? where id_vend = ?;";

        try {
            comandoSQL = minhaConexao.prepareStatement(sql);

            comandoSQL.setString(1, vendedor.getNome());
            comandoSQL.setDouble(2, vendedor.getPercentual());
            comandoSQL.setInt(3, vendedor.getId());

            comandoSQL.executeUpdate();
            comandoSQL.close();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar vendedor: " + e.getMessage());
        }
    }

    public void excluir(int pk) {
        PreparedStatement comandoSQL;

        String sql = "delete from vendedor where id_vend = ?";

        try {
            comandoSQL = minhaConexao.prepareStatement(sql);

            comandoSQL.setInt(1, pk);

            comandoSQL.executeUpdate();
            comandoSQL.close();
        } catch (SQLException e) {
            System.err.println("Erro ao deletar vendedor: " + e.getMessage());
        }
    }

    public void excluir(MdlVendedor vendedor) {
        excluir(vendedor.getId());
    }

    public MdlVendedor recuperar(int index) {
        String sql = "select id_vend, nome_vend, percentual_vend from vendedor where id_vend = ?";

        try {
            PreparedStatement stp = minhaConexao.prepareStatement(sql);
            stp.setInt(1, index);
            ResultSet resultado = stp.executeQuery();

            MdlVendedor obj = new MdlVendedor();

            if (resultado.next()) {
                obj.setId(Integer.parseInt(resultado.getString("id_vend")));
                obj.setNome(resultado.getString("nome_vend"));
                obj.setPercentual(resultado.getDouble("percentual_vend"));
            }
            return obj;

        } catch (SQLException ex) {
            Logger.getLogger(MdlVendedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<MdlVendedor> recuperarTodos() {
        String sql = "select id_vend, nome_vend, percentual_vend from vendedor";
        ArrayList<MdlVendedor> lista = new ArrayList();
        try {
            PreparedStatement stp = minhaConexao.prepareStatement(sql);
            ResultSet resultado = stp.executeQuery();

            while (resultado.next()) {
                MdlVendedor obj = new MdlVendedor();
                obj.setId(Integer.parseInt(resultado.getString("id_vend")));
                obj.setNome(resultado.getString("nome_vend"));
                obj.setPercentual(resultado.getDouble("percentual_vend"));
                lista.add(obj);
            }
            return lista;

        } catch (SQLException ex) {
            Logger.getLogger(MdlVendedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
