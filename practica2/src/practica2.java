/**
 * Nombre del alumno: Avila Campos Rafael
 * Numero de cuenta  : 323101288
 *
 */


import java.util.Scanner;


/**
 * Clase principal que simula un sistema simple de biblioteca
 */
public class practica2 {

    private static void listarLibros(Biblioteca biblioteca) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private static void buscarLibro(Scanner sc, Biblioteca biblioteca) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    // -------------------- Clase TextoColor --------------------
    /**
     * Clase para mostrar texto con color en consola usando códigos ANSI.
     */
    static class TextoColor {
        private String texto;
        private String color;

        public TextoColor(String texto, String color) {
            this.texto = texto;
            this.color = color;
        }

        /**
         * Devuelve el texto con el color especificado en consola.
         */
        public String mostrar() {
            String codigoColor = switch (color.toLowerCase()) {
                case "rojo" -> "\u001B[31m";
                case "verde" -> "\u001B[32m";
                case "amarillo" -> "\u001B[33m";
                case "azul" -> "\u001B[34m";
                case "magenta" -> "\u001B[35m";
                case "cyan" -> "\u001B[36m";
                default -> "\u001B[0m"; // Color por defecto
            };
            return codigoColor + texto + "\u001B[0m";
        }
    }

    // -------------------- Clase Libro --------------------
    /**
     * Representa un libro con ISBN, título, autor y hasta 3 géneros.
     * No se usan arreglos para los géneros.
     */
    static class Libro {
        private String isbn;
        private String titulo;
        private String autor;

        // Variables individuales para hasta 3 géneros
        private String genero1;
        private String genero2;
        private String genero3;
        private int contadorGeneros = 0;

        public Libro(String isbn, String titulo, String autor) {
            this.isbn = isbn;
            this.titulo = titulo;
            this.autor = autor;
        }

        /**
         * Agrega un género al libro. Máximo 3.
         */
        public void agregaGenero(String genero) {
            if (contadorGeneros == 0) {
                genero1 = genero;
            } else if (contadorGeneros == 1) {
                genero2 = genero;
            } else if (contadorGeneros == 2) {
                genero3 = genero;
            }
            contadorGeneros++;
        }

        /**
         * Verifica si el libro tiene el género especificado.
         */
        public boolean tieneGenero(String genero) {
            return (genero1 != null && genero1.equalsIgnoreCase(genero)) ||
                   (genero2 != null && genero2.equalsIgnoreCase(genero)) ||
                   (genero3 != null && genero3.equalsIgnoreCase(genero));
        }

        // Getters
        public String getISBN() { return isbn; }
        public String getTitulo() { return titulo; }
        public String getAutor() { return autor; }

        /**
         * Representación en texto del libro.
         */
        @Override
        public String toString() {
            String generos = "";
            if (genero1 != null) generos += genero1;
            if (genero2 != null) generos += ", " + genero2;
            if (genero3 != null) generos += ", " + genero3;

            return "ISBN: " + isbn + "\nTítulo: " + titulo + "\nAutor: " + autor + "\nGéneros: " + generos;
        }
    }

    // -------------------- Clase Biblioteca --------------------
    /**
     * Clase que almacena hasta 3 libros usando variables individuales.
     */
    static class Biblioteca {
        private Libro libro1;
        private Libro libro2;
        private Libro libro3;

        /**
         * Agrega un libro si hay espacio disponible.
         */
        public void agregaLibro(Libro libro) {
            if (libro1 == null) {
                libro1 = libro;
            } else if (libro2 == null) {
                libro2 = libro;
            } else if (libro3 == null) {
                libro3 = libro;
            }
        }

        // Métodos de búsqueda
        public Libro buscarPorISBN(String isbn) {
            if (libro1 != null && libro1.getISBN().equalsIgnoreCase(isbn)) return libro1;
            if (libro2 != null && libro2.getISBN().equalsIgnoreCase(isbn)) return libro2;
            if (libro3 != null && libro3.getISBN().equalsIgnoreCase(isbn)) return libro3;
            return null;
        }

        public Libro buscarPorTitulo(String titulo) {
            if (libro1 != null && libro1.getTitulo().equalsIgnoreCase(titulo)) return libro1;
            if (libro2 != null && libro2.getTitulo().equalsIgnoreCase(titulo)) return libro2;
            if (libro3 != null && libro3.getTitulo().equalsIgnoreCase(titulo)) return libro3;
            return null;
        }

        public Libro buscarPorAutor(String autor) {
            if (libro1 != null && libro1.getAutor().equalsIgnoreCase(autor)) return libro1;
            if (libro2 != null && libro2.getAutor().equalsIgnoreCase(autor)) return libro2;
            if (libro3 != null && libro3.getAutor().equalsIgnoreCase(autor)) return libro3;
            return null;
        }

        public Libro buscarPorGenero(String genero) {
            if (libro1 != null && libro1.tieneGenero(genero)) return libro1;
            if (libro2 != null && libro2.tieneGenero(genero)) return libro2;
            if (libro3 != null && libro3.tieneGenero(genero)) return libro3;
            return null;
        }

        /**
         * Devuelve una lista textual de todos los libros registrados.
         */
        public String listar() {
            String resultado = "";
            if (libro1 != null) resultado += libro1.toString() + "\n\n";
            if (libro2 != null) resultado += libro2.toString() + "\n\n";
            if (libro3 != null) resultado += libro3.toString() + "\n\n";
            if (resultado.isEmpty()) return "No hay libros registrados.";
            return resultado;
        }
    }

    // -------------------- Método principal --------------------
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Biblioteca biblioteca = new Biblioteca();

        // Mostrar título con color
        TextoColor titulo = new TextoColor("Biblioteca Nacional de Francia", "azul");
        System.out.println(titulo.mostrar());

        boolean salir = false;

        // Bucle del menú principal
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

    // -------------------- Métodos auxiliares --------------------

    /**
     * Permite registrar un nuevo libro y agregar hasta 3 géneros.
     */
    private static void registrarLibro(Scanner sc, Biblioteca biblioteca) {
        System.out.print("ISBN: ");
        String isbn = sc.nextLine();

        // Verificar si ya existe un libro con ese ISBN
        if (biblioteca.buscarPorISBN(isbn) != null) {
            TextoColor duplicado = new TextoColor("Ya existe un libro con ese ISBN.", "rojo");
            System.out.println(duplicado.mostrar());
            return;
        }

        System.out.print("Título: ");
        String titulo = sc.nextLine();

        System.out.print("Autor: ");
        String autor = sc.nextLine();

        Libro nuevoLibro = new Libro(isbn, titulo, autor);

        // Solicitar hasta 3 géneros
        System.out.print("¿Cuántos géneros desea agregar? (máximo 3): ");
        int cantidadGeneros = Integer.parseInt(sc.nextLine());

        if (cantidadGeneros > 3) cantidadGeneros = 3;

        for (int i = 1; i <= cantidadGeneros; i++) {
            System.out.print("Género " + i + ": ");
            String genero = sc.nextLine().trim();
            nuevoLibro.agregaGenero(genero);
        }

        biblioteca.agregaLibro(nuevoLibro);

        TextoColor exito = new TextoColor("Libro registrado correctamente.", "cyan");
        System.out.println(exito.mostrar());
    }

}