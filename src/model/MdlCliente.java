package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MdlCliente {

    private int id;
    private String nome;
    private String cpf;
    private Calendar ultimaCompra;

    //construtores
    //sem parametros
    public MdlCliente() {
    }

    //sem ID
    public MdlCliente(String nome, String cpf, Calendar ultimaCompra) {
        this.nome = nome;
        this.cpf = cpf;
        this.ultimaCompra = ultimaCompra;
    }

    //completo
    public MdlCliente(int id, String nome, String cpf, Calendar ultimaCompra) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.ultimaCompra = ultimaCompra;
    }

    public MdlCliente(int id, String nome, String cpf, Date UltComp) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        ultimaCompra = Calendar.getInstance();
        ultimaCompra.setTime(UltComp);
    }

    public static MdlCliente parseCliente(String dados[]) {
        MdlCliente cliente = new MdlCliente();

        cliente.setId(Integer.parseInt(dados[0]));
        cliente.setCpf(dados[1]);
        cliente.setNome(dados[2]);
        //cliente.setUltimaCompraString(dados[3]);

        return cliente;
    }

    public String[] toArray() {
        String dados[] = new String[4];
        dados[0] = String.valueOf(this.id);
        dados[1] = this.cpf;
        dados[2] = this.nome;
        dados[3] = String.valueOf(this.ultimaCompra);

        return dados;
    }

    //get e set
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Calendar getUltimaCompra() {
        return ultimaCompra;
    }

    public void setUltimaCompra(Calendar ultimaCompra) {
        this.ultimaCompra = ultimaCompra;
    }

    public void setUltimaCompraString(String data) {
        try {
            SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
            Date novaData = dataFormatada.parse(data);

            ultimaCompra.setTime(novaData);
        } catch (ParseException ex) {
            ultimaCompra = null;
            System.out.println("Erro ao converter a data: " + ex.getMessage());
        }
    }
}
