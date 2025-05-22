function cambiarEstilo() { // creo la funcion
    var body = document.body;

    if (body.style.backgroundColor === 'rgb(255, 255, 204)') { // compruebo que el color sea blanco si es asi cambio el color
        body.style.backgroundColor = '#ADD8E6';   // Azul oscuro
        body.style.color = '#00000';             // Gris claro
    } else {
        body.style.backgroundColor = '#ffffcc';   // Amarillo pálido
        body.style.color = '#333333';             // Gris oscuro
    }
}

// Espera a que todo el contenido del documento HTML esté cargado
document.addEventListener('DOMContentLoaded', function () {

    // Selecciona todas las filas <tr> dentro del <tbody> de las tablas con la clase 'resaltable'
    const filas = document.querySelectorAll('.resaltable tbody tr');

    // Recorre cada fila seleccionada
    filas.forEach(fila => {

        // Agrega un evento cuando el mouse pasa por encima de la fila
        fila.addEventListener('mouseover', function () {
            // Cambia el color de fondo de la fila a un celeste suave
            fila.style.backgroundColor = '#d0f0fd';

            // Cambia el cursor del mouse a "pointer" (mano), indicando que se puede interactuar
            fila.style.cursor = 'pointer';
        });

        // Agrega un evento cuando el mouse sale de la fila
        fila.addEventListener('mouseout', function () {
            // Restaura el color de fondo original (sin color especial)
            fila.style.backgroundColor = '';
        });
    });
});

