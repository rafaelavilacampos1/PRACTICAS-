import java.util.Random;
import java.util.Scanner;

public class Memorama {

    static final int TAM = 4;          // Tablero 4x4
    static final char CUBIERTO = '#';  // Símbolo que representa una carta cubierta
    static Random rand = new Random();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        char[][] tablero = new char[TAM][TAM];        // Tablero con los símbolos
        char[][] visible = new char[TAM][TAM];        // Tablero mostrado al jugador
        inicializarTablero(tablero, visible);

        int movimientos = 0;
        int descubiertos = 0;

        System.out.println("=== MEMORAMA ===");
        System.out.println("Encuentra las 8 parejas.\n");

        while (descubiertos < TAM * TAM) {
            mostrarTablero(visible);

            System.out.println("Movimiento #" + (movimientos + 1));

            // Primer carta
            int[] c1 = pedirCoordenada("Primera carta");
            while (visible[c1[0]][c1[1]] != CUBIERTO) {
                System.out.println("❌ Esa carta ya está descubierta. Elige otra.");
                c1 = pedirCoordenada("Primera carta");
            }

            visible[c1[0]][c1[1]] = tablero[c1[0]][c1[1]];
            mostrarTablero(visible);

            // Segunda carta
            int[] c2 = pedirCoordenada("Segunda carta");
            while ((c2[0] == c1[0] && c2[1] == c1[1]) || visible[c2[0]][c2[1]] != CUBIERTO) {
                System.out.println("❌ Coordenada inválida o carta ya descubierta. Intenta otra.");
                c2 = pedirCoordenada("Segunda carta");
            }

            visible[c2[0]][c2[1]] = tablero[c2[0]][c2[1]];
            mostrarTablero(visible);

            movimientos++;

            // Verificar si coinciden
            if (tablero[c1[0]][c1[1]] == tablero[c2[0]][c2[1]]) {
                System.out.println("✅ ¡Pareja encontrada!");
                descubiertos += 2;
            } else {
                System.out.println("❌ No coinciden. Se volverán a tapar.");
                esperar(1500); // pequeña pausa
                visible[c1[0]][c1[1]] = CUBIERTO;
                visible[c2[0]][c2[1]] = CUBIERTO;
            }

            System.out.println();
        }

        System.out.println("🎉 ¡Felicidades! Has descubierto todas las parejas.");
        System.out.println("Movimientos totales: " + movimientos);
    }

    // ==== FUNCIONES AUXILIARES ====

    static void inicializarTablero(char[][] tablero, char[][] visible) {
        // Crear 8 pares de símbolos (A-H)
        char[] simbolos = new char[16];
        char letra = 'A';
        for (int i = 0; i < 8; i++) {
            simbolos[i * 2] = letra;
            simbolos[i * 2 + 1] = letra;
            letra++;
        }

        // Mezclar los símbolos
        for (int i = 0; i < simbolos.length; i++) {
            int j = rand.nextInt(simbolos.length);
            char temp = simbolos[i];
            simbolos[i] = simbolos[j];
            simbolos[j] = temp;
        }

        // Rellenar el tablero con los símbolos mezclados
        int idx = 0;
        for (int i = 0; i < TAM; i++) {
            for (int j = 0; j < TAM; j++) {
                tablero[i][j] = simbolos[idx];
                visible[i][j] = CUBIERTO;
                idx++;
            }
        }
    }

    static void mostrarTablero(char[][] visible) {
        System.out.print("   ");
        for (int j = 0; j < TAM; j++) System.out.print(j + " ");
        System.out.println();
        for (int i = 0; i < TAM; i++) {
            System.out.print(i + "  ");
            for (int j = 0; j < TAM; j++) {
                System.out.print(visible[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static int[] pedirCoordenada(String texto) {
        int fila, col;
        while (true) {
            System.out.print(texto + " - fila (0-3): ");
            fila = sc.nextInt();
            System.out.print(texto + " - columna (0-3): ");
            col = sc.nextInt();

            if (fila >= 0 && fila < TAM && col >= 0 && col < TAM) break;
            System.out.println("❌ Coordenadas fuera de rango. Intenta de nuevo.");
        }
        return new int[]{fila, col};
    }

    static void esperar(int milis) {
        try {
            Thread.sleep(milis);
        } catch (InterruptedException e) {
            // Ignorar
        }
    }
}

