package model;

import dao.DaoPedido;
import dao.DaoVendedor;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class MdlVendedorComissao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double percentual;
    private double valor;
    @ManyToOne 
    private MdlVendedor vendedor;
    @ManyToOne
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

    public MdlVendedorComissao(int id, double percentual, double valor, int idVendedor, int idPedido) {
        this.id = id;
        this.percentual = percentual;
        this.valor = valor;

        DaoVendedor daoVendedor = new DaoVendedor();
        this.vendedor = daoVendedor.recuperar(idVendedor);

        DaoPedido daoPedido = new DaoPedido();
        this.pedido = daoPedido.recuperar(idPedido);
    }

    public static MdlVendedorComissao parseVendedorComissao(String dados[]) {
        MdlVendedorComissao obj = new MdlVendedorComissao();

        obj.setId(Integer.parseInt(dados[0]));
        obj.setPercentual(Double.parseDouble(dados[1]));
        obj.setValor(Double.parseDouble(dados[2]));
        obj.setVendedor(new DaoVendedor().recuperar(Integer.parseInt(dados[3])));
        obj.setPedido(new DaoPedido().recuperar(Integer.parseInt(dados[4])));

        return obj;
    }

    public String[] toArray() {
        String dados[] = new String[5];
        dados[0] = String.valueOf(this.id);
        dados[1] = String.valueOf(this.percentual);
        dados[2] = String.valueOf(this.valor);
        dados[3] = String.valueOf(this.vendedor.getId());
        dados[4] = String.valueOf(this.pedido.getId());

        return dados;
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
