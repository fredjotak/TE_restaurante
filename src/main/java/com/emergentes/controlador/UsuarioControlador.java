package com.emergentes.controlador;

import com.emergentes.dao.RolDAO;
import com.emergentes.dao.RolDAOimpl;
import com.emergentes.dao.UsuarioDAO;
import com.emergentes.dao.UsuarioDAOimpl;
import com.emergentes.modelo.Rol;
import com.emergentes.modelo.Usuario;
import com.emergentes.utiles.Logueado;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
            }
        } catch (Exception e) {
            System.out.println("Error GET Usuario: "+e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Logueado log = new Logueado(request, response);
        UsuarioDAO dao = new UsuarioDAOimpl();
        int id = Integer.parseInt(request.getParameter("hdnId"));
        int ci = Integer.parseInt(request.getParameter("nroCI"));
        String nombres = request.getParameter("txtNombres");
        String apellidoPaterno = request.getParameter("txtApellidoPaterno");
        String apellidoMaterno = request.getParameter("txtApellidoMaterno");
        int idRol = Integer.parseInt(request.getParameter("cbRol"));
        
        Usuario usuario = new Usuario();
        usuario.setCi(ci);
        usuario.setNombres(nombres);
        usuario.setApellidoPaterno(apellidoPaterno);
        usuario.setApellidoMaterno(apellidoMaterno);
        usuario.setIdRol(idRol);
        
        if(id==0){ // Nuevo Usuario
            try {
            dao.insert(usuario);
            } catch (Exception ex) {
                System.out.println("Error al insertar en Usuario: "+ ex.getMessage());
            }
        } else { // Edición de Usuario
            try {
            dao.update(usuario);
            } catch (Exception ex) {
                System.out.println("Error al actualizar en Usuario: "+ ex.getMessage());
            }
        }
        response.sendRedirect("UsuarioControlador");
    }
}