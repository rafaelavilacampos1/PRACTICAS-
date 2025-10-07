/**
 * Clase que representa el chasis del automóvil.
 * Incluye el color, el tipo de acabado y el precio correspondiente.
 */
public class Chasis {

    // Color del chasis seleccionado por el usuario
    private String color;

    // Tipo de acabado del chasis (mate, brillante, metálico)
    private String acabado;

    // Precio adicional según el acabado elegido
    private int precio;

    /**
     * Constructor de la clase Chasis.
     *
     * @param color   Color elegido para el chasis
     * @param acabado Tipo de acabado del chasis
     * @param precio  Precio adicional del acabado
     */
    public Chasis(String color, String acabado, int precio) {
        this.color = color;
        this.acabado = acabado;
        this.precio = precio;
    }

    /**
     * Devuelve el precio del chasis (basado en el tipo de acabado).
     *
     * @return precio del acabado del chasis
     */
    public int getPrecio() {
        return precio;
    }

    /**
     * Devuelve una representación en texto del chasis,
     * incluyendo su color, acabado y precio.
     *
     * @return descripción del chasis
     */
    @Override
    public String toString() {
        return "Chasis: Color " + color + ", Acabado: " + acabado + ", Precio: $" + precio;
    }
}
