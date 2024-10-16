import java.time.LocalDate;
import java.util.Iterator;
import java.util.Scanner;

public class Principal {

    static ListaEnlazadaSimple<Empleado> listaEmpleados = getEmpleados();


    public static void main(String[] args) {
        /* TODO: Validar entrada de empleados
         * Usar los metodos de busqueda para verificar que
         * no exista otro empleado con el mismo legajo o dni
         * en caso de que existan lanzar una excepcion `RuntimeExcepcion('mensaje')`
         * */
    }

    private static ListaEnlazadaSimple<Empleado> getEmpleados() {
        Empleado empleado1 = new Empleado("Erick Cruz", 212, 12);
        Empleado empleado2 = new Empleado("Acosta Gloss", 122, 222);
        Empleado empleado3 = new Empleado("Gonza Carillo", 321, 4444);
        Empleado empleado4 = new Empleado("Brian Cruz", 444, 5555);

        ListaEnlazadaSimple<Empleado> listaEmpleados = new ListaEnlazadaSimple<>();

        listaEmpleados.addLast(empleado1);
        listaEmpleados.addLast(empleado2);
        listaEmpleados.addLast(empleado3);
        listaEmpleados.addLast(empleado4);
        return listaEmpleados;
    }

    // TODO: Añadir busqueda por ingreso ´LocalTime´
    public static Empleado buscarEmpleadoPor(String nombre, boolean remover) {
        nombre = nombre.toLowerCase();
        for (Empleado empleado : listaEmpleados) {
            String nombreEmpleado = empleado.getNombre().toLowerCase();
            if (nombreEmpleado.equals(nombre)) {
                if (remover) {
                    listaEmpleados.removeOf(empleado);
                }
                return empleado;
            }
        }
        return null;
    }

    public static Empleado buscarEmpleadoPor(String tipo, int dato, boolean remover) {
        for (Empleado empleado : listaEmpleados) {
            int datoEmpleado = tipo.equals("dni") ? empleado.getDni() : empleado.getLegajo();

            if (datoEmpleado == dato) {
                if (remover) {
                    listaEmpleados.removeOf(empleado);
                }
                return empleado;
            }

        }
        return null;
    }

    public static void imprimirLista(ListaEnlazadaSimple<Empleado> empleados) {
        for (Empleado empleado : empleados) {
            System.out.println(empleado);
        }
    }

    public static void imprimirMenu() {
        System.out.println("\n**** Gestor de empleados ****");
        System.out.println("1) Agregar un empleado.");
        System.out.println("2) Eliminar un empleado.");
        System.out.println("3) Buscar un empleado.");
        System.out.println("4) Mostrar todos los empleados.");
        System.out.println("0) Salir.");
    }
}
