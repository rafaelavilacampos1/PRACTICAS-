import java.util.Scanner;

public class practica2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Biblioteca biblioteca = new Biblioteca();

        // Título
        TextoColor titulo = new TextoColor("Biblioteca Nacional de Francia", "azul");
        System.out.println(titulo.mostrar());

        boolean salir = false;

        while (!salir) {
            TextoColor menu = new TextoColor(
                "\n1. Registrar libro\n2. Buscar libro\n3. Listar libros\n4. Salir",
                "verde"
            );
            System.out.println(menu.mostrar());

            System.out.print("Seleccione una opción: ");
            String opcion = sc.nextLine();

            switch (opcion) {
                case "1":
                    registrarLibro(sc, biblioteca);
                    break;
                case "2":
                    buscarLibro(sc, biblioteca);
                    break;
                case "3":
                    listarLibros(biblioteca);
                    break;
                case "4":
                    salir = true;
                    break;
                default:
                    TextoColor error = new TextoColor("Opción no válida.", "rojo");
                    System.out.println(error.mostrar());
            }
        }

        TextoColor despedida = new TextoColor("¡Hasta pronto!", "amarillo");
        System.out.println(despedida.mostrar());
    }

    private static void registrarLibro(Scanner sc, Biblioteca biblioteca) {
        System.out.print("ISBN: ");
        String isbn = sc.nextLine();

        // Verificar si ya existe
        if (biblioteca.buscarPorISBN(isbn) != null) {
            TextoColor duplicado = new TextoColor("Ya existe un libro con ese ISBN.", "rojo");
            System.out.println(duplicado.mostrar());
            return;
        }

        System.out.print("Título: ");
        String titulo = sc.nextLine();

        System.out.print("Autor: ");
        String autor = sc.nextLine();

        System.out.print("Géneros (separados por coma): ");
        String generosInput = sc.nextLine();
        String[] generos = generosInput.split(",");

        Libro nuevoLibro = new Libro(isbn, titulo, autor);

        for (String genero : generos) {
            nuevoLibro.agregaGenero(genero.trim());
        }

        biblioteca.agregaLibro(nuevoLibro);

        TextoColor exito = new TextoColor("Libro registrado correctamente.", "cyan");
        System.out.println(exito.mostrar());
    }

    private static void buscarLibro(Scanner sc, Biblioteca biblioteca) {
        System.out.println("Buscar por:\n1. ISBN\n2. Autor\n3. Título\n4. Género");
        System.out.print("Seleccione una opción: ");
        String opcion = sc.nextLine();

        System.out.print("Ingrese el valor a buscar: ");
        String valor = sc.nextLine();

        Libro resultado = null;

        switch (opcion) {
            case "1":
                resultado = biblioteca.buscarPorISBN(valor);
                break;
            case "2":
                resultado = biblioteca.buscarPorAutor(valor);
                break;
            case "3":
                resultado = biblioteca.buscarPorTitulo(valor);
                break;
            case "4":
                resultado = biblioteca.buscarPorGenero(valor);
                break;
        }

        if (resultado != null) {
            TextoColor encontrado = new TextoColor(resultado.toString(), "verde");
            System.out.println(encontrado.mostrar());
        } else {
            TextoColor noEncontrado = new TextoColor("No se encontró ningún libro.", "rojo");
            System.out.println(noEncontrado.mostrar());
        }
    }

    private static void listarLibros(Biblioteca biblioteca) {
        String todos = biblioteca.listar(); // Suponiendo que devuelve un string con todos los libros
        TextoColor lista = new TextoColor(todos, "magenta");
        System.out.println(lista.mostrar());
    }
}





}
}

