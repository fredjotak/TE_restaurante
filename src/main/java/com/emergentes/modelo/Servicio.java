package com.emergentes.modelo;

import java.sql.Date;

public class Servicio {
    private int id;
    private Date fecha;
    private String hora;
    private int idMesa;
    private String nombreMesa;
    private int idEncargado;
    private String nombreCompletoEncargado;
    private float total;
    private boolean cancelada;
    
    public Servicio(){
        this.id = 0;
        this.idMesa = 0;
        this.nombreMesa = "";
        this.idEncargado = 0;
        this.nombreCompletoEncargado = "";
        this.total = 0;
        this.cancelada = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public int getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(int idMesa) {
        this.idMesa = idMesa;
    }
    public String getNombreMesa() {
        return nombreMesa;
    }

    public void setNombreMesa(String nombreMesa) {
        this.nombreMesa = nombreMesa;
    }

    public int getIdEncargado() {
        return idEncargado;
    }

    public void setIdEncargado(int idEncargado) {
        this.idEncargado = idEncargado;
    }

    public String getNombreCompletoEncargado() {
        return nombreCompletoEncargado;
    }

    public void setNombreCompletoEncargado(String nombreCompletoEncargado) {
        this.nombreCompletoEncargado = nombreCompletoEncargado;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public boolean isCancelada() {
        return cancelada;
    }

    public void setCancelada(boolean cancelada) {
        this.cancelada = cancelada;
    }

    @Override
    public String toString() {
        return "Servicio{" + "id=" + id + ", fecha=" + fecha + ", hora=" + hora + ", idMesa=" + idMesa + ", nombreMesa=" + nombreMesa + ", idEncargado=" + idEncargado + ", nombreCompletoEncargado=" + nombreCompletoEncargado + ", total=" + total + ", cancelada=" + cancelada + '}';
    }
}