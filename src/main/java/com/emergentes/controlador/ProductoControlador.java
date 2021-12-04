package com.emergentes.controlador;

import com.emergentes.dao.ProductoDAO;
import com.emergentes.dao.ProductoDAOimpl;
import com.emergentes.modelo.Producto;
import com.emergentes.utiles.Logueado;
import java.io.IOException;
import java.io.PrintWriter;
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
                request.setAttribute("producto", producto);
                request.getRequestDispatcher("frmProducto").forward(request, response);
            } else if(action.equals("edit")){
                id = request.getParameter("id");
                producto = dao.getById(id);
                request.setAttribute("producto", producto);
                request.getRequestDispatcher("frmProducto").forward(request, response);
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
    }
}