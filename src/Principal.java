import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Principal {

    static ListaEnlazadaSimple<Empleado> listaEmpleados = getEmpleados();
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        /* TODO: Validar entrada de empleados
         * Usar los metodos de busqueda para verificar que
         * no exista otro empleado con el mismo legajo o dni
         * en caso de que existan lanzar una excepcion `RuntimeExcepcion('mensaje')`
         * */

        imprimirLista(listaEmpleados);
//        agregarEmpleado();
    }

    public static LocalDate fechasAleatorias() {
        long minDay = LocalDate.of(1980, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(2014, 12, 31).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        return LocalDate.ofEpochDay(randomDay);
    }

    private static ListaEnlazadaSimple<Empleado> getEmpleados() {

        Empleado empleado1 = new Empleado("Erick Cruz", 212, 12, fechasAleatorias());
        Empleado empleado2 = new Empleado("Acosta Gloss", 122, 222, fechasAleatorias());
        Empleado empleado3 = new Empleado("Gonza Carillo", 321, 4444, fechasAleatorias());
        Empleado empleado4 = new Empleado("Brian Cruz", 444, 5555, fechasAleatorias());

        ListaEnlazadaSimple<Empleado> listaEmpleados = new ListaEnlazadaSimple<>();

        listaEmpleados.addLast(empleado1);
        listaEmpleados.addLast(empleado2);
        listaEmpleados.addLast(empleado3);
        listaEmpleados.addLast(empleado4);
        return listaEmpleados;
    }

    // TODO: Añadir busqueda por ingreso ´LocalTime´
    public static ArrayList<Empleado> buscarEmpleadoPor(String nombre) {
        ArrayList<Empleado> encontrados = new ArrayList<>();
        nombre = nombre.toLowerCase();
        for (Empleado empleado : listaEmpleados) {
            String nombreEmpleado = empleado.getNombre().toLowerCase();
            if (nombreEmpleado.equals(nombre)) {
                encontrados.add(empleado);
            }
        }
        return encontrados;
    }

    public static ArrayList<Empleado> buscarEmpleadoPor(LocalDate nacimiento) {
        ArrayList<Empleado> encontrados = new ArrayList<>();
        for (Empleado empleado : listaEmpleados) {
            LocalDate nacimientoEmpleado = empleado.getFechaNacimiento();

            if (nacimientoEmpleado.equals(nacimiento)) {
                encontrados.add(empleado);
            }
        }

        return encontrados;
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

    public static void agregarEmpleado() {
        System.out.println("\n**** Agregar empleado ****");
        String nombre = Helper.validarStringNoVacio(input, "Ingrese nombre del empleado:");
        // Legajo
        int legajo = Helper.validarEntero(input, "Ingrese legajo para el empleado '" + nombre + '\'');
        if (buscarEmpleadoPor("legajo", legajo, false) != null) {
            throw new RuntimeException("Ya existe un empleado con el legajo: '" + legajo + '\'');
        }
        // Dni
        int dni = Helper.validarEntero(input, "Ingrese dni para el empleado '" + nombre + '\'');
        if (buscarEmpleadoPor("dni", dni, false) != null) {
            throw new RuntimeException("Ya existe un empleado con el dni: '" + legajo + '\'');
        }

        LocalDate fechaNacimiento = Helper.validarFecha(input, "Ingrese fecha de nacimiento", "dd/MM/yyyy");

        listaEmpleados.addLast(new Empleado(nombre, legajo, dni, fechaNacimiento));
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
