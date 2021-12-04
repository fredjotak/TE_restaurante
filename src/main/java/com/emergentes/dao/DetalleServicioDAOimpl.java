package com.emergentes.dao;

import com.emergentes.modelo.DetalleServicio;
import com.emergentes.utiles.ConexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DetalleServicioDAOimpl extends ConexionDB implements DetalleServicioDAO {

    @Override
    public void insert(DetalleServicio detalleServicio) throws Exception {
        try {
            this.conectar();
            String sql = "INSERT INTO detalle_servicio(id_servicio, id_producto, cantidad) VALUES(?, ?, ?)";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, detalleServicio.getIdServicio());
            ps.setString(2, detalleServicio.getIdProducto());
            ps.setInt(3, detalleServicio.getCantidad());
            
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void update(DetalleServicio detalleServicio) throws Exception {
        try {
            this.conectar();
            String sql = "UPFATE detalle_servicio SET id_servicio = ?, id_producto = ?, cantidad = ? WHERE id_detalle_servicio = ?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, detalleServicio.getIdServicio());
            ps.setString(2, detalleServicio.getIdProducto());
            ps.setInt(3, detalleServicio.getCantidad());
            ps.setInt(4, detalleServicio.getId());
            
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        try {
            this.conectar();
            String sql = "DELETE FROM detalle_servicio WHERE id_detalle_servicio = ?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, id);
            
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public DetalleServicio getById(int id) throws Exception {
        DetalleServicio detalleServicio = new DetalleServicio();
        try {
            this.conectar();
            String sql = "SELECT id_detalle_servicio, id_servicio, hora, id_producto, cantidad, subtotal, comentario, estado\n" +
"FROM detalle_servicio WHERE id_detalle_servicio = ?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                detalleServicio.setId(rs.getInt("id_detalle_servicio"));
                detalleServicio.setIdServicio(rs.getInt("id_servicio"));
                detalleServicio.setHora(rs.getString("hora"));
                detalleServicio.setIdProducto(rs.getString("id_producto"));
                // detalleServicio.setNombreProducto(rs.getString("nombre"));
                detalleServicio.setCantidad(rs.getInt("cantidad"));
                // detalleServicio.setPrecioU(rs.getFloat("precio"));
                detalleServicio.setSubtotal(rs.getFloat("subtotal"));
                detalleServicio.setComentario(rs.getString("comentario"));
                detalleServicio.setEstado(rs.getInt("estado")==1);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return detalleServicio;
    }

    @Override
    public List<DetalleServicio> getAll() throws Exception {
        List<DetalleServicio> lista = new ArrayList<DetalleServicio>();
        try {
            this.conectar();
            String sql = "SELECT id_detalle_servicio, id_servicio, hora, d.id_producto, nombre, cantidad, precio, subtotal, comentario, estado\n" +
"FROM detalle_servicio d JOIN producto p ON(d.id_producto = p.id_producto)";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                DetalleServicio detalleServicio = new DetalleServicio();
                detalleServicio.setId(rs.getInt("id_detalle_servicio"));
                detalleServicio.setIdServicio(rs.getInt("id_servicio"));
                detalleServicio.setHora(rs.getString("hora"));
                detalleServicio.setIdProducto(rs.getString("id_producto"));
                detalleServicio.setNombreProducto(rs.getString("nombre"));
                detalleServicio.setCantidad(rs.getInt("cantidad"));
                detalleServicio.setPrecioU(rs.getFloat("precio"));
                detalleServicio.setSubtotal(rs.getFloat("subtotal"));
                detalleServicio.setComentario(rs.getString("comentario"));
                detalleServicio.setEstado(rs.getInt("estado")==1);
                lista.add(detalleServicio);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return lista;
    }

    @Override
    public List<DetalleServicio> getListIdServicio(int idServicio) throws Exception {
        List<DetalleServicio> lista = new ArrayList<DetalleServicio>();
        try {
            this.conectar();
            String sql = "SELECT id_detalle_servicio, id_servicio, hora, d.id_producto, nombre, cantidad, precio, subtotal, comentario, estado\n" +
"FROM detalle_servicio d JOIN producto p ON(d.id_producto = p.id_producto) WHERE id_servicio = ?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, idServicio);
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                DetalleServicio detalleServicio = new DetalleServicio();
                detalleServicio.setId(rs.getInt("id_detalle_servicio"));
                detalleServicio.setIdServicio(rs.getInt("id_servicio"));
                detalleServicio.setHora(rs.getString("hora"));
                detalleServicio.setIdProducto(rs.getString("id_producto"));
                detalleServicio.setNombreProducto(rs.getString("nombre"));
                detalleServicio.setCantidad(rs.getInt("cantidad"));
                detalleServicio.setPrecioU(rs.getFloat("precio"));
                detalleServicio.setSubtotal(rs.getFloat("subtotal"));
                detalleServicio.setComentario(rs.getString("comentario"));
                detalleServicio.setEstado(rs.getInt("estado")==1);
                lista.add(detalleServicio);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return lista;
    }
}