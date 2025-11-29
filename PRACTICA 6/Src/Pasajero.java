

/* ---------------------- IMPLEMENTACIÓN CONCRETA DE Persona ---------------------- */
class Pasajero extends PersonaAbstracta {
    public Pasajero(String nombreCompleto, int edad) {
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