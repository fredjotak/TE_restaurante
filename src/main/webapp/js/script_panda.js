((d) => {
    let formulario = d.querySelector("form");
    let pass = d.querySelector("#contrasenia");
    let contenedor = d.querySelector(".contenedor__panda");
    let submit = d.querySelector(".boton");


})(document);
let listaErrores = [
    'Usuario o contraseñas inválidos !!!',
    'El correo ya en encuentra registrado !!!',
    'Las contraseñas no coinciden !!!',
    'Código inválido. Intente nuevamente',
];

function mostrarError(error){
    let formulario = document.querySelector("form");
    let vistaError = document.querySelector(".alerta");
    vistaError.innerText = listaErrores[error];
    formulario.classList.add("entrada-incorrecta");
    setTimeout(function(){
        formulario.classList.remove('entrada-incorrecta');
    }, 3000);
}