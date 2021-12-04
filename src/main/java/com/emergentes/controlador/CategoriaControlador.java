package com.emergentes.controlador;

import com.emergentes.dao.CategoriaDAO;
import com.emergentes.dao.CategoriaDAOimpl;
import com.emergentes.modelo.Categoria;
import com.emergentes.utiles.Logueado;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CategoriaControlador", urlPatterns = {"/CategoriaControlador"})
public class CategoriaControlador extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Logueado log = new Logueado(request, response);
            CategoriaDAO dao = new CategoriaDAOimpl();
            Categoria categoria = new Categoria();
            int id = 0;
            
            String action = (request.getParameter("action")!=null)? request.getParameter("action"): "view";
            if(action.equals("add")){
                request.setAttribute("categoria", categoria);
                request.getRequestDispatcher("frmCategoria.jsp").forward(request, response);
            } else if(action.equals("edit")){
                id = Integer.parseInt(request.getParameter("id"));
                categoria = dao.getById(id);
                request.setAttribute("categoria", categoria);
                request.getRequestDispatcher("frmCategoria.jsp").forward(request, response);
            } else if(action.equals("delete")){
                id = Integer.parseInt(request.getParameter("id"));
                dao.delete(id);
                response.sendRedirect("CategoriaControlador");
            } else if(action.equals("view")){
                List<Categoria> listaCategorias = dao.getAll();
                request.setAttribute("listaCategorias", listaCategorias);
                request.getRequestDispatcher("categorias.jsp").forward(request, response);
            }
        } catch (Exception e) {
            System.out.println("Error en CategoriaControlador GET: "+e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Logueado log = new Logueado(request, response);
        CategoriaDAO dao = new CategoriaDAOimpl();
        int id = Integer.parseInt(request.getParameter("hdnId"));
        String nombre = request.getParameter("txtNombre");
        String tipo = request.getParameter("txtTipo");
        
        Categoria categoria = new Categoria();
        categoria.setId(id);
        categoria.setNombre(nombre);
        categoria.setTipo(tipo);
        
        if(id==0){ // Nuevo Categoria
            try {
            dao.insert(categoria);
            } catch (Exception ex) {
                System.out.println("Error al insertar Categoria: "+ ex.getMessage());
            }
        } else { // Edición de Categoria
            try {
            dao.update(categoria);
            } catch (Exception ex) {
                System.out.println("Error al actualizar Categoria: "+ ex.getMessage());
            }
        }
        response.sendRedirect("CategoriaControlador");
    }
}