package model;

import dao.DaoCliente;
import dao.DaoVendedor;
import java.util.Calendar;
import java.util.Date;

public class MdlPedido {
    private int id;
    private Calendar data;
    private String observacao;
    private MdlCliente cliente;
    private MdlVendedor vendedor;

    public MdlPedido() {
    }

    public MdlPedido(Calendar data, String observacao, MdlCliente cliente, MdlVendedor vendedor) {
        this.data = data;
        this.observacao = observacao;
        this.cliente = cliente;
        this.vendedor = vendedor;
    }

    public MdlPedido(int id, Calendar data, String observacao, MdlCliente cliente, MdlVendedor vendedor) {
        this.id = id;
        this.data = data;
        this.observacao = observacao;
        this.cliente = cliente;
        this.vendedor = vendedor;
    }

    public MdlPedido(int id, Date date, String observacao, int idCliente, int idVendedor) {
        this.id = id;
        
        data = Calendar.getInstance();
        data.setTime(date);
        
        this.observacao = observacao;
        
        DaoCliente daoCliente = new DaoCliente();
        this.cliente = daoCliente.Recupera(idCliente);
        
        DaoVendedor daoVendedor = new DaoVendedor();
        this.vendedor = daoVendedor.Recupera(idVendedor);    
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
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
