package bd3_trabalho1;

import facade.OperacaoPedido;
import java.util.Date;

public class Bd3_trabalho1 {

    public static void main(String[] args) {
        String dadosPedidosMovimento[][] = new String[1][5];
        String dadosPedidosProdutos[][] = new String[1][6];
        dadosPedidosMovimento[0][0] = "0";
        dadosPedidosMovimento[0][1] = "s";
        //dadosPedidosMovimento[0][2] = new Date();
        dadosPedidosMovimento[0][3] = "teste";
        dadosPedidosMovimento[0][4] = "1";
        
        dadosPedidosProdutos[0][0] = "0";
        dadosPedidosProdutos[0][1] = "5.5";
        dadosPedidosProdutos[0][2] = "2";
        dadosPedidosProdutos[0][3] = "11";
        dadosPedidosProdutos[0][4] = "1";
        dadosPedidosProdutos[0][5] = "1";

        OperacaoPedido.gavarPedido("0", "iscas de frango", "1", "1", dadosPedidosProdutos, dadosPedidosMovimento, "0");
    }
}
