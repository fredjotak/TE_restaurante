package com.emergentes.controlador;

import com.emergentes.dao.MesaDAO;
import com.emergentes.dao.MesaDAOimpl;
import com.emergentes.dao.UsuarioDAO;
import com.emergentes.dao.UsuarioDAOimpl;
import com.emergentes.modelo.Mesa;
import com.emergentes.modelo.Usuario;
import com.emergentes.utiles.Logueado;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
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
            UsuarioDAO usuarioDAO = new UsuarioDAOimpl();
            List<Usuario> listaEncargados = usuarioDAO.getAll();
            if(action.equals("add")){
                request.setAttribute("mesa", mesa);
                request.setAttribute("listaEncargados", listaEncargados);
                request.getRequestDispatcher("frmMesa.jsp").forward(request, response);
            } else if(action.equals("edit")){
                id = Integer.parseInt(request.getParameter("id"));
                mesa = dao.getById(id);
                request.setAttribute("mesa", mesa);
                request.setAttribute("listaEncargados", listaEncargados);
                request.getRequestDispatcher("frmMesa.jsp").forward(request, response);
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
            } else if(action.equals("search")){
                String variableBuscar = request.getParameter("vm");
                List<Mesa> listaMesas = new ArrayList<Mesa>();
                try {
                    listaMesas = dao.getFilterNombre(variableBuscar.trim());
                } catch (Exception e) {
                    System.out.println("Error al buscar en mesas");
                }
                String json = new Gson().toJson(listaMesas);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json);
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
        String nombre = request.getParameter("txtNombre");
        int idEncargado = Integer.parseInt(request.getParameter("cbIdEncargado"));
        boolean ocupada = Integer.parseInt(request.getParameter("rbOcupada"))==1;
        
        Mesa mesa = new Mesa();
        mesa.setId(id);
        mesa.setNombre(nombre);
        mesa.setEncargado(idEncargado);
        mesa.setOcupada(ocupada);
        
        if(id==0){ // Nuevo Mesa
            try {
                dao.insert(mesa);
            } catch (Exception ex) {
                System.out.println("Error al insertar Mesa: "+ ex.getMessage());
            }
        } else { // Edici??n de Mesa
            try {
                dao.update(mesa);
            } catch (Exception ex) {
                System.out.println("Error al actualizar Mesa: "+ ex.getMessage());
            }
        }
        response.sendRedirect("MesaControlador");
    }
}