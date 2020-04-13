package control;

import dao.DaoProduto;
import java.util.ArrayList;
import model.MdlProduto;

public class CntlProduto {
    public static void salvar(String dados[]) {
        MdlProduto produto = MdlProduto.parseProduto(dados);
        DaoProduto dao = new DaoProduto();

        if (produto.getId() == 0) {
            dao.inserir(produto);
        } else {
            dao.atualizar(produto);
        }
    }

    public static void deletar(int id) {
        DaoProduto dao = new DaoProduto();
        dao.excluir(id);
    }

    public static String[] recuperar(int id) {
        DaoProduto dao = new DaoProduto();
        MdlProduto produto = dao.recuperar(id);
        return produto.toArray();
    }

    public static String[][] recuperarTodos() {
        DaoProduto dao = new DaoProduto();
        ArrayList<MdlProduto> lista;
        lista = dao.recuperarTodos();
        String dados[][] = new String[lista.size()][4];

        for (int i = 0; i < lista.size(); i++) {
            MdlProduto cliente = lista.get(i);
            dados[i] = cliente.toArray();
        }
        return dados;
    }
}
