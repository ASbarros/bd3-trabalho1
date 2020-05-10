package model;

import dao.DaoCliente;
import dao.DaoVendedor;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class MdlPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String data;
    @Column
    private String observacao;
    @ManyToOne
    private MdlCliente cliente;
    @ManyToOne
    private MdlVendedor vendedor;

    public MdlPedido() {
    }

    public MdlPedido(Date data, String observacao, MdlCliente cliente, MdlVendedor vendedor) {
        this.data = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(data);
        this.observacao = observacao;
        this.cliente = cliente;
        this.vendedor = vendedor;
    }

    public MdlPedido(int id, Date data, String observacao, MdlCliente cliente, MdlVendedor vendedor) {
        this.id = id;
        this.data = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(data);
        this.observacao = observacao;
        this.cliente = cliente;
        this.vendedor = vendedor;
    }

    public MdlPedido(int id, Date date, String observacao, int idCliente, int idVendedor) {
        this.id = id;

        this.data = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);

        this.observacao = observacao;

        DaoCliente daoCliente = new DaoCliente();
        this.cliente = daoCliente.recuperar(idCliente);

        DaoVendedor daoVendedor = new DaoVendedor();
        this.vendedor = daoVendedor.recuperar(idVendedor);
    }

    public static MdlPedido parsePedido(String dados[]) {
        MdlPedido pedido = new MdlPedido();

        pedido.setId(Integer.parseInt(dados[0]));
        pedido.setData(dados[1]);
        //pedido.setData(new Date());
        pedido.setObservacao(dados[2]);
        pedido.setCliente(new DaoCliente().recuperar(Integer.parseInt(dados[3])));
        pedido.setVendedor(new DaoVendedor().recuperar(Integer.parseInt(dados[4])));

        return pedido;
    }

    public String[] toArray() {
        String dados[] = new String[5];
        dados[0] = String.valueOf(this.id);
        dados[1] = this.data;
        dados[2] = this.observacao;
        dados[3] = String.valueOf(this.cliente.getId());
        dados[4] = String.valueOf(this.vendedor.getId());

        return dados;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setData(Date data) {
        this.data = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(data);
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public MdlCliente getCliente() {
        return cliente;
    }

    public void setCliente(MdlCliente cliente) {
        this.cliente = cliente;
    }

    public MdlVendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(MdlVendedor vendedor) {
        this.vendedor = vendedor;
    }

}
