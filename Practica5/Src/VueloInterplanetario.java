import java.time.LocalDate;

public class VueloInterplanetario extends Vuelo {

    private String planetaSalida;
    private String planetaDestino;
    private CertificadoSalud certificado;

    public VueloInterplanetario(
            String nombreCompleto, LocalDate fechaViaje, int edad,
            String planetaSalida, String planetaDestino,
            CertificadoSalud certificado) {

        super(nombreCompleto, fechaViaje, edad);
        this.planetaSalida = planetaSalida;
        this.planetaDestino = planetaDestino;
        this.certificado = certificado;
    }

    @Override
    public boolean validarDatos() {

        if (!validarNombre()) return false;
        if (!validarEdad()) return false;

        if (!fechaViaje.isAfter(LocalDate.now())) return false;

        if (planetaSalida.equals(planetaDestino)) return false;

        if (!certificado.esTipo("P")) return false;

        if (certificado.getFecha().isBefore(fechaViaje)) return false;

        return certificado.esApto();
    }
}
