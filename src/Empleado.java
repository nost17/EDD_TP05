import java.time.LocalDate;

public class Empleado {
    private int dni;
    private int legajo;
    private String nombre;
    private LocalDate ingreso;

    public Empleado() {
    }

    public Empleado(String nombre, int legajo, int dni, LocalDate ingreso) {
        this.nombre = nombre;
        this.legajo = legajo;
        this.dni = dni;
        this.ingreso = ingreso;
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

    public LocalDate getIngreso() {
        return ingreso;
    }

    public void setIngreso(LocalDate ingreso) {
        this.ingreso = ingreso;
    }

    @Override
    public String toString() {
        return "Empleado " + legajo + " { " +
                "Dni: " + dni +
                ", Nombre: '" + nombre + '\'' +
                ", Ingreso: " + ingreso +
                " }";
    }
}
