package com.emergentes.controlador;

import com.emergentes.dao.DetalleServicioDAO;
import com.emergentes.dao.DetalleServicioDAOimpl;
import com.emergentes.dao.MesaDAO;
import com.emergentes.dao.MesaDAOimpl;
import com.emergentes.dao.ProductoDAO;
import com.emergentes.dao.ProductoDAOimpl;
import com.emergentes.dao.ServicioDAO;
import com.emergentes.dao.ServicioDAOimpl;
import com.emergentes.dao.ServicioTemporalDAO;
import com.emergentes.dao.ServicioTemporalDAOimpl;
import com.emergentes.modelo.DetalleServicio;
import com.emergentes.modelo.Mesa;
import com.emergentes.modelo.Producto;
import com.emergentes.modelo.Servicio;
import com.emergentes.modelo.ServicioTemporal;
import com.emergentes.modelo.Usuario;
import com.emergentes.utiles.Logueado;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
                
                ProductoDAO daoProd = new ProductoDAOimpl();
                List<Producto> listaProductos = daoProd.getAll();
                request.setAttribute("listaProductos", listaProductos);
                float total = 0;
                for(int i=0; i<listaTemporal.size(); i++){
                    total += (listaTemporal.get(i).getCantidad()*listaTemporal.get(i).getPrecio());
                }
                request.setAttribute("total", total);
                
                request.getRequestDispatcher("frmMesaProductos.jsp").forward(request, response);
                //response.sendRedirect("MesaControladorDisponibles");
            } else if(action.equals("cancel")){
                id = Integer.parseInt(request.getParameter("id"));
                mesa = dao.getById(id);
                
                dao = new MesaDAOimpl();
                dao.desocupar(id);
                
                ServicioTemporalDAO daoT = new ServicioTemporalDAOimpl();
                daoT.deleteByMesa(id);
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
        String action = request.getParameter("hdnOption");
        int idMesa = 0, cantidad = 0;
        String idProducto = request.getParameter("cbIdProducto");
        try {
            idMesa = Integer.parseInt(request.getParameter("hdnIdMesa"));
        } catch (Exception e) {
            response.sendRedirect("MesaControladorDisponibles");
        }
        try {
            cantidad = Integer.parseInt(request.getParameter("nroCantidad"));
        } catch (Exception e) {
            System.out.println("No hay cantidad");
        }
        System.out.println("son mesa"+idMesa+", prod:"+idProducto+", op:"+action);
        if(action.equals("add")){
            ServicioTemporalDAO dao = new ServicioTemporalDAOimpl();
            ServicioTemporal servicioTMP = new ServicioTemporal();
            servicioTMP.setIdMesa(idMesa);
            servicioTMP.setCantidad(cantidad);
            servicioTMP.setIdProducto(idProducto);
            servicioTMP.setComentario("Sin observación");
            try {
                dao.insert(servicioTMP);
            } catch (Exception ex) {
                System.out.println("Error al inertar al carrito "+ex.getMessage());
            }
            response.sendRedirect("MesaControladorDisponibles?action=go&id="+idMesa);
        } else if(action.equals("confirm")){
            System.out.println("====== Nueva venta");
            //int idEncargado = Integer.parseInt(request.getParameter("idEnc"))
            
            HttpSession sesion = request.getSession();
            Usuario usuario = new Usuario();
            usuario = (Usuario)sesion.getAttribute("user");
            
            ServicioDAO dao = new ServicioDAOimpl();
            Servicio servicio = new Servicio();
            servicio.setIdMesa(idMesa);
            servicio.setCancelada(true);
            servicio.setIdEncargado(usuario.getId());
            
            int idVenta  = 0;
            try {
                idVenta = dao.insert(servicio);
                System.out.println("Id es :"+idVenta);
                ServicioTemporalDAO daoTMP = new ServicioTemporalDAOimpl();
                List<ServicioTemporal> lista = daoTMP.getFilterIdMesa(idMesa);
                System.out.println("obtiene lista");
                for(ServicioTemporal item : lista ){
                    try {
                        DetalleServicioDAO daoDetalle = new DetalleServicioDAOimpl();
                        DetalleServicio detalle = new DetalleServicio();
                        detalle.setCantidad(item.getCantidad());
                        detalle.setIdProducto(item.getIdProducto());
                        detalle.setIdServicio(idVenta);
                        daoDetalle.insert(detalle);
                    } catch (Exception e) {
                        System.out.println("-> "+item.toString());
                    }
                }
                System.out.println("aui");
                daoTMP = new ServicioTemporalDAOimpl();
                daoTMP.deleteByMesa(idMesa);
                
                
                MesaDAO mesaD = new MesaDAOimpl();
                mesaD.desocupar(idMesa);
                
                response.sendRedirect("MesaControladorDisponibles");
            } catch (Exception ex) {
                System.out.println("Error al generar llave"+ ex.getMessage());
            }
        }
        
    }
}