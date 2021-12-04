package com.emergentes.utiles;

import com.emergentes.modelo.Usuario;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Logueado {
    public Logueado(HttpServletRequest request, HttpServletResponse response){
        Usuario usuario = new Usuario();
        System.out.println("Por Loguaedo");
        try {
            HttpSession sesion = request.getSession();
            if(sesion.getAttribute("user")!=null){
                usuario = (Usuario)sesion.getAttribute("user");
                if(usuario.getUsuario().equals("")){
                    response.sendRedirect("LoginControl");
                }
            } else {
                response.sendRedirect("LoginControl");
            }
        } catch(IOException e){
            try {
                response.sendRedirect("LoginControl");
            } catch (IOException ex) {
                System.out.println("Error inesperado");
            }
        }
    }
}