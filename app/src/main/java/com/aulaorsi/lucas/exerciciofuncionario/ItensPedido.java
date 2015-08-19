package com.aulaorsi.lucas.exerciciofuncionario;

/**
 * Created by lucas on 15/08/15.
 */
public class ItensPedido {

    public static final String COLUNA_ID = "_id";
    public static final String COLUNA_IDPEDIDO = "idpedido";
    public static final String COLUNA_PRODUTO = "produto";

    private long id;
    private long idpedido;
    private String produto;

    public ItensPedido(long id, long idpedido, String produto) {
        this.id = id;
        this.idpedido = idpedido;
        this.produto = produto;
    }

    public ItensPedido() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdPedido() {
        return idpedido;
    }

    public void setIdPedido(long idpedido) {
        this.idpedido = idpedido;
    }

    public String getProduto() { return produto; }

    public void setProduto(String produto) { this.produto = produto; }


}
