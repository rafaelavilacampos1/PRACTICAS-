public class Chasis {
    private String color;
    private String acabado;
    private int precio;

    public Chasis(String color, String acabado, int precio) {
        this.color = color;
        this.acabado = acabado;
        this.precio = precio;
    }

    public int getPrecio() {
        return precio;
    }

    @Override
    public String toString() {
        return "Chasis: Color " + color + ", Acabado: " + acabado + ", Precio: $" + precio;
    }
}
