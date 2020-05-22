package model;

import dao.DaoProduto;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class MdlProdutoMovimento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String tipo;
    private String data;
    private String descricao;
    @ManyToOne
    private MdlProduto produto;

    public MdlProdutoMovimento() {
    }

    public MdlProdutoMovimento(String tipo, Date data, String descricao, MdlProduto produto) {
        this.tipo = tipo;
        this.data = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(data);
        this.descricao = descricao;
        this.produto = produto;
    }

    public MdlProdutoMovimento(int id, String tipo, Calendar data, String descricao, MdlProduto produto) {
        this.id = id;
        this.tipo = tipo;
        this.data = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(data);
        this.descricao = descricao;
        this.produto = produto;
    }

    public static MdlProdutoMovimento parseProdutoMovimento(String dados[]) {
        MdlProdutoMovimento obj = new MdlProdutoMovimento();

        obj.setId(Integer.parseInt(dados[0]));
        obj.setTipo(dados[1]);
        //obj.setData(dados[2]);
        obj.setData(new Date());
        obj.setDescricao(dados[3]);
        obj.setProduto(new DaoProduto().recuperar(Integer.parseInt(dados[4])));

        return obj;
    }

    public String[] toArray() {
        String dados[] = new String[5];
        dados[0] = String.valueOf(this.id);
        dados[1] = this.tipo;
        dados[2] = this.data;
        dados[3] = this.descricao;
        dados[4] = String.valueOf(this.produto.getId());

        return dados;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setData(Date data) {
        this.data = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(data);
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public MdlProduto getProduto() {
        return produto;
    }

    public void setProduto(MdlProduto produto) {
        this.produto = produto;
    }

}
