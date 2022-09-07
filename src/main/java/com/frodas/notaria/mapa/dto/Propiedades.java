package com.frodas.notaria.mapa.dto;

public class Propiedades {

    private String codigo;
    private String nombre;
    private String cantidad;

    private String urlImg;

    public Propiedades() {
        super();
    }

    public Propiedades(String codigo, String nombre, String cantidad, String urlImg) {
        super();
        this.codigo = codigo;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.urlImg = urlImg;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }
}
