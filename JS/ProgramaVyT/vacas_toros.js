// Importar  el módulo readline para leer datos desde la consola
const readline = require("readline");

// Creamos la interfaz de entrada y salida (teclado / pantalla)
const rl = readline.createInterface({
  input: process.stdin,   // lo que escribe el usuario
  output: process.stdout  // lo que se muestra en pantalla
});

// ===============================
// FUNCIÓN PARA GENERAR EL NÚMERO SECRETO
// ===============================
function generarNumeroSecreto() {
  let numeros = []; // array 

  // Repetir hasta tener 4 dígitos
  while (numeros.length < 4) {
    let n = Math.floor(Math.random() * 10); // número aleatorio del 0 al 9

    // Evitar que el primer dígito sea 0
    if (numeros.length === 0 && n === 0) continue;

    // Evitar números repetidos
    if (!numeros.includes(n)) {
      numeros.push(n); // agregamos el número al array
    }
  }

  // Convertimos el array en string, ej: [1,2,3,4] -> "1234"
  return numeros.join("");
}

// Guardamos el número secreto que debe adivinar el usuario
const secreto = generarNumeroSecreto();

// ===============================
// FUNCIÓN PRINCIPAL DEL JUEGO
// ===============================
function pedirIntento() {

  // Pedimos al usuario que ingrese un número
  rl.question("Ingresa un número de 4 dígitos: ", (intento) => {

    // -------- VALIDACIONES --------
    if (
      intento.length !== 4 ||        // debe tener 4 dígitos
      isNaN(intento) ||              // debe ser número
      new Set(intento).size !== 4 || // no debe repetir dígitos
      intento[0] === "0"             // no debe empezar con 0
    ) {
      console.log("x Número inválido. Probá de nuevo.\n");
      return pedirIntento(); // vuelve a pedir otro intento
    }

    // Contadores
    let toros = 0;
    let vacas = 0;

    // Recorremos cada posición del número
    for (let i = 0; i < 4; i++) {

      // TORO: mismo número en la misma posición
      if (intento[i] === secreto[i]) {
        toros++;

      // VACA: número existe pero en otra posición
      } else if (secreto.includes(intento[i])) {
        vacas++;
      }
    }

    // -------- RESULTADO --------
    if (toros === 4) {
      // El usuario ganó
      console.log(`!! ¡Felicidades! El número secreto era: ${secreto}`);
      rl.close(); // cerramos el programa
    } else {
      // El usuario sigue jugando
      console.log(`  ${vacas} vacas,   ${toros} toros`);
      console.log("Por favor, intente de nuevo:\n");
      pedirIntento(); // vuelve a pedir otro intento
    }
  });
}

// Mensaje inicial
console.log("  Programa Vacas y Toros");

// Arrancamos el juego
pedirIntento();
