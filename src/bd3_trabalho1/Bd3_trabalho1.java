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
import control.CntlVendedorComissao;
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
        dados[1] = "0.10";
        dados[2] = "60";
        dados[3] = "1";
        dados[4] = "1";

        CntlVendedorComissao.salvar(dados);

        String d[] = CntlVendedorComissao.recuperar(1);
        dados2 = CntlVendedorComissao.recuperarTodos();
        System.out.println(dados2[0][2]);
        System.out.println(d[1]);
        System.out.println("termino");
    }

}
