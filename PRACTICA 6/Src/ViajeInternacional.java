/* ---------------------- VIAJE INTERNACIONAL ---------------------- */
class ViajeInternacional extends ViajeAbstracto {
    private String nacionalidad; // 2 letras mayúsculas

    public ViajeInternacional(String origen, String destino, String fechaPartidaStr,
                              String numeroIdentificacion, String fechaVencimientoIdentificacionStr,
                              PersonaAbstracta pasajero, String nacionalidad) {
        super(origen, destino, fechaPartidaStr, numeroIdentificacion, fechaVencimientoIdentificacionStr, pasajero);
        this.nacionalidad = nacionalidad != null ? nacionalidad.trim() : "";
    }

    private void validarCodigoPais(String codigo, String campo) {
        if (codigo == null || codigo.length() != 2 || !codigo.equals(codigo.toUpperCase()) || !codigo.matches("[A-Z]{2}")) {
            errores.add(campo + ": Debe constar de solo dos letras en mayúsculas.");
        }
    }

    public boolean validar() {
        errores.clear();
        validarPasajeroBasico();
        validarOrigenDestinoNoVacios();
        validarDestinoDistintoOrigen();
        validarFechaPartidaMayorActual();
        validarIdentificacion();
        validarCodigoPais(origen, "ORIGEN");
        validarCodigoPais(nacionalidad, "NACIONALIDAD");
        return errores.isEmpty();
    }
}