import java.util.Scanner; // Importamos la clase Scanner para leer datos del usuario

public class NumerosPrimos { // Nombre de la clase (se recomienda que empiece en mayúscula)

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // Creamos el objeto Scanner para entrada por teclado

        int x;

        // Pedimos al usuario un número entero distinto de 0 y 1
        do {
            System.out.print("Ingresa un número entero (distinto de 0 y 1): ");
            
            // Validamos que la entrada sea un número entero
            while (!sc.hasNextInt()) {
                System.out.println("Por favor ingresa un número válido.");
                sc.next(); // Descartamos la entrada inválida
                System.out.print("Ingresa un número entero (distinto de 0 y 1): ");
            }

            x = sc.nextInt(); // Leemos el número entero

        } while (x == 0 || x == 1); // Repetimos mientras el número sea 0 o 1

        x = Math.abs(x); // Tomamos el valor absoluto en caso de que sea negativo

        // Generamos e imprimimos los resultados de la fórmula 2n + 1 para n desde 1 hasta x
        for (int n = 1; n <= x; n++) {
            int resultado = 2 * n + 1; // Calculamos 2n + 1
            System.out.print("n = " + n + " -> 2n + 1 = " + resultado);

            // Verificamos si el resultado es un número primo
            if (esPrimo(resultado)) {
                System.out.print(" (es primo)");
            }

            System.out.println(); // Salto de línea
        }

        sc.close(); // Cerramos el Scanner (buena práctica)
    }

    // Función que determina si un número es primo
    public static boolean esPrimo(int numero) {
        if (numero < 2) return false; // Los números menores a 2 no son primos

        // Verificamos si tiene divisores entre 2 y la raíz cuadrada del número
        for (int i = 2; i <= Math.sqrt(numero); i++) {
            if (numero % i == 0) return false; // Si es divisible, no es primo
        }

        return true; // Si no tuvo divisores, es primo
    }
}
