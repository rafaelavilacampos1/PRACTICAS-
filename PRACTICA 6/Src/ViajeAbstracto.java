import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

/* ---------------------- VIAJE ---------------------- */
abstract class ViajeAbstracto implements Validable {
    protected String origen; // 2 letras MAYÚSCULAS (para internacional), o planeta/país según tipo
    protected String destino;
    protected LocalDate fechaPartida;
    protected String numeroIdentificacion;
    protected LocalDate fechaVencimientoIdentificacion;
    protected PersonaAbstracta pasajero; // contiene nombre y edad
    protected List<String> errores = new ArrayList<>();
    protected static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("yyyy-M-d");

    public ViajeAbstracto(String origen, String destino, String fechaPartidaStr,
                          String numeroIdentificacion, String fechaVencimientoIdentificacionStr,
                          PersonaAbstracta pasajero) {
        this.origen = origen != null ? origen.trim() : "";
        this.destino = destino != null ? destino.trim() : "";
        this.numeroIdentificacion = numeroIdentificacion != null ? numeroIdentificacion.trim() : "";
        this.pasajero = pasajero;
        parsearFechas(fechaPartidaStr, fechaVencimientoIdentificacionStr);
    }

    private void parsearFechas(String fechaPartidaStr, String fechaVencimientoIdentificacionStr) {
        errores.removeIf(s -> s.startsWith("FECHA"));
        try {
            this.fechaPartida = LocalDate.parse(fechaPartidaStr, FORMATO_FECHA);
        } catch (DateTimeParseException e) {
            errores.add("FECHA: Fecha de partida inválida. Formato esperado: YYYY-M-D (ej: 2025-12-06).");
        }
        try {
            this.fechaVencimientoIdentificacion = LocalDate.parse(fechaVencimientoIdentificacionStr, FORMATO_FECHA);
        } catch (DateTimeParseException e) {
            errores.add("FECHA: Fecha de vencimiento de identificación inválida. Formato: YYYY-M-D.");
        }
    }

    protected void validarOrigenDestinoNoVacios() {
        if (origen.isEmpty()) errores.add("ORIGEN: Origen no puede estar vacío.");
        if (destino.isEmpty()) errores.add("DESTINO: Destino no puede estar vacío.");
    }

    protected void validarDestinoDistintoOrigen() {
        if (!origen.isEmpty() && !destino.isEmpty() && origen.equalsIgnoreCase(destino)) {
            errores.add("ORIGEN/DESTINO: El destino no puede ser igual al lugar del cual se parte.");
        }
    }

    protected void validarFechaPartidaMayorActual() {
        if (fechaPartida != null && !fechaPartida.isAfter(LocalDate.now())) {
            errores.add("FECHA: La fecha de partida debe ser mayor a la fecha actual (" + LocalDate.now() + ").");
        }
    }

    protected void validarIdentificacion() {
        if (numeroIdentificacion == null || numeroIdentificacion.isEmpty()) {
            errores.add("IDENTIFICACION: El número de identificación no puede ser vacío.");
        }
        if (fechaVencimientoIdentificacion != null && !fechaVencimientoIdentificacion.isAfter(LocalDate.now())) {
            errores.add("IDENTIFICACION: La fecha de vencimiento de la identificación debe ser mayor a la fecha actual.");
        }
    }

    protected void validarPasajeroBasico() {
        if (pasajero != null) {
            pasajero.validar();
            errores.addAll(pasajero.obtenerErrores());
        } else {
            errores.add("PASAJERO: Datos del pasajero ausentes.");
        }
    }

    @Override
    public List<String> obtenerErrores() {
        return errores;
    }
}