package control;

import dao.DaoVendedorComissao;
import java.util.ArrayList;
import model.MdlVendedorComissao;

public class CntlVendedorComissao {
    public static void salvar(String dados[]) {
        MdlVendedorComissao obj = MdlVendedorComissao.parseVendedorComissao(dados);
        DaoVendedorComissao dao = new DaoVendedorComissao();

        if (obj.getId() == 0) {
            dao.inserir(obj);
        } else {
            dao.atualizar(obj);
        }
    }

    public static void deletar(int id) {
        DaoVendedorComissao dao = new DaoVendedorComissao();
        dao.excluir(id);
    }

    public static String[] recuperar(int id) {
        DaoVendedorComissao dao = new DaoVendedorComissao();
        MdlVendedorComissao obj = dao.recuperar(id);
        
        return obj.toArray();
    }

    public static String[][] recuperarTodos() {
        DaoVendedorComissao dao = new DaoVendedorComissao();
        ArrayList<MdlVendedorComissao> lista;
        lista = dao.recuperarTodos();
        String dados[][] = new String[lista.size()][4];

        for (int i = 0; i < lista.size(); i++) {
            MdlVendedorComissao pedido = lista.get(i);
            dados[i] = pedido.toArray();
        }
        return dados;
    }
}
