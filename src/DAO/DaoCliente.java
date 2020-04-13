package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.MdlCliente;

public class DaoCliente {

    private Connection minhaConexao;
    // private String comandoSQL;

    public DaoCliente() {
        minhaConexao = FabricaConexao.getConexaoPADRAO();
    }

    public DaoCliente(Connection cnx) {
        minhaConexao = cnx;
    }

    public void inserir(MdlCliente cliente) {
        PreparedStatement comandoSQL;

        String sql = "insert into cliente (nome_cli, cpf_cli) values (?,?)";

        try {
            comandoSQL = minhaConexao.prepareStatement(sql);

            comandoSQL.setString(1, cliente.getNome());
            comandoSQL.setString(2, cliente.getCpf());
            // comandoSQL.setDate(3, new java.sql.Date(cliente.getUltimaCompra().getTime().getTime()));

            comandoSQL.executeUpdate();
            comandoSQL.close();
        } catch (SQLException e) {
            System.err.println("Erro ao incluir cliente: " + e.getMessage());
        }
    }

    public void atualizar(MdlCliente cliente) {
        PreparedStatement comandoSQL;

        String sql = "update cliente set nome_cli = ?, cpf_cli = ?, ultcomp_cli = ? where id_cli = ?";

        try {
            comandoSQL = minhaConexao.prepareStatement(sql);

            comandoSQL.setString(1, cliente.getNome());
            comandoSQL.setString(2, cliente.getCpf());
            //comandoSQL.setDate(3, new java.sql.Date(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(cliente.getUltimaCompra()).getTime()));
            comandoSQL.setDate(3, new Date(0));
            comandoSQL.setInt(4, cliente.getId());

            comandoSQL.executeUpdate();
            comandoSQL.close();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar cliente: " + e.getMessage());
        }
    }

    public void excluir(int pk) {
        PreparedStatement comandoSQL;

        String sql = "delete from cliente where id_cli = ?";

        try {
            comandoSQL = minhaConexao.prepareStatement(sql);

            comandoSQL.setInt(1, pk);

            comandoSQL.executeUpdate();
            comandoSQL.close();
        } catch (SQLException e) {
            System.err.println("Erro ao deletar cliente: " + e.getMessage());
        }
    }

    public void excluir(MdlCliente cliente) {
        excluir(cliente.getId());
    }

    public MdlCliente recuperar(int index) {
        String sql = "select id_cli, nome_cli, cpf_cli, ultcomp_cli from cliente where id_cli = ?";

        try {
            PreparedStatement stp = minhaConexao.prepareStatement(sql);
            stp.setInt(1, index);
            ResultSet resultado = stp.executeQuery();

            MdlCliente obj = new MdlCliente();

            if (resultado.next()) {
                obj.setId(Integer.parseInt(resultado.getString("id_cli")));
                obj.setNome(resultado.getString("nome_cli"));
                obj.setCpf(resultado.getString("cpf_cli"));
                // obj.setUltimaCompraString(resultado.getString("ultcomp_cli"));

            }
            return obj;

        } catch (SQLException ex) {
            Logger.getLogger(MdlCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<MdlCliente> recuperarTodos() {
        String sql = "select id_cli, nome_cli, cpf_cli, ultcomp_cli from cliente";
        ArrayList<MdlCliente> lista = new ArrayList();
        try {
            PreparedStatement stp = minhaConexao.prepareStatement(sql);
            ResultSet resultado = stp.executeQuery();

            while (resultado.next()) {
                MdlCliente obj = new MdlCliente();
                obj.setId(Integer.parseInt(resultado.getString("id_cli")));
                obj.setNome(resultado.getString("nome_cli"));
                obj.setCpf(resultado.getString("cpf_cli"));
                //obj.setUltimaCompra(resultado.getString("ultcomp_cli"));

                lista.add(obj);
            }
            return lista;

        } catch (SQLException ex) {
            Logger.getLogger(MdlCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
