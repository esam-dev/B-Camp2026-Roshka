
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

function actualizarEstado(mensaje) {
  estadoEl.textContent = mensaje || '';
}

function resetearTablero() {
  tablero = Array(9).fill("");
  document.querySelectorAll('#tablero td').forEach(td => td.textContent = "");
}

function habilitarTablero(habilitar) {
  if (habilitar) {
    tableroEl.classList.remove('deshabilitado');
  } else {
    tableroEl.classList.add('deshabilitado');
  }
}

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

function reiniciarJuego() {
  juegoActivo = false;
  resetearTablero();
  habilitarTablero(false);
  actualizarEstado('');
  turno = 'X';
}

iniciarBtn.addEventListener('click', iniciarJuego);
reiniciarBtn.addEventListener('click', reiniciarJuego);