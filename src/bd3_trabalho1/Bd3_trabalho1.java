package bd3_trabalho1;

import facade.OperacaoPedido;

public class Bd3_trabalho1 {

    public static void main(String[] args) {
       /*
        String dadosPedidosMovimento[][] = new String[1][5];
        dadosPedidosMovimento[0][0] = "0";
        dadosPedidosMovimento[0][1] = "s";
        //dadosPedidosMovimento[0][2] = new Date();
        dadosPedidosMovimento[0][3] = "Andre Teixeira";
        dadosPedidosMovimento[0][4] = "1";
        
        String dadosPedidosProdutos[][] = new String[1][6];
        dadosPedidosProdutos[0][0] = "0";
        dadosPedidosProdutos[0][1] = "5.5";
        dadosPedidosProdutos[0][2] = "2";
        dadosPedidosProdutos[0][3] = "11";
        dadosPedidosProdutos[0][4] = "1";
        dadosPedidosProdutos[0][5] = "1";

        OperacaoPedido.gavarPedido("0", "Anderson Barros", "1", "1", dadosPedidosProdutos, dadosPedidosMovimento, "0");
        */
        // /*
        
        
        String dadosPedidosMovimento[][] = new String[1][5];
        dadosPedidosMovimento[0][0] = "22";
        dadosPedidosMovimento[0][1] = "s";
        //dadosPedidosMovimento[0][2] = new Date();
        dadosPedidosMovimento[0][3] = "Andre Teixeira";
        dadosPedidosMovimento[0][4] = "1";
        
        String dadosPedidosProdutos[][] = new String[1][6];
        dadosPedidosProdutos[0][0] = "24";
        dadosPedidosProdutos[0][1] = "5.5";
        dadosPedidosProdutos[0][2] = "2";
        dadosPedidosProdutos[0][3] = "11";
        dadosPedidosProdutos[0][4] = "1";
        dadosPedidosProdutos[0][5] = "1";
        
        OperacaoPedido.excluir("60", dadosPedidosProdutos, dadosPedidosMovimento, "22");
        // */
    }
}