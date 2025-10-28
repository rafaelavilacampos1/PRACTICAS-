import java.util.Random;
import java.util.Scanner;

public class BatallaNaval {

    static final int TAM = 10;
    static final char AGUA = '~';
    static final char BARCO = 'B';
    static final char IMPACTO = 'X';
    static final char FALLO = 'O';

    static Random rand = new Random();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        char[][] tableroJugador = crearTablero();
        char[][] tableroIA = crearTablero();
        char[][] tableroIAvisible = crearTablero();

        // Colocar barcos de la IA aleatoriamente
        colocarBarcosAleatorio(tableroIA);

        // Colocar barcos del jugador manualmente
        colocarBarcosJugador(tableroJugador);

        System.out.println("\n¡Comienza la batalla!\n");

        boolean turnoJugador = rand.nextBoolean();

        while (true) {
            if (turnoJugador) {
                System.out.println("===== Tu turno =====");
                mostrarTableros(tableroJugador, tableroIAvisible);

                int fila = pedirNumero("Fila (0-9): ");
                int col = pedirNumero("Columna (0-9): ");

                if (tableroIA[fila][col] == BARCO) {
                    System.out.println("🎯 ¡Impacto!");
                    tableroIA[fila][col] = IMPACTO;
                    tableroIAvisible[fila][col] = IMPACTO;
                } else if (tableroIA[fila][col] == AGUA) {
                    System.out.println("💦 Fallaste.");
                    tableroIA[fila][col] = FALLO;
                    tableroIAvisible[fila][col] = FALLO;
                } else {
                    System.out.println("Ya habías atacado ahí. Pierdes turno.");
                }

                if (juegoTerminado(tableroIA)) {
                    System.out.println("\n🏆 ¡Has ganado la batalla!");
                    break;
                }

                turnoJugador = false;
            } else {
                System.out.println("===== Turno de la IA =====");

                int fila, col;
                do {
                    fila = rand.nextInt(TAM);
                    col = rand.nextInt(TAM);
                } while (tableroJugador[fila][col] == IMPACTO || tableroJugador[fila][col] == FALLO);

                if (tableroJugador[fila][col] == BARCO) {
                    System.out.println("La IA acertó en (" + fila + "," + col + ")");
                    tableroJugador[fila][col] = IMPACTO;
                } else {
                    System.out.println("La IA falló en (" + fila + "," + col + ")");
                    tableroJugador[fila][col] = FALLO;
                }

                if (juegoTerminado(tableroJugador)) {
                    System.out.println("\n💀 La IA ha hundido todos tus barcos. ¡Perdiste!");
                    break;
                }

                turnoJugador = true;
            }
        }

        System.out.println("\n=== Resultado final ===");
        mostrarTableros(tableroJugador, tableroIA);
    }

    // ==== FUNCIONES AUXILIARES ====

    static char[][] crearTablero() {
        char[][] t = new char[TAM][TAM];
        for (int i = 0; i < TAM; i++) {
            for (int j = 0; j < TAM; j++) {
                t[i][j] = AGUA;
            }
        }
        return t;
    }

    static void mostrarTablero(char[][] t) {
        System.out.print("  ");
        for (int i = 0; i < TAM; i++) System.out.print(i + " ");
        System.out.println();
        for (int i = 0; i < TAM; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < TAM; j++) {
                System.out.print(t[i][j] + " ");
            }
            System.out.println();
        }
    }

    static void mostrarTableros(char[][] jugador, char[][] enemigo) {
        System.out.println("\n=== Tu tablero ===");
        mostrarTablero(jugador);
        System.out.println("=== Tablero enemigo ===");
        mostrarTablero(enemigo);
    }

    static void colocarBarcosJugador(char[][] tablero) {
        int[] barcos = {4, 3, 3, 2};
        for (int i = 0; i < barcos.length; i++) {
            boolean colocado = false;
            while (!colocado) {
                System.out.println("\nColoca un barco de longitud " + barcos[i]);
                mostrarTablero(tablero);
                int fila = pedirNumero("Fila inicial: ");
                int col = pedirNumero("Columna inicial: ");
                System.out.print("Orientación (h = horizontal, v = vertical): ");
                char orient = sc.next().toLowerCase().charAt(0);

                colocado = colocarBarco(tablero, fila, col, barcos[i], orient);
                if (!colocado) {
                    System.out.println("❌ No se pudo colocar. Intenta de nuevo.");
                }
            }
        }
    }

    static void colocarBarcosAleatorio(char[][] tablero) {
        int[] barcos = {4, 3, 3, 2};
        for (int i = 0; i < barcos.length; i++) {
            boolean colocado = false;
            while (!colocado) {
                int fila = rand.nextInt(TAM);
                int col = rand.nextInt(TAM);
                char orient = rand.nextBoolean() ? 'h' : 'v';
                colocado = colocarBarco(tablero, fila, col, barcos[i], orient);
            }
        }
    }

    static boolean colocarBarco(char[][] t, int fila, int col, int largo, char orient) {
        if (orient == 'h') {
            if (col + largo > TAM) return false;
            for (int j = 0; j < largo; j++) {
                if (t[fila][col + j] != AGUA) return false;
            }
            for (int j = 0; j < largo; j++) t[fila][col + j] = BARCO;
        } else {
            if (fila + largo > TAM) return false;
            for (int i = 0; i < largo; i++) {
                if (t[fila + i][col] != AGUA) return false;
            }
            for (int i = 0; i < largo; i++) t[fila + i][col] = BARCO;
        }
        return true;
    }

    static boolean juegoTerminado(char[][] t) {
        for (int i = 0; i < TAM; i++) {
            for (int j = 0; j < TAM; j++) {
                if (t[i][j] == BARCO) return false;
            }
        }
        return true;
    }

    static int pedirNumero(String mensaje) {
        int n;
        while (true) {
            System.out.print(mensaje);
            n = sc.nextInt();
            if (n >= 0 && n < TAM) break;
            System.out.println("Número fuera de rango. Intenta otra vez.");
        }
        return n;
    }
}
