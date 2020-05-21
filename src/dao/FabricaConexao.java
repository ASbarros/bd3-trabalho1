package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class FabricaConexao {
/*
    private static final String STR_DRIVER = "org.gjt.mm.mysql.Driver";  // definição de qual banco será utilizado
    private static final String DATABASE = "bancosaulo"; // Nome do banco de dados         
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
*/
    
    
    private static EntityManager gerenciador;
    private static EntityManagerFactory fabrica;

    public FabricaConexao() {
        try{
        fabrica = Persistence.createEntityManagerFactory("bd3_trabalho1PU");
        gerenciador = fabrica.createEntityManager();
        //gerenciador.getTransaction().begin();
        }catch(Exception e){
            System.err.println("ERRO NA CONSTRUÇÃO DA CONEXÃO: " + e);
        }
    }

    public static EntityManager getConexaoPADRAO() {
        return gerenciador;
    }

    public static EntityManager getConexaoCUSTOMIZADA() {
        EntityManagerFactory fabricaCustomizada = Persistence.createEntityManagerFactory("remoto7PU");
        EntityManager gerenciadorCostomizado = fabricaCustomizada.createEntityManager();
        gerenciadorCostomizado.getTransaction().begin();
        return gerenciadorCostomizado;
    }

    public static void fechaConexão() {
        gerenciador.close();
    }
    public static void fechaFabrica() {
        fabrica.close();
    }
}
