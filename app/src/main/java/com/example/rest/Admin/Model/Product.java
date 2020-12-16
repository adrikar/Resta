package com.example.rest.Admin.Model;

public class Product {
    String name , dascri, image;
    Double price;
    int cant;

    public Product() {
    }

    public Product(String name, String dascri, String image,Double price, int cant) {
        this.name = name;
        this.dascri = dascri;
        this.image = image;
        this.price = price;
        this.cant = cant;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDascri() {
        return dascri;
    }

    public void setDascri(String dascri) {
        this.dascri = dascri;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getCant() {
        return cant;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }
}
