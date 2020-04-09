package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.MdlVendedorComissao;

public class DaoVendedorComissao {
    private Connection minhaConexao;
    private String comandoSQL;

    public DaoVendedorComissao() {
        minhaConexao = FabricaConexao.getConexaoPADRAO();
    }

    public DaoVendedorComissao(Connection cnx) {
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

    public ArrayList<MdlVendedorComissao> recuperar(String SQL) {
        ArrayList<MdlVendedorComissao> listaVendedor = new ArrayList<>();

        try {
            Statement objSTM = minhaConexao.createStatement();
            objSTM.executeQuery(SQL);

            ResultSet objResultSet = objSTM.getResultSet();
            while (objResultSet.next()) {
                
                int idPedProd = objResultSet.getInt("id_vendcom");   
                DaoVendedorComissao daoComissao = new DaoVendedorComissao(minhaConexao);
                MdlVendedorComissao objCliente = daoComissao.Recupera(idPedProd);
                                
                int id = objResultSet.getInt("id_vendcom");
                double percentual = objResultSet.getDouble("percentual_vendcom");
                double valor = objResultSet.getDouble("vlrcomissao_vendcom");
                int idVendedor = objResultSet.getInt("id_vend_vendcom");
                int idPedido = objResultSet.getInt("id_ped_pedprod");
              
                MdlVendedorComissao obj = new MdlVendedorComissao(id, percentual, valor, idVendedor, idPedido);
                                    
                listaVendedor.add(obj);
            }

            objResultSet.close();
            objSTM.close();
        } catch (NumberFormatException | SQLException erro) {
            System.err.println("Erro ao Recuperar Objetos pedido produto: " + erro.getMessage());
           
        }
        return listaVendedor;
    }

    public ArrayList<MdlVendedorComissao> recuperaTodos() {
        return recuperar("select * from vendedor_comissao");
    }

    public MdlVendedorComissao Recupera(int pk) {
        ArrayList<MdlVendedorComissao> listaDeContas = recuperar("select * from vendedor_comissao where id_vendcom = " + pk);
        if (listaDeContas.size() > 0) {
            return listaDeContas.get(0);
        } else {
            return new MdlVendedorComissao();
        }
    }
}

