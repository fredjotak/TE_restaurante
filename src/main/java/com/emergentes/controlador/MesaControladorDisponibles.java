package com.emergentes.controlador;

import com.emergentes.dao.MesaDAO;
import com.emergentes.dao.MesaDAOimpl;
import com.emergentes.dao.ServicioTemporalDAO;
import com.emergentes.dao.ServicioTemporalDAOimpl;
import com.emergentes.modelo.Mesa;
import com.emergentes.modelo.ServicioTemporal;
import com.emergentes.utiles.Logueado;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MesaControladorDisponibles", urlPatterns = {"/MesaControladorDisponibles"})
public class MesaControladorDisponibles extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Logueado log = new Logueado(request, response);
       try {
            MesaDAO dao = new MesaDAOimpl();
            Mesa mesa = new Mesa();
            int id;
            
            String action = (request.getParameter("action")!=null)? request.getParameter("action"): "view";
            
            if(action.equals("go")){
                id = Integer.parseInt(request.getParameter("id"));
                mesa = dao.getById(id);
                
                dao = new MesaDAOimpl();
                if(!mesa.isOcupada()){
                    dao.ocupar(id);
                }
                // nueva venta
                
                dao = new MesaDAOimpl();
                mesa = dao.getById(id);
                request.setAttribute("mesa", mesa);
                
                ServicioTemporalDAO daoTMP = new ServicioTemporalDAOimpl();
                List<ServicioTemporal> listaTemporal = daoTMP.getFilterIdMesa(mesa.getId());
                request.setAttribute("listaTemporal", listaTemporal);
                
                request.getRequestDispatcher("frmMesaProductos.jsp").forward(request, response);
                //response.sendRedirect("MesaControladorDisponibles");
            } else if(action.equals("cancel")){
                id = Integer.parseInt(request.getParameter("id"));
                mesa = dao.getById(id);
                
                dao = new MesaDAOimpl();
                dao.desocupar(id);
                response.sendRedirect("MesaControladorDisponibles");
            } else if(action.equals("view")){
                List<Mesa> listaMesas = dao.getAll();
                request.setAttribute("listaMesas", listaMesas);
                request.getRequestDispatcher("mesasDisponibles.jsp").forward(request, response);
            }
        } catch (Exception e) {
            System.out.println("Error en MesaControladorDisponibles GET: "+e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Logueado log = new Logueado(request, response);
    }
}