package com.emergentes.controlador;

import com.emergentes.dao.CategoriaDAO;
import com.emergentes.dao.CategoriaDAOimpl;
import com.emergentes.modelo.Categoria;
import com.emergentes.utiles.Logueado;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
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
            /*if(action.equals("add")){
                request.setAttribute("categoria", categoria);
                request.getRequestDispatcher("frmCategoria.jsp").forward(request, response);
            } else if(action.equals("edit")){
                id = Integer.parseInt(request.getParameter("id"));
                categoria = dao.getById(id);
                request.setAttribute("categoria", categoria);
                request.getRequestDispatcher("frmCategoria.jsp").forward(request, response);
            } else*/
            System.out.println("opcion categoria: "+action);
            if (action.equals("get-id")){
                id = Integer.parseInt(request.getParameter("id"));
                categoria = dao.getById(id);
                String json = new Gson().toJson(categoria);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json);
            } else if(action.equals("delete")){
                id = Integer.parseInt(request.getParameter("id"));
                dao.delete(id);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("{\"notificacion\": \"CATEGORIA ELIMINADA EXITOSAMENTE\"}");
                //response.sendRedirect("CategoriaControlador");
            } else if(action.equals("view")){
                //List<Categoria> listaCategorias = dao.getAll();
                //request.setAttribute("listaCategorias", listaCategorias);
                request.getRequestDispatcher("categorias.jsp").forward(request, response);
            } else if(action.equals("search")){
                String variableBuscar = request.getParameter("vc");
                List<Categoria> listaCategorias = new ArrayList<Categoria>();
                try {
                    listaCategorias = dao.getFilterNombre(variableBuscar.trim());
                } catch (Exception e) {
                    System.out.println("Error al buscar en categoria");
                }
                String json = new Gson().toJson(listaCategorias);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json);
            } else if(action.equals("get-all")){
                List<Categoria> listaCategorias = dao.getAll();
                String json = new Gson().toJson(listaCategorias);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json);
            }
        } catch (Exception e) {
            System.out.println("Error en CategoriaControlador GET: "+e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Logueado log = new Logueado(request, response);
        /*CategoriaDAO dao = new CategoriaDAOimpl();
        int id = Integer.parseInt(request.getParameter("hdnId"));
        String nombre = request.getParameter("txtNombre");
        String tipo = request.getParameter("txtTipo");
        
        Categoria categoria = new Categoria();
        categoria.setId(id);
        categoria.setNombre(nombre);
        categoria.setTipo(tipo);*/
        BufferedReader reader = request.getReader(); //Se toma la fuente de datos de la solicitud
        Gson gson = new Gson();
        Properties properties = gson.fromJson(reader, Properties.class);
        Categoria categoria = new Categoria();
        System.out.println("Peticion Categoria : "+properties);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("Conectado exitosamente a POST Categoria");
        /*if(id==0){ // Nuevo Categoria
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
        }*/
        //response.sendRedirect("CategoriaControlador");
    }
}