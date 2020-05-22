package model;

import dao.DaoProduto;
import dao.DaoPedido;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class MdlPedidoProduto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double quantidade;
    private double valor;
    private double total;
    @ManyToOne
    private MdlProduto produto;
    @ManyToOne
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

    public static MdlPedidoProduto parsePedidoProduto(String dados[]) {
        MdlPedidoProduto obj = new MdlPedidoProduto();

        obj.setId(Integer.parseInt(dados[0]));
        obj.setQuantidade(Double.parseDouble(dados[1]));
        obj.setValor(Double.parseDouble(dados[2]));
        obj.setTotal(Double.parseDouble(dados[3]));
        obj.setProduto(new DaoProduto().recuperar(Integer.parseInt(dados[4])));
        obj.setPedido(new DaoPedido().recuperar(Integer.parseInt(dados[5])));

        return obj;
    }

    public String[] toArray() {
        String dados[] = new String[6];
        dados[0] = String.valueOf(this.id);
        dados[1] = String.valueOf(this.quantidade);
        dados[2] = String.valueOf(this.valor);
        dados[3] = String.valueOf(this.total);
        dados[4] = String.valueOf(this.produto.getId());
        dados[5] = String.valueOf(this.pedido.getId());
        return dados;
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
