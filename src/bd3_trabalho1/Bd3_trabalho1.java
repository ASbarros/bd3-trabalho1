/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd3_trabalho1;

import control.CntlCliente;
import control.CntlProduto;
import control.CntlVendedor;

/**
 *
 * @author hetikos
 */
public class Bd3_trabalho1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("init");
        String dados[] = new String[5];
        String dados2[][] = new String[2][5];
        dados[0] = "1";
        dados[1] = "anderson";
        dados[2] = "0.25";
        dados[3] = "kg";
        dados[4] = "0.10";

        CntlVendedor.salvar(dados);
        String d[] = CntlVendedor.recuperar(1);
        dados2 = CntlVendedor.recuperarTodos();
        System.out.println(dados2[0][2]);
        System.out.println(d[1]);
        System.out.println("termino");
    }

}
