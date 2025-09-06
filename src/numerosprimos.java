import java.util.Scanner;

public class numerosprimos{

public static void main (String []args){
Scanner sc=new Scanner(System.in);


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
