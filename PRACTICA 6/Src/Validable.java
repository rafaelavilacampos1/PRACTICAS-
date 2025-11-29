import java.util.*;



/**
 * Agencia Aeroespacial - versión con clases abstractas e interfaces.
 * Autor: Rafael Avila Campos no cuenta 323101288
 */

interface Validable {
    boolean validar();
    List<String> obtenerErrores();
}

/* ---------------------- PERSONA ---------------------- */
abstract class PersonaAbstracta implements Validable {
    protected String nombreCompleto;
    protected int edad;
    protected List<String> errores = new ArrayList<>();

    public PersonaAbstracta(String nombreCompleto, int edad) {
        this.nombreCompleto = nombreCompleto != null ? nombreCompleto.trim() : "";
        this.edad = edad;
    }

    public void validarNombreCompleto() {
        errores.removeIf(s -> s.startsWith("NOMBRE"));
        if (nombreCompleto.isEmpty() || !nombreCompleto.contains(" ")) {
            errores.add("NOMBRE: El nombre completo debe contener al menos un nombre y un apellido.");
        }
    }

    public void validarEdad() {
        errores.removeIf(s -> s.startsWith("EDAD"));
        if (edad < 18) errores.add("EDAD: No pueden volar personas menores de 18 años.");
        if (edad > 75) errores.add("EDAD: No pueden volar personas mayores de 75 años.");
    }

    @Override
    public List<String> obtenerErrores() {
        return errores;
    }
}