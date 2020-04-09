package control;

import dao.DaoProdutoMovimento;
import java.util.ArrayList;
import model.MdlProdutoMovimento;

public class CntlProdutoMovimento {
    public static void salvar(String dados[]) {
        MdlProdutoMovimento obj = MdlProdutoMovimento.parseProdutoMovimento(dados);
        DaoProdutoMovimento dao = new DaoProdutoMovimento();

        if (obj.getId() == 0) {
            dao.inserir(obj);
        } else {
            dao.atualizar(obj);
        }
    }

    public static void deletar(int id) {
        DaoProdutoMovimento dao = new DaoProdutoMovimento();
        dao.excluir(id);
    }

    public static String[] recuperar(int id) {
        DaoProdutoMovimento dao = new DaoProdutoMovimento();
        MdlProdutoMovimento obj = dao.recuperar(id);
        
        return obj.toArray();
    }

    public static String[][] recuperarTodos() {
        DaoProdutoMovimento dao = new DaoProdutoMovimento();
        ArrayList<MdlProdutoMovimento> lista;
        lista = dao.recuperarTodos();
        String dados[][] = new String[lista.size()][4];

        for (int i = 0; i < lista.size(); i++) {
            MdlProdutoMovimento pedido = lista.get(i);
            dados[i] = pedido.toArray();
        }
        return dados;
    }
}
