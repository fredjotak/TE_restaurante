<%
    String opcion = request.getParameter("opcion");
%>
<aside class="contenedor-menu">
    <div class="contenedor-menu__titulo">
        <h2>Menu</h2>
    </div>
    <nav class="menu">
        <ul class="lista">
            <li class="lista__item <%=(opcion.equals("principal")? "lista__item--select": "")%>" data-hijos="false">
                <div class="lista-contenido">
                    <svg class="lista-contenido__img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
                        <path d="M3 13h1v7c0 1.103.897 2 2 2h12c1.103 0 2-.897 2-2v-7h1a1 1 0 0 0 .707-1.707l-9-9a.999.999 0 0 0-1.414 0l-9 9A1 1 0 0 0 3 13zm7 7v-5h4v5h-4zm2-15.586 6 6V15l.001 5H16v-5c0-1.103-.897-2-2-2h-4c-1.103 0-2 .897-2 2v5H6v-9.586l6-6z"></path>
                    </svg>
                    <a href="#">Inicio</a>
                </div>
            <%--</li>
            <li class="lista__item <%=(opcion.equals("clientes")? "lista__item--select": "")%>" data-hijos="false">
                <div class="lista-contenido">
                    <svg class="lista-contenido__img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
                        <path d="M12 2a5 5 0 1 0 5 5 5 5 0 0 0-5-5zm0 8a3 3 0 1 1 3-3 3 3 0 0 1-3 3zm9 11v-1a7 7 0 0 0-7-7h-4a7 7 0 0 0-7 7v1h2v-1a5 5 0 0 1 5-5h4a5 5 0 0 1 5 5v1z"></path>
                    </svg>
                    <!-- <img class="lista-contenido__img" src="recursos/user.svg" alt=""> -->
                    <a href="#">Clientes</a>
                </div>
            </li>--%>
            <li class="lista__item <%=(opcion.equals("productos") || opcion.equals("categorias")? "lista__item--select": "")%>" data-hijos="true">
                <div class="lista-contenido">
                    <!-- <img class="lista-contenido__img" src="recursos/cube.svg" alt=""> -->
                    <svg class="lista-contenido__img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
                        <path d="m21.406 6.086-9-4a1.001 1.001 0 0 0-.813 0l-9 4c-.02.009-.034.024-.054.035-.028.014-.058.023-.084.04-.022.015-.039.034-.06.05a.87.87 0 0 0-.19.194c-.02.028-.041.053-.059.081a1.119 1.119 0 0 0-.076.165c-.009.027-.023.052-.031.079A1.013 1.013 0 0 0 2 7v10c0 .396.232.753.594.914l9 4c.13.058.268.086.406.086a.997.997 0 0 0 .402-.096l.004.01 9-4A.999.999 0 0 0 22 17V7a.999.999 0 0 0-.594-.914zM12 4.095 18.538 7 12 9.905l-1.308-.581L5.463 7 12 4.095zM4 16.351V8.539l7 3.111v7.811l-7-3.11zm9 3.11V11.65l7-3.111v7.812l-7 3.11z"></path>
                    </svg>
                    <a href="#">Productos</a>
                    <img class="lista-contenido__img lista-contenido__img--right" src="recursos/flecha.svg" alt="">
                </div>
                <ul class="lista-opciones">
                    <li class="lista-opciones__item">
                        <a href="CategoriaControlador">Categoria</a>
                    </li>
                    <li class="lista-opciones__item">
                        <a href="ProductoControlador">Productos</a>
                    </li>
                </ul>
            </li>
            <li class="lista__item <%=(opcion.equals("mesas") || opcion.equals("mesas_disponibles")? "lista__item--select": "")%>" data-hijos="true">
                <div class="lista-contenido">
                    <!-- <img class="lista-contenido__img" src="recursos/mesa1.svg" alt=""> -->
                    <svg class="lista-contenido__img" xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" >
                        <path d="M10 3H4a1 1 0 0 0-1 1v6a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4a1 1 0 0 0-1-1zM9 9H5V5h4v4zm5 2h6a1 1 0 0 0 1-1V4a1 1 0 0 0-1-1h-6a1 1 0 0 0-1 1v6a1 1 0 0 0 1 1zm1-6h4v4h-4V5zM3 20a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1v-6a1 1 0 0 0-1-1H4a1 1 0 0 0-1 1v6zm2-5h4v4H5v-4zm8 5a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1v-6a1 1 0 0 0-1-1h-6a1 1 0 0 0-1 1v6zm2-5h4v4h-4v-4z"></path>
                    </svg>
                    <a href="#">Mesas</a>
                    <img class="lista-contenido__img lista-contenido__img--right" src="recursos/flecha.svg" alt="">
                </div>
                <ul class="lista-opciones">
                    <li class="lista-opciones__item">
                        <a href="MesaControlador">Administrar Mesas</a>
                    </li>
                    <li class="lista-opciones__item">
                        <a href="MesaControladorDisponibles">Control de Mesas</a>
                    </li>
                    <li class="lista-opciones__item">
                        <a href="#">Comandas</a>
                    </li>
                </ul>
            </li>
            <li class="lista__item <%=(opcion.equals("ventasRapidas")? "lista__item--select": "")%>" data-hijos="false">
                <div class="lista-contenido">
                    <svg class="lista-contenido__img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
                        <path d="M21.822 7.431A1 1 0 0 0 21 7H7.333L6.179 4.23A1.994 1.994 0 0 0 4.333 3H2v2h2.333l4.744 11.385A1 1 0 0 0 10 17h8c.417 0 .79-.259.937-.648l3-8a1 1 0 0 0-.115-.921zM17.307 15h-6.64l-2.5-6h11.39l-2.25 6z"></path>
                        <circle cx="10.5" cy="19.5" r="1.5"></circle>
                        <circle cx="17.5" cy="19.5" r="1.5"></circle>
                    </svg>
                    <!-- <img class="lista-contenido__img" src="recursos/cart.svg" alt=""> -->
                    <a href="#">Ventas Rapidas</a>
                </div>
            </li>
            <li class="lista__item <%=(opcion.equals("reporte_ventas")? "lista__item--select": "")%>" data-hijos="true">
                <div class="lista-contenido">
                    <!-- <img class="lista-contenido__img" src="recursos/mesa1.svg" alt=""> -->
                    <svg class="lista-contenido__img" version="1.1" id="Layer_1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" width="24" height="24"
	 viewBox="0 0 492.308 492.308" style="enable-background:new 0 0 492.308 492.308;" xml:space="preserve">
<g>
	<g>
		<path d="M355.813,0H32.264v492.308h427.779V104.231L355.813,0z M361.582,33.615l64.846,64.846h-64.846V33.615z M440.351,472.615
			H51.957V19.692h289.933v98.462h98.462V472.615z"/>
	</g>
</g>
<g>
	<g>
		<path d="M315.543,331.885v109.947h92.308V331.885H315.543z M388.159,422.139h-52.923v-70.563h52.923V422.139z"/>
	</g>
</g>
<g>
	<g>
		<path d="M199.995,272.808v169.024h92.308V272.808H199.995z M272.611,422.139h-52.923V292.5h52.923V422.139z"/>
	</g>
</g>
<g>
	<g>
		<path d="M84.447,213.731v228.101h92.308V213.731H84.447z M157.062,422.139h-52.923V233.423h52.923V422.139z"/>
	</g>
</g>
<g>
</g>
<g>
</g>
<g>
</g>
<g>
</g>
<g>
</g>
<g>
</g>
<g>
</g>
<g>
</g>
<g>
</g>
<g>
</g>
<g>
</g>
<g>
</g>
<g>
</g>
<g>
</g>
<g>
</g>
</svg>
                    <a href="#">Reportes</a>
                    <img class="lista-contenido__img lista-contenido__img--right" src="recursos/flecha.svg" alt="">
                </div>
                <ul class="lista-opciones">
                    <li class="lista-opciones__item">
                        <a href="ServicioControlador">Reporte de Ventas</a>
                    </li>
                    <li class="lista-opciones__item">
                        <a href="">Reporte de productos</a>
                    </li>
                </ul>
            </li>
            <li class="lista__item <%=(opcion.equals("usuarios") || opcion.equals("datos_empresa")? "lista__item--select select": "")%>" data-hijos="true">
                <div class="lista-contenido">
                    <svg class="lista-contenido__img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
                        <path d="M12 16c2.206 0 4-1.794 4-4s-1.794-4-4-4-4 1.794-4 4 1.794 4 4 4zm0-6c1.084 0 2 .916 2 2s-.916 2-2 2-2-.916-2-2 .916-2 2-2z"></path><path d="m2.845 16.136 1 1.73c.531.917 1.809 1.261 2.73.73l.529-.306A8.1 8.1 0 0 0 9 19.402V20c0 1.103.897 2 2 2h2c1.103 0 2-.897 2-2v-.598a8.132 8.132 0 0 0 1.896-1.111l.529.306c.923.53 2.198.188 2.731-.731l.999-1.729a2.001 2.001 0 0 0-.731-2.732l-.505-.292a7.718 7.718 0 0 0 0-2.224l.505-.292a2.002 2.002 0 0 0 .731-2.732l-.999-1.729c-.531-.92-1.808-1.265-2.731-.732l-.529.306A8.1 8.1 0 0 0 15 4.598V4c0-1.103-.897-2-2-2h-2c-1.103 0-2 .897-2 2v.598a8.132 8.132 0 0 0-1.896 1.111l-.529-.306c-.924-.531-2.2-.187-2.731.732l-.999 1.729a2.001 2.001 0 0 0 .731 2.732l.505.292a7.683 7.683 0 0 0 0 2.223l-.505.292a2.003 2.003 0 0 0-.731 2.733zm3.326-2.758A5.703 5.703 0 0 1 6 12c0-.462.058-.926.17-1.378a.999.999 0 0 0-.47-1.108l-1.123-.65.998-1.729 1.145.662a.997.997 0 0 0 1.188-.142 6.071 6.071 0 0 1 2.384-1.399A1 1 0 0 0 11 5.3V4h2v1.3a1 1 0 0 0 .708.956 6.083 6.083 0 0 1 2.384 1.399.999.999 0 0 0 1.188.142l1.144-.661 1 1.729-1.124.649a1 1 0 0 0-.47 1.108c.112.452.17.916.17 1.378 0 .461-.058.925-.171 1.378a1 1 0 0 0 .471 1.108l1.123.649-.998 1.729-1.145-.661a.996.996 0 0 0-1.188.142 6.071 6.071 0 0 1-2.384 1.399A1 1 0 0 0 13 18.7l.002 1.3H11v-1.3a1 1 0 0 0-.708-.956 6.083 6.083 0 0 1-2.384-1.399.992.992 0 0 0-1.188-.141l-1.144.662-1-1.729 1.124-.651a1 1 0 0 0 .471-1.108z"></path>
                    </svg>
                    <!-- <img class="lista-contenido__img" src="recursos/cog.svg" alt=""> -->
                    <a href="#">Configuracion</a>
                    <img class="lista-contenido__img lista-contenido__img--right" src="recursos/flecha.svg" alt="">
                </div>
                <ul class="lista-opciones">
                    <li class="lista-opciones__item">
                        <a href="empresa.jsp">Empresa</a>
                    </li>
                    <li class="lista-opciones__item">
                        <a href="UsuarioControlador">Usuarios</a>
                    </li>
                    <li class="lista-opciones__item">
                        <a href="LogOutControl">Cerrar Sesión</a>
                    </li>
                </ul>
            </li>
        </ul>
    </nav>
</aside>