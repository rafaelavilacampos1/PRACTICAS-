import java.util.Random;  // Para generar jugadas aleatorias de la máquina
import java.util.Scanner; // Para leer la entrada del usuario

public class PiedraPapelTijera { // Se recomienda usar mayúscula en el nombre de la clase

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);     // Scanner para leer la entrada del usuario
        Random random = new Random();            // Random para generar jugadas aleatorias

        String[] opciones = {"piedra", "papel", "tijera"}; // Opciones posibles

        int puntosMaquina = 0;   // Contador de puntos de la máquina
        int puntosJugador = 0;   // Contador de puntos del jugador

        System.out.println("Hola, bienvenido al juego de Piedra, Papel o Tijera. Elige una opción para jugar.");

        // El juego continúa hasta que alguno gane 2 rondas
        while (puntosJugador < 2 && puntosMaquina < 2) {
            System.out.println("\nIntroduce un número para elegir una opción:");
            System.out.println("1 - Piedra");
            System.out.println("2 - Papel");
            System.out.println("3 - Tijera");
            System.out.println("0 - Volver al menú principal");
            System.out.print("Tu elección: ");

            // Validación de entrada: debe ser un número entero
            while (!sc.hasNextInt()) {
                System.out.println("Opción no válida.");
                sc.next(); // Descartar entrada inválida
                System.out.print("Tu elección: ");
            }

            int respuesta = sc.nextInt();

            // Salida anticipada si el usuario elige 0
            if (respuesta == 0) {
                System.out.println("Saliendo del juego...");
                return;
            }

            // Validar si la respuesta está dentro del rango válido
            if (respuesta < 1 || respuesta > 3) {
                System.out.println("Opción no válida.");
                continue;
            }

            // La máquina elige aleatoriamente una opción (1, 2 o 3)
            int respuestaMaquina = random.nextInt(3) + 1;

            System.out.println("Tú elegiste: " + opciones[respuesta - 1]);
            System.out.println("La máquina eligió: " + opciones[respuestaMaquina - 1]);

            // Verificar el resultado de la ronda
            if (respuesta == respuestaMaquina) {
                System.out.println("Empate.");
            } else if (
                (respuesta == 1 && respuestaMaquina == 3) || // Piedra gana a tijera
                (respuesta == 2 && respuestaMaquina == 1) || // Papel gana a piedra
                (respuesta == 3 && respuestaMaquina == 2)    // Tijera gana a papel
            ) {
                puntosJugador++;
                System.out.println("¡Ganaste la ronda!");
            } else {
                puntosMaquina++;
                System.out.println("La máquina ganó la ronda.");
            }

            // Mostrar marcador actual
            System.out.println("Marcador: Tú " + puntosJugador + " - Máquina " + puntosMaquina);
        }

        // Mostrar resultado final
        if (puntosJugador == 2) {
            System.out.println(" ¡Ganaste contra la máquina!");
        } else {
            System.out.println(" Perdiste contra la máquina.");
        }

        sc.close(); // Cerrar el Scanner (buena práctica)
    }
}
