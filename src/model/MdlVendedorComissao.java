package model;

public class MdlVendedorComissao {
    private int id;
    private double percentual;
    private double valor;
    private MdlVendedor vendedor;
    private MdlPedido pedido;

    public MdlVendedorComissao() {
    }

    public MdlVendedorComissao(double percentual, double valor, MdlVendedor vendedor, MdlPedido pedido) {
        this.percentual = percentual;
        this.valor = valor;
        this.vendedor = vendedor;
        this.pedido = pedido;
    }

    public MdlVendedorComissao(int id, double percentual, double valor, MdlVendedor vendedor, MdlPedido pedido) {
        this.id = id;
        this.percentual = percentual;
        this.valor = valor;
        this.vendedor = vendedor;
        this.pedido = pedido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPercentual() {
        return percentual;
    }

    public void setPercentual(double percentual) {
        this.percentual = percentual;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public MdlVendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(MdlVendedor vendedor) {
        this.vendedor = vendedor;
    }

    public MdlPedido getPedido() {
        return pedido;
    }

    public void setPedido(MdlPedido pedido) {
        this.pedido = pedido;
    }
    
    
}
