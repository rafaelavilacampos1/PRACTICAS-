import java.util.ArrayList;

public class Biblioteca {
    private ArrayList<Libro> libros;

    public Biblioteca() {
        libros = new ArrayList<>();
    }

    public void agregarLibro(Libro libro) {
        libros.add(libro);
    }

    public Libro buscarPorISBN(String isbn) {
        for (Libro libro : libros) {
            if (libro.getIsbn().equalsIgnoreCase(isbn)) {
                return libro;
            }
        }
        return null;
    }

    public void buscarPorAutor(String autor) {
        boolean encontrado = false;
        for (Libro libro : libros) {
            if (libro.getAutor().equalsIgnoreCase(autor)) {
                new TextoColor(libro.toString(), "verde").mostrar();
                encontrado = true;
            }
        }
        if (!encontrado) {
            new TextoColor("No se encontraron libros de ese autor.", "rojo").mostrar();
        }
    }

    public void buscarPorTitulo(String titulo) {
        boolean encontrado = false;
        for (Libro libro : libros) {
            if (libro.getTitulo().equalsIgnoreCase(titulo)) {
                new TextoColor(libro.toString(), "verde").mostrar();
                encontrado = true;
            }
        }
        if (!encontrado) {
            new TextoColor("No se encontraron libros con ese título.", "rojo").mostrar();
        }
    }

    public void buscarPorGenero(String genero) {
        boolean encontrado = false;
        for (Libro libro : libros) {
            for (String g : libro.getGeneros()) {
                if (g.equalsIgnoreCase(genero)) {
                    new TextoColor(libro.toString(), "verde").mostrar();
                    encontrado = true;
                    break;
                }
            }
        }
        if (!encontrado) {
            new TextoColor("No se encontraron libros con ese género.", "rojo").mostrar();
        }
    }

    public void mostrarLibros() {
        if (libros.isEmpty()) {
            new TextoColor("No hay libros registrados.", "rojo").mostrar();
            return;
        }

        for (Libro libro : libros) {
            new TextoColor(libro.toString(), "azul").mostrar();
        }
    }
}
