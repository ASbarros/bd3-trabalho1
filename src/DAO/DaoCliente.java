package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
            comandoSQL.setDate(3, new java.sql.Date(cliente.getUltimaCompra().getTime().getTime()));
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

    /*
    public ArrayList<MdlCliente> recuperar(String SQL) {
        ArrayList<MdlCliente> listaCliente = new ArrayList<>();

        try {
            Statement objSTM = minhaConexao.createStatement();
            objSTM.executeQuery(SQL);

            ResultSet objResultSet = objSTM.getResultSet();
            while (objResultSet.next()) {

                int idCliente = objResultSet.getInt("id_cli");
                DaoCliente daoCliente = new DaoCliente(minhaConexao);
                MdlCliente objCliente = daoCliente.Recupera(idCliente);

                int id = objResultSet.getInt("id_cli");
                String nome = objResultSet.getString("nome_cli");
                String cpf = objResultSet.getString("cpf_cli");
                java.util.Date ultComp = objResultSet.getDate("data");

                MdlCliente obj = new MdlCliente(id, nome, cpf, ultComp);

                listaCliente.add(obj);
            }

            objResultSet.close();
            objSTM.close();
        } catch (NumberFormatException | SQLException erro) {
            System.err.println("Erro ao Recuperar Objetos cliente: " + erro.getMessage());

        }
        return listaCliente;
    }

    public ArrayList<MdlCliente> recuperaTodos() {
        return recuperar("select * from cliente");
    }

    public MdlCliente Recupera(int pk) {
        ArrayList<MdlCliente> listaDeContas = recuperar("select * from Cliente where id_cli = " + pk);
        if (listaDeContas.size() > 0) {
            return listaDeContas.get(0);
        } else {
            return new MdlCliente();
        }
    }
     */
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

            if (resultado.next()) {
                MdlCliente obj = new MdlCliente();
                obj.setId(Integer.parseInt(resultado.getString("id_cli")));
                obj.setNome(resultado.getString("nome_cli"));
                obj.setCpf(resultado.getString("cpf_cli"));
                // obj.setUltimaCompraString(resultado.getString("ultcomp_cli"));
                lista.add(obj);
            }
            return lista;

        } catch (SQLException ex) {
            Logger.getLogger(MdlCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
