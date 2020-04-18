package control;

import dao.DaoVendedor;
import java.util.ArrayList;
import model.MdlVendedor;

public class CntlVendedor {
public static void salvar(String dados[]) {
        MdlVendedor vendedor = MdlVendedor.parseVendedor(dados);
        DaoVendedor dao = new DaoVendedor();

        if (vendedor.getId() == 0) {
            dao.inserir(vendedor);
        } else {
            dao.atualizar(vendedor);
        }
    }

    public static void deletar(int id) {
        DaoVendedor dao = new DaoVendedor();
        dao.excluir(id);
    }

    public static String[] recuperar(int id) {
        DaoVendedor dao = new DaoVendedor();
        MdlVendedor cliente = dao.recuperar(id);
        return cliente.toArray();
    }

    public static String[][] recuperarTodos() {
        DaoVendedor dao = new DaoVendedor();
        ArrayList<MdlVendedor> lista;
        lista = dao.recuperarTodos();
        String dados[][] = new String[lista.size()][4];

        for (int i = 0; i < lista.size(); i++) {
            MdlVendedor vendedor = lista.get(i);
            dados[i] = vendedor.toArray();
        }
        return dados;
    }
}
