/**
 * Clase que representa un curso dentro del sistema. 
 * Incluye información general del curso, su profesor asignado,
 * el cupo máximo y la lista de alumnos inscritos.
 * Implementa {@link Serializable} para permitir guardar y cargar datos desde archivo.
 */
import java.io.Serializable; // Pa guardar
import java.util.ArrayList;  // Pa listas

// Clase Curso, aqui se junta todo
public class Curso implements Serializable {
    private static final long serialVersionUID = 1L; // Version del archivo

    // --- DATOS DEL CURSO ---
    /** ID único que identifica al curso. */
    private final String idCurso;    // ID unico del curso

    /** Nombre del curso (ej. Matemáticas I). */
    private String nombre;     // Nombre (ej. Matematicas I)

    /** Categoría del curso (ej. Ciencias). */
    private String categoria;  // Categoria (ej. Ciencias)

    /** Nivel educativo del curso (ej. Prepa). */
    private String nivel;      // Nivel (ej. Prepa)

    /** Plataforma utilizada para impartir el curso (Zoom, Meet, etc.). */
    private String plataforma; // Zoom, Meet, etc

    /** Hora en que inicia el curso, en formato 24 horas. */
    private int horaInicio;    // Hora q empieza (formato 24h)

    /** Hora en que termina el curso. */
    private int horaFin;       // Hora q termina

    /** Cantidad máxima de alumnos permitidos en el curso. */
    private final int cupoMaximo;    // Cuantos alumnos caben
    
    /** Fecha de inicio de inscripciones. */
    private String fechaInscripcionInicio; 

    /** Fecha de fin de inscripciones. */
    private String fechaInscripcionFin;
    
    /** Profesor asignado al curso, o null si no hay profesor aún. */
    private Profesor profesorAsignado;          // Variable q guarda al objeto Profesor entero

    /** Lista de alumnos inscritos en el curso. */
    private final ArrayList<Alumno> alumnosInscritos; // Lista q guarda objetos Alumno

    // --- CONSTRUCTOR ---
    /**
     * Crea un nuevo curso con los datos proporcionados.
     *
     * @param id      ID único del curso
     * @param nombre  Nombre del curso
     * @param cat     Categoría del curso
     * @param niv     Nivel educativo del curso
     * @param plat    Plataforma donde se imparte
     * @param hInicio Hora de inicio (formato 24h)
     * @param hFin    Hora de término
     * @param cupo    Cupo máximo de alumnos
     */
    public Curso(String id, String nombre, String cat, String niv, String plat, int hInicio, int hFin, int cupo) {
        this.idCurso = id;          // Guardo ID
        this.nombre = nombre;       // Guardo nombre
        this.categoria = cat;       // Guardo categoria
        this.nivel = niv;           // Guardo nivel
        this.plataforma = plat;     // Guardo plataforma
        this.horaInicio = hInicio;  // Guardo hora inicio
        this.horaFin = hFin;        // Guardo hora fin
        this.cupoMaximo = cupo;     // Guardo cupo
        this.profesorAsignado = null; // Empiezo en null pq no hay profe asignado aun
        this.alumnosInscritos = new ArrayList<>(); // Creo la lista de alumnos vacia
    }

    // --- GETTERS ---
    /** @return ID del curso. */
    public String getIdCurso() { return idCurso; }       // Dame ID

    /** @return Nombre del curso. */
    public String getNombre() { return nombre; }         // Dame nombre

    /** @return Nivel educativo del curso. */
    public String getNivel() { return nivel; }           // Dame nivel

    /** @return Categoría del curso. */
    public String getCategoria() { return categoria; }   // Dame categoria

    /** @return Plataforma usada para impartir el curso. */
    public String getPlataforma() { return plataforma; } // Dame plataforma

    /** @return Cantidad actual de alumnos inscritos. */
    public int getCantidadAlumnos() { return alumnosInscritos.size(); } // Cuantos alumnos hay inscritos?

    /** @return true si el curso ya tiene un profesor asignado. */
    public boolean tieneProfesor() { return profesorAsignado != null; } // Regresa true si la variable profe NO esta vacia

    // --- SETTERS (Pa editar) ---
    /** Cambia el nombre del curso. */
    public void setNombre(String nombre) { this.nombre = nombre; } // Cambia nombre

    /** Cambia la categoría del curso. */
    public void setCategoria(String cat) { this.categoria = cat; } // Cambia categoria

    /** Cambia el nivel educativo del curso. */
    public void setNivel(String niv) { this.nivel = niv; }         // Cambia nivel

    /** Cambia la plataforma del curso. */
    public void setPlataforma(String plat) { this.plataforma = plat; } // Cambia plataforma

    /**
     * Establece las fechas de inicio y fin de inscripción del curso.
     *
     * @param inicio Fecha de inicio de inscripciones
     * @param fin    Fecha de fin de inscripciones
     */
    public void setFechasInscripcion(String inicio, String fin) { // Cambia las fechas
        this.fechaInscripcionInicio = inicio;
        this.fechaInscripcionFin = fin;
    }

    // --- LOGICA ---

    /**
     * Asigna un profesor al curso.
     *
     * @param p Profesor que será asignado
     */
    public void asignarProfesor(Profesor p) {
        this.profesorAsignado = p; // Guardo al profe q me pasaron
    }

    /**
     * Intenta inscribir a un alumno en el curso.
     * Verifica que haya cupo y que el alumno no esté ya inscrito.
     *
     * @param a Alumno a inscribir
     * @return true si se inscribió exitosamente, false si no había cupo o ya estaba inscrito
     */
    public boolean inscribirAlumno(Alumno a) {
        // 1. Checo cupo: Si ya hay mas o igual alumnos que el maximo...
        if (alumnosInscritos.size() >= cupoMaximo) return false; // ...regreso false (fallo)
        
        // 2. Checo duplicados: Recorro la lista de los q ya estan...
        for(Alumno existente : alumnosInscritos) {
            // ...si el ID del q ya esta es igual al ID del nuevo...
            if(existente.getNumeroCuenta().equals(a.getNumeroCuenta())) {
                return false; // ...regreso false (ya estaba inscrito)
            }
        }
        
        // Si paso las pruebas, lo agrego a la lista
        alumnosInscritos.add(a);
        return true; // Regreso true (exito)
    }

    /**
     * Indica si todavía hay espacio disponible en el curso.
     *
     * @return true si el número de alumnos es menor al cupo máximo
     */
    public boolean hayCupo() {
        return alumnosInscritos.size() < cupoMaximo; // True si hay espacio
    }

    /**
     * Devuelve una cadena con la información principal del curso,
     * incluyendo el profesor asignado y el estado del cupo.
     *
     * @return Cadena formateada con la información del curso
     */
    @Override
    public String toString() {
        // Operador ternario: Si profe es null, pon "SIN ASIGNAR", si no, pon su nombre
        String nombreProfe = (profesorAsignado == null) ? "SIN ASIGNAR" : profesorAsignado.getNombre();
        // Regreso una cadena formateada bonita con todos los datos
        return String.format("Curso: %s [%s] | ID: %s | Profe: %s | Alumnos: %d/%d", 
               nombre, categoria, idCurso, nombreProfe, alumnosInscritos.size(), cupoMaximo);
    }
}
