package bd3_trabalho1;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import visao.*;

public class Bd3_trabalho1 {

    public static void main(String[] args) {
        
        //CrudCliente tela = new CrudCliente(2);
        //CrudProduto tela = new CrudProduto(2);
        //CrudVendedor tela = new CrudVendedor(2);
        CrudPedido tela = new CrudPedido();
        //CrudListaProduto tela = new CrudListaProduto();
        tela.setVisible(true);
        
        System.out.println("Fim!");
    }
}
