package model;

import dao.DaoProduto;
import dao.DaoPedido;

public class MdlPedidoProduto {
    private int id;
    private double quantidade;
    private double valor;
    private double total;
    private MdlProduto produto;
    private MdlPedido pedido;

    public MdlPedidoProduto() {
    }

    public MdlPedidoProduto(double quantidade, double valor, double total, MdlProduto produto, MdlPedido pedido) {
        this.quantidade = quantidade;
        this.valor = valor;
        this.total = total;
        this.produto = produto;
        this.pedido = pedido;
    }

    public MdlPedidoProduto(int id, double quantidade, double valor, double total, MdlProduto produto, MdlPedido pedido) {
        this.id = id;
        this.quantidade = quantidade;
        this.valor = valor;
        this.total = total;
        this.produto = produto;
        this.pedido = pedido;
    }

    public MdlPedidoProduto(int id, double quantidade, double valor, double total, int idProduto, int idPedido) {
        this.id = id;
        this.quantidade = quantidade;
        this.valor = valor;
        this.total = total;
        
        DaoProduto daoProduto = new DaoProduto();
        this.produto = daoProduto.recuperar(idProduto);
        
        DaoPedido daopedido = new DaoPedido();
        this.pedido = daopedido.recuperar(idPedido);
 
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public MdlProduto getProduto() {
        return produto;
    }

    public void setProduto(MdlProduto produto) {
        this.produto = produto;
    }

    public MdlPedido getPedido() {
        return pedido;
    }

    public void setPedido(MdlPedido pedido) {
        this.pedido = pedido;
    }
}
