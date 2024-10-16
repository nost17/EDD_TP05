import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Principal {

    static ListaEnlazadaSimple<Empleado> listaEmpleados = getEmpleados();
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {


    }

    public static LocalDate fechasAleatorias() {
        long minDay = LocalDate.of(1980, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(2014, 12, 31).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        return LocalDate.ofEpochDay(randomDay);
    }

    private static ListaEnlazadaSimple<Empleado> getEmpleados() {

        Empleado empleado1 = new Empleado("Erick Cruz", "ejemplo1@mail.com", 212, 12, fechasAleatorias());
        Empleado empleado2 = new Empleado("Acosta Gloss", "ejemplo2@mail.com", 122, 222, fechasAleatorias());
        Empleado empleado3 = new Empleado("Gonza Carillo", "ejemplo3@mail.com", 321, 4444, fechasAleatorias());
        Empleado empleado4 = new Empleado("Brian Cruz", "ejemplo4@mail.com", 444, 5555, fechasAleatorias());
        Empleado empleado5 = new Empleado("Erick Cruz", "ejemplo5@mail.com", 511, 1152, fechasAleatorias());

        ListaEnlazadaSimple<Empleado> listaEmpleados = new ListaEnlazadaSimple<>();

        listaEmpleados.addLast(empleado1);
        listaEmpleados.addLast(empleado2);
        listaEmpleados.addLast(empleado3);
        listaEmpleados.addLast(empleado4);
        listaEmpleados.addLast(empleado5);
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

    public static Empleado buscarEmpleadoPor(String correo, boolean remover) {
        for (Empleado empleado : listaEmpleados) {
            if (empleado.getEmail().equals(correo)) {
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
        // E-mail
        String correo = Helper.validarCorreoElectronico(input, "Ingrese e-mail del empleado");
        if (!correo.isEmpty() && buscarEmpleadoPor(correo, false) != null) {
            throw new RuntimeException("Ya existe un empleado con el e-mail: '" + correo + '\'');
        }

        LocalDate fechaNacimiento = Helper.validarFecha(input, "Ingrese fecha de nacimiento", "dd/MM/yyyy");

        listaEmpleados.addLast(new Empleado(nombre, correo, legajo, dni, fechaNacimiento));
    }

    public static Empleado removerEmpleadoPorSeleccion(ArrayList<Empleado> listaEncontrados) {
        int cantidadEncontrados = listaEncontrados.size();

        System.out.println("\n**** Remover empleado ****");

        for (int i = 0; i < cantidadEncontrados; i++) {
            Empleado empleado = listaEncontrados.get(i);
            int indice = listaEncontrados.indexOf(empleado) + 1;
            System.out.println(indice + ") " + empleado);
        }
        System.out.println("0) Ninguno");
        int sel = Helper.validarEnteroEnRango(input, "Seleccione un empleado a remover", 0, cantidadEncontrados);

        if (sel == 0) {
            return null;
        }

        Empleado seleccion = listaEncontrados.get(sel - 1);
        listaEmpleados.removeOf(seleccion);
        return seleccion;
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
