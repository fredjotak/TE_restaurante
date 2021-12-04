package com.emergentes.modelo;

public class Mesa {
    private int id;
    private String nombre;
    private int encargado;
    private boolean ocupada;
    
    public Mesa(){
        this.id = 0;
        this.nombre = "";
        this.encargado = 0;
        this.ocupada = false;
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

    public int getEncargado() {
        return encargado;
    }

    public void setEncargado(int encargado) {
        this.encargado = encargado;
    }

    public boolean isOcupada() {
        return ocupada;
    }

    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }

    @Override
    public String toString() {
        return "Mesa{" + "id=" + id + ", nombre=" + nombre + ", encargado=" + encargado + ", ocupada=" + ocupada + '}';
    }
}