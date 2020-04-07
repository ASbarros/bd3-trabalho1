package model;

public class MdlProduto {
    private int id;
    private String descricao;
    private double saldo;
    private String unidade;
    private double valor;

    public MdlProduto() {
    }

    public MdlProduto(String descricao, double saldo, String unidade, double valor) {
        this.descricao = descricao;
        this.saldo = saldo;
        this.unidade = unidade;
        this.valor = valor;
    }

    public MdlProduto(int id, String descricao, double saldo, String unidade, double valor) {
        this.id = id;
        this.descricao = descricao;
        this.saldo = saldo;
        this.unidade = unidade;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    
    
    
}
