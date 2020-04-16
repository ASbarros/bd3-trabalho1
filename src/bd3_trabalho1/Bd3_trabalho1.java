package bd3_trabalho1;

import facade.OperacaoPedido;
import javax.swing.JFrame;
import visao.CrudCliente;

public class Bd3_trabalho1 {

    public static void main(String[] args) {
        CrudCliente tela = new CrudCliente(12);
        tela.setVisible(true);
    }
}