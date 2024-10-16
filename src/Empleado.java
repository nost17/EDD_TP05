import java.time.LocalDate;

public class Empleado {
    private int dni;
    private int legajo;
    private String nombre;
    private LocalDate fechaNacimiento;

    public Empleado() {
    }

    public Empleado(String nombre, int legajo, int dni, LocalDate fechaNacimiento) {
        this.nombre = nombre;
        this.legajo = legajo;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
    }

    public Empleado(String nombre, int legajo, int dni) {
        this.nombre = nombre;
        this.legajo = legajo;
        this.dni = dni;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public int getLegajo() {
        return legajo;
    }

    public void setLegajo(int legajo) {
        this.legajo = legajo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Override
    public String toString() {
        return "Empleado " + legajo + " { " +
                "Dni: " + dni +
                ", Nombre: '" + nombre + '\'' +
                ", Nacimiento: " + fechaNacimiento +
                " }";
    }
}
