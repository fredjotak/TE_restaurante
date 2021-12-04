package com.emergentes.modelo;

public class Comentario {
    private int id;
    private String descripcion;
    private int idCategoria;
    
    public Comentario(){
        this.id = 0;
        this.descripcion = "";
        this.idCategoria = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    @Override
    public String toString() {
        return "Comentario{" + "id=" + id + ", descripcion=" + descripcion + ", idCategoria=" + idCategoria + '}';
    }
}