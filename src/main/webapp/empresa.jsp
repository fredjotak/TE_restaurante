<%@page import="com.emergentes.modelo.Usuario"%>
<%
    Usuario usuario = new Usuario();
    try {
        usuario = (Usuario)session.getAttribute("user");
        if(usuario.getUsuario().equals("")){
            response.sendRedirect("LoginControl");
        }
    } catch(Exception e){
        response.sendRedirect("LoginControl");
    }
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/styleGlobal.css">
        <link rel="stylesheet" href="css/estilos.css">
        <title>Reporte de Ventas</title>
    </head>
    <body>
        <header class="cabezera">
            <div class="logo">
                <img src="imagenes/logoPrin3.png" alt="">
            </div>
            <div class="logo_menu">
                <div class="barra"></div>
            </div>
        </header>
        <main class="principal">
            <jsp:include page="WEB-INF/menu.jsp">
                <jsp:param name="opcion" value="datos_empresa" />
            </jsp:include>
            
            <article class="contenedor-contenido">
                <div class="contenido">
                <div class="contenido__titulo">
                    <h2>DATOS DE EMPRESA</h2>
                </div>
                <div class="contenido__cuerpo configuracion">
                    <div class="configuracion__logo">
                        <div class="conf-img">
                            <img src="./imagenes/logoPrin3.png" alt="">
                        </div><!--
                        <div class="conf-select-img">
                            <button>Seleccionar Archivo</button>
                        </div>-->
                    </div>
                    <div class="configuracion__datos">
                        <form action="">
                            <div class="rows">
                                <label for="razon-social">Razón Social</label>
                                <input type="text" name="razon-social" id="razon-social" value="8899-ACR" disabled>
                            </div>
                            <div class="rows">
                                <label for="giro">Giro:</label>
                                <input type="text" name="giro" id="giro" value="2021" disabled>
                            </div>
                            <div class="rows">
                                <label for="doc-fiscal">No. Documento Fiscal</label>
                                <input type="text" name="doc-fiscal" id="doc-fiscal" value="100002143" disabled>
                            </div>
                            <div class="rows">
                                <label for="telefono">Telefono</label>
                                <input type="text" name="telefono" id="telefono" value="+591 76543210" disabled>
                            </div>
                            <div class="rows">
                                <label for="email">Email</label>
                                <input type="email" name="email" id="email" value="frealdi.corp@gmail.com" disabled>
                            </div>
                            <div class="rows">
                                <label for="direccion">Direccion</label>
                                <input type="text" name="direccion" id="direccion" value="Av. 16 de Julio S/N #999" disabled>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            </article>
        </main>
        <script src="js/index.js"></script>
    </body>
</html>