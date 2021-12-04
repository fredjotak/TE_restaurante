package com.emergentes.dao;

import com.emergentes.modelo.Rol;
import com.emergentes.utiles.ConexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RolDAOimpl extends ConexionDB implements RolDAO{

    @Override
    public void insert(Rol rol) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Rol rol) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Rol getById(int id) throws Exception {
        Rol rol = new Rol();
        try {
            this.conectar();
            String sql = "SELECT * FROM rol WHERE id_rol = ?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                rol.setId(rs.getInt("id_rol"));
                rol.setNombre(rs.getString("nombre"));
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return rol;
    }

    @Override
    public List<Rol> getAll() throws Exception {
        List<Rol> lista = new ArrayList<Rol>();
        try {
            this.conectar();
            String sql = "SELECT * FROM rol";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Rol rol = new Rol();
                rol.setId(rs.getInt("id_rol"));
                rol.setNombre(rs.getString("nombre"));
                lista.add(rol);
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