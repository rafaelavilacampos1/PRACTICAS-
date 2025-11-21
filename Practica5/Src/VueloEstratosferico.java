import java.time.LocalDate;

public class VueloEstratosferico extends VueloInternacional {

    private CertificadoSalud certificado;

    public VueloEstratosferico(
            String nombreCompleto, LocalDate fechaViaje, int edad, String paisSalida, String nacionalidad, LocalDate vencimientoID, String numeroID, String destino, CertificadoSalud certificado) {

        super(nombreCompleto, fechaViaje, edad,
              paisSalida, nacionalidad, vencimientoID, numeroID, destino);

        this.certificado = certificado;
    }

    @Override
    public boolean validarDatos() {

        if (!super.validarDatos()) return false;

        if (!validarEdad()) return false;

        // Certificado debe ser estratosférico (E)
        if (!certificado.esTipo("E")) return false;

        // Fecha del certificado >= fecha del viaje
        if (certificado.getFecha().isBefore(fechaViaje)) return false;

        return certificado.esApto();
    }
}
