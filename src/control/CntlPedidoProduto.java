package control;

import dao.DaoPedidoProduto;
import java.util.ArrayList;
import model.MdlPedidoProduto;

public class CntlPedidoProduto {
    public static void salvar(String dados[]) {
        MdlPedidoProduto obj = MdlPedidoProduto.parsePedidoProduto(dados);
        DaoPedidoProduto dao = new DaoPedidoProduto();

        if (obj.getId() == 0) {
            dao.inserir(obj);
        } else {
            dao.atualizar(obj);
        }
    }

    public static void deletar(int id) {
        DaoPedidoProduto dao = new DaoPedidoProduto();
        dao.excluir(id);
    }

    public static String[] recuperar(int id) {
        DaoPedidoProduto dao = new DaoPedidoProduto();
        MdlPedidoProduto pedido = dao.recuperar(id);
        return pedido.toArray();
    }

    public static String[][] recuperarTodos() {
        DaoPedidoProduto dao = new DaoPedidoProduto();
        ArrayList<MdlPedidoProduto> lista;
        lista = dao.recuperarTodos();
        String dados[][] = new String[lista.size()][4];

        for (int i = 0; i < lista.size(); i++) {
            MdlPedidoProduto pedido = lista.get(i);
            dados[i] = pedido.toArray();
        }
        return dados;
    }
}
