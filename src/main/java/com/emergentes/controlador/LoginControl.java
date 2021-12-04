package com.emergentes.controlador;

import com.emergentes.modelo.Usuario;
import com.emergentes.utiles.Validate;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "LoginControl", urlPatterns = {"/LoginControl"})
public class LoginControl extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("login.jsp");
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String usuario = request.getParameter("user");
            String conatrasenia = request.getParameter("contrasenia");
            
            Validate validat = new Validate();
            if(validat.checkUser(usuario, conatrasenia)){
                System.out.println("Se conecto "+usuario);
                Usuario user = validat.getUser();
                HttpSession sesion = request.getSession();
                sesion.setAttribute("user", user);
                response.sendRedirect("MesaControladorDisponibles");
            } else {
                System.out.println("incorrecto");
                response.sendRedirect("LoginControl");
            }
        } catch (Exception ex) {
            Logger.getLogger(LoginControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}