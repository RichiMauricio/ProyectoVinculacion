/**
 * Created by Mauricio on 28/04/2017.
 */
num=0;
function crear(obj) {
    num++;
    fi = document.getElementById('fiel'); // 1
    contenedor = document.createElement('div'); // 2
    contenedor.id = 'div'+num; // 3
    fi.appendChild(contenedor); // 4

    ele = document.createElement('input'); // 5
    ele.type = 'file'; // 6
    ele.name = 'fil'+num; // 6
    contenedor.appendChild(ele); // 7

    ele = document.createElement('input'); // 5
    ele.type = 'button'; // 6
    ele.value = 'Borrar'; // 8
    ele.name = 'div'+num; // 8
    ele.onclick = function () {borrar(this.name)} // 9
    contenedor.appendChild(ele); // 7
}
function borrar(obj) {
    fi = document.getElementById('fiel'); // 1
    fi.removeChild(document.getElementById(obj)); // 10
}