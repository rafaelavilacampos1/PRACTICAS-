public class Llanta {
    private String marca;
    private int tamaño;
    private String material;
    private int precio;

    public Llanta(String marca, int tamaño, String material, int precio) {
        this.marca = marca;
        this.tamaño = tamaño;
        this.material = material;
        this.precio = precio;
    }

    public int getPrecio() {
        return precio;
    }

    @Override
    public String toString() {
        return "Llantas: Marca " + marca + ", Tamaño: " + tamaño + "\", Material: " + material + ", Precio: $" + precio;
    }
}
