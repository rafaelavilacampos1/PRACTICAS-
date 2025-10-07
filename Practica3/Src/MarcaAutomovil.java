public class MarcaAutomovil {
    private String nombre;
    private String paisFabricacion;
    private int precioImportacion;

    public MarcaAutomovil(String nombre, String paisFabricacion, int precioImportacion) {
        this.nombre = nombre;
        this.paisFabricacion = paisFabricacion;
        this.precioImportacion = precioImportacion;
    }

    public int getPrecioImportacion() {
        return precioImportacion;
    }

    @Override
    public String toString() {
        return "Marca: " + nombre + ", País: " + paisFabricacion + ", Precio Importación: $" + precioImportacion;
    }
}
