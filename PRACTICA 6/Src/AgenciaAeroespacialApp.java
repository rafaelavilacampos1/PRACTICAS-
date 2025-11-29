import java.time.format.DateTimeFormatter;
import java.util.*;

/* ---------------------- CLASE MAIN ---------------------- */
public class AgenciaAeroespacialApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("yyyy-M-d");

    public static void main(String[] args) {
        System.out.println("=== Agencia Aeroespacial (Año 3050) ===");
        System.out.println("Seleccione tipo de viaje:");
        System.out.println("1) Internacional");
        System.out.println("2) Estratosférico");
        System.out.println("3) Interplanetario");
        System.out.print("Opción: ");
        String opcion = scanner.nextLine().trim();

        try {
            switch (opcion) {
                case "1" -> manejarViajeInternacional();
                case "2" -> manejarViajeEstratosferico();
                case "3" -> manejarViajeInterplanetario();
                default -> System.out.println("Opción inválida. Saliendo.");
            }
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /* ---------- METODOS AUXILIARES PARA CAPTURA ---------- */
    private static void manejarViajeInternacional() {
        System.out.println("== Viaje Internacional ==");
        String origen = pedir("País del cual parte (2 letras MAYÚSCULAS): ");
        String nacionalidad = pedir("Nacionalidad (2 letras MAYÚSCULAS): ");
        String nombre = pedir("Nombre completo (al menos nombre y apellido): ");
        int edad = Integer.parseInt(pedir("Edad (años): "));
        String numeroIdentificacion = pedir("Número de identificación: ");
        String fechaVencId = pedir("Fecha de vencimiento de identificación (YYYY-M-D): ");
        String destino = pedir("Destino (país o lugar): ");
        String fechaPartida = pedir("Fecha de partida (YYYY-M-D): ");

        Pasajero pasajero = new Pasajero(nombre, edad);
        ViajeInternacional viaje = new ViajeInternacional(origen, destino, fechaPartida, numeroIdentificacion,
                fechaVencId, pasajero, nacionalidad);

        boolean valido = viaje.validar();
        imprimirResultado(valido, viaje.obtenerErrores());
    }

    private static void manejarViajeEstratosferico() {
        System.out.println("== Viaje Estratosférico ==");
        String origen = pedir("País del cual parte (2 letras MAYÚSCULAS): ");
        String nombre = pedir("Nombre completo (al menos nombre y apellido): ");
        int edad = Integer.parseInt(pedir("Edad (años): "));
        String numeroIdentificacion = pedir("Número de identificación: ");
        String fechaVencId = pedir("Fecha de vencimiento de identificación (YYYY-M-D): ");
        String destino = pedir("Destino (debe ser distinto al origen): ");
        String fechaPartida = pedir("Fecha de partida (YYYY-M-D): ");
        String numeroCertificado = pedir("Número de certificado de salud (ej. CS-JUAN-PEREZ-2025-12-06-A-E): ");

        Pasajero pasajero = new Pasajero(nombre, edad);
        ViajeEstratosferico viaje = new ViajeEstratosferico(origen, destino, fechaPartida, numeroIdentificacion,
                fechaVencId, pasajero, numeroCertificado);

        boolean valido = viaje.validar();
        imprimirResultado(valido, viaje.obtenerErrores());
    }

    private static void manejarViajeInterplanetario() {
        System.out.println("== Viaje Interplanetario ==");
        String planetaOrigen = pedir("Planeta del cual parte (ej: Tierra, Marte): ");
        String planetaDestino = pedir("Planeta destino (sólo sistema solar): ");
        String nombre = pedir("Nombre completo (al menos nombre y apellido): ");
        int edad = Integer.parseInt(pedir("Edad (años): "));
        String numeroIdentificacion = pedir("Número de identificación (puede ser cualquier identificador): ");
        String fechaVencId = pedir("Fecha de vencimiento de identificación (YYYY-M-D): ");
        String fechaPartida = pedir("Fecha de partida (YYYY-M-D): ");
        String numeroCertificado = pedir("Número de certificado de salud (ej. CS-JUAN-PEREZ-2025-12-06-A-P): ");

        Pasajero pasajero = new Pasajero(nombre, edad);
        ViajeInterplanetario viaje = new ViajeInterplanetario(planetaOrigen, planetaDestino, fechaPartida, numeroIdentificacion,
                fechaVencId, pasajero, numeroCertificado);

        boolean valido = viaje.validar();
        imprimirResultado(valido, viaje.obtenerErrores());
    }

    private static String pedir(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }

    private static void imprimirResultado(boolean valido, List<String> errores) {
        System.out.println("---------- RESULTADO ----------");
        if (valido) {
            System.out.println("ÉXITO: El pasajero puede viajar.");
        } else {
            System.out.println("NO PUEDE VIAJAR. Errores detectados:");
            for (String e : errores) {
                System.out.println(" - " + e);
            }
        }
    }
}