package com.rrvq.pruebadelibreriajsoupextraedatoshtml;

public class DatosCripto {
    String imgurl;
    String nombre;
    String precio;

    public DatosCripto(String imgurl, String nombre, String precio) {
        this.imgurl = imgurl;
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
}
