import java.util.Scanner;
public class practica2{
public static void main(String[] args) {
Scanner sc = new  Scanner(System.in);

 // Mostrar título de la biblioteca
        TextoColor titulo = new TextoColor("=== Biblioteca de Alejandría ===", "azul");
        titulo.mostrar();

        Biblioteca biblioteca = new Biblioteca();
        int opcion;

        do {
            // Menú con color distinto
            TextoColor menu = new TextoColor(
                "\n1. Registrar libro\n2. Buscar por ISBN\n3. Buscar por autor\n4. Buscar por título\n5. Buscar por género\n6. Listar todos\n7. Salir",
                "amarillo"
            );
            menu.mostrar();

            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1: // Registrar libro
                    System.out.print("ISBN: ");
                    String isbn = sc.nextLine();

                    // Validar que no exista
                    if (biblioteca.buscarPorISBN(isbn) != null) {
                        TextoColor err = new TextoColor("Ya existe un libro con ese ISBN.", "rojo");
                        err.mostrar();
                        break;
                    }

                    System.out.print("Título: ");
                    String tituloLibro = sc.nextLine();

                    System.out.print("Autor: ");
                    String autor = sc.nextLine();

                    System.out.print("Géneros (separados por coma): ");
                    String generos = sc.nextLine();

                    Libro nuevoLibro = new Libro(isbn, tituloLibro, autor, generos);
                    biblioteca.agregarLibro(nuevoLibro);

                    TextoColor ok = new TextoColor("Libro registrado correctamente.", "verde");
                    ok.mostrar();
                    break;

                case 2: // Buscar por ISBN
                    System.out.print("Introduce ISBN: ");
                    String busqISBN = sc.nextLine();
                    Libro libro = biblioteca.buscarPorISBN(busqISBN);
                    if (libro != null) {
                        TextoColor encontrado = new TextoColor(libro.toString(), "verde");
                        encontrado.mostrar();
                    } else {
                        TextoColor noEnc = new TextoColor("No se encontró el libro.", "rojo");
                        noEnc.mostrar();
                    }
                    break;

                case 3: // Buscar por autor
                    System.out.print("Introduce autor: ");
                    String busqAutor = sc.nextLine();
                    biblioteca.buscarPorAutor(busqAutor);
                    break;

                case 4: // Buscar por título
                    System.out.print("Introduce título: ");
                    String busqTitulo = sc.nextLine();
                    biblioteca.buscarPorTitulo(busqTitulo);
                    break;

                case 5: // Buscar por género
                    System.out.print("Introduce género: ");
                    String busqGenero = sc.nextLine();
                    biblioteca.buscarPorGenero(busqGenero);
                    break;

                case 6: // Listar todos
                    biblioteca.mostrarLibros();
                    break;

                case 7: // Salir
                    TextoColor bye = new TextoColor("¡Gracias por usar la Biblioteca!", "azul");
                    bye.mostrar();
                    break;

                default:
                    TextoColor invalido = new TextoColor("Opción inválida", "rojo");
                    invalido.mostrar();
            }

        } while (opcion != 7);

        sc.close();













}
}

