package com.emergentes.dao;

import com.emergentes.modelo.Producto;
import com.emergentes.utiles.ConexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAOimpl extends ConexionDB implements ProductoDAO{

    @Override
    public void insert(Producto producto) throws Exception {
        try {
            this.conectar();
            String sql = "INSERT INTO producto(nombre, precio, id_categoria) VALUES(?, ?, ?)";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setString(1, producto.getNombre());
            ps.setFloat(2, producto.getPrecio());
            ps.setInt(3, producto.getIdCategoria());
            
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void update(Producto producto) throws Exception {
        try {
            this.conectar();
            String sql = "UPDATE producto SET nombre = ?, precio = ?, id_categoria = ? WHERE id_producto = ?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setString(1, producto.getNombre());
            ps.setFloat(2, producto.getPrecio());
            ps.setInt(3, producto.getIdCategoria());
            ps.setString(4, producto.getId());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void delete(String id) throws Exception {
        try {
            this.conectar();
            String sql = "DELETE FROM producto WHERE id_producto = ?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setString(1, id);
            
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public Producto getById(String id) throws Exception {
        Producto producto = new Producto();
        try {
            this.conectar();
            String sql = "SELECT id_producto, nombre, precio, id_categoria FROM producto WHERE id_producto = ?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setString(1, id);
            
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                producto.setId(rs.getString("id_producto"));
                producto.setNombre(rs.getString("nombre"));
                producto.setPrecio(rs.getFloat("precio"));
                producto.setIdCategoria(rs.getInt("id_categoria"));
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return producto;
    }

    @Override
    public List<Producto> getAll() throws Exception {
        List<Producto> lista = new ArrayList<Producto>();
        try {
            this.conectar();
            String sql = "SELECT id_producto, nombre, precio, id_categoria FROM producto";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Producto producto = new Producto();
                producto.setId(rs.getString("id_producto"));
                producto.setNombre(rs.getString("nombre"));
                producto.setPrecio(rs.getFloat("precio"));
                lista.add(producto);
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
    public List<Producto> getFilterNombre(String texto) throws Exception {
        List<Producto> lista = new ArrayList<Producto>();
        try {
            this.conectar();
            String sql = "SELECT id_producto, nombre, precio, id_categoria FROM producto WHERE nombre LIKE '%"+texto+"%';";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Producto producto = new Producto();
                producto.setId(rs.getString("id_producto"));
                producto.setNombre(rs.getString("nombre"));
                producto.setPrecio(rs.getFloat("precio"));
                lista.add(producto);
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