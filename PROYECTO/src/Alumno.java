/**
 * Clase que representa a un alumno dentro del sistema.
 * Implementa {@link Serializable} para permitir guardar objetos en archivos,
 * incluyendo su información personal y los cursos en los que está inscrito.
 */
import java.io.Serializable; // Importo esto pa que java sepa guardar el objeto en un archivo
import java.util.ArrayList;  // Importo la lista dinamica pq no se cuantos cursos va a meter

// La clase Alumno. "implements Serializable" es la magia pa guardar en disco duro
public class Alumno implements Serializable {
    
    // Esto es un numero de version pa que no se confunda al cargar el archivo despues
    /** Identificador de versión para la serialización del objeto. */
    private static final long serialVersionUID = 1L; 

    // --- AQUI VAN LOS DATOS DEL ALUMNO (Variables) ---
    /** Nombre completo del alumno. */
    private String nombre;          // Variable pa guardar el nombre completo

    /** Edad del alumno. */
    private int edad;               // Variable pa guardar la edad (numerito)

    /** Escuela de procedencia del alumno. */
    private String escuela;         // Variable pa guardar de donde viene

    /** Número de cuenta único del alumno (no se repite). */
    private final String numeroCuenta;    // Este es su ID unico, no se debe repetir

    /** Lista de IDs de cursos en los que está inscrito el alumno. */
    private final ArrayList<String> cursosInscritos; // Lista pa guardar los IDs de las materias q mete

    // --- CONSTRUCTOR ---
    /**
     * Crea un nuevo alumno con los datos proporcionados.
     *
     * @param nombre        Nombre del alumno
     * @param edad          Edad del alumno
     * @param escuela       Escuela de procedencia
     * @param numeroCuenta  Número de cuenta único
     */
    // Esta funcion se ejecuta solita cuando haces "new Alumno()"
    public Alumno(String nombre, int edad, String escuela, String numeroCuenta) {
        this.nombre = nombre;             // Guardo el nombre q me pasaron en la variable de arriba
        this.edad = edad;                 // Guardo la edad
        this.escuela = escuela;           // Guardo la escuela
        this.numeroCuenta = numeroCuenta; // Guardo el ID
        this.cursosInscritos = new ArrayList<>(); // Creo la lista vacia pa llenarla luego
    }

    // --- GETTERS (Pa sacar info pq las variables son privadas) ---
    /** @return Número de cuenta del alumno. */
    public String getNumeroCuenta() { return numeroCuenta; } // Devuelve el ID

    /** @return Nombre del alumno. */
    public String getNombre() { return nombre; }             // Devuelve el nombre

    /** @return Edad del alumno. */
    public int getEdad() { return edad; }                    // Devuelve la edad

    /** @return Escuela de procedencia del alumno. */
    public String getEscuela() { return escuela; }           // Devuelve la escuela

    // --- SETTERS (Pa cambiar info si me equivoque) ---
    /** Cambia el nombre del alumno. */
    public void setNombre(String nombre) { this.nombre = nombre; }   // Cambia el nombre

    /** Cambia la edad del alumno. */
    public void setEdad(int edad) { this.edad = edad; }              // Cambia la edad

    /** Cambia la escuela de procedencia del alumno. */
    public void setEscuela(String escuela) { this.escuela = escuela; } // Cambia la escuela

    // --- LOGICA DEL NEGOCIO ---

    /**
     * Verifica si el alumno aún puede inscribir más cursos.
     * La regla establece un máximo de 6 cursos inscritos.
     *
     * @return true si tiene menos de 6 cursos; false si ya alcanzó el límite
     */
    // Esta funcion checa si el alumno todavia tiene cupo en su horario
    public boolean puedeInscribir() {
        // La regla dice maximo 6. Si el tamaño de la lista es menor a 6, regresa true (simon)
        return this.cursosInscritos.size() < 6;
    }

    /**
     * Agrega un curso al historial del alumno si aún no está inscrito en él.
     *
     * @param idCurso ID del curso a agregar
     */
    // Esta funcion mete el ID de un curso a la lista del alumno
    public void agregarCurso(String idCurso) {
        // Primero pregunto: Oye, ¿ya tiene este curso en la lista?
        if (!this.cursosInscritos.contains(idCurso)) {
            this.cursosInscritos.add(idCurso); // Si no lo tiene, lo agrego
        }
    }

    /**
     * Devuelve una representación en texto del alumno,
     * incluyendo su nombre, número de cuenta y cantidad de cursos inscritos.
     *
     * @return Cadena con la información del alumno
     */
    // Esta funcion convierte todo el objeto en texto pa imprimirlo bonito en consola
    @Override
    public String toString() {
        // Regreso una cadena con el nombre, ID y cuantas materias lleva
        return "Alumno: " + nombre + " | ID: " + numeroCuenta + " | Materias inscritas: " + cursosInscritos.size();
    }
}
