package com.emergentes.modelo;

public class DetalleServicio {
    private int id;
    private int idServicio;
    private String hora;
    private String idProducto;
    private String nombreProducto;
    private int cantidad;
    private float precioU;
    private float subtotal;
    private String comentario;
    private boolean estado;
    
    public DetalleServicio(){
        this.id = 0;
        this.idServicio = 0;
        this.idProducto = "";
        this.nombreProducto = "";
        this.cantidad = 0;
        this.precioU = 0;
        this.subtotal = 0;
        this.comentario = "";
        this.estado = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public float getPrecioU() {
        return precioU;
    }

    public void setPrecioU(float precioU) {
        this.precioU = precioU;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "DetalleServicio{" + "id=" + id + ", idServicio=" + idServicio + ", hora=" + hora + ", idProducto=" + idProducto + ", nombreProducto=" + nombreProducto + ", cantidad=" + cantidad + ", precioU=" + precioU + ", subtotal=" + subtotal + ", comentario=" + comentario + ", estado=" + estado + '}';
    }
}