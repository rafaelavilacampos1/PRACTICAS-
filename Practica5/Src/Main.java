import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("1. Vuelo Internacional");
        System.out.println("2. Vuelo Estratosferico");
        System.out.println("3. Vuelo Interplanetario");
        System.out.print("Seleccione una opcion: ");
        int op = sc.nextInt(); sc.nextLine();

        System.out.print("Nombre completo: ");
        String nombre = sc.nextLine();

        System.out.print("Fecha de viaje (AAAA-MM-DD): ");
        LocalDate fechaViaje = LocalDate.parse(sc.nextLine());

        System.out.print("Edad: ");
        int edad = sc.nextInt();
        sc.nextLine();

        Vuelo vuelo = null;

        switch (op) {

            case 1: {
                System.out.print("Pais salida (2 letras): ");
                String pais = sc.nextLine();

                System.out.print("Nacionalidad (2 letras): ");
                String nac = sc.nextLine();

                System.out.print("Vencimiento ID (AAAA-MM-DD): ");
                LocalDate venc = LocalDate.parse(sc.nextLine());

                System.out.print("Numero ID: ");
                String num = sc.nextLine();

                System.out.print("Destino: ");
                String dest = sc.nextLine();

                vuelo = new VueloInternacional(nombre, fechaViaje, edad, pais, nac, venc, num, dest);
                break;
            }

            case 2: {
                System.out.print("Pais salida: ");
                String pais = sc.nextLine();

                System.out.print("Nacionalidad: ");
                String nac = sc.nextLine();

                System.out.print("Vencimiento ID (AAAA-MM-DD): ");
                LocalDate venc = LocalDate.parse(sc.nextLine());

                System.out.print("Numero ID: ");
                String num = sc.nextLine();

                System.out.print("Destino: ");
                String dest = sc.nextLine();

                System.out.print("Certificado salud: ");
                CertificadoSalud cert = new CertificadoSalud(sc.nextLine());

                vuelo = new VueloEstratosferico(nombre, fechaViaje, edad,
                        pais, nac, venc, num, dest, cert);
                break;
            }

            case 3: {
                System.out.print("Planeta salida: ");
                String ps = sc.nextLine();

                System.out.print("Planeta destino: ");
                String pd = sc.nextLine();

                System.out.print("Certificado salud interplanetario: ");
                CertificadoSalud cert = new CertificadoSalud(sc.nextLine());

                vuelo = new VueloInterplanetario(nombre, fechaViaje, edad,
                        ps, pd, cert);
                break;
            }

            default:
                System.out.println("Opcion no valida");
                System.exit(0);
        }

        if (vuelo.validarDatos()) {
            System.out.println("Viaje autorizado.");
        } else {
            System.out.println("Viaje denegado.");
        }

        sc.close();
    }
}
