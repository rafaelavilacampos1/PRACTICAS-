import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.*;

public class CertificadoSalud extends CertificadoAbstracto {

    private String nombre;
    private String apellido;
    private String claveSalud; // A o NA
    private String tipoViaje;  // E o P

    public CertificadoSalud(String numeroCertificado) {
        super(numeroCertificado);
        parsearYValidar();
    }

    private void parsearYValidar() {

        errors.clear();

        // Validar prefijo
        if (!numeroCertificado.startsWith("CS-")) {
            errores.add("CERTIFICADO: Debe comenzar con 'CS-'.");
            return;
        }

        // Quitar "CS-"
        String cuerpo = numeroCertificado.substring(3);

        // Dividir
        String[] partes = cuerpo.split("-");

        // Esperamos exactamente 7 partes
        if (partes.length != 7) {
            errores.add("CERTIFICADO: Formato inválido. Ejemplo: CS-JUAN-PEREZ-2025-12-06-A-E");
            return;
        }

        try {
            this.nombre = partes[0];
            this.apellido = partes[1];

            int año = Integer.parseInt(partes[2]);
            int mes = Integer.parseInt(partes[3]);
            int dia = Integer.parseInt(partes[4]);

            this.claveSalud = partes[5].toUpperCase();
            this.tipoViaje = partes[6].toUpperCase();

            // Validar fecha
            this.fechaCertificado = LocalDate.of(año, mes, dia);

        } catch (NumberFormatException e) {
            errores.add("CERTIFICADO: Año, mes o día no son números válidos.");
            return;
        } catch (DateTimeException e) {
            errores.add("CERTIFICADO: La fecha del certificado no es válida.");
            return;
        }

        // Validación clave salud
        if (!claveSalud.equals("A") && !claveSalud.equals("NA")) {
            errores.add("CERTIFICADO: claveSalud inválida. Debe ser 'A' o 'NA'.");
        }

        // Validación tipo viaje
        if (!tipoViaje.equals("E") && !tipoViaje.equals("P")) {
            errores.add("CERTIFICADO: tipoViaje inválido. Debe ser 'E' o 'P'.");
        }
    }

    @Override
    public boolean validar() {
        return errores.isEmpty();
    }

    public LocalDate getFechaCertificado() {
        return (LocalDate) fechaCertificado;

    }

    public String getClaveSalud() {
        return claveSalud;
    }

    public String getTipoViaje() {
        return tipoViaje;
    }

    @Override
    public List<String> obtenerErrores() {
        return (List<String>) errores;
    }

    @Override
    public String toString() {
        return "CertificadoSalud{" +
                "numeroCertificado='" + numeroCertificado + '\'' +
                ", fechaCertificado=" + fechaCertificado +
                ", claveSalud='" + claveSalud + '\'' +
                ", tipoViaje='" + tipoViaje + '\'' +
                '}';
    }
}

