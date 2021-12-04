package com.emergentes.controlador;

import com.emergentes.dao.MesaDAO;
import com.emergentes.dao.MesaDAOimpl;
import com.emergentes.modelo.Mesa;
import com.emergentes.utiles.Logueado;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MesaControlador", urlPatterns = {"/MesaControlador"})
public class MesaControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Logueado log = new Logueado(request, response);
        try {
            MesaDAO dao = new MesaDAOimpl();
            Mesa mesa = new Mesa();
            int id;
            
            String action = (request.getParameter("action")!=null)? request.getParameter("action"): "view";
            if(action.equals("add")){
                request.setAttribute("mesa", mesa);
                request.getRequestDispatcher("frmMesa").forward(request, response);
            } else if(action.equals("edit")){
                id = Integer.parseInt(request.getParameter("id"));
                mesa = dao.getById(id);
                request.setAttribute("mesa", mesa);
                request.getRequestDispatcher("frmMesa").forward(request, response);
            } else if(action.equals("change")){
                id = Integer.parseInt(request.getParameter("id"));
                mesa = dao.getById(id);
                
                dao = new MesaDAOimpl();
                if(mesa.isOcupada()){
                    dao.desocupar(id);
                } else {
                    dao.ocupar(id);
                }
                response.sendRedirect("MesaControlador");
            }else if(action.equals("delete")){
                id = Integer.parseInt(request.getParameter("id"));
                dao.delete(id);
                response.sendRedirect("MesaControlador");
            } else if(action.equals("view")){
                List<Mesa> listaMesas = dao.getAll();
                request.setAttribute("listaMesas", listaMesas);
                request.getRequestDispatcher("mesas.jsp").forward(request, response);
            }
        } catch (Exception e) {
            System.out.println("Error en MesaControlador GET: "+e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Logueado log = new Logueado(request, response);
        MesaDAO dao = new MesaDAOimpl();
        int id = Integer.parseInt(request.getParameter("hdnId"));
        String nombre = request.getParameter("txt");
        int idEncargado = Integer.parseInt(request.getParameter("cb"));
        boolean ocupada = Integer.parseInt(request.getParameter("rb"))==1;
        
        Mesa mesa = new Mesa();
        mesa.setNombre(nombre);
        mesa.setEncargado(idEncargado);
        mesa.setOcupada(ocupada);
        
        if(id==0){ // Nuevo Mesa
            try {
            dao.insert(mesa);
            } catch (Exception ex) {
                System.out.println("Error al insertar Mesa: "+ ex.getMessage());
            }
        } else { // Edición de Mesa
            try {
            dao.update(mesa);
            } catch (Exception ex) {
                System.out.println("Error al actualizar Mesa: "+ ex.getMessage());
            }
        }
    }
}