import java.util.Random;
import java.util.Scanner;

public class practica {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int opcion;

            do {
                System.out.println("Menu principal: elige un juego ");
                System.out.println("Pulsa 1 para jugar piedra, papel o tijera");
                System.out.println("Pulsa 2 para números primos ");
                System.out.println("Pulsa 3 para salir del menú ");

                while (!sc.hasNextInt()) {
                    System.out.println("Opción no válida. Dijimos que solo 1, 2 o 3.");
                    sc.next(); // descarta entrada inválida
                }

                opcion = sc.nextInt();

                switch (opcion) {
                    case 1 -> piedrapapelotijera(sc);
                    case 2 -> primos(sc);
                    case 3 -> System.out.println("Eh, ¿no que querías jugar? ¿Pa' qué te sales?");
                    default -> System.out.println("Opción no válida. Dijimos que solo 1, 2 o 3.");
                }

            } while (opcion != 3);

        }
    }

    public static void piedrapapelotijera(Scanner sc) {
        Random random = new Random();
        String[] opciones = {"piedra", "papel", "tijera"};

        int puntosmaquina = 0;
        int puntosjugador = 0;

        System.out.println("Hola, bienvenido al juego de piedra, papel o tijera. Elige una opción para jugar.");

        while (puntosjugador < 2 && puntosmaquina < 2) {
            System.out.println("Introduce un número para elegir una opción:");
            System.out.println("1 - piedra");
            System.out.println("2 - papel");
            System.out.println("3 - tijera");
            System.out.println("0 - volver al menú principal");
            System.out.print("Tu elección: ");

            while (!sc.hasNextInt()) {
                System.out.println("Opción no válida.");
                sc.next(); // descarta entrada inválida
                System.out.print("Tu elección: ");
            }

            int respuesta = sc.nextInt();

            if (respuesta == 0) {
                System.out.println("Saliendo del juego...");
                return;
            }

            if (respuesta < 1 || respuesta > 3) {
                System.out.println("Opción no valida.");
                continue;
            }

            int respuestamaquina = random.nextInt(3) + 1;

            System.out.println("Tu elegiste: " + opciones[respuesta - 1]);
            System.out.println("Respuesta máquina: " + opciones[respuestamaquina - 1]);

            if (respuesta == respuestamaquina) {
                System.out.println("Empate.");
            } else if (
                (respuesta == 1 && respuestamaquina == 3) ||
                (respuesta == 2 && respuestamaquina == 1) ||
                (respuesta == 3 && respuestamaquina == 2)
            ) {
                puntosjugador++;
                System.out.println("Ganaste la ronda.");
            } else {
                puntosmaquina++;
                System.out.println("Gano la máquina.");
            }

            System.out.println("Marcador: Tú " + puntosjugador + " - " + puntosmaquina + " Maquina");
        }

        if (puntosjugador == 2) {
            System.out.println("¡Ganaste contra la máquina!");

        } else if (puntosmaquina == 2) {
            System.out.println("Perdiste contra la máquina.");

        }
    }

    public static void primos(Scanner sc) {
        int x;

        do {
            System.out.print("Ingresa un número entero (distinto de 0 y 1): ");
            while (!sc.hasNextInt()) {
                System.out.println("Por favor ingresa un número válido.");
                sc.next(); // descarta entrada inválida
                System.out.print("Ingresa un número entero (distinto de 0 y 1): ");
            }

            x = sc.nextInt();
        } while (x == 0 || x == 1);

        x = Math.abs(x);

        for (int n = 1; n <= x; n++) {
            int resultado = 2 * n + 1;
            System.out.print("n = " + n + " -> 2n + 1 = " + resultado);
            if (esPrimo(resultado)) {
                System.out.print(" (es primo)");
            }
            System.out.println();
        }
    }

    public static boolean esPrimo(int numero) {
        if (numero < 2) return false;
        for (int i = 2; i <= Math.sqrt(numero); i++) {
            if (numero % i == 0) return false;
        }
        return true;
    }
}

















