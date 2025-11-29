

/* ---------------------- IMPLEMENTACIÓN CONCRETA DE Persona ---------------------- */
class Persona extends PersonaAbstracta {
    public Persona(String nombreCompleto, int edad) {
        super(nombreCompleto, edad);
    }

    @Override
    public boolean validar() {
        errores.clear();
        validarNombreCompleto();
        validarEdad();
        return errores.isEmpty();
    }
}