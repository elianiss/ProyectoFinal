import Models.Empleado;
import Models.Veterinario;
import java.sql.Date;
import java.util.Scanner;
import Models.Ganado;
import Models.ProduccionLeche;
import utils.Conexion;
import Models.Ventaleche;
import Models.Cliente;
import java.text.DecimalFormat;

import javax.swing.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        boolean accesoPermitido = false;
        do {
            String usuario = JOptionPane.showInputDialog("Ingrese su usuario:");
            String password = JOptionPane.showInputDialog("Ingrese su contraseña:");


            if (Conexion.validarUsuario(usuario, password)) {
                JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso" + usuario + "!");
                accesoPermitido = true;
                mostrarMenuPrincipal();
            } else {
                JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos. Intente de nuevo.");
            }
        } while (!accesoPermitido);
    }
    public static void mostrarMenuPrincipal() {
        int opcion;
        do {
            String menu = """
                    ---- Menú Principal ----
                    1. Gestionar Empleados
                    2. Gestionar Ganado
                    3. Gestionar Veterinario
                    4. Gestionar Producción de Leche
                    5. Gestionar Venta de Leche
                    6. Gestionar Clientes
                    5. Salir
                    """;

            String input = JOptionPane.showInputDialog(menu + "\nSeleccione una opción:");
            if (input == null) break; // Salir si el usuario cierra el cuadro de diálogo
            opcion = Integer.parseInt(input);

            switch (opcion) {
                case 1 -> EmpleadosMenu();
                case 2 -> GanadosMenu();
                case 3 -> VeterinariosMenu();
                case 4 -> ProduccionMenu();
                case 5 -> Ventadeleche();
                case 6  ->clientes();
                case 7 -> JOptionPane.showMessageDialog(null, "Saliendo del sistema...");
                default -> JOptionPane.showMessageDialog(null, "Opción no válida.");
            }
        } while (opcion != 5);
    }


    public static void EmpleadosMenu() {
        Scanner sc = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("---- Menú Principal ----");
            System.out.println("1. Insertar Empleado");
            System.out.println("2. Actualizar Empleado");
            System.out.println("3. Eliminar Empleado");
            System.out.println("4. Consultar Empleado");
            System.out.println("5. Consultar Todos los Empleados");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    // Insertar un nuevo empleado
                    System.out.print("Ingrese el ID del empleado: ");
                    int id = sc.nextInt();
                    sc.nextLine(); // Limpiar buffer
                    System.out.print("Ingrese el nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Ingrese la posición: ");
                    String posicion = sc.nextLine();
                    System.out.print("Ingrese la fecha de contratación (YYYY-MM-DD): ");
                    Date fecha = Date.valueOf(sc.nextLine());

                    Empleado empInsertar = new Empleado(id, nombre, posicion, fecha);
                    empInsertar.insertar();
                    break;

                case 2:
                    // Actualizar un empleado existente
                    System.out.print("Ingrese el ID del empleado a actualizar: ");
                    int idActualizar = sc.nextInt();
                    sc.nextLine(); // Limpiar buffer
                    System.out.print("Ingrese el nuevo nombre: ");
                    String nombreActualizar = sc.nextLine();
                    System.out.print("Ingrese la nueva posición: ");
                    String posicionActualizar = sc.nextLine();
                    System.out.print("Ingrese la nueva fecha de contratación (YYYY-MM-DD): ");
                    Date fechaActualizar = Date.valueOf(sc.nextLine());

                    Empleado empActualizar = new Empleado(idActualizar, nombreActualizar, posicionActualizar, fechaActualizar);
                    empActualizar.actualizar();
                    break;

                case 3:
                    // Eliminar un empleado
                    System.out.print("Ingrese el ID del empleado a eliminar: ");
                    int idEliminar = sc.nextInt();
                    Empleado empEliminar = new Empleado(idEliminar, "", "", null);
                    empEliminar.eliminar();
                    break;

                case 4:
                    // Consultar un empleado por su ID
                    System.out.print("Ingrese el ID del empleado a consultar: ");
                    int idConsultar = sc.nextInt();
                    Empleado empConsultar = new Empleado(idConsultar, "", "", null);
                    empConsultar.consultar();
                    break;

                case 5:
                    // Consultar todos los empleados
                    Empleado.consultarTodos();
                    Empleado.mostrarEmpleadosEnMemoria();
                    break;

                case 6:
                    // Salir del sistema
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 6);

        sc.close();
    }

    public static void GanadosMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\nGestión de Ganado:");
            System.out.println("1. Insertar Ganado");
            System.out.println("2. Actualizar Ganado");
            System.out.println("3. Eliminar Ganado");
            System.out.println("4. Consultar Ganado");
            System.out.println("5. Consultar Todos los Ganados");
            System.out.println("6. Mostrar Ganado en Memoria");
            System.out.println("7. Volver al Menú Principal");

            opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    // Insertar Ganado
                    System.out.println("Ingrese los datos del ganado:");
                    System.out.print("ID Ganado: ");
                    int idGanado = scanner.nextInt();
                    scanner.nextLine(); // Limpiar buffer
                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Fecha de Nacimiento (YYYY-MM-DD): ");
                    String fechaNacimiento = scanner.nextLine();
                    System.out.print("Raza: ");
                    String raza = scanner.nextLine();
                    System.out.print("Estado de Salud: ");
                    String estadoSalud = scanner.nextLine();
                    System.out.print("Peso: ");
                    double peso = scanner.nextDouble();
                    System.out.print("ID Finca: ");
                    int idFinca = scanner.nextInt();

                    Ganado ganado = new Ganado(idGanado, nombre, java.sql.Date.valueOf(fechaNacimiento), raza, estadoSalud, peso, idFinca);
                    ganado.insertar();
                    break;
                case 2:
                    // Actualizar Ganado
                    System.out.println("Ingrese el ID del ganado a actualizar:");
                    int idActualizar = scanner.nextInt();
                    scanner.nextLine(); // Limpiar buffer
                    System.out.print("Nombre: ");
                    String nuevoNombre = scanner.nextLine();
                    System.out.print("Fecha de Nacimiento (YYYY-MM-DD): ");
                    String nuevaFechaNacimiento = scanner.nextLine();
                    System.out.print("Raza: ");
                    String nuevaRaza = scanner.nextLine();
                    System.out.print("Estado de Salud: ");
                    String nuevoEstadoSalud = scanner.nextLine();
                    System.out.print("Peso: ");
                    double nuevoPeso = scanner.nextDouble();
                    System.out.print("ID Finca: ");
                    int nuevaIdFinca = scanner.nextInt();

                    Ganado ganadoActualizar = new Ganado(idActualizar, nuevoNombre, java.sql.Date.valueOf(nuevaFechaNacimiento), nuevaRaza, nuevoEstadoSalud, nuevoPeso, nuevaIdFinca);
                    ganadoActualizar.actualizar();
                    break;
                case 3:
                    // Eliminar Ganado
                    System.out.println("Ingrese el ID del ganado a eliminar:");
                    int idEliminar = scanner.nextInt();
                    Ganado ganadoEliminar = new Ganado(idEliminar, "", null, "", "", 0.0, 0);
                    ganadoEliminar.eliminar();
                    break;
                case 4:
                    // Consultar Ganado
                    System.out.println("Ingrese el ID del ganado a consultar:");
                    int idConsultar = scanner.nextInt();
                    Ganado ganadoConsultar = new Ganado(idConsultar, "", null, "", "", 0.0, 0);
                    ganadoConsultar.consultar();
                    break;
                case 5:
                    // Consultar Todos los Ganados
                    Ganado.consultarTodos();
                    break;
                case 6:
                    // Mostrar Ganado en Memoria
                    Ganado.mostrarGanadoEnMemoria();
                    break;
                case 7:
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        } while (opcion != 7);
    }

    public static void VeterinariosMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- Menú de Gestión de Veterinarios ---");
            System.out.println("1. Insertar veterinario");
            System.out.println("2. Actualizar veterinario");
            System.out.println("3. Eliminar veterinario");
            System.out.println("4. Consultar veterinario por ID");
            System.out.println("5. Consultar todos los veterinarios");
            System.out.println("6. Mostrar veterinarios en memoria");
            System.out.println("0. Salir");
            System.out.print("Selecciona una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir salto de línea

            switch (opcion) {
                case 1:
                    System.out.println("---- Insertar Veterinario ----");
                    System.out.print("ID: ");
                    int idInsert = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nombre: ");
                    String nombreInsert = scanner.nextLine();
                    System.out.print("Especialidad: ");
                    String especialidadInsert = scanner.nextLine();
                    System.out.print("Teléfono: ");
                    String telefonoInsert = scanner.nextLine();

                    Veterinario nuevoVeterinario = new Veterinario(idInsert, nombreInsert, especialidadInsert, telefonoInsert);
                    nuevoVeterinario.insertar();
                    break;

                case 2:
                    System.out.println("---- Actualizar Veterinario ----");
                    System.out.print("ID del veterinario a actualizar: ");
                    int idUpdate = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nuevo nombre: ");
                    String nombreUpdate = scanner.nextLine();
                    System.out.print("Nueva especialidad: ");
                    String especialidadUpdate = scanner.nextLine();
                    System.out.print("Nuevo teléfono: ");
                    String telefonoUpdate = scanner.nextLine();

                    Veterinario veterinarioActualizar = new Veterinario(idUpdate, nombreUpdate, especialidadUpdate, telefonoUpdate);
                    veterinarioActualizar.actualizar();
                    break;

                case 3:
                    System.out.println("---- Eliminar Veterinario ----");
                    System.out.print("ID del veterinario a eliminar: ");
                    int idDelete = scanner.nextInt();
                    scanner.nextLine();

                    Veterinario veterinarioEliminar = new Veterinario(idDelete, null, null, null);
                    veterinarioEliminar.eliminar();
                    break;

                case 4:
                    System.out.println("---- Consultar Veterinario por ID ----");
                    System.out.print("ID del veterinario: ");
                    int idConsultar = scanner.nextInt();
                    scanner.nextLine();

                    Veterinario veterinarioConsultar = new Veterinario(idConsultar, null, null, null);
                    veterinarioConsultar.consultar();
                    break;

                case 5:
                    System.out.println("---- Consultar Todos los Veterinarios ----");
                    Veterinario.consultarTodos();
                    Veterinario.mostrarVeterinariosEnMemoria();
                    break;

                case 6:
                    System.out.println("---- Mostrar Veterinarios en Memoria ----");
                    Veterinario.mostrarVeterinariosEnMemoria();
                    break;

                case 0:
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción inválida. Intenta nuevamente.");
            }
        } while (opcion != 0);

        scanner.close();

    }
    public static void ProduccionMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- Menú de Gestión de Producción de Leche ---");
            System.out.println("1. Insertar producción");
            System.out.println("2. Actualizar producción");
            System.out.println("3. Eliminar producción");
            System.out.println("4. Consultar producción por ID");
            System.out.println("5. Consultar todas las producciones por ID Ganado");
            System.out.println("6. Mostrar detalles de las producciones por ID Ganado");
            System.out.println("0. Salir");
            System.out.print("Selecciona una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    // Insertar producción
                    System.out.println("---- Insertar Producción ----");
                    System.out.print("ID Producción: ");
                    int idProduccion = scanner.nextInt();
                    System.out.print("ID Ganado: ");
                    int idGanado = scanner.nextInt();
                    scanner.nextLine(); // Limpiar buffer
                    System.out.print("Fecha (YYYY-MM-DD): ");
                    String fechaStr = scanner.nextLine();
                    System.out.print("Turno (Mañana/Tarde): ");
                    String turno = scanner.nextLine();
                    System.out.print("Cantidad de Leche: ");
                    double cantidadLeche = scanner.nextDouble();

                    ProduccionLeche nuevaProduccion = new ProduccionLeche(
                            idProduccion, idGanado, java.sql.Date.valueOf(fechaStr), turno, cantidadLeche
                    );
                    nuevaProduccion.insertar();
                    break;

                case 2:
                    // Actualizar producción
                    System.out.println("---- Actualizar Producción ----");
                    System.out.print("ID Producción a actualizar: ");
                    int idActualizar = scanner.nextInt();
                    System.out.print("ID Ganado: ");
                    int nuevoIdGanado = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Fecha (YYYY-MM-DD): ");
                    String nuevaFechaStr = scanner.nextLine();
                    System.out.print("Turno (Mañana/Tarde): ");
                    String nuevoTurno = scanner.nextLine();
                    System.out.print("Cantidad de Leche: ");
                    double nuevaCantidadLeche = scanner.nextDouble();

                    ProduccionLeche produccionActualizar = new ProduccionLeche(
                            idActualizar, nuevoIdGanado, java.sql.Date.valueOf(nuevaFechaStr), nuevoTurno, nuevaCantidadLeche
                    );
                    produccionActualizar.actualizar();
                    break;

                case 3:
                    // Eliminar producción
                    System.out.println("---- Eliminar Producción ----");
                    System.out.print("ID Producción a eliminar: ");
                    int idEliminar = scanner.nextInt();

                    ProduccionLeche produccionEliminar = new ProduccionLeche(idEliminar, 0, null, "", 0.0);
                    produccionEliminar.eliminar();
                    break;

                case 4:
                    // Consultar producción por ID
                    System.out.println("---- Consultar Producción por ID ----");
                    System.out.print("ID Producción: ");
                    int idConsultar = scanner.nextInt();

                    ProduccionLeche produccionConsultar = new ProduccionLeche(idConsultar, 0, null, "", 0.0);
                    produccionConsultar.consultar();
                    break;

                case 5:
                    // Consultar todas las producciones por ID Ganado
                    System.out.println("---- Consultar Todas las Producciones por ID Ganado ----");
                    System.out.print("ID Ganado: ");
                    int idGanadoConsultar = scanner.nextInt();

                    ArrayList<ProduccionLeche> producciones = ProduccionLeche.listarProduccionesPorGanado(idGanadoConsultar);
                    if (producciones.isEmpty()) {
                        System.out.println("No se encontraron producciones para el ganado con ID: " + idGanadoConsultar);
                    } else {
                        System.out.println("Producciones encontradas:");
                        for (ProduccionLeche produccion : producciones) {
                            System.out.println(produccion);
                        }
                    }
                    break;

                case 6:
                    // Mostrar detalles de producciones por ID Ganado
                    System.out.println("---- Mostrar Detalles de Producciones por ID Ganado ----");
                    System.out.print("ID Ganado: ");
                    int idGanadoDetalles = scanner.nextInt();

                    ProduccionLeche.mostrarProduccionesPorGanado(idGanadoDetalles);
                    break;

                case 0:
                    System.out.println("Saliendo del menú de producción...");
                    break;

                default:
                    System.out.println("Opción inválida. Intenta nuevamente.");
            }
        } while (opcion != 0);

        scanner.close();
    }

    public static void Ventadeleche(){

                Scanner scanner = new Scanner(System.in);
                int opcion = 0;

                do {
                    System.out.println("****** Menú de Gestión de Ventas de Leche ******");
                    System.out.println("1. Insertar venta");
                    System.out.println("2. Actualizar venta");
                    System.out.println("3. Eliminar venta");
                    System.out.println("4. Consultar venta");
                    System.out.println("5. Mostrar informe general de ventas");
                    System.out.println("6. Ver ventas diarias");
                    System.out.println("7. Ver ventas semanales");
                    System.out.println("8. Ver ventas mensuales");
                    System.out.println("9. Salir");
                    System.out.print("Seleccione una opción: ");

                    opcion = Integer.parseInt(scanner.nextLine());

                    switch (opcion) {
                        case 1: {
                            System.out.print("Ingrese ID de la venta: ");
                            int idVenta = Integer.parseInt(scanner.nextLine());
                            System.out.print("Ingrese ID del cliente: ");
                            int idCliente = Integer.parseInt(scanner.nextLine());
                            System.out.print("Ingrese la fecha de la venta (yyyy-MM-dd): ");
                            Date fechaVenta = Date.valueOf(scanner.nextLine());
                            System.out.print("Ingrese la cantidad vendida (litros): ");
                            double cantidadVendida = Double.parseDouble(scanner.nextLine());
                            System.out.print("Ingrese el precio por litro: ");
                            double precioPorLitro = Double.parseDouble(scanner.nextLine());

                            Ventaleche nuevaVenta = new Ventaleche(idVenta, idCliente, fechaVenta, cantidadVendida, precioPorLitro);
                            nuevaVenta.insertar();
                            break;
                        }
                        case 2: {
                            System.out.print("Ingrese ID de la venta a actualizar: ");
                            int idVenta = Integer.parseInt(scanner.nextLine());
                            System.out.print("Ingrese ID del cliente: ");
                            int idCliente = Integer.parseInt(scanner.nextLine());
                            System.out.print("Ingrese la nueva fecha de la venta (yyyy-MM-dd): ");
                            Date fechaVenta = Date.valueOf(scanner.nextLine());
                            System.out.print("Ingrese la nueva cantidad vendida (litros): ");
                            double cantidadVendida = Double.parseDouble(scanner.nextLine());
                            System.out.print("Ingrese el nuevo precio por litro: ");
                            double precioPorLitro = Double.parseDouble(scanner.nextLine());

                            Ventaleche ventaActualizar = new Ventaleche(idVenta, idCliente, fechaVenta, cantidadVendida, precioPorLitro);
                            ventaActualizar.actualizar();
                            break;
                        }
                        case 3: {
                            System.out.print("Ingrese ID de la venta a eliminar: ");
                            int idVenta = Integer.parseInt(scanner.nextLine());

                            Ventaleche ventaEliminar = new Ventaleche(idVenta, 0, null, 0, 0);
                            ventaEliminar.eliminar();
                            break;
                        }
                        case 4: {
                            System.out.print("Ingrese ID de la venta a consultar: ");
                            int idVenta = Integer.parseInt(scanner.nextLine());

                            Ventaleche ventaConsultar = new Ventaleche(idVenta, 0, null, 0, 0);
                            ventaConsultar.consultar();
                            break;
                        }
                        case 5: {
                            Ventaleche.mostrarInformeVentas();
                            break;
                        }
                        case 6: {
                            System.out.print("Ingrese la fecha a consultar (yyyy-MM-dd): ");
                            Date fecha = Date.valueOf(scanner.nextLine());
                            Ventaleche.verVentasDiarias(fecha);
                            break;
                        }
                        case 7: {
                            System.out.print("Ingrese la fecha de inicio (yyyy-MM-dd): ");
                            Date fechaInicio = Date.valueOf(scanner.nextLine());
                            System.out.print("Ingrese la fecha de fin (yyyy-MM-dd): ");
                            Date fechaFin = Date.valueOf(scanner.nextLine());
                            Ventaleche.verVentasSemanales(fechaInicio, fechaFin);
                            break;
                        }
                        case 8: {
                            System.out.print("Ingrese el mes (1-12): ");
                            int mes = Integer.parseInt(scanner.nextLine());
                            System.out.print("Ingrese el año: ");
                            int anio = Integer.parseInt(scanner.nextLine());
                            Ventaleche.verVentasMensuales(mes, anio);
                            break;
                        }
                        case 9:
                            System.out.println("¡Gracias por usar el sistema!");
                            break;
                        default:
                            System.out.println("Opción no válida, intente de nuevo.");
                    }
                } while (opcion != 9);

                scanner.close();
            }


public static void clientes() {
    Scanner scanner = new Scanner(System.in);
    int opcion;

    do {
        System.out.println("********** Menú de Gestión de Clientes **********");
        System.out.println("1. Insertar Cliente");
        System.out.println("2. Actualizar Cliente");
        System.out.println("3. Eliminar Cliente");
        System.out.println("4. Consultar Cliente por ID");
        System.out.println("5. Consultar Todos los Clientes");
        System.out.println("6. Mostrar Clientes en Memoria");
        System.out.println("7. Salir");
        System.out.print("Seleccione una opción: ");

        opcion = Integer.parseInt(scanner.nextLine());

        switch (opcion) {
            case 1: {
                System.out.print("Ingrese ID del Cliente: ");
                int idCliente = Integer.parseInt(scanner.nextLine());
                System.out.print("Ingrese Nombre del Cliente: ");
                String nombre = scanner.nextLine();
                System.out.print("Ingrese Teléfono del Cliente: ");
                String telefono = scanner.nextLine();
                System.out.print("Ingrese Dirección del Cliente: ");
                String direccion = scanner.nextLine();

                Cliente nuevoCliente = new Cliente(idCliente, nombre, telefono, direccion);
                nuevoCliente.insertar();
                break;
            }
            case 2: {
                System.out.print("Ingrese ID del Cliente a actualizar: ");
                int idCliente = Integer.parseInt(scanner.nextLine());
                System.out.print("Ingrese Nombre del Cliente: ");
                String nombre = scanner.nextLine();
                System.out.print("Ingrese Teléfono del Cliente: ");
                String telefono = scanner.nextLine();
                System.out.print("Ingrese Dirección del Cliente: ");
                String direccion = scanner.nextLine();

                Cliente clienteActualizar = new Cliente(idCliente, nombre, telefono, direccion);
                clienteActualizar.actualizar();
                break;
            }
            case 3: {
                System.out.print("Ingrese ID del Cliente a eliminar: ");
                int idCliente = Integer.parseInt(scanner.nextLine());

                Cliente clienteEliminar = new Cliente(idCliente, null, null, null);
                clienteEliminar.eliminar();
                break;
            }
            case 4: {
                System.out.print("Ingrese ID del Cliente a consultar: ");
                int idCliente = Integer.parseInt(scanner.nextLine());

                Cliente clienteConsultar = new Cliente(idCliente, null, null, null);
                clienteConsultar.consultar();
                break;
            }
            case 5: {
                Cliente.consultarTodos();
                break;
            }
            case 6: {
                Cliente.mostrarClientesEnMemoria();
                break;
            }
            case 7:
                System.out.println("¡Gracias por usar el sistema de gestión de clientes!");
                break;
            default:
                System.out.println("Opción no válida, intente de nuevo.");
        }

    } while (opcion != 7);

    scanner.close();

}
}
