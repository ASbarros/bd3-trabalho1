package control;

import dao.DaoCliente;
import java.util.ArrayList;
import model.MdlCliente;

public class CntlCliente {

    public static void salvar(String dados[]) {
        MdlCliente cliente = MdlCliente.parseCliente(dados);
        DaoCliente dao = new DaoCliente();

        if (cliente.getId() == 0) {
            dao.inserir(cliente);
        } else {
            dao.atualizar(cliente);
        }
    }

    public static void deletar(int id) {
        DaoCliente dao = new DaoCliente();
        dao.excluir(id);
    }

    public static String[] recuperar(int id) {
        DaoCliente dao = new DaoCliente();
        MdlCliente cliente = dao.recuperar(id);
        return cliente.toArray();
    }

    public static String[][] recuperarTodos() {
        DaoCliente dao = new DaoCliente();
        ArrayList<MdlCliente> lista = new ArrayList<>();
        lista = dao.recuperarTodos();
        String dados[][] = new String[lista.size()][4];
        System.out.println(lista.size());

        for (int i = 0; i < lista.size(); i++) {
            MdlCliente cliente = lista.get(i);
            dados[i] = cliente.toArray();
        }
        return dados;
    }
}
