<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Iniciar Sesión</title>
        <link rel="stylesheet" href="css/styleGlobal.css">
        <link rel="stylesheet" href="css/estilos-login.css">
    </head>
    <body>
        <div class="banner">
            <video autoplay  muted loop>
                <source src="imagenes/Vídeo Promocional Restaurante San Blas Jatetxea Comida Casera.mp4" type="video/mp4">
            </video>
            <div class="contenido">
                <form action="LoginControl" method="POST">
                    <h1>Iniciar Sesión</h1>
                    <div class="form-img">
                        <img src="imagenes/logoPrin3.png" alt="">
                    </div>
                    <div class="form-grupo">
                        <input class="form-grupo__input" id="user" name="user" type="text" required>
                        <label class="form-grupo__label" for="user">Usuario</label>
                    </div>
                    <div class="form-grupo">
                        <input class="form-grupo__input" id="contrasenia" name="contrasenia" type="password" required>
                        <label class="form-grupo__label" for="contrasenia">Contraseña</label>
                        <button class="boton">Iniciar</button>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>