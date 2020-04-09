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

    public static MdlVendedor parseVendedor(String dados[]) {
        MdlVendedor vendedor = new MdlVendedor();

        vendedor.setId(Integer.parseInt(dados[0]));
        vendedor.setNome(dados[1]);
        vendedor.setPercentual(Double.parseDouble(dados[2]));

        return vendedor;
    }

    public String[] toArray() {
        String dados[] = new String[4];
        dados[0] = String.valueOf(this.id);
        dados[1] = this.nome;
        dados[2] = String.valueOf(this.percentual);

        return dados;
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
