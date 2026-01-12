
const jugador1Input = document.getElementById('jugador1');
const jugador2Input = document.getElementById('jugador2');
const simboloSelect = document.getElementById('simbolo');
const iniciarBtn = document.getElementById('iniciar');
const reiniciarBtn = document.getElementById('reiniciar');
const tableroEl = document.getElementById('tablero');
const estadoEl = document.getElementById('estado');

let tablero = Array(9).fill("");
let turno = "X";
let jugador1 = "";
let jugador2 = "";
let simbolos = { j1: "X", j2: "O" };
let juegoActivo = false;

// funcion de actualizar estados 
function actualizarEstado(mensaje) {
  estadoEl.textContent = mensaje || '';
}

function resetearTablero() {
  tablero = Array(9).fill("");
  document.querySelectorAll('#tablero td').forEach(td => td.textContent = "");
}

// funcion de habiliitar tablero 
function habilitarTablero(habilitar) {
  if (habilitar) {
    tableroEl.classList.remove('deshabilitado');
  } else {
    tableroEl.classList.add('deshabilitado');
  }
}
// funcion de  iniciar juego
function iniciarJuego() {
  jugador1 = jugador1Input.value.trim();
  jugador2 = jugador2Input.value.trim();
  const simboloJugador1 = simboloSelect.value;

  if (!jugador1 || !jugador2 || !simboloJugador1) {
    alert('Por favor completa ambos nombres y selecciona el símbolo para el Jugador 1.');
    return;
  }

  simbolos.j1 = simboloJugador1;
  simbolos.j2 = simboloJugador1 === 'X' ? 'O' : 'X';
  turno = 'X';
  juegoActivo = true;

  resetearTablero();
  habilitarTablero(true);
  actualizarEstado(`Turno: ${turno} (${turno === simbolos.j1 ? jugador1 : jugador2})`);
}

// funcion de jugar  
function jugar(e) {
  if (!juegoActivo) return;
   
  const celda = e.target;
  const index = celda.dataset.index;

  if (tablero[index]!== "") return;
  tablero[index] = turno;
  celda.textContent = turno;

  if (verificarGanador()){
    const ganador = turno === simbolos.j1 ? jugador1 : jugador2;
    actualizarEstado(  `Ganó ${ganador}` );
    alert()`Ganó ${ganador}`;
    juegoActivo = false;
    return;
  }

  if (!tablero.includes("")){
    actualizarEstado("Empate");
    alert("Empate !")
    juegoActivo = false;
    return;
  }

  turno = turno === "X" ? "O" : "X";
  actualizarEstado(`Turno: ${turno} (${turno === simbolos.j1 ? jugador1 : jugador2})`
  );
}

// funcion de verificar ganador 
function verificarGanador(){
  const combinaciones = [
     [0,1,2],[3,4,5],[6,7,8],
    [0,3,6],[1,4,7],[2,5,8],
    [0,4,8],[2,4,6]
  ];

  return combinaciones.some(c=> 
    tablero[c[0]] &&
    tablero[c[0]] === tablero[c[1]] &&
    tablero[c[0]] === tablero[c[2]]
  );
}

document.querySelectorAll( '#tablero td').forEach(td =>{
  td.addEventListener('click', jugar);
})


// funcion  de reiniciar juego 
function reiniciarJuego() {
  juegoActivo = false;
  resetearTablero();
  habilitarTablero(false);
  actualizarEstado('');
  turno = 'X';
}

iniciarBtn.addEventListener('click', iniciarJuego);
reiniciarBtn.addEventListener('click', reiniciarJuego);