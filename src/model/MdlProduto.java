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

    public static MdlProduto parseProduto(String dados[]) {
        MdlProduto produto = new MdlProduto();

        produto.setId(Integer.parseInt(dados[0]));
        produto.setDescricao(dados[1]);
        produto.setSaldo(Double.parseDouble(dados[2]));
        produto.setUnidade(dados[3]);
        produto.setValor(Double.parseDouble(dados[4]));
        
        return produto;
    }

    public String[] toArray() {
        String dados[] = new String[5];
        dados[0] = String.valueOf(this.id);
        dados[1] = this.descricao;
        dados[2] = String.valueOf(this.saldo);
        dados[3] = this.unidade;
        dados[4] = String.valueOf(this.valor);

        return dados;
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
