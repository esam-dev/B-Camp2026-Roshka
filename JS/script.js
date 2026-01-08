
const jugador1Input = document.getElementById('jugador1');
const jugador2Input = document.getElementById('jugador2'); 
const simboloSelect = document.getElementById('simbolo');
const iniciarBtn = document.getElementById('Iniciar Boton');
const reiniciarBtn = document.getElementById('Reiniciar Boton ');
const celdas = document.querySelectorAll('# tablero td');

let tablero = ["", "", "", "", "", "", "", "", ""];
let turno = "X";
let jugador1 = "";
let jugador2 = "";
let juegoActivo = false;

function iniciarJuego(){
    jugador1 = jugador1Input .value.trim();
    jugador2 = jugador2Input.value.trim();
    const simboloJugador1 = simboloSelect.value;

    if (!jugador1 || !jugador2 || simboloJugador1){
        aler("Por favor completa los campos correspondientes")
    }
}