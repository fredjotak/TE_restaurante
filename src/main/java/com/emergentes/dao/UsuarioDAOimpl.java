package com.emergentes.dao;

import com.emergentes.modelo.Usuario;
import com.emergentes.utiles.ConexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOimpl extends ConexionDB implements UsuarioDAO{

    @Override
    public void insert(Usuario usuario) throws Exception {
        try {
            this.conectar();
            String sql = "INSERT INTO usuario(ci, nombres, apellido_paterno, apellido_materno, id_rol) VALUES(?, ?, ?, ?, ?)";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, usuario.getCi());
            ps.setString(2, usuario.getNombres());
            ps.setString(3, usuario.getApellidoPaterno());
            ps.setString(4, usuario.getApellidoMaterno());
            ps.setInt(5, usuario.getIdRol());
            
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void update(Usuario usuario) throws Exception {
        try {
            this.conectar();
            String sql = "UPDATE usuario SET ci = ?, nombres = ?, apellido_paterno = ?, apellido_materno = ?, id_rol = ? WHERE id_usuario = ?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, usuario.getCi());
            ps.setString(2, usuario.getNombres());
            ps.setString(3, usuario.getApellidoPaterno());
            ps.setString(4, usuario.getApellidoMaterno());
            ps.setInt(5, usuario.getIdRol());
            ps.setInt(6, usuario.getId());
            
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
            String sql = "DELETE FROM usuario WHERE id_usuario = ?";
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
    public Usuario getById(int id) throws Exception {
        Usuario usuario = new Usuario();
        try {
            this.conectar();
            String sql = "SELECT id_usuario, ci, nombres, apellido_paterno, apellido_materno, usuario, u.id_rol, r.nombre nombre_rol\n" +
"FROM usuario u LEFT JOIN rol r ON(u.id_rol=r.id_rol) WHERE id_usuario = ?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                usuario.setId(rs.getInt("id_usuario"));
                usuario.setCi(rs.getInt("ci"));
                usuario.setNombres(rs.getString("nombres"));
                usuario.setApellidoPaterno(rs.getString("apellido_paterno"));
                usuario.setApellidoMaterno(rs.getString("apellido_materno"));
                usuario.setUsuario(rs.getString("usuario"));
                usuario.setIdRol(rs.getInt("id_rol"));
                usuario.setNombreRol("nombre_rol");
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return usuario;
    }

    @Override
    public List<Usuario> getAll() throws Exception {
        List<Usuario> lista = new ArrayList<Usuario>();
        try {
            this.conectar();
            String sql = "SELECT id_usuario, ci, nombres, apellido_paterno, apellido_materno, usuario, u.id_rol, r.nombre nombre_rol\n" +
"FROM usuario u LEFT JOIN rol r ON(u.id_rol=r.id_rol)";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id_usuario"));
                usuario.setCi(rs.getInt("ci"));
                usuario.setNombres(rs.getString("nombres"));
                usuario.setApellidoPaterno(rs.getString("apellido_paterno"));
                usuario.setApellidoMaterno(rs.getString("apellido_materno"));
                usuario.setUsuario(rs.getString("usuario"));
                usuario.setIdRol(rs.getInt("id_rol"));
                usuario.setNombreRol(rs.getString("nombre_rol"));
                lista.add(usuario);
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