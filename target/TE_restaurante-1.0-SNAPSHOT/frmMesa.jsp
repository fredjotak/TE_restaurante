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
        <title>${(mesa.id==0)? "Nuevo Mesa": "Editar Mesa"}</title>
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
                <jsp:param name="opcion" value="mesas" />
            </jsp:include>
            
            <article class="contenedor-contenido">
                <div class="contenido">
                    <div class="contenido__titulo" style="justify-content: initial; align-items: center;">
                        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"><path d="m7 17.013 4.413-.015 9.632-9.54c.378-.378.586-.88.586-1.414s-.208-1.036-.586-1.414l-1.586-1.586c-.756-.756-2.075-.752-2.825-.003L7 12.583v4.43zM18.045 4.458l1.589 1.583-1.597 1.582-1.586-1.585 1.594-1.58zM9 13.417l6.03-5.973 1.586 1.586-6.029 5.971L9 15.006v-1.589z"></path><path d="M5 21h14c1.103 0 2-.897 2-2v-8.668l-2 2V19H8.158c-.026 0-.053.01-.079.01-.033 0-.066-.009-.1-.01H5V5h6.847l2-2H5c-1.103 0-2 .897-2 2v14c0 1.103.897 2 2 2z"></path></svg>
                        <h2>${(mesa.id==0)? "NUEVA MESA": "EDITAR MESA"}</h2>
                    </div>
                    <div class="contenido__cuerpo">
                        <form action="MesaControlador" class="form_edit-nuevo mesas" method="POST">
                            <input type="hidden" name="hdnId" value="${mesa.id}">
                            <div class="rows">
                                <label for="txtNombre">Nombre:</label>
                                <input type="text" name="txtNombre" value="${mesa.nombre}" id="txtNombre" placeholder="Nombre de la mesa" required>
                            </div>
                            <div class="rows">
                                <label for="carnet">Descripción: </label>
                                <input type="text">
                            </div>
                            <div class="rows">
                                <label for="cbIdEncargado">Encargado:</label>
                                <select name="cbIdEncargado" id="cbIdEncargado">
                                    <option value="">-- Seleccione encargado --</option>
                                    <c:forEach var="item" items="${listaEncargados}">
                                        <option value="${item.id}" ${(mesa.encargado==item.id)? "selected": ""}>${item.nombres} ${item.apellidoPaterno} ${item.apellidoMaterno}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="rows">
                                <label for="rbOcupada">Estado: </label>
                                <select name="rbOcupada" id="rbOcupada">
                                    <option value="1" ${(mesa.ocupada)? "selected": ""}>Ocupado</option>
                                    <option value="0" ${(mesa.ocupada)? "": "selected"}>Disponible</option>
                                </select>
                            </div>
                            <div class="rows">
                                <a href="MesaControlador" class="botones botton--cerrar">Cerrar</a>
                            </div>
                            <div class="rows">
                                <input class="botones" type="submit" value='${(mesa.id==0)? "Registrar": "Actualizar"}'>
                            </div>
                        </form>
                    </div>
                </div>
            </article>
        </main>
        <script src="js/index.js"></script>
    </body>
</html>