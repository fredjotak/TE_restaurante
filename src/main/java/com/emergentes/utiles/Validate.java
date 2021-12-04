package com.emergentes.utiles;

import com.emergentes.dao.UsuarioDAO;
import com.emergentes.dao.UsuarioDAOimpl;
import com.emergentes.modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Validate extends ConexionDB{
    Connection conn = this.conectar();
    private Usuario user;
    
    public boolean checkUser(String usuario, String contrasenia){
        boolean resultado = false;
        PreparedStatement pr;
        String sql = "SELECT * FROM usuario WHERE usuario = ? AND contrasenia = MD5(?)";
        try {
            pr = conn.prepareStatement(sql);
            pr.setString(1, usuario);
            pr.setString(2, contrasenia);
            
            ResultSet rs = pr.executeQuery();
            resultado = rs.next();
            if(resultado){
                int id = rs.getInt("id_usuario");
                UsuarioDAO dao = new UsuarioDAOimpl();
                try {
                    this.user = dao.getById(id);
                } catch (Exception ex) {
                    System.out.println("Error en Validate CheckUser");
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error al autenticar");
        }
        return resultado;
    }
    
    public Usuario getUser(){
        return user;
    }
}