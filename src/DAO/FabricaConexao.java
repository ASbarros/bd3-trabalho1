package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class FabricaConexao {

    private static final String STR_DRIVER = "com.mysql.cj.jdbc.Driver";  // definição de qual banco será utilizado
    private static final String DATABASE = "banco3-saulo-2020-1"; // Nome do banco de dados         
    private static final String IP = "localhost";  // ip de conexao
    private static final String STR_CON = "jdbc:mysql://" + IP + ":3306/" + DATABASE; // string de conexao com o banco de dados
    private static final String USER = "root"; // Nome do usuário
    private static final String PASSWORD = ""; // senha
    private static Connection objConexao = null;

    public FabricaConexao() {
        try{
            Class.forName(STR_DRIVER);
            objConexao = DriverManager.getConnection(STR_CON, USER, PASSWORD);
        }catch (ClassNotFoundException | SQLException e) {   
            String errorMsg = "Driver nao encontrado: "+e.getMessage();    
            System.err.println("ERRO NA CONSTRUÇÃO DA CONEXÃO: " + errorMsg);
        }   
    }
    
    public static Connection getConexaoPADRAO() {
        if (objConexao == null) {            
            FabricaConexao objGlobal = new FabricaConexao();                        
        }        
        return objConexao;
    }
    
    public static Connection getConexaoCUSTOMIZADA(){
        Connection cnx = null;
        try {
            Class.forName(STR_DRIVER);
            cnx = DriverManager.getConnection(STR_CON, USER, PASSWORD);
            
            cnx.setAutoCommit(false);
            cnx.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        } catch (ClassNotFoundException | SQLException ex) {
            System.err.println("ERRO AO GERAR NOVA CONEXAO " + ex.getMessage());
        }
        return cnx;
    }

}
