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
        <title>Detalle de Venta ${servicio.id}</title>
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
                <jsp:param name="opcion" value="reporte_ventas" />
            </jsp:include>
            
            <article class="contenedor-contenido">
                <div class="contenido">
                <div class="contenido__titulo">
                    <h2>DETALLE DE VENTA ${servicio.id}</h2>
                </div>
                <div class="contenido__cuerpo cuerpo-det-mesa">
                    <div class="cuerpo-aside"></div>
                    <div class="cuerpo-article">
                        <div class="buscar">
                            <form action="">
                                <label for="cantidad">Cant</label>
                                <input type="text" name="cantidad" id="cantidad">
                                <label for="codigo">Codigo</label>
                                <input type="text" name="codigo" id="codigo">
                                <!--<div class="submit">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24">
                                        <path d="M10 18a7.952 7.952 0 0 0 4.897-1.688l4.396 4.396 1.414-1.414-4.396-4.396A7.952 7.952 0 0 0 18 10c0-4.411-3.589-8-8-8s-8 3.589-8 8 3.589 8 8 8zm0-14c3.309 0 6 2.691 6 6s-2.691 6-6 6-6-2.691-6-6 2.691-6 6-6z">
                                        </path>
                                    </svg>
                                    <input type="submit" value="Buscar">
                                </div>-->
                            </form>
                        </div>
                        <table>
                            <tr>
                                <th>ID</th>
                                <th>CANTIDAD</th>
                                <th>DESCRIPCION</th>
                                <th>PRECIO U. Bs.</th>
                                <th>SUBTOTAL</th>
                            </tr>
                            <c:forEach var="item" items="${detalleServicios}">
                                <tr>
                                    <td>${item.id}</td>
                                    <td>${item.cantidad}</td>
                                    <td>${item.nombreProducto}</td>
                                    <td>${item.precioU}</td>
                                    <td>${item.subtotal}</td>
                                </tr>
                            </c:forEach>
                            <tr>
                                <td colspan="3"></td>
                                <td><b>Total Bs.</b></td>
                                <td>${servicio.total}</td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
            </article>
        </main>
        <script src="js/index.js"></script>
    </body>
</html>