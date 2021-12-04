<%@page import="com.emergentes.modelo.Usuario"%>
<%
    Usuario usuario = new Usuario();
    System.out.println("Instancia");
    try {
        usuario = (Usuario)session.getAttribute("user");
        System.out.println("Es en aqui: "+usuario.getUsuario());
        if(usuario.getUsuario().equals("")){
            response.sendRedirect("LoginControl");
        }
    } catch(Exception e){
        System.out.println("Ocurrio un error");
        response.sendRedirect("LoginControl");
        System.out.println("Ir \n");
    }
%>