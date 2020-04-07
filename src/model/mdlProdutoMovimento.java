package model;

import java.util.Calendar;

public class mdlProdutoMovimento {
    private int id;
    private String tipo;
    private Calendar data;
    private String descricao;
    private MdlProduto produto;

    public mdlProdutoMovimento() {
    }

    public mdlProdutoMovimento(String tipo, Calendar data, String descricao, MdlProduto produto) {
        this.tipo = tipo;
        this.data = data;
        this.descricao = descricao;
        this.produto = produto;
    }

    public mdlProdutoMovimento(int id, String tipo, Calendar data, String descricao, MdlProduto produto) {
        this.id = id;
        this.tipo = tipo;
        this.data = data;
        this.descricao = descricao;
        this.produto = produto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public MdlProduto getProduto() {
        return produto;
    }

    public void setProduto(MdlProduto produto) {
        this.produto = produto;
    }
    
    
}
