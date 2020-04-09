package control;

import dao.DaoPedido;
import java.util.ArrayList;
import model.MdlPedido;

public class CntlPedido {
 public static void salvar(String dados[]) {
        MdlPedido pedido = MdlPedido.parsePedido(dados);
        DaoPedido dao = new DaoPedido();

        if (pedido.getId() == 0) {
            dao.inserir(pedido);
        } else {
            dao.atualizar(pedido);
        }
    }

    public static void deletar(int id) {
        DaoPedido dao = new DaoPedido();
        dao.excluir(id);
    }

    public static String[] recuperar(int id) {
        DaoPedido dao = new DaoPedido();
        MdlPedido pedido = dao.recuperar(id);
        return pedido.toArray();
    }

    public static String[][] recuperarTodos() {
        DaoPedido dao = new DaoPedido();
        ArrayList<MdlPedido> lista;
        lista = dao.recuperarTodos();
        String dados[][] = new String[lista.size()][4];

        for (int i = 0; i < lista.size(); i++) {
            MdlPedido pedido = lista.get(i);
            dados[i] = pedido.toArray();
        }
        return dados;
    }
}
