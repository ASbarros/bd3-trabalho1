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
import model.MdlPedido;

public class DaoPedido {

    private Connection minhaConexao;

    public DaoPedido() {
        minhaConexao = FabricaConexao.getConexaoPADRAO();
    }

    public DaoPedido(Connection cnx) {
        minhaConexao = cnx;
    }

    public void inserir(MdlPedido pedido) {
        PreparedStatement comandoSQL;
        String sql = "insert into pedido (data_ped, observacao_pedido, id_cli_ped, id_vend_ped) values (?, ?, ?, ?)";

        try {
            comandoSQL = minhaConexao.prepareStatement(sql);
            comandoSQL.setDate(1, new java.sql.Date(new SimpleDateFormat("dd/MM/yyyy").parse(pedido.getData()).getTime()));
            comandoSQL.setString(2, pedido.getObservacao());
            comandoSQL.setInt(3, pedido.getCliente().getId());
            comandoSQL.setInt(4, pedido.getVendedor().getId());

            comandoSQL.executeUpdate();
            comandoSQL.close();
        } catch (SQLException e) {
            System.err.println("Erro ao incluir pedido: " + e.getMessage());
        } catch (ParseException ex) {
            Logger.getLogger(DaoPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void atualizar(MdlPedido pedido) {
        PreparedStatement comandoSQL;

        String sql = "update pedido set data_ped = ?, observacao_pedido = ?, id_cli_ped = ?, id_vend_ped = ? where id_ped = ?";

        try {
            comandoSQL = minhaConexao.prepareStatement(sql);

            comandoSQL.setDate(1, new java.sql.Date(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(pedido.getData()).getTime()));
            comandoSQL.setString(2, pedido.getObservacao());
            comandoSQL.setInt(3, pedido.getCliente().getId());
            comandoSQL.setInt(4, pedido.getVendedor().getId());
            comandoSQL.setInt(5, pedido.getId());

            comandoSQL.executeUpdate();
            comandoSQL.close();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar pedido: " + e.getMessage());
        } catch (ParseException ex) {
            Logger.getLogger(DaoPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void excluir(int pk) {
        PreparedStatement comandoSQL;

        String sql = "delete from pedido where id_ped = ?";

        try {
            comandoSQL = minhaConexao.prepareStatement(sql);

            comandoSQL.setInt(1, pk);

            comandoSQL.executeUpdate();
            comandoSQL.close();
        } catch (SQLException e) {
            System.err.println("Erro ao deletar pedido: " + e.getMessage());
        }
    }

    public void excluir(MdlPedido pedido) {
        excluir(pedido.getId());
    }

    public MdlPedido recuperar(int index) {
        String sql = "select id_ped, data_ped, observacao_pedido, id_cli_ped, id_vend_ped from pedido where id_ped = ?";

        try {
            PreparedStatement stp = minhaConexao.prepareStatement(sql);
            stp.setInt(1, index);
            ResultSet resultado = stp.executeQuery();

            MdlPedido obj = new MdlPedido();

            if (resultado.next()) {
                obj.setId(Integer.parseInt(resultado.getString("id_ped")));
                obj.setData(resultado.getDate("data_ped"));
                obj.setObservacao(resultado.getString("observacao_pedido"));
                obj.setCliente(new DaoCliente().recuperar(resultado.getInt("id_cli_ped")));
                obj.setVendedor(new DaoVendedor().recuperar(resultado.getInt("id_vend_ped")));

            }
            return obj;

        } catch (SQLException ex) {
            Logger.getLogger(MdlPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<MdlPedido> recuperarTodos() {
        String sql = "select id_ped, data_ped, observacao_pedido, id_cli_ped, id_vend_ped from pedido";
        ArrayList<MdlPedido> lista = new ArrayList();
        try {
            PreparedStatement stp = minhaConexao.prepareStatement(sql);
            ResultSet resultado = stp.executeQuery();

            if (resultado.next()) {
                MdlPedido obj = new MdlPedido();
                obj.setId(Integer.parseInt(resultado.getString("id_ped")));
                obj.setData(resultado.getDate("data_ped"));
                obj.setObservacao(resultado.getString("observacao_pedido"));
                obj.setCliente(new DaoCliente().recuperar(resultado.getInt("id_cli_ped")));
                obj.setVendedor(new DaoVendedor().recuperar(resultado.getInt("id_vend_ped")));
                lista.add(obj);
            }
            return lista;

        } catch (SQLException ex) {
            Logger.getLogger(MdlPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
