package com.emergentes.controlador;

import com.emergentes.dao.RolDAO;
import com.emergentes.dao.RolDAOimpl;
import com.emergentes.dao.UsuarioDAO;
import com.emergentes.dao.UsuarioDAOimpl;
import com.emergentes.modelo.Rol;
import com.emergentes.modelo.Usuario;
import com.emergentes.utiles.Logueado;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.util.Properties;

@WebServlet(name = "UsuarioControlador", urlPatterns = {"/UsuarioControlador"})
public class UsuarioControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Logueado log = new Logueado(request, response);
        try {
            UsuarioDAO dao = new UsuarioDAOimpl();
            int id;
            Usuario usuario = new Usuario();
            
            RolDAO rolDAO = new RolDAOimpl();
            List<Rol> listaRol = rolDAO.getAll();
            
            String action = (request.getParameter("action")!=null)? request.getParameter("action"): "view";
            System.out.println("opcion es "+ action);
            if(action.equals("add")){
                request.setAttribute("usuario", usuario);
                request.setAttribute("listaRol", listaRol);
                request.getRequestDispatcher("frmUsuario.jsp").forward(request, response);
            } else if(action.equals("edit")){
                id = Integer.parseInt(request.getParameter("id"));
                usuario = dao.getById(id);
                request.setAttribute("usuario", usuario);
                request.setAttribute("listaRol", listaRol);
                request.getRequestDispatcher("frmUsuario.jsp").forward(request, response);
            } else if(action.equals("delete")){
                id = Integer.parseInt(request.getParameter("id"));
                dao.delete(id);
                response.sendRedirect("UsuarioControlador");
            } else if(action.equals("view")){
                List<Usuario> listaUsuarios = dao.getAll();
                request.setAttribute("listaUsuarios", listaUsuarios);
                request.getRequestDispatcher("usuarios.jsp").forward(request, response);
            } else if(action.equals("search")){
                String variableBuscar = request.getParameter("v");
                List<Usuario> listaUsuarios = new ArrayList<Usuario>();
                try {
                    //System.out.println("loser");
                    listaUsuarios = dao.getAllFilterCiNombres(variableBuscar.trim());
                } catch (Exception e) {
                    System.out.println("Error al consultar usuarios");
                }
                String json = new Gson().toJson(listaUsuarios);
                //System.out.println("JSON>>"+json);
                //System.out.println("Convirtio el primero");
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json);
            }
        } catch (Exception e) {
            System.out.println("Error GET Usuario: "+e.getMessage()+e.toString()+"____"+e.getCause()+"___"+e.getClass());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BufferedReader reader = request.getReader(); //Se toma la fuente de datos de la solicitud
        Gson gson = new Gson();
        Usuario usuario = new Usuario();
        Properties properties = gson.fromJson(reader, Properties.class);
        UsuarioDAO dao = new UsuarioDAOimpl();
        usuario.setId(Integer.parseInt(properties.getProperty("hdnId")));
        usuario.setCi(Integer.parseInt(properties.getProperty("nroCI")));
        usuario.setNombres(properties.getProperty("txtNombres"));
        usuario.setApellidoPaterno(properties.getProperty("txtApellidoPaterno"));
        usuario.setApellidoMaterno(properties.getProperty("txtApellidoMaterno"));
        usuario.setIdRol(Integer.parseInt(properties.getProperty("cbRol")));
        
        if(usuario.getId()==0){ // Nuevo Usuario
            try {
                dao.insert(usuario);
                /*
                String json = new Gson().toJson(properties);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json);
                */
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("{\"notificacion\": \"NUEVO USUARIO REGISTRADO EXITOSAMENTE\"}");
            } catch (Exception ex) {
                System.out.println("Error al insertar en Usuario: "+ ex.getMessage());
            }
        } else { // Edición de Usuario
            try {
                dao.update(usuario);

                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("{\"notificacion\": \"USUARIO ACTUALIZADO EXITOSAMENTE\"}");
            } catch (Exception ex) {
                System.out.println("Error al actualizar en Usuario: "+ ex.getMessage());
            }
        }
    }
}