package model;

import java.util.Calendar;

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
}
