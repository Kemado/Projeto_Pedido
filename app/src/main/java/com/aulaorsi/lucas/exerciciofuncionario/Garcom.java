package com.aulaorsi.lucas.exerciciofuncionario;

/**
 * Created by lucas on 21/08/15.
 */
public class Garcom {

    public static final String COLUNA_ID = "_id";
    public static final String COLUNA_NOME = "nome";

    private long id;
    private String nome;

    public Garcom(long id, long idpedido, String produto) {
        this.id = id;
        this.nome = nome;
    }

    public Garcom() {

    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
