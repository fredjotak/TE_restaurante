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
        <title>${(producto.id.equals(""))? "Nuevo Producto": "Editar Producto"}</title>
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
                <jsp:param name="opcion" value="productos" />
            </jsp:include>
            
            <article class="contenedor-contenido">
                <div class="contenido">
                    <div class="contenido__titulo" style="justify-content: initial; align-items: center;">
                        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"><path d="m7 17.013 4.413-.015 9.632-9.54c.378-.378.586-.88.586-1.414s-.208-1.036-.586-1.414l-1.586-1.586c-.756-.756-2.075-.752-2.825-.003L7 12.583v4.43zM18.045 4.458l1.589 1.583-1.597 1.582-1.586-1.585 1.594-1.58zM9 13.417l6.03-5.973 1.586 1.586-6.029 5.971L9 15.006v-1.589z"></path><path d="M5 21h14c1.103 0 2-.897 2-2v-8.668l-2 2V19H8.158c-.026 0-.053.01-.079.01-.033 0-.066-.009-.1-.01H5V5h6.847l2-2H5c-1.103 0-2 .897-2 2v14c0 1.103.897 2 2 2z"></path></svg>
                        <h2>${(producto.id.equals(""))? "NUEVO PRODUCTO": "EDITAR PRODUCTO"}</h2>
                    </div>
                    <div class="contenido__cuerpo">
                        <form action="ProductoControlador" method="POST" class="form_edit-nuevo productos">
                            <input type="hidden" name="hdnId" value="${producto.id}">
                            <div class="rows">
                                <label for="txtId">Codigo:</label>
                                <input type="text" name="txtId" value="${producto.id}" id="txtId" readonly>
                            </div>
                            <div class="rows">
                                <label for="txtNombre">Nombre:</label>
                                <input type="text" id="txtNombre" name="txtNombre" value="${producto.nombre}" placeholder="Nombre del producto" required>
                            </div>
                            <div class="rows">
                                <label for="txtDescripcion">Descripción:</label>
                                <input type="text" name="txtDescripcion" id="txtDescripcion" placeholder="Descripción del producto" readonly>
                            </div>
                            <div class="rows">
                                <label for="cbIdCategoria">Categoria: </label>
                                <select name="cbIdCategoria" id="cbIdCategoria" required>
                                    <option value="">Seleccione Categoria</option>
                                    <c:forEach var="item" items="${listaCategoria}">
                                        <option value="${item.id}" ${(item.id==producto.idCategoria?"selected": "")}>${item.nombre}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="rows">
                                <label for="nroPrecio">Precio de Venta: </label>
                                <input type="number" name="nroPrecio" value="${producto.precio}"  id="nroPrecio" min="0" step=".1" max="100000" required>
                            </div>
                            <div class="rows">
                                <label for="">Estado: </label>
                                <select name="estado">
                                    <option value="1">Activo</option>
                                    <option value="2">Inactivo</option>
                                </select>
                            </div>
                            <div class="rows">
                                <a href="ProductoControlador" class="botones botton--cerrar">Cerrar</a>
                            </div>
                            <div class="rows">
                                <input class="botones" type="submit" value='${(producto.id.equals(""))? "Registrar": "Actualizar"}'>
                            </div>
                        </form>
                    </div>
                </div>
            </article>
        </main>
        <script src="js/index.js"></script>
    </body>
</html>