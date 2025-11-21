import java.time.LocalDate;

public class CertificadoSalud {

    private String nombre;
    private String apellido;
    private LocalDate fecha;
    private String claveSalud;  // A o NA
    private String tipoViaje;   // E o P

    public CertificadoSalud(String codigo) {
        // Formato: CS-NOMBRE-APELLIDO-AAAA-MM-DD-CLAVE-TIPO
        String[] p = codigo.split("-");

        if (p.length != 8 || !p[0].equals("CS")) {
            throw new IllegalArgumentException("Certificado invalido.");
        }

        this.nombre = p[1];
        this.apellido = p[2];
        this.fecha = LocalDate.of(Integer.parseInt(p[3]),
                                Integer.parseInt(p[4]),
                                Integer.parseInt(p[5]));
        this.claveSalud = p[6];
        this.tipoViaje = p[7];
    }

    public boolean esApto() {
        return claveSalud.equals("A");
    }

    public boolean esTipo(String tipo) {
        return tipoViaje.equals(tipo);
    }

    public LocalDate getFecha() {
        return fecha;
    }
}
