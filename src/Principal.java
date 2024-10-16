import org.w3c.dom.html.HTMLLegendElement;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Principal {

    static ListaEnlazadaSimple<Empleado> listaEmpleados = getEmpleados();
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            imprimirMenu();
            int opcion = Helper.validarEnteroEnRango(input, "Seleccione una opcion", 0, 4);
            if (opcion == 0) {
                System.out.println("Terminando programa...");
                break;
            }
            ejecutarOpcion(opcion);
        }
    }

    public static LocalDate fechasAleatorias() {
        long minDay = LocalDate.of(1980, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(2014, 12, 31).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        return LocalDate.ofEpochDay(randomDay);
    }

    private static ListaEnlazadaSimple<Empleado> getEmpleados() {

        Empleado empleado1 = new Empleado("Cruz Erick", "ejemplo1@mail.com", 212, 12, fechasAleatorias());
        Empleado empleado2 = new Empleado("Cruz Nestor", "ejemplo2@mail.com", 122, 222, fechasAleatorias());
        Empleado empleado3 = new Empleado("Gloss Maximo", "ejemplo3@mail.com", 321, 4444, fechasAleatorias());
        Empleado empleado4 = new Empleado("Ramos Franco", "ejemplo4@mail.com", 444, 5555, fechasAleatorias());
        Empleado empleado5 = new Empleado("Mendoza Gonzalo", "ejemplo5@mail.com", 511, 1152, fechasAleatorias());

        ListaEnlazadaSimple<Empleado> listaEmpleados = new ListaEnlazadaSimple<>();

        listaEmpleados.addLast(empleado1);
        listaEmpleados.addLast(empleado2);
        listaEmpleados.addLast(empleado3);
        listaEmpleados.addLast(empleado4);
        listaEmpleados.addLast(empleado5);
        return listaEmpleados;
    }

    public static void buscarEmpleado(String accion) {
        String opcion = seleccionarAtributo(accion);
        if (opcion == null) {
            return;
        }

        System.out.println("\n**** Buscar empleado ****");

        switch (opcion) {
            case "nombre" -> {
                String nombre = Helper.validarStringNoVacio(input, "Ingrese nombre del empleado a buscar");
                ArrayList<Empleado> coincidencias = buscarEmpleadoPor(nombre);
                if (coincidencias.isEmpty()) {
                    throw new RuntimeException("No se encontraron coincidencias/el empleado no existe.");
                }
                System.out.println("**** Empleados encontrados ****");
                imprimirListaEmpleados(coincidencias);
            }
            case "dni" -> {
                int dni = Helper.validarEntero(input, "Ingrese dni del empleado a buscar");
                Empleado empleado = buscarEmpleadoPor("dni", dni, false);
                if (empleado == null) {
                    throw new RuntimeException("El empleado con dni '" + dni + "' no existe.");
                }
                System.out.println("El empleado buscado es: " + empleado);
            }
            case "legajo" -> {
                int legajo = Helper.validarEntero(input, "Ingrese legajo del empleado a buscar");
                Empleado empleado = buscarEmpleadoPor("legajo", legajo, false);
                if (empleado == null) {
                    throw new RuntimeException("El empleado con legajo '" + legajo + "' no existe.");
                }
                System.out.println("El empleado buscado es: " + empleado);
            }
            case "e-mail" -> {
                String correo = Helper.validarCorreoElectronico(input, "Ingrese correo del empleado a buscar");
                if (correo.isEmpty()) {
                    throw new RuntimeException("Correo no valido");
                }
                Empleado empleado = buscarEmpleadoPor(correo, false);
                if (empleado == null) {
                    throw new RuntimeException("El empleado con e-mail '" + correo + "' no existe.");
                }
                System.out.println("El empleado buscado es: " + empleado);
            }
            case "fecha de nacimiento" -> {
                LocalDate nacimiento = Helper.validarFecha(input, "Ingrese nacimiento del empleado a buscar", "dd/MM/yyyy");
                ArrayList<Empleado> coincidencias = buscarEmpleadoPor(nacimiento);
                if (coincidencias.isEmpty()) {
                    throw new RuntimeException("No se encontraron coincidencias/el empleado no existe.");
                }
                System.out.println("**** Empleados encontrados ****");
                imprimirListaEmpleados(coincidencias);
            }
        }
    }

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
        int legajo;
        while (true) {
            legajo = Helper.validarEntero(input, "Ingrese legajo para el empleado '" + nombre + '\'');
            if (buscarEmpleadoPor("legajo", legajo, false) == null) {
                break;
            }
            System.out.println("Ya existe un empleado con el legajo: '" + legajo + '\'');
        }
        // Dni
        int dni;
        while (true) {
            dni = Helper.validarEntero(input, "Ingrese dni para el empleado '" + nombre + '\'');
            if (buscarEmpleadoPor("dni", dni, false) == null) {
                break;
            }
            System.out.println("Ya existe un empleado con el dni: '" + legajo + '\'');

        }
        // E-mail
        String correo;
        while (true) {
            correo = Helper.validarCorreoElectronico(input, "Ingrese e-mail del empleado");
            if (correo.isEmpty() || buscarEmpleadoPor(correo, false) == null) {
                break;
            }
            System.out.println("Ya existe un empleado con el e-mail: '" + correo + '\'');
        }

        LocalDate fechaNacimiento = Helper.validarFecha(input, "Ingrese fecha de nacimiento", "dd/MM/yyyy");

        listaEmpleados.addLast(new Empleado(nombre, correo, legajo, dni, fechaNacimiento));
    }

    public static void removerEmpleado(String accion) {
        String opcion = seleccionarAtributo(accion);
        if (opcion == null) {
            return;
        }

        switch (opcion) {
            case "nombre" -> {
                String nombre = Helper.validarStringNoVacio(input, "Ingrese nombre del empleado a buscar");
                ArrayList<Empleado> coincidencias = buscarEmpleadoPor(nombre);
                if (coincidencias.isEmpty()) {
                    throw new RuntimeException("No se encontraron coincidencias/el empleado no existe.");
                }
                System.out.println(removerEmpleadoPorSeleccion(coincidencias));
            }
            case "dni" -> {
                int dni = Helper.validarEntero(input, "Ingrese dni del empleado a remover");
                Empleado empleado = buscarEmpleadoPor("dni", dni, true);
                if (empleado == null) {
                    throw new RuntimeException("El empleado con dni '" + dni + "' no existe.");
                }
                System.out.println("Se removio el " + empleado);
            }
            case "legajo" -> {
                int legajo = Helper.validarEntero(input, "Ingrese legajo del empleado a remover");
                Empleado empleado = buscarEmpleadoPor("legajo", legajo, true);
                if (empleado == null) {
                    throw new RuntimeException("El empleado con legajo '" + legajo + "' no existe.");
                }
                System.out.println("Se removio el " + empleado);
            }
            case "e-mail" -> {
                String correo = Helper.validarCorreoElectronico(input, "Ingrese correo del empleado a remover");
                if (correo.isEmpty()) {
                    throw new RuntimeException("Correo no valido");
                }
                Empleado empleado = buscarEmpleadoPor(correo, true);
                if (empleado == null) {
                    throw new RuntimeException("El empleado con e-mail '" + correo + "' no existe.");
                }
                System.out.println("Se removio el " + empleado);
            }
            case "fecha de nacimiento" -> {
                LocalDate nacimiento = Helper.validarFecha(input, "Ingrese nacimiento del empleado a remover", "dd/MM/yyyy");
                ArrayList<Empleado> coincidencias = buscarEmpleadoPor(nacimiento);
                if (coincidencias.isEmpty()) {
                    throw new RuntimeException("No se encontraron coincidencias/el empleado no existe.");
                }
                System.out.println(removerEmpleadoPorSeleccion(coincidencias));
            }
        }
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
            throw new RuntimeException("Ninguna seleccion.");
        }

        Empleado seleccion = listaEncontrados.get(sel - 1);
        listaEmpleados.removeOf(seleccion);
        return seleccion;
    }

    public static String seleccionarAtributo(String accion) {
        String[] opciones = {"Nombre", "Dni", "Legajo", "E-mail", "Fecha de nacimiento"};

        System.out.println("\n**** " + accion + " empleado por ****");
        for (int i = 0; i < opciones.length; i++) {
            System.out.println((i + 1) + ") " + opciones[i]);
        }
        System.out.println("0) Salir");

        int opcion = Helper.validarEnteroEnRango(input, "Seleccione una opcion", 1, opciones.length);

        if (opcion == 0) {
            return null;
        }

        return opciones[opcion - 1].toLowerCase();

    }

    public static void ejecutarOpcion(int opcion) {
        switch (opcion) {
            case 1 -> agregarEmpleado();
            case 2 -> {
                try {
                    removerEmpleado("Remover");
                } catch (RuntimeException e) {
                    System.out.println(e.getMessage());
                }
            }
            case 3 -> {
                try {
                    buscarEmpleado("Buscar");
                } catch (RuntimeException e) {
                    System.out.println(e.getMessage());
                }
            }
            case 4 -> {
                System.out.println("\n**** Lista de empleados ****");
                imprimirListaEmpleados(listaEmpleados);
            }
        }
    }


    public static <E extends Iterable<Empleado>> void imprimirListaEmpleados(E empleados) {
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