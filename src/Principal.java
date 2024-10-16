public class Principal {
    public static void main(String[] args) {

        Empleado empleado1 = new Empleado("Erick Cruz", 212, 12);
        Empleado empleado2 = new Empleado("Acosta Gloss", 122, 222);
        Empleado empleado3 = new Empleado("Gonza Carillo", 321, 4444);
        Empleado empleado4 = new Empleado("Brian Cruz", 444, 5555);

        ListaEnlazadaSimple<Empleado> listaEmpleados = new ListaEnlazadaSimple<>();

        listaEmpleados.addLast(empleado1);
        listaEmpleados.addLast(empleado2);
        listaEmpleados.addLast(empleado3);
        listaEmpleados.addLast(empleado4);

    }
}
