public class Libro {
    private String isbn;
    private String titulo;
    private String autor;
    private String[] generos;

    public Libro(String isbn, String titulo, String autor, String generos) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.generos = generos.split("\\s*,\\s*");
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String[] getGeneros() {
        return generos;
    }

    @Override
    public String toString() {
        return "ISBN: " + isbn +
            "\nTítulo: " + titulo +
            "\nAutor: " + autor +
            "\nGéneros: " + String.join(", ", generos);
    }
}
