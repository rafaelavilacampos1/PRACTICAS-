import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

abstract class CertificadoAbstracto implements Validable {
    public String numeroCertificado;
    public LocalDate fechaCertificado;
    public List<String> errores = new ArrayList<>();

    public CertificadoAbstracto(String numeroCertificado) {
        this.numeroCertificado = numeroCertificado != null ? numeroCertificado.trim() : "";
    }

    @Override
    public List<String> obtenerErrores() {
        return errores;
    }
}
