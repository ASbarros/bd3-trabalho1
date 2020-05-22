package facade;

import control.*;
import dao.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

public class OperacaoPedido {

    public static void gavarPedido(String codPedido, String Observacao, String idCliente, String idVendedor, String dadosPedidosProdutos[][], String dadosPedidosMovimento[][], String codComissao) {
        EntityManager minhaConexao = FabricaConexao.getConexaoCUSTOMIZADA();
        double totalPedido = 0;

        try {
            // salva em pedidos
            String dadosPedidos[] = new String[5];

            dadosPedidos[0] = codPedido;
            dadosPedidos[2] = Observacao;
            dadosPedidos[3] = idCliente;
            dadosPedidos[4] = idVendedor;

            CntlPedido.salvar(dadosPedidos);

            dadosPedidos[0] = CntlPedido.recuperarUltimo();//atualiza o codigo do pedido

            // salva em pedido_produto
            for (String[] dadosPedidosProduto : dadosPedidosProdutos) {
                totalPedido = totalPedido + Double.parseDouble(dadosPedidosProduto[3]);
                dadosPedidosProduto[5] = dadosPedidos[0];

                //se saida
                String[] produto = CntlProduto.recuperar(Integer.valueOf(dadosPedidosProduto[4]));
                if (dadosPedidosMovimento[0][1].equals("s")) {
                    produto[2] = String.valueOf(Double.parseDouble(produto[2]) - Double.parseDouble(dadosPedidosProduto[1]));
                } else {
                    //se entrada
                    produto[2] = String.valueOf(Double.parseDouble(produto[2]) + Double.parseDouble(dadosPedidosProduto[1]));
                }
                CntlProduto.salvar(produto);
                CntlPedidoProduto.salvar(dadosPedidosProduto);
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
            dadosComissao[4] = dadosPedidos[0];

            CntlVendedorComissao.salvar(dadosComissao);

            //Atualiza a ultima compra
            String[] cliente = CntlCliente.recuperar(Integer.valueOf(idCliente));//dados do cliente
            //cliente[3] = new Date().toString();//altera da data
            CntlCliente.salvar(cliente);//salva a data

            //Finaliza o processo de gravar
            minhaConexao.getTransaction().commit();
            minhaConexao.close();
        } catch (Exception e) {
            System.out.println("erro ao gravar pedido: " + e.getMessage());
            minhaConexao.getTransaction().rollback();
            minhaConexao.close();
        }
    }

    public static void excluir(String codPedido, String dadosPedidosProdutos[][], String dadosPedidosMovimento[][], String codComissao) {
        EntityManager minhaConexao = FabricaConexao.getConexaoCUSTOMIZADA();
        String[] produtoMovimento = CntlProdutoMovimento.recuperar(Integer.parseInt(dadosPedidosMovimento[0][0]));//recupera movimento para exemplo

        try {
            //recupera os dados dos pedidos
            String dadosPedido[] = CntlPedido.recuperar(Integer.parseInt(codPedido));

            //exclui os registros de produto movimento
            for (String[] dadosPedidosMovimento1 : dadosPedidosMovimento) {
                CntlProdutoMovimento.deletar(Integer.parseInt(dadosPedidosMovimento1[0]));
            }

            // exclui os registros pedido_produto
            for (String[] dadosPedidosProduto : dadosPedidosProdutos) {
                //se saida de produtos
                String[] produto = CntlProduto.recuperar(Integer.valueOf(dadosPedidosProduto[4]));
                if (produtoMovimento[1].equals("s")) {
                    produto[2] = String.valueOf(Double.parseDouble(produto[2]) + Double.parseDouble(dadosPedidosProduto[1]));
                } else {
                    //se entrada
                    produto[2] = String.valueOf(Double.parseDouble(produto[2]) - Double.parseDouble(dadosPedidosProduto[1]));
                }
                //atualiza a quantidade de produtos no banco
                CntlProduto.salvar(produto);
                //exclui o registro do pedido produto
                CntlPedidoProduto.deletar(Integer.parseInt(dadosPedidosProduto[0]));
            }

            //Exclui comissao vendedor
            CntlVendedorComissao.deletar(Integer.valueOf(codComissao));//dados da comissao do vendedor

            //exclui do registro produtos pedido
            for (String dadosPedidosProduto[] : dadosPedidosProdutos) {
                CntlProduto.deletar(Integer.parseInt(dadosPedidosProduto[0]));
            }

            //exclui pedido
            CntlPedido.deletar(Integer.valueOf(dadosPedido[0]));

            //Atualiza a ultima compra
            String[] cliente = CntlCliente.recuperar(Integer.valueOf(dadosPedido[3]));//dados do cliente
            cliente[3] = CntlPedido.recuperarUltimo(Integer.valueOf(cliente[0]));//altera da data

            CntlCliente.salvar(cliente);//salva a data

            //Finaliza o processo de gravar
                minhaConexao.getTransaction().commit();
            minhaConexao.close();
        } catch (Exception e) {
            System.out.println("erro ao excluir pedido: " + e.getMessage());
            minhaConexao.getTransaction().rollback();
            minhaConexao.close();
        }
    }
}
