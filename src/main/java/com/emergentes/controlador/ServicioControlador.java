package com.emergentes.controlador;

import com.emergentes.dao.DetalleServicioDAO;
import com.emergentes.dao.DetalleServicioDAOimpl;
import com.emergentes.dao.ServicioDAO;
import com.emergentes.dao.ServicioDAOimpl;
import com.emergentes.modelo.DetalleServicio;
import com.emergentes.modelo.Servicio;
import com.emergentes.utiles.Logueado;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ServicioControlador", urlPatterns = {"/ServicioControlador"})
public class ServicioControlador extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Logueado log = new Logueado(request, response);
        try {
            ServicioDAO dao = new ServicioDAOimpl();
            Servicio servicio = new Servicio();
            int id = 0;
            
            String action = (request.getParameter("action")!=null)? request.getParameter("action"): "view";
            if(action.equals("add")){
                request.setAttribute("servicio", servicio);
                request.getRequestDispatcher("frmServicio").forward(request, response);
            } else if(action.equals("edit")){
                id = Integer.parseInt(request.getParameter("id"));
                servicio = dao.getById(id);
                request.setAttribute("servicio", servicio);
                request.getRequestDispatcher("frmServicio").forward(request, response);
            } else if(action.equals("details")){
                DetalleServicioDAO daodet = new DetalleServicioDAOimpl();
                id = Integer.parseInt(request.getParameter("id"));
                servicio = dao.getById(id);
                request.setAttribute("servicio", servicio);
                List<DetalleServicio> detalleServicios = daodet.getListIdServicio(servicio.getId());
                request.setAttribute("detalleServicios", detalleServicios);
                request.getRequestDispatcher("detalle_servicio.jsp").forward(request, response);
            } else if(action.equals("delete")){
                id = Integer.parseInt(request.getParameter("id"));
                dao.delete(id);
                response.sendRedirect("ServicioControlador");
            } else if(action.equals("view")){
                List<Servicio> listaServicios = dao.getAll();
                for(Servicio ls: listaServicios){
                    System.out.println(ls.toString());
                }
                request.setAttribute("listaServicios", listaServicios);
                request.getRequestDispatcher("servicios.jsp").forward(request, response);
            }
        } catch (Exception e) {
            System.out.println("Error en ServicioControlador GET: "+e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Logueado log = new Logueado(request, response);
    }
}