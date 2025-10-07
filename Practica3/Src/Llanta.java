/**
 * Clase que representa las llantas del automóvil.
 * Incluye la marca, el tamaño del rin, el material y el precio total.
 */
public class Llanta {

    // Marca de las llantas (Yokohama, Firestone, etc.)
    private String marca;

    // Tamaño del rin (en pulgadas)
    private int tamaño;

    // Material del rin (aluminio o acero)
    private String material;

    // Precio total de las llantas, incluyendo material y marca
    private int precio;

    /**
     * Constructor de la clase Llanta.
     *
     * @param marca    Marca de las llantas
     * @param tamaño   Tamaño del rin en pulgadas
     * @param material Material del rin
     * @param precio   Precio total de las llantas
     */
    public Llanta(String marca, int tamaño, String material, int precio) {
        this.marca = marca;
        this.tamaño = tamaño;
        this.material = material;
        this.precio = precio;
    }

    /**
     * Devuelve el precio total de las llantas.
     *
     * @return precio de las llantas
     */
    public int getPrecio() {
        return precio;
    }

    /**
     * Devuelve una representación en texto de las llantas,
     * mostrando la marca, tamaño, material y precio.
     *
     * @return descripción de las llantas
     */
    @Override
    public String toString() {
        return "Llantas: Marca " + marca + ", Tamaño: " + tamaño + "\", Material: " + material + ", Precio: $" + precio;
    }
}
