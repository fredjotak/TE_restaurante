package com.emergentes.controlador;

import com.emergentes.dao.RolDAO;
import com.emergentes.dao.RolDAOimpl;
import com.emergentes.modelo.Rol;
import com.emergentes.modelo.Usuario;
import com.emergentes.utiles.Logueado;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Properties;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "RolControlador", urlPatterns = {"/RolControlador"})
public class RolControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Logueado log = new Logueado(request, response);
        try {
            //String action = (request.getParameter("action")!=null)? request.getParameter("action"): "view";
            String action = (request.getParameter("action")!=null)? request.getParameter("action"): "none";
            RolDAO rolDAO = new RolDAOimpl();
            if(action.equals("get-id")){
                int id = Integer.parseInt(request.getParameter("id"));
                Rol rol = rolDAO.getById(id);
                String json = new Gson().toJson(rol);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json);
            } else if(action.equals("delete")){
                int id = Integer.parseInt(request.getParameter("id"));
                rolDAO.delete(id);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("{\"notificacion\": \"ROL ELIMINADO EXITOSAMENTE\"}");
            } else if(action.equals("get-all")){
                List<Rol> listaRol = rolDAO.getAll();
                String json = new Gson().toJson(listaRol);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json);
            }
        } catch (Exception e) {
            System.out.println("Error GET Rol : "+e.getMessage()+e.toString()+"____"+e.getCause()+"___"+e.getClass());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
}