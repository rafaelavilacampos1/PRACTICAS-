
import java.util.Scanner;

public class Catalogo {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("¿Cuántos juegos quieres registrar? ");
        int n = sc.nextInt();
        sc.nextLine(); // limpiar buffer

        String[] nombres = new String[n];
        String[] generos = new String[n];
        double[] calificaciones = new double[n];

        // Registrar los juegos
        for (int i = 0; i < n; i++) {
            System.out.println("\n--- Juego #" + (i + 1) + " ---");
            System.out.print("Nombre: ");
            nombres[i] = sc.nextLine();

            System.out.print("Género: ");
            generos[i] = sc.nextLine();

            System.out.print("Calificación (0.0 a 10.0): ");
            calificaciones[i] = sc.nextDouble();
            sc.nextLine(); // limpiar buffer
        }

        int opcion;
        do {
            System.out.println("\n=== CATÁLOGO DE JUEGOS DE MESA ===");
            System.out.println("1. Mostrar todos los juegos");
            System.out.println("2. Ordenar por nombre");
            System.out.println("3. Ordenar por calificación");
            System.out.println("4. Ordenar por género");
            System.out.println("5. Mostrar mejor calificado");
            System.out.println("6. Mostrar peor calificado");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    mostrarJuegos(nombres, generos, calificaciones);
                    break;
                case 2:
                    ordenarPorNombre(nombres, generos, calificaciones);
                    mostrarJuegos(nombres, generos, calificaciones);
                    break;
                case 3:
                    ordenarPorCalificacion(nombres, generos, calificaciones);
                    mostrarJuegos(nombres, generos, calificaciones);
                    break;
                case 4:
                    ordenarPorGenero(nombres, generos, calificaciones);
                    mostrarJuegos(nombres, generos, calificaciones);
                    break;
                case 5:
                    mostrarMejorCalificado(nombres, generos, calificaciones);
                    break;
                case 6:
                    mostrarPeorCalificado(nombres, generos, calificaciones);
                    break;
                case 0:
                    System.out.println("👋 Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    // === Funciones auxiliares ===

    static void mostrarJuegos(String[] nombres, String[] generos, double[] calificaciones) {
        System.out.println("\n--- Lista de Juegos ---");
        for (int i = 0; i < nombres.length; i++) {
            System.out.println((i + 1) + ". " + nombres[i] + " | " + generos[i] + " | Calificación: " + calificaciones[i]);
        }
    }

    static void ordenarPorNombre(String[] nombres, String[] generos, double[] calificaciones) {
        for (int i = 0; i < nombres.length - 1; i++) {
            for (int j = i + 1; j < nombres.length; j++) {
                if (nombres[i].compareToIgnoreCase(nombres[j]) > 0) {
                    intercambiar(nombres, generos, calificaciones, i, j);
                }
            }
        }
        System.out.println("\n✅ Juegos ordenados por nombre.");
    }

    static void ordenarPorGenero(String[] nombres, String[] generos, double[] calificaciones) {
        for (int i = 0; i < generos.length - 1; i++) {
            for (int j = i + 1; j < generos.length; j++) {
                if (generos[i].compareToIgnoreCase(generos[j]) > 0) {
                    intercambiar(nombres, generos, calificaciones, i, j);
                }
            }
        }
        System.out.println("\n✅ Juegos ordenados por género.");
    }

    static void ordenarPorCalificacion(String[] nombres, String[] generos, double[] calificaciones) {
        for (int i = 0; i < calificaciones.length - 1; i++) {
            for (int j = i + 1; j < calificaciones.length; j++) {
                if (calificaciones[i] < calificaciones[j]) { // de mayor a menor
                    intercambiar(nombres, generos, calificaciones, i, j);
                }
            }
        }
        System.out.println("\n✅ Juegos ordenados por calificación.");
    }

    static void intercambiar(String[] nombres, String[] generos, double[] calificaciones, int i, int j) {
        String tempNombre = nombres[i];
        nombres[i] = nombres[j];
        nombres[j] = tempNombre;

        String tempGenero = generos[i];
        generos[i] = generos[j];
        generos[j] = tempGenero;

        double tempCalif = calificaciones[i];
        calificaciones[i] = calificaciones[j];
        calificaciones[j] = tempCalif;
    }

    static void mostrarMejorCalificado(String[] nombres, String[] generos, double[] calificaciones) {
        int idx = 0;
        for (int i = 1; i < calificaciones.length; i++) {
            if (calificaciones[i] > calificaciones[idx]) idx = i;
        }
        System.out.println("\n🏆 Mejor calificado:");
        System.out.println(nombres[idx] + " | " + generos[idx] + " | Calificación: " + calificaciones[idx]);
    }

    static void mostrarPeorCalificado(String[] nombres, String[] generos, double[] calificaciones) {
        int idx = 0;
        for (int i = 1; i < calificaciones.length; i++) {
            if (calificaciones[i] < calificaciones[idx]) idx = i;
        }
        System.out.println("\n💀 Peor calificado:");
        System.out.println(nombres[idx] + " | " + generos[idx] + " | Calificación: " + calificaciones[idx]);
    }
}

