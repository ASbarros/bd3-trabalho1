package facade;

import control.*;
import dao.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OperacaoPedido {

    public static void gavarPedido(String codPedido, String Observacao, String idCliente, String idVendedor, String dadosPedidosProdutos[][], String dadosPedidosMovimento[][], String codComissao) {
        Connection minhaConexao = FabricaConexao.getConexaoCUSTOMIZADA();
        double totalPedido = 0;
        
        try {
            // salva em pedidos
            String dadosPedidos[] = new String[5];

            dadosPedidos[0] = codPedido;
            dadosPedidos[2] = Observacao;
            dadosPedidos[3] = idCliente;
            dadosPedidos[4] = idVendedor;

            CntlPedido.salvar(dadosPedidos);

            // salva em pedido_produto
            for (String[] dadosPedidosProduto : dadosPedidosProdutos) {
                totalPedido = totalPedido + Double.parseDouble(dadosPedidosProduto[3]);
                CntlPedidoProduto.salvar(dadosPedidosProduto);
                
                //se saida
                if(dadosPedidosMovimento[0][1].equals("s")){
                    String[] produto = CntlProduto.recuperar(Integer.valueOf(dadosPedidosProduto[4]));
                    produto[2] = String.valueOf(Double.parseDouble(produto[2])-Double.parseDouble(dadosPedidosProduto[1]));
                }else{//se entrada
                    String[] produto = CntlProduto.recuperar(Integer.valueOf(dadosPedidosProduto[4]));
                    produto[2] = String.valueOf(Double.parseDouble(produto[2])-Double.parseDouble(dadosPedidosProduto[1]));
                }
            }

            // Salva produto movimento
            for (String[] dadosPedidosMovimento1 : dadosPedidosMovimento) {
                CntlProdutoMovimento.salvar(dadosPedidosMovimento1);
            }

            //Salva comissão do vendedor
            String[] vendedor = CntlVendedor.recuperar(Integer.valueOf(idVendedor));//dados do vendedor

            String[] dadosComissao = new String[5];
            dadosComissao[0] = codComissao;
            dadosComissao[1] = vendedor[2];
            dadosComissao[2] = String.valueOf(totalPedido * Double.valueOf(vendedor[2]));
            dadosComissao[3] = idVendedor;
            dadosComissao[4] = codPedido;

            CntlVendedorComissao.salvar(dadosComissao);

            //Atualiza a ultima compra
            String[] cliente = CntlCliente.recuperar(Integer.valueOf(idCliente));//dados do cliente
            cliente[3] = new Date().toString();//altera da data
            CntlCliente.salvar(cliente);//salva a data
                                    System.out.println("aki");

            //Finaliza o processo de gravar
            minhaConexao.commit();
            minhaConexao.close();
        } catch (SQLException e) {
            System.out.println("erro ao gravar pedido: " + e.getMessage());
            try {
                minhaConexao.rollback();
                minhaConexao.close();
            } catch (SQLException ex) {
                System.out.println("erro aqui:");
                Logger.getLogger(OperacaoPedido.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
