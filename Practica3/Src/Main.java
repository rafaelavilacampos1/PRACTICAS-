import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. Marca y país
        System.out.println("Seleccione la marca:");
        System.out.println("1. Honda\n2. BYD\n3. Volkswagen\n4. Toyota\n5. Nissan");
        int marcaOp = sc.nextInt();
        String marca = "", pais = "";
        int precioImport = 0;

        switch (marcaOp) {
            case 1 -> {
                marca = "Honda";
                System.out.println("Seleccione país: 1. México (25k), 2. Japón (105k), 3. India (55k)");
                int op = sc.nextInt();
                pais = switch (op) {
                    case 1 -> "México"; case 2 -> "Japón"; default -> "India";
                };
                precioImport = switch (op) {
                    case 1 -> 25000; case 2 -> 105000; default -> 55000;
                };
            }
            case 2 -> {
                marca = "BYD"; pais = "China"; precioImport = 30000;
            }
            case 3 -> {
                marca = "Volkswagen";
                System.out.println("Seleccione país: 1. México (15k), 2. Alemania (80k), 3. China (32k), 4. Brasil (42k)");
                int op = sc.nextInt();
                pais = switch (op) {
                    case 1 -> "México"; case 2 -> "Alemania"; case 3 -> "China"; default -> "Brasil";
                };
                precioImport = switch (op) {
                    case 1 -> 15000; case 2 -> 80000; case 3 -> 32000; default -> 42000;
                };
            }
            case 4 -> {
                marca = "Toyota";
                System.out.println("Seleccione país: 1. México (20k), 2. EE.UU. (40k), 3. Brasil (40k), 4. Francia (90k)");
                int op = sc.nextInt();
                pais = switch (op) {
                    case 1 -> "México"; case 2 -> "EE.UU."; case 3 -> "Brasil"; default -> "Francia";
                };
                precioImport = switch (op) {
                    case 1 -> 20000; case 2 -> 40000; case 3 -> 40000; default -> 90000;
                };
            }
            case 5 -> {
                marca = "Nissan";
                System.out.println("Seleccione país: 1. Japón (100k), 2. México (18k)");
                int op = sc.nextInt();
                pais = (op == 1) ? "Japón" : "México";
                precioImport = (op == 1) ? 100000 : 18000;
            }
        }

        MarcaAutomovil m = new MarcaAutomovil(marca, pais, precioImport);

        // 2. Transmisión
        System.out.println("Seleccione transmisión (1. Manual, 2. Automática):");
        String transmision = (sc.nextInt() == 1) ? "Manual" : "Automática";

        // 3. Tipo de auto
        System.out.println("Seleccione tipo de auto: 1. Compacto, 2. Semicompacto, 3. Sedán, 4. Deportivo");
        String tipo = switch (sc.nextInt()) {
            case 1 -> "Compacto";
            case 2 -> "Semicompacto";
            case 3 -> "Sedán";
            default -> "Deportivo";
        };

        // 4. Año
        System.out.println("Seleccione año del auto (2023, 2024, 2025):");
        int anio = sc.nextInt();

        // 5. Color chasis
        System.out.println("Seleccione color del chasis:");
        sc.nextLine(); // consume newline
        String color = sc.nextLine();

        System.out.println("Seleccione acabado (mate, brillante, metálico):");
        String acabado = sc.nextLine();
        int precioAcabado = switch (acabado.toLowerCase()) {
            case "mate" -> 0;
            case "brillante" -> 10000;
            default -> 15000;
        };

        Chasis ch = new Chasis(color, acabado, precioAcabado);

        // 6. Película anti-asalto
        System.out.println("¿Desea película anti-asalto? (1. Sí, 2. No):");
        boolean pelicula = sc.nextInt() == 1;

        // 7. Llantas
        System.out.println("Seleccione marca de llantas:\n1. Yokohama (5k), 2. Firestone (8k), 3. Pirelli (6.5k), 4. Goodyear (6k), 5. Michelin (10k)");
        int opLlanta = sc.nextInt();
        String[] marcas = {"Yokohama", "Firestone", "Pirelli", "Goodyear", "Michelin"};
        int[] precios = {5000, 8000, 6500, 6000, 10000};
        String marcaLlanta = marcas[opLlanta - 1];
        int precioLlanta = precios[opLlanta - 1];

        System.out.println("Seleccione tamaño del rin (15, 16, 17, 18):");
        int tamañoRin = sc.nextInt();

        System.out.println("Material del rin (aluminio o acero):");
        sc.nextLine();
        String materialRin = sc.nextLine();
        precioLlanta += (materialRin.equalsIgnoreCase("aluminio")) ? 8000 : 3000;

        Llanta llanta = new Llanta(marcaLlanta, tamañoRin, materialRin, precioLlanta);

        // 8. Frenos
        System.out.println("Seleccione tipo de frenos (1. Disco $3200, 2. Tambor $2100):");
        String tipoFrenos = (sc.nextInt() == 1) ? "Disco" : "Tambor";

        // 9. Sistema electrónico
        System.out.println("¿Desea pantalla digital? (1. Sí, 2. No):");
        boolean pantalla = sc.nextInt() == 1;
        System.out.println("¿Desea espejos electrónicos? (1. Sí, 2. No):");
        boolean espejos = sc.nextInt() == 1;
        System.out.println("¿Desea cámara de reversa? (1. Sí, 2. No):");
        boolean camara = sc.nextInt() == 1;
        System.out.println("¿Desea sensor de reversa? (1. Sí, 2. No):");
        boolean sensor = sc.nextInt() == 1;

        SistemaElectronico sistema = new SistemaElectronico(pantalla, espejos, camara, sensor);

        // Crear auto
        Automovil auto = new Automovil(m, transmision, tipo, anio, ch, pelicula, llanta, tipoFrenos, sistema);

        // Mostrar resumen
        System.out.println(auto);

        // Confirmación
        System.out.println("¿Desea comprar el vehículo? (1. Sí, 2. No)");
        int confirm = sc.nextInt();
        if (confirm == 1)
            System.out.println("¡Gracias por su compra!");
        else
            System.out.println("Compra cancelada. ¡Gracias por visitar!");

        sc.close();
    }
}
