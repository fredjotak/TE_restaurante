package com.emergentes.dao;

import com.emergentes.modelo.Categoria;
import com.emergentes.utiles.ConexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAOimpl extends ConexionDB implements CategoriaDAO{

    @Override
    public void insert(Categoria categoria) throws Exception {
        try {
            this.conectar();
            String sql = "INSERT INTO categoria(nombre, tipo) VALUES(?, ?)";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setString(1, categoria.getNombre());
            ps.setString(2, categoria.getTipo());
            
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void update(Categoria categoria) throws Exception {
        try {
            this.conectar();
            String sql = "UPDATE categoria SET nombre = ?, tipo = ?  WHERE id_categoria = ?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setString(1, categoria.getNombre());
            ps.setString(2, categoria.getTipo());
            ps.setInt(3, categoria.getId());
            
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
            String sql = "DELETE FROM categoria  WHERE id_categoria = ?";
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
    public Categoria getById(int id) throws Exception {
        Categoria categoria = new Categoria();
        try {
            this.conectar();
            String sql = "SELECT id_categoria, nombre, tipo FROM categoria WHERE id_categoria = ?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                categoria.setId(rs.getInt("id_categoria"));
                categoria.setNombre(rs.getString("nombre"));
                categoria.setTipo(rs.getString("tipo"));
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return categoria;
    }

    @Override
    public List<Categoria> getAll() throws Exception {
        List<Categoria> lista = new ArrayList<Categoria>();
        try {
            this.conectar();
            String sql = "SELECT id_categoria, nombre, tipo FROM categoria";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt("id_categoria"));
                categoria.setNombre(rs.getString("nombre"));
                categoria.setTipo(rs.getString("tipo"));
                lista.add(categoria);
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
    public List<Categoria> getFilterNombre(String texto) throws Exception {
        List<Categoria> lista = new ArrayList<Categoria>();
        try {
            this.conectar();
            String sql = "SELECT id_categoria, nombre, tipo FROM categoria WHERE nombre LIKE '%"+texto+"%'";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt("id_categoria"));
                categoria.setNombre(rs.getString("nombre"));
                categoria.setTipo(rs.getString("tipo"));
                lista.add(categoria);
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