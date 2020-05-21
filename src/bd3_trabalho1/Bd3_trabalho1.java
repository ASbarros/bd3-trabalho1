package bd3_trabalho1;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Bd3_trabalho1 {

    public static void main(String[] args) {
        EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("bd3_trabalho1PU");
        EntityManager gerente = fabrica.createEntityManager();

        gerente.getTransaction().begin();

        gerente.getTransaction().commit();

        gerente.close();

        System.out.println("Fim!");
        fabrica.close();
    }
}
