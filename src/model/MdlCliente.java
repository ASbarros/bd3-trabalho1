package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class MdlCliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private String cpf;
    private String ultimaCompra;
    
    @OneToMany (mappedBy = "cliente")
    private List<MdlPedido> pedidos;
    //construtores
    //sem parametros
    public MdlCliente() {
    }

    //sem ID
    public MdlCliente(String nome, String cpf, Date ultimaCompra) {
        this.nome = nome;
        this.cpf = cpf;
        this.ultimaCompra = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(ultimaCompra);
    }

    //completo
    public MdlCliente(int id, String nome, String cpf, Date ultimaCompra) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.ultimaCompra = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(ultimaCompra);
    }

    public static MdlCliente parseCliente(String dados[]) {
        MdlCliente cliente = new MdlCliente();

        cliente.setId(Integer.parseInt(dados[0]));
        cliente.setCpf(dados[1]);
        cliente.setNome(dados[2]);
        cliente.setUltimaCompra(dados[3]);

        return cliente;
    }

    public String[] toArray() {
        String dados[] = new String[4];
        dados[0] = String.valueOf(this.id);
        dados[1] = this.cpf;
        dados[2] = this.nome;
        if (this.ultimaCompra == null) {
            dados[3] = "";
        } else {
            dados[3] = String.valueOf(this.ultimaCompra);
        }

        return dados;
    }

    //get e set
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getUltimaCompra() {
        return ultimaCompra;
    }

    public void setUltimaCompra(Date ultimaCompra) {
        this.ultimaCompra = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(ultimaCompra);
    }

    public void setUltimaCompra(String ultimaCompra) {
        this.ultimaCompra = ultimaCompra;
    }

}
