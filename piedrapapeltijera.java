import java.util.Random;
import java.util.Scanner;

public class piedrapapeltijera{

public static void main(String[]args){
Scanner sc = new Scanner(System.in);
Random random=new Random();

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
                System.out.println("Opción no válida.");
                continue;
            }

            int respuestamaquina = random.nextInt(3)+1;

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
                System.out.println("Ganó la máquina.");
            }

            System.out.println("Marcador: Tú " + puntosjugador + " - " + puntosmaquina + " Máquina");
        }

        if (puntosjugador == 2) {
            System.out.println("¡Ganaste contra la máquina!");
        } else {
            System.out.println("Perdiste contra la máquina.");
        }



}
}