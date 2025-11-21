import java.time.LocalDate;

public abstract class Vuelo {

    protected String nombreCompleto;
    protected LocalDate fechaViaje;
    protected int edad;

    public Vuelo(String nombreCompleto, LocalDate fechaViaje, int edad) {
        this.nombreCompleto = nombreCompleto;
        this.fechaViaje = fechaViaje;
        this.edad = edad;
    }

    // Método abstracto que cada tipo de vuelo implementará
    public abstract boolean validarDatos();

    // Método común para validar nombre
    protected boolean validarNombre() {
        return nombreCompleto != null && nombreCompleto.trim().contains(" ");
    }

    // Método común para validar edad (solo estratosférico e interplanetario)
    protected boolean validarEdad() {
        return edad >= 18 && edad <= 75;
    }
}
