/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd3_trabalho1;

import control.CntlCliente;

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
        String dados[] = new String[4];
        String dados2[][]=new String[2][4];
        dados[0] = "0";
        dados[1] = "123456789";
        dados[2] = "barros";
        dados[3] = "09/04/2020";

        //CntlCliente.salvar(dados);
        String d[] = CntlCliente.recuperar(1);
        dados2 = CntlCliente.recuperarTodos();
        System.out.println(dados2[0][0]);
        System.out.println("termino");
    }

}
