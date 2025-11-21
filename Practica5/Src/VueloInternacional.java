import java.time.LocalDate;

public class VueloInternacional extends Vuelo {

    private String paisSalida;
    private String nacionalidad;
    private LocalDate vencimientoID;
    private String numeroID;
    private String destino;

    public VueloInternacional(
            String nombreCompleto, LocalDate fechaViaje, int edad,
            String paisSalida, String nacionalidad, LocalDate vencimientoID,
            String numeroID, String destino) {

        super(nombreCompleto, fechaViaje, edad);
        this.paisSalida = paisSalida;
        this.nacionalidad = nacionalidad;
        this.vencimientoID = vencimientoID;
        this.numeroID = numeroID;
        this.destino = destino;
    }

    @Override
    public boolean validarDatos() {

        if (!validarNombre()) return false;
        if (paisSalida.length() != 2) return false;
        if (nacionalidad.length() != 2) return false;
        if (!vencimientoID.isAfter(LocalDate.now())) return false;
        if (numeroID.isEmpty()) return false;
        if (!fechaViaje.isAfter(LocalDate.now())) return false;
        if (paisSalida.equals(destino)) return false;

        return true;
    }
}
