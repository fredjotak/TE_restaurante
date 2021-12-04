package com.emergentes.modelo;

public class Producto {
    private String id;
    private String nombre;
    private float precio;
    private int idCategoria;
    
    public Producto(){
        this.id = "";
        this.nombre = "";
        this.precio = 0;
        this.idCategoria = 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    @Override
    public String toString() {
        return "Producto{" + "id=" + id + ", nombre=" + nombre + ", precio=" + precio + ", idCategoria=" + idCategoria + '}';
    }
}