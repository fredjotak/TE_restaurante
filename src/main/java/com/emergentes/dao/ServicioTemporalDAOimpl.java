package com.emergentes.dao;

import com.emergentes.modelo.ServicioTemporal;
import com.emergentes.utiles.ConexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ServicioTemporalDAOimpl extends ConexionDB implements ServicioTemporalDAO{

    @Override
    public void insert(ServicioTemporal servicioTemporal) throws Exception {
        try {
            this.conectar();
            String sql = "INSERT INTO servicio_temporal(id_producto, id_mesa, cantidad, comentario) VALUES(?, ?, ?, ?)";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setString(1, servicioTemporal.getIdProducto());
            ps.setInt(2, servicioTemporal.getIdMesa());
            ps.setInt(3, servicioTemporal.getCantidad());
            ps.setString(4, servicioTemporal.getComentario());
            
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void update(ServicioTemporal servicioTemporal) throws Exception {
        try {
            this.conectar();
            String sql = "UPDATE servicio_temporal SET id_producto = ?, id_mesa = ?, cantidad = ?, comentario = ? WHERE id_servicio_temporal = ?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setString(1, servicioTemporal.getIdProducto());
            ps.setInt(2, servicioTemporal.getIdMesa());
            ps.setInt(3, servicioTemporal.getCantidad());
            ps.setString(4, servicioTemporal.getComentario());
            ps.setInt(5, servicioTemporal.getId());
            
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
            String sql = "DELETE FROM servicio_temporal WHERE id_servicio_temporal = ?";
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
    public ServicioTemporal getById(int id) throws Exception {
        ServicioTemporal servicioTemporal = new ServicioTemporal();
        try {
            this.conectar();
            String sql = "SELECT * FROM servicio_temporal WHERE id_servicio_temporal = ?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                servicioTemporal.setId(rs.getInt("id_servicio_temporal"));
                servicioTemporal.setIdProducto(rs.getString("id_producto"));
                servicioTemporal.setHora(rs.getString("hora"));
                servicioTemporal.setIdMesa(rs.getInt("id_mesa"));
                servicioTemporal.setCantidad(rs.getInt("cantidad"));
                servicioTemporal.setComentario(rs.getString("comentario"));
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return servicioTemporal;
    }

    @Override
    public List<ServicioTemporal> getAll() throws Exception {
        List<ServicioTemporal> lista = new ArrayList<ServicioTemporal>();
        try {
            this.conectar();
            String sql = "SELECT id_servicio_temporal, hora, s.id_producto, nombre, id_mesa, cantidad, comentario \n" +
"FROM servicio_temporal s LEFT JOIN producto p ON(s.id_producto = p.id_producto)";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                ServicioTemporal servicioTemporal = new ServicioTemporal();
                servicioTemporal.setId(rs.getInt("id_servicio_temporal"));
                servicioTemporal.setIdProducto(rs.getString("id_producto"));
                servicioTemporal.setNombreProducto(rs.getString("nombre"));
                servicioTemporal.setHora(rs.getString("hora"));
                servicioTemporal.setIdMesa(rs.getInt("id_mesa"));
                servicioTemporal.setCantidad(rs.getInt("cantidad"));
                servicioTemporal.setComentario(rs.getString("comentario"));
                lista.add(servicioTemporal);
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
    public List<ServicioTemporal> getFilterIdMesa(int idMesa) throws Exception {
        List<ServicioTemporal> lista = new ArrayList<ServicioTemporal>();
        try {
            this.conectar();
            String sql = "SELECT id_servicio_temporal, hora, s.id_producto, nombre, id_mesa, cantidad, comentario \n" +
"FROM servicio_temporal s LEFT JOIN producto p ON(s.id_producto = p.id_producto) WHERE s.id_mesa = ?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, idMesa);
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                ServicioTemporal servicioTemporal = new ServicioTemporal();
                servicioTemporal.setId(rs.getInt("id_servicio_temporal"));
                servicioTemporal.setIdProducto(rs.getString("id_producto"));
                servicioTemporal.setNombreProducto(rs.getString("nombre"));
                servicioTemporal.setHora(rs.getString("hora"));
                servicioTemporal.setIdMesa(rs.getInt("id_mesa"));
                servicioTemporal.setCantidad(rs.getInt("cantidad"));
                servicioTemporal.setComentario(rs.getString("comentario"));
                lista.add(servicioTemporal);
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