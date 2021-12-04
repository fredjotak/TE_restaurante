const d = document;

d.addEventListener('DOMContentLoaded', (e)=>{
    console.log("Hola");
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
    })

});