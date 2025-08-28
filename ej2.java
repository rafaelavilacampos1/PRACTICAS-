import java.util.Scanner;

public class ej2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int x;

        // Pedir al usuario un número entero distinto de 0 y 1
        do {
            System.out.print("Ingresa un número entero (distinto de 0 y 1): ");
            x = scanner.nextInt();
        } while (x == 0 || x == 1);

        // Tomar valor absoluto si es negativo
        x = Math.abs(x);

        // Evaluar la fórmula 2n + 1 desde n = 1 hasta x
        for (int n = 1; n <= x; n++) {
            int resultado = 2 * n + 1;
            System.out.print("n = " + n + " -> 2n + 1 = " + resultado);
            if (esPrimo(resultado)) {
                System.out.print(" (es primo)");
            }
            System.out.println();
        }

        scanner.close();
    }

    // Método para verificar si un número es primo
    public static boolean esPrimo(int numero) {
        if (numero < 2) return false;
        for (int i = 2; i <= Math.sqrt(numero); i++) {
            if (numero % i == 0) return false;
        }
        return true;
    }
}