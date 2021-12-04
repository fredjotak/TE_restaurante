package com.emergentes.modelo;

public class Categoria {
    private int id;
    private String nombre;
    private String tipo;
    
    public Categoria(){
        this.id = 0;
        this.nombre = "";
        this.tipo = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Categoria{" + "id=" + id + ", nombre=" + nombre + ", tipo=" + tipo + '}';
    }
}