package com.aulaorsi.lucas.exerciciofuncionario;

/**
 * Created by lucas on 5/30/15.
 */
public class Pedido {

    public static final String COLUNA_ID = "_id";
    public static final String COLUNA_MESA = "mesa";
    public static final String COLUNA_GARCOM = "garcom";
    public static final String COLUNA_STATUS = "status";

    private long id;
    private int mesa;
    private String garcom;
    private int status;

    public Pedido(long id, int mesa, String garcom, int status) {
        this.id = id;
        this.mesa = mesa;
        this.garcom = garcom;
        this.status = status;
    }

    public Pedido() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getMesa() {
        return mesa;
    }

    public void setMesa(int mesa) {
        this.mesa = mesa;
    }

    public String getGarcom() {
        return garcom;
    }

    public void setGarcom(String garcom) {
        this.garcom = garcom;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
