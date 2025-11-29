import java.time.LocalDate;

/* ---------------------- VIAJE ESTRATOSFÉRICO ---------------------- */


class ViajeEstratosferico extends ViajeAbstracto {
    private CertificadoSalud certificado;

    public ViajeEstratosferico(String origen, String destino, String fechaPartidaStr,
                        String numeroIdentificacion, String fechaVencimientoIdentificacionStr,
                        PersonaAbstracta pasajero, String numeroCertificado) {
        super(origen, destino, fechaPartidaStr, numeroIdentificacion, fechaVencimientoIdentificacionStr, pasajero);
        this.certificado = new CertificadoSalud(numeroCertificado);
    }

    public boolean validar() {
        errores.clear();
        validarPasajeroBasico();
        validarOrigenDestinoNoVacios();
        validarDestinoDistintoOrigen();
        validarFechaPartidaMayorActual();
        validarIdentificacion();

        // El certificado debe ser válido, de tipo 'E', y su fecha >= fecha de viaje
        if (!certificado.validar()) {
            errores.addAll(certificado.obtenerErrores());
        } else {
            if (!"E".equals(certificado.getTipoViaje())) {
                errores.add("CERTIFICADO: Para vuelos estratosféricos el certificado debe indicar tipo 'E'.");
            }
            if (certificado.getFechaCertificado() == null || fechaPartida == null ||
                    certificadoinvalidoFecha(certificado.getFechaCertificado(), fechaPartida)) {
                errores.add("CERTIFICADO: La fecha del certificado debe ser igual o mayor a la fecha de viaje.");
            }
            if ("NA".equals(certificado.getClaveSalud())) {
                errores.add("CERTIFICADO: El pasajero no está apto según el certificado de salud (NA).");
            }
        }

        return errores.isEmpty();
    }

    private boolean certificadoinvalidoFecha(LocalDate fechaCert, LocalDate fechaViaje) {
        return fechaCert.isBefore(fechaViaje);
    }
}