
import java.util.Random;
import java.util.Scanner;

public class main{
public static void main(String[]args){

// el random va usar el azar
    try (Scanner sc = new Scanner(System.in)) {
        // el random va usar el azar
        Random random = new Random();

        String[] opciones = {"piedra", "papel", "tijera"};
        // menu de opciones elige una opcion para comenzar a jugar
        System.out.println(" hola bienvenido al juego de priedra papel o tijera eligue una opcion para jugar ");
        System.out.println("introduce un numero para elegir una opcion ");
        System.out.println("1-piedra");
        System.out.println("2-papael");
        System.out.println("3-tijera");
        System.out.print("tu eleccion: ");
        // esta funcion va a guardar la respuesta del jugador
        int respuesta = sc.nextInt();

        if(respuesta < 1 || respuesta > 3){

            System.out.println("opcion no valida");
            return;
        }
        int respuestamaquina = random.nextInt(3) + 1;

        System.out.println("tu elegiste: " + opciones[respuesta -1]);
        System.out.println("respuesta maquina: "+opciones[respuestamaquina - 1]);

        if (respuesta==respuestamaquina){
            System.out.println("que tonto empataste ");
//aqui comparamos los resultados
        } else if(
                (respuesta == 1 && respuestamaquina == 3) ||
                (respuesta == 2 && respuestamaquina == 1)||
                (respuesta == 3 && respuestamaquina == 2)
                ) {
            System.out.println("ganaste");
        } else {
            System.out.println ("que pendejo te gano una maquina");
        }   }
}
}
