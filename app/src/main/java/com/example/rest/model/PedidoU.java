package com.example.rest.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

public class PedidoU {
    String name;

    int cant;

    public PedidoU() {
    }

    public PedidoU(String name, int cant) {
        this.name = name;

        this.cant = cant;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCant() {
        return cant;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public String toString() {
        return
                "Nombre:'" + name + '\''+System.lineSeparator()+
                "Cantidad:" + cant ;
    }
}
