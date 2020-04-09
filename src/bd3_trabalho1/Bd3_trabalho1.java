/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd3_trabalho1;

import control.CntlCliente;
import control.CntlPedido;
import control.CntlPedidoProduto;
import control.CntlProduto;
import control.CntlVendedor;
import java.util.Date;

public class Bd3_trabalho1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("init");
        String dados[] = new String[6];
        String dados2[][] = new String[2][6];
        dados[0] = "1";
        dados[1] = "2.3";
        dados[2] = "5";
        dados[3] = "6";
        dados[4] = "1";
        dados[5] = "1";

        CntlPedidoProduto.salvar(dados);
        String d[] = CntlPedidoProduto.recuperar(1);
        dados2 = CntlPedidoProduto.recuperarTodos();
        System.out.println(dados2[0][2]);
        System.out.println(d[1]);
        System.out.println("termino");
    }

}
