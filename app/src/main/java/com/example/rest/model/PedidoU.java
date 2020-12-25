package com.example.rest.model;

public class PedidoU {
    String name, user;

    int cant;

    public PedidoU() {
    }

    public PedidoU(String name, String user, int cant) {
        this.name = name;
        this.user = user;
        this.cant = cant;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getCant() {
        return cant;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }
}
