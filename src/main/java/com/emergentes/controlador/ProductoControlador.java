package com.emergentes.controlador;

import com.emergentes.dao.CategoriaDAO;
import com.emergentes.dao.CategoriaDAOimpl;
import com.emergentes.dao.ProductoDAO;
import com.emergentes.dao.ProductoDAOimpl;
import com.emergentes.modelo.Categoria;
import com.emergentes.modelo.Producto;
import com.emergentes.utiles.Logueado;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ProductoControlador", urlPatterns = {"/ProductoControlador"})
public class ProductoControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Logueado log = new Logueado(request, response);
        try {
            ProductoDAO dao = new ProductoDAOimpl();
            Producto producto = new Producto();
            String id = "";
            
            String action = (request.getParameter("action")!=null)? request.getParameter("action"): "view";
            if(action.equals("add")){
                CategoriaDAO catDao = new CategoriaDAOimpl();
                List<Categoria> listaCategoria = catDao.getAll();
                request.setAttribute("listaCategoria", listaCategoria);
                request.setAttribute("producto", producto);
                request.getRequestDispatcher("frmProducto.jsp").forward(request, response);
            } else if(action.equals("edit")){
                CategoriaDAO catDao = new CategoriaDAOimpl();
                id = request.getParameter("id");
                producto = dao.getById(id);
                request.setAttribute("producto", producto);
                List<Categoria> listaCategoria = catDao.getAll();
                request.setAttribute("listaCategoria", listaCategoria);
                request.getRequestDispatcher("frmProducto.jsp").forward(request, response);
            } else if(action.equals("delete")){
                id = request.getParameter("id");
                dao.delete(id);
                response.sendRedirect("ProductoControlador");
            } else if(action.equals("view")){
                List<Producto> listaProductos = dao.getAll();
                request.setAttribute("listaProductos", listaProductos);
                request.getRequestDispatcher("productos.jsp").forward(request, response);
            }
        } catch (Exception e) {
            System.out.println("Error en ProductoControlador GET: "+e.getMessage());
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Logueado log = new Logueado(request, response);
        ProductoDAO dao = new ProductoDAOimpl();
        String id = request.getParameter("hdnId");
        String nombre = request.getParameter("txtNombre");
        int idCategoria = Integer.parseInt(request.getParameter("cbIdCategoria"));
        Float precio = Float.parseFloat(request.getParameter("nroPrecio"));
        
        Producto producto = new Producto();
        producto.setId(id);
        producto.setNombre(nombre);
        producto.setIdCategoria(idCategoria);
        producto.setPrecio(precio);
        
        if(id.equals("")){ // Nuevo Producto
            try {
            dao.insert(producto);
            } catch (Exception ex) {
                System.out.println("Error al insertar en Producto: "+ ex.getMessage());
            }
        } else { // Edición de Usuario
            try {
            dao.update(producto);
            } catch (Exception ex) {
                System.out.println("Error al actualizar en Producto: "+ ex.getMessage());
            }
        }
        response.sendRedirect("ProductoControlador");
    }
}