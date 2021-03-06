const d = document;
const $table = d.querySelector('.crud-table--categoria'),
      $template = d.getElementById('crud-template').content,
      $formBuscarCategoria = d.forms["buscarCategoria"],
      $fragment = d.createDocumentFragment(),
      $inputform = $formBuscarCategoria.elements["vc"];

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
    console.log(xhr);
    xhr.open(method || 'GET',url);
    xhr.setRequestHeader("Content-type","application/json; charset=utf-8");
    xhr.send(JSON.stringify(data));  
}


$inputform.addEventListener('keyup',(e) =>{
    let a = $table.querySelector("tbody");
    while(a.hasChildNodes()){
        a.removeChild(a.firstChild);
    }
    ajax({
        url: `CategoriaControlador?action=search&vc=${$inputform.value}`,
        exito:(res) => {
            console.log(res)
            res.forEach(el => {
                $template.querySelector(".cu-id").textContent = el.id;
                $template.querySelector(".cu-nombre").textContent = el.nombre;
                $template.querySelector(".cu-tipo").textContent = el.tipo;
                $template.querySelector(".cu-edit").href = `CategoriaControlador?action=edit&id=${el.id}`;
                $template.querySelector(".cu-delete").href = `CategoriaControlador?action=delete&id=${el.id}`;
                let $clone = d.importNode($template,true);
                $fragment.appendChild($clone);
            });
            
            
            $table.querySelector("tbody").appendChild($fragment);
            setTimeout(animacionIconos ,100);
        },
        error:(err) => {
            $table.insertAdjacentHTML("afterend",`<p><b>${err}</b></p>`);
        },
    });
});

d.addEventListener('DOMContentLoaded', (e)=>{
    actualizarCategorias();
});

function animacionIconos(){
    const contAcciones = d.querySelectorAll('.iconos-tables');
    contAcciones.forEach(elemento =>{
        elemento.addEventListener('click', e =>{
            console.log(elemento.className);
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
}

const actualizarCategorias = function(){
    ajax({
        url: 'CategoriaControlador?action=get-all',
        exito:(res) => {
            console.log(res)
            res.forEach(el => {
                $template.querySelector(".cu-id").textContent = el.id;
                $template.querySelector(".cu-nombre").textContent = el.nombre;
                $template.querySelector(".cu-tipo").textContent = el.tipo;
                $template.querySelector(".cu-edit").href = `CategoriaControlador?action=edit&id=${el.id}`;
                $template.querySelector(".cu-delete").href = `CategoriaControlador?action=delete&id=${el.id}`;
                let $clone = d.importNode($template,true);
                $fragment.appendChild($clone);
            });
            
            $table.querySelector("tbody").appendChild($fragment);
            setTimeout(animacionIconos ,100);
        },
        error:(err) => {
            $table.insertAdjacentHTML("afterend",`<p><b>${err}</b></p>`);
        },
    });
    
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
    $inputform.value = '';
}