package bd3_trabalho1;

import control.CntlCliente;
import control.CntlPedido;
import control.CntlPedidoProduto;
import control.CntlProduto;
import control.CntlProdutoMovimento;
import control.CntlVendedor;
import control.CntlVendedorComissao;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Bd3_trabalho1 {
    public static void main(String[] args) {
        System.out.println("init");
        String dados[] = new String[6];
        String dados2[][] = new String[2][6];
        dados[0] = "0";
        dados[1] = "S";
        dados[2] = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
        dados[3] = "teste";
        dados[4] = "1";

        CntlProdutoMovimento.salvar(dados);

        String d[] = CntlProdutoMovimento.recuperar(1);
        dados2 = CntlProdutoMovimento.recuperarTodos();
        System.out.println(dados2[0][2]);
        System.out.println(d[1]);
        System.out.println("termino");
    }

}
