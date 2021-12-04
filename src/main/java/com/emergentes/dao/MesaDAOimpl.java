package com.emergentes.dao;

import com.emergentes.modelo.Mesa;
import com.emergentes.utiles.ConexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MesaDAOimpl extends ConexionDB implements MesaDAO{

    @Override
    public void insert(Mesa mesa) throws Exception {
        try {
            this.conectar();
            String sql = "INSERT INTO mesas(nombre, encargado, ocupada) VALUES(?, ?, ?)";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setString(1, mesa.getNombre());
            ps.setInt(2, mesa.getEncargado());
            ps.setInt(3, mesa.isOcupada()? 1: 0);
            
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void update(Mesa mesa) throws Exception {
        try {
            this.conectar();
            String sql = "UPDATE mesas SET nombre = ?, encargado = ?, ocupada = ?  WHERE id_mesa = ?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setString(1, mesa.getNombre());
            ps.setInt(2, mesa.getEncargado());
            ps.setInt(3, mesa.isOcupada()? 1: 0);
            ps.setInt(4, mesa.getId());
            
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
            String sql = "DELETE FROM mesas WHERE id_mesa = ?";
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
    public void ocupar(int id) throws Exception {
        try {
            this.conectar();
            String sql = "UPDATE mesas SET ocupada = ?  WHERE id_mesa = ?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, 1);
            ps.setInt(2, id);
            
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void desocupar(int id) throws Exception {
        try {
            this.conectar();
            String sql = "UPDATE mesas SET ocupada = ?  WHERE id_mesa = ?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, 0);
            ps.setInt(2, id);
            
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public Mesa getById(int id) throws Exception {
        Mesa mesa = new Mesa();
        try {
            this.conectar();
            String sql = "SELECT id_mesa, nombre, encargado, ocupada FROM mesas WHERE id_mesa = ?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                mesa.setId(rs.getInt("id_mesa"));
                mesa.setNombre(rs.getString("nombre"));
                mesa.setEncargado(rs.getInt("encargado"));
                mesa.setOcupada(rs.getInt("ocupada")==1);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return mesa;
    }

    @Override
    public List<Mesa> getAll() throws Exception {
        List<Mesa> lista = new ArrayList<Mesa>();
        try {
            this.conectar();
            String sql = "SELECT id_mesa, nombre, encargado, ocupada FROM mesas";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Mesa mesa = new Mesa();
                mesa.setId(rs.getInt("id_mesa"));
                mesa.setNombre(rs.getString("nombre"));
                mesa.setEncargado(rs.getInt("encargado"));
                mesa.setOcupada(rs.getInt("ocupada")==1);
                lista.add(mesa);
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