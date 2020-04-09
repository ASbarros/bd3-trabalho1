package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.MdlVendedor;

public class DaoVendedor {
    private Connection minhaConexao;
    private String comandoSQL;

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

    public ArrayList<MdlVendedor> recuperar(String SQL) {
        ArrayList<MdlVendedor> listaCliente = new ArrayList<>();

        try {
            Statement objSTM = minhaConexao.createStatement();
            objSTM.executeQuery(SQL);

            ResultSet objResultSet = objSTM.getResultSet();
            while (objResultSet.next()) {
                            
                int idVendedor = objResultSet.getInt("id_vend");   
                DaoVendedor daoVendedor = new DaoVendedor(minhaConexao);
                MdlVendedor objVendedor = daoVendedor.Recupera(idVendedor);
                                
                int id = objResultSet.getInt("id_vend");
                String nome = objResultSet.getString("nome_vend");
                double percentual = objResultSet.getDouble("percentual_vend");
              
                MdlVendedor obj = new MdlVendedor(id, nome, percentual);
                                    
                listaCliente.add(obj);
            }

            objResultSet.close();
            objSTM.close();
        } catch (NumberFormatException | SQLException erro) {
            System.err.println("Erro ao Recuperar Objetos vend: " + erro.getMessage());
           
        }
        return listaCliente;
    }

    public ArrayList<MdlVendedor> recuperaTodos() {
        return recuperar("select * from vendedor");
    }

    public MdlVendedor Recupera(int pk) {
        ArrayList<MdlVendedor> listaDeContas = recuperar("select * from vendedor where id_vend = " + pk);
        if (listaDeContas.size() > 0) {
            return listaDeContas.get(0);
        } else {
            return new MdlVendedor();
        }
    }
}
