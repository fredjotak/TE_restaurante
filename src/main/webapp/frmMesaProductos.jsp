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
        <title>Atención en ${mesa.nombre}</title>
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
                <jsp:param name="opcion" value="mesas_disponibles" />
            </jsp:include>
            
            <article class="contenedor-contenido">
                <div class="contenido">
                    <div class="contenido__titulo">
                        <h2>DETALLE DE MESA ${mesa.nombre}</h2>
                    </div>
                    <div class="contenido__cuerpo cuerpo-det-mesa">
                        <div class="cuerpo-aside">
                            <div class="datos-mesa">
                                <div class="datos-mesa__img">
                                    <img src="imagenes/restaurante.png" alt="" srcset="">
                                </div>
                                <div class="datos-mesa__datos">
                                    <h2>${mesa.nombre}</h2>
                                    <a href="MesaControladorDisponibles">Todas las mesas</a>
                                </div>
                            </div>
                            <div class="opciones-control-mesas">
                                <button>Cuenta Individual</button>
                                <a href="MesaControladorDisponibles?action=cancel&id=${mesa.id}" class="botones">Anular Pedido</a>
                            </div>
                        </div>
                        <div class="cuerpo-article">
                            <div class="buscar">
                                <form action="">
                                    <label for="cantidad">Cant</label>
                                    <input type="text" name="cantidad" id="cantidad">
                                    <label for="codigo">Codigo</label>
                                    <input type="text" name="codigo" id="codigo">
                                    <div class="submit">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24">
                                            <path d="M10 18a7.952 7.952 0 0 0 4.897-1.688l4.396 4.396 1.414-1.414-4.396-4.396A7.952 7.952 0 0 0 18 10c0-4.411-3.589-8-8-8s-8 3.589-8 8 3.589 8 8 8zm0-14c3.309 0 6 2.691 6 6s-2.691 6-6 6-6-2.691-6-6 2.691-6 6-6z">
                                            </path>
                                        </svg>
                                        <input type="submit" value="Buscar">
                                    </div>
                                </form>
                            </div>
                            <table>
                                <tr>
                                    <th>COD</th>
                                    <th>CANT</th>
                                    <th>DESCRIP</th>
                                    <th>PRECIO Bs.</th>
                                    <th>DESC %</th>
                                    <th>Subtotal</th>
                                </tr>
                                <c:forEach var="item" items="${listaTemporal}">
                                    <tr>
                                        <td>${item.idProducto}</td>
                                        <td>${item.cantidad}</td>
                                        <td>${item.nombreProducto}</td>
                                        <td>${item.comentario}</td>
                                    </tr>
                                </c:forEach>
                                    <!--<td>1000</td>
                                    <td>3</td>
                                    <td>Arandanos Natural</td>
                                    <td>2.40</td>
                                    <td>0</td>
                                    <td>7.20</td>
                                </tr>
                                <tr>
                                    <td>1010</td>
                                    <td>2</td>
                                    <td>Coca Cola 3lt.</td>
                                    <td>14</td>
                                    <td>0</td>
                                    <td>28</td>
                                </tr>
                                <tr>
                                    <td>1000</td>
                                    <td>3</td>
                                    <td>Arandanos Natural</td>
                                    <td>2.40</td>
                                    <td>0</td>
                                    <td>7.20</td>
                                </tr>-->
                                <tr>
                                    <td colspan="4"></td>
                                    <td><b>Total.</b></td>
                                    <td>42.40</td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div><%--
                <div class="contenido">
                    <div class="contenido__titulo" style="justify-content: initial; align-items: center;">
                        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"><path d="m7 17.013 4.413-.015 9.632-9.54c.378-.378.586-.88.586-1.414s-.208-1.036-.586-1.414l-1.586-1.586c-.756-.756-2.075-.752-2.825-.003L7 12.583v4.43zM18.045 4.458l1.589 1.583-1.597 1.582-1.586-1.585 1.594-1.58zM9 13.417l6.03-5.973 1.586 1.586-6.029 5.971L9 15.006v-1.589z"></path><path d="M5 21h14c1.103 0 2-.897 2-2v-8.668l-2 2V19H8.158c-.026 0-.053.01-.079.01-.033 0-.066-.009-.1-.01H5V5h6.847l2-2H5c-1.103 0-2 .897-2 2v14c0 1.103.897 2 2 2z"></path></svg>
                        <h2>${(usuario.id==0)? "NUEVO USUARIO": "EDITAR USUARIO"}</h2>
                    </div>
                    <div class="contenido__cuerpo">
                        <form action="UsuarioControlador" class="form_edit-nuevo cliente" method="POST">
                            <input type="hidden" name="hdnId" value="${usuario.id}" >
                            <div class="rows">
                                <label for="txtNombres">Nombres:</label>
                                <input type="text" name="txtNombres" value="${usuario.nombres}" id="txtNombres" placeholder="Nombre Completo del Usuario" required>
                            </div>
                            <div class="rows">
                                <label for="txtApellidoPaterno">Apellido Paterno:</label>
                                <input type="text" name="txtApellidoPaterno" value="${usuario.apellidoPaterno}" id="txtApellidoPaterno" required>
                            </div>
                            <div class="rows">
                                <label for="txtApellidoMaterno">Apellido Materno:</label>
                                <input type="text" name="txtApellidoMaterno" value="${usuario.apellidoMaterno}" id="txtApellidoMaterno" required>
                            </div>
                            <div class="rows">
                                <label for="nroCI">Cédula de Identidad </label>
                                <input type="number" name="nroCI" value="${usuario.ci}" id="nroCI" step="1" required>
                            </div>
                            <div class="rows">
                                <label for="cbRol">Rol: </label>
                                <select name="cbRol">
                                    <option value="">-- Seleccione rol --</option>
                                    <c:forEach var="item" items="${listaRol}">
                                        <option value="${item.id}" ${(item.id==usuario.idRol)? "selected": ""}>${item.nombre}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="rows">
                                <a href="UsuarioControlador" class="botones botton--cerrar">Cerrar</a>
                            </div>
                            <div class="rows">
                                <input class="botones" type="submit" value='${(usuario.id==0)? "Registrar": "Actualizar"}'>
                            </div>
                        </form>
                    </div>
                </div>--%>
            </article>
        </main>
        <script src="js/index.js"></script>
    </body>
</html>