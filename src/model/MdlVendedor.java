package model;

public class MdlVendedor {
    private int id;
    private String nome;
    private double percentual;

    public MdlVendedor() {
    }

    public MdlVendedor(String nome, double percentual) {
        this.nome = nome;
        this.percentual = percentual;
    }

    public MdlVendedor(int id, String nome, double percentual) {
        this.id = id;
        this.nome = nome;
        this.percentual = percentual;
    }

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

    public double getPercentual() {
        return percentual;
    }

    public void setPercentual(double percentual) {
        this.percentual = percentual;
    }
    
    
}
