package com.emergentes.modelo;

public class Usuario {
    private int id;
    private int ci;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String usuario;
    private int idRol;
    private String nombreRol;
    
    public Usuario(){
        this.id = 0;
        this.ci = 0;
        this.nombres = "";
        this.apellidoPaterno = "";
        this.apellidoMaterno = "";
        this.usuario = "";
        this.idRol = 0;
        this.nombreRol = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCi() {
        return ci;
    }

    public void setCi(int ci) {
        this.ci = ci;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", ci=" + ci + ", nombres=" + nombres + ", apellidoPaterno=" + apellidoPaterno + ", apellidoMaterno=" + apellidoMaterno + ", usuario=" + usuario + ", idRol=" + idRol + ", nombreRol=" + nombreRol + '}';
    }
}