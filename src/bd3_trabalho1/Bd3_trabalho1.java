package bd3_trabalho1;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import visao.CrudCliente;

public class Bd3_trabalho1 {

    public static void main(String[] args) {
        EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("bd3_trabalho1PU");
        
        CrudCliente tela = new CrudCliente();
        tela.setVisible(true);
        
        System.out.println("Fim!");
        fabrica.close();
    }
}
