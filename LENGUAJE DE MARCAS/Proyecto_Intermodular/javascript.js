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

// Obtener todas las tablas con clase 'resaltable'
var tablas = document.getElementsByClassName('resaltable');

// Recorrer cada tabla encontrada
for (var i = 0; i < tablas.length; i++) {
    var tabla = tablas[i];

    // Obtener los <tbody> (asumimos uno por tabla)
    var cuerpos = tabla.getElementsByTagName('tbody');
    if (cuerpos.length === 0) continue;

    // Obtener todas las filas <tr> dentro del <tbody>
    var filas = cuerpos[0].getElementsByTagName('tr');

    for (var j = 0; j < filas.length; j++) {
        var fila = filas[j];

        // Usar una función anónima para capturar correctamente el contexto de cada fila
        (function(fila) {
            // Evento cuando el mouse entra a la fila
            fila.addEventListener('mouseover', function () {
                fila.style.backgroundColor = '#d0f0fd';
                fila.style.cursor = 'pointer';
            });

            // Evento cuando el mouse sale de la fila
            fila.addEventListener('mouseout', function () {
                fila.style.backgroundColor = '';
            });
        })(fila); // Llamamos a la función anónima con el contexto de cada fila
    }
}



