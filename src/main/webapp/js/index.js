const d = document;
const $table = d.querySelector('.crud-table--usuarios'),
      $template = d.getElementById('crud-template').content,
      $formBuscarUsuarios = d.forms["BuscarUsuarios"],
      $fragment = d.createDocumentFragment(),
      $inputform = $formBuscarUsuarios.elements["v"],
      $formCrud = d.querySelector(".form_edit-nuevo"),
      $modalCrud = d.querySelector(".modal"),
      $cerrarform = $formCrud.elements["cerrarForm"],
      $enviarform = $formCrud.elements["enviarForm"],
      $selectRolform = $formCrud.elements["cbRol"];
      
      //console.log($enviarform, '\n', $inputform);
d.querySelector(".abrirform").addEventListener('click', ()=> {$modalCrud.classList.add("modal--mostrar");})

$cerrarform.addEventListener('click', e => {
    $modalCrud.classList.remove("modal--mostrar");
    $formCrud.hdnId.value = "";
    $formCrud.txtNombres.value = "";
    $formCrud.txtApellidoPaterno.value = "";
    $formCrud.txtApellidoMaterno.value = "";
    $formCrud.nroCI.value = "";
    $formCrud.cbRol.value = "";
    readUsuario();
});


d.querySelectorAll(".cu-edit").forEach(el => {
    el.addEventListener('click', e => {
       console.log(e.target); 
    });
})

const readUsuario = function(){
    let a = $table.querySelector("tbody");
    while(a.hasChildNodes()){
        a.removeChild(a.firstChild);
    }
    ajax({
        url: `UsuarioControlador?action=search&v=${$inputform.value}`,
        method: 'GET',
        exito:(res) => {
            res.forEach(el => {
                $template.querySelector(".cu-id").textContent = el.id;
                $template.querySelector(".cu-ci").textContent = el.ci;
                $template.querySelector(".cu-nombre").textContent = el.nombres;
                $template.querySelector(".cu-paterno").textContent = el.apellidoPaterno;
                $template.querySelector(".cu-materno").textContent = el.apellidoMaterno;
                $template.querySelector(".cu-usuario").textContent = el.usuario;
                $template.querySelector(".cu-rol").textContent = el.nombreRol;
                $template.querySelector(".cu-edit").setAttribute("data-id", `${el.id}`);
                $template.querySelector(".cu-delete").setAttribute("data-id", `${el.id}`); //.href = `UsuarioControlador?action=delete&id=${el.id}`;
                let $clone = d.importNode($template,true);
                $fragment.appendChild($clone);
            });
            
            
            $table.querySelector("tbody").appendChild($fragment);
            /*setTimeout(animacionIconos ,100);*/
        },
        error:(err) => {
            $table.insertAdjacentHTML("afterend",`<p><b>${err}</b></p>`);
        },
        data: {
            
        }
    });
} 
     
const ajax = (options) => {
    let {url,method,exito,error,data} = options;
    const xhr = new XMLHttpRequest();
    
    xhr.addEventListener("readystatechange", e =>{
        if(xhr.readyState !== 4) return;
        if(xhr.status >= 200 && xhr.status < 300){
            let json = JSON.parse(xhr.responseText);
            exito(json);
        }else{
            let message = xhr.statusText || "Ocurrio un error";
            error(`Error ${xhr.status}: ${message}`)
        }
    });  
    console.log(data);
    xhr.open(method || 'GET',url); 
    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    xhr.send(JSON.stringify(data)); 
}

$formCrud.addEventListener('submit', e => {
    e.preventDefault();
    ajax({
            method: "POST",
            url: `UsuarioControlador`,
            exito: (res) => { 
                activarAlerta(res.notificacion);
                readUsuario();
                $formCrud.hdnId.value = "";
                $formCrud.txtNombres.value = "";
                $formCrud.txtApellidoPaterno.value = "";
                $formCrud.txtApellidoMaterno.value = "";
                $formCrud.nroCI.value = "";
                $formCrud.cbRol.value = "";
            },
            error: (err) => {console.log(err)},
            data: {
                hdnId: $formCrud.hdnId.value,
                txtNombres: $formCrud.txtNombres.value,
                txtApellidoPaterno: $formCrud.txtApellidoPaterno.value,
                txtApellidoMaterno: $formCrud.txtApellidoMaterno.value,
                nroCI: $formCrud.nroCI.value,
                cbRol: $formCrud.cbRol.value
            }
        });
        readUsuario();
        /*setTimeout(()=>{$modalCrud.classList.remove("modal--mostrar");},500);*/
});

$inputform.addEventListener('keyup',(e) =>{
    readUsuario();
});

function enviar(){
    ajax({
        url: 'RolControlador?action=get-all',
        exito:(res) => {
            let i=0;
            res.forEach(el => {
                $selectRolform.options[i] = new Option(`${el.nombre}`,`${el.id}`);
                i++;
            });
            
        },
        error: (err) => {console.log(err)}
    });
}

d.addEventListener('DOMContentLoaded', (e)=>{
    
    readUsuario();
    
    enviar();
    
    const item = d.querySelectorAll(".lista__item");

    item.forEach( elemento =>{
        elemento.addEventListener('click',(e) =>{
            item.forEach( ele => {
                ele.classList.remove('lista__item--select');
            });
            elemento.classList.add('lista__item--select');
            if(elemento.dataset.hijos == 'true'){
                elemento.classList.toggle('select');
                if(elemento.className.match(/ select/i)){
                    elemento.firstElementChild.lastElementChild.src = "recursos/flecha-abajo.svg";
                }else{
                    elemento.firstElementChild.lastElementChild.src = "recursos/flecha.svg";
                }
            }
        });
    });
});

d.addEventListener('click', e => {
    if(e.target.matches('.iconos-tables') || e.target.matches('.iconos-tables :first-child') || e.target.matches('.icono-tables :nth-child(2)')){
        e.path.forEach(el => {
            if(el.className == "iconos-tables"){
                if(el.lastElementChild.className.match(/mostrar-edit-eli/i)){
                    el.lastElementChild.classList.remove('mostrar-edit-eli');
                }
                else{
                    d.querySelectorAll('.iconos-tables').forEach(elementos =>{
                        elementos.lastElementChild.classList.remove('mostrar-edit-eli');
                    });
                    el.lastElementChild.classList.add('mostrar-edit-eli');
                }
            }
        });
    } 
    
    if(e.target.matches('.cont-edit') || e.target.matches('.cont-edit *')){
        /*e.target.getElementById("crud-titulo").innerHTML = "Editar Usuario"
        $modalCrud.classList.add("modal--mostrar");*/
        
        $modalCrud.querySelector("#crud-titulo").innerHTML = "Editar Usuario";
        $modalCrud.querySelector("#crud-boton-enviar").innerHTML = "Actualizar";
        e.path.forEach(el =>  {
            if(el.className == 'column-datos'){
                $formCrud.hdnId.value = el.querySelector(".cu-id").innerHTML;
                $formCrud.txtNombres.value = el.querySelector(".cu-nombre").innerHTML;
                $formCrud.txtApellidoPaterno.value = el.querySelector(".cu-paterno").innerHTML;
                $formCrud.txtApellidoMaterno.value = el.querySelector(".cu-materno").innerHTML;
                $formCrud.nroCI.value = el.querySelector(".cu-ci").innerHTML;
                $formCrud.cbRol.value = el.querySelector(".cu-rol").innerHTML;
            }
        })
        
        $modalCrud.classList.add("modal--mostrar");
    } else if (e.target.matches('.cont-eli') || e.target.matches('.cont-eli *')){
        e.path.forEach(el =>  {
            if(el.className == 'column-datos'){ 
                // Eliminar usuario
                console.log('click en '+ el.querySelector(".cu-nombre").innerHTML+", id"+el.querySelector(".cu-id").innerHTML);
                ajax({
                    url: `UsuarioControlador?action=delete&id=`+el.querySelector(".cu-id").innerHTML,
                    method: 'GET',
                    exito:(res) => {
                        activarAlerta(res.notificacion);
                        readUsuario();
                    },
                    error:(err) => {
                        $table.insertAdjacentHTML("afterend",`<p><b>${err}</b></p>`);
                    },
                    data: {}
                });
            }
        });
    }
});

/*d.addEventListener('click', e => {
    console.log(e.target);
   if(e.target.matches('.iconos-tables') || e.target.matches('.iconos-tables *')){
       console.log(e.target);
   } 
});*/

/*function animacionIconos(){
    const contAcciones = d.querySelectorAll('.iconos-tables');
    contAcciones.forEach(elemento =>{
        elemento.addEventListener('click', e =>{
            if(elemento.lastElementChild.className.match(/mostrar-edit-eli/i)){
                console.log('yes');
                elemento.lastElementChild.classList.remove('mostrar-edit-eli');
            }
            else{
                contAcciones.forEach(elementos =>{
                    elementos.lastElementChild.classList.remove('mostrar-edit-eli');
                });
                elemento.lastElementChild.classList.add('mostrar-edit-eli');
            }
        });
    });
}*/

const as = document.querySelector("#notify");
function activarAlerta(mensaje){
    let t = as.querySelector("p");
    t.innerText = mensaje;
    as.classList.add("mostrar");
    setTimeout(function(){ as.classList.remove("mostrar"); }, 2000);
}