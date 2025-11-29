import java.time.LocalDate;
import java.util.*;

/* ---------------------- VIAJE INTERPLANETARIO ---------------------- */
class ViajeInterplanetario extends ViajeAbstracto {
    private String planetaOrigen;
    private String planetaDestino;
    private CertificadoSalud certificado;
    private static final Set<String> PLANETAS_SISTEMA_SOLAR = new HashSet<>(Arrays.asList(
            "MERCURIO", "VENUS", "TIERRA", "MARTE", "JUPITER", "SATURNO", "URANO", "NEPTUNO"
    ));

    public ViajeInterplanetario(String planetaOrigen, String planetaDestino, String fechaPartidaStr,
                                String numeroIdentificacion, String fechaVencimientoIdentificacionStr,
                                PersonaAbstracta pasajero, String numeroCertificado) {
        // Usamos origen/destino genéricos de la superclase para guardar los planetas (no códigos de país)
        super(planetaOrigen, planetaDestino, fechaPartidaStr, numeroIdentificacion, fechaVencimientoIdentificacionStr, pasajero);
        this.planetaOrigen = planetaOrigen != null ? planetaOrigen.trim().toUpperCase() : "";
        this.planetaDestino = planetaDestino != null ? planetaDestino.trim().toUpperCase() : "";
        this.certificado = new CertificadoSalud(numeroCertificado);
    }

    private void validarPlanetas() {
        if (!PLANETAS_SISTEMA_SOLAR.contains(planetaOrigen)) {
            errores.add("PLANETA: Planeta de origen no es válido o no pertenece al sistema solar.");
        }
        if (!PLANETAS_SISTEMA_SOLAR.contains(planetaDestino)) {
            errores.add("PLANETA: Planeta destino no es válido o no pertenece al sistema solar.");
        }
        if (planetaOrigen.equals(planetaDestino)) {
            errores.add("PLANETA: No se puede volar hacia el mismo planeta del cual se parte.");
        }
    }

    public boolean validar() {
        errores.clear();
        validarPasajeroBasico();
        validarPlanetas();
        validarFechaPartidaMayorActual();
        validarIdentificacion();
        // No requiere nacionalidad para interplanetario según especificación.

        // Certificado: debe ser tipo 'P' (interplanetario) y fecha >= fecha de viaje y claveSalud 'A'
        if (!certificado.validar()) {
            errores.addAll(certificado.obtenerErrores());
        } else {
            if (!"P".equals(certificado.getTipoViaje())) {
                errores.add("CERTIFICADO: Para vuelos interplanetarios el certificado debe indicar tipo 'P'.");
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