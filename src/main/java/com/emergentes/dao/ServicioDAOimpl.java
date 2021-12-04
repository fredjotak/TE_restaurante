package com.emergentes.dao;

import com.emergentes.modelo.Servicio;
import com.emergentes.utiles.ConexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ServicioDAOimpl extends ConexionDB implements ServicioDAO{

    @Override
    public void insert(Servicio servicio) throws Exception {
        try {
            this.conectar();
            String sql = "INSERT INTO servicio(id_mesa, id_encargado, cancelada) VALUES(?, ?, ?)";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, servicio.getIdMesa());
            ps.setInt(2, servicio.getIdEncargado());
            ps.setInt(3, servicio.isCancelada()? 1: 0);
            
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void update(Servicio servicio) throws Exception {
        try {
            this.conectar();
            String sql = "UPDATE servicio SET id_mesa = ?, id_encargado = ?, cancelada = ? WHERE id_servicio = ?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, servicio.getIdMesa());
            ps.setInt(2, servicio.getIdEncargado());
            ps.setInt(3, servicio.isCancelada()? 1: 0);
            ps.setInt(4, servicio.getId());
            
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
            String sql = "DELETE FROM servicio WHERE id_servicio = ?";
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
    public Servicio getById(int id) throws Exception {
        Servicio servicio = new Servicio();
        try {
            this.conectar();
            String sql = "SELECT id_servicio, DATE_FORMAT(fecha_hora, '%Y-%m-%d') fecha, DATE_FORMAT(fecha_hora, '%h:%i:%s') hora, id_mesa, id_encargado, cancelada, total\n" +
                        "FROM servicio WHERE id_servicio = ?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                servicio.setId(rs.getInt("id_servicio"));
                servicio.setFecha(rs.getDate("fecha"));
                servicio.setHora(rs.getString("hora"));
                servicio.setIdMesa(rs.getInt("id_mesa"));
                servicio.setIdEncargado(rs.getInt("id_encargado"));
                servicio.setCancelada(rs.getInt("cancelada")==1);
                servicio.setTotal(rs.getFloat("total"));
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return servicio;
    }

    @Override
    public List<Servicio> getAll() throws Exception {
        List<Servicio> lista = new ArrayList<Servicio>();
        try {
            this.conectar();
            String sql = "SELECT id_servicio, DATE_FORMAT(fecha_hora, '%Y-%m-%d') fecha, DATE_FORMAT(fecha_hora, '%h:%i:%s') hora, s.id_mesa, m.nombre, id_encargado, CONCAT(nombres, ' ', apellido_paterno, ' ', apellido_materno) encargado, cancelada, total\n" +
                        "FROM servicio s LEFT JOIN mesas m ON(s.id_mesa=m.id_mesa) LEFT JOIN usuario ON(id_usuario=id_encargado)";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Servicio servicio = new Servicio();
                servicio.setId(rs.getInt("id_servicio"));
                servicio.setFecha(rs.getDate("fecha"));
                servicio.setHora(rs.getString("hora"));
                servicio.setIdMesa(rs.getInt("id_mesa"));
                servicio.setNombreMesa(rs.getString("nombre"));
                servicio.setIdEncargado(rs.getInt("id_encargado"));
                servicio.setNombreCompletoEncargado(rs.getString("encargado"));
                servicio.setCancelada(rs.getInt("cancelada")==1);
                servicio.setTotal(rs.getFloat("total"));
                lista.add(servicio);
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