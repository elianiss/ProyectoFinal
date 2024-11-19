package Models;

import Abstracts.EmpleadoAbs;
import utils.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Empleado extends EmpleadoAbs {

    public Empleado(int idEmpleado, String nombre, String posicion, java.sql.Date fechaContratacion) {
        super(idEmpleado, nombre, posicion, fechaContratacion);
    }

    private static ArrayList<Empleado> empleados = new ArrayList<>();
    @Override
    public void insertar() {
        Connection conn = Conexion.getConexion();
        String sql = "INSERT INTO empleado (ID_Empleado, Nombre, Posicion, Fecha_Contratacion) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idEmpleado);
            ps.setString(2, nombre);
            ps.setString(3, posicion);
            ps.setDate(4, fechaContratacion);
            ps.executeUpdate();
            System.out.println("Empleado insertado exitosamente.");
            // Agregar el empleado a la lista en memoria
            empleados.add(this);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actualizar() {
        Connection conn = Conexion.getConexion();
        String sql = "UPDATE empleado SET Nombre = ?, Posicion = ?, Fecha_Contratacion = ? WHERE ID_Empleado = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setString(2, posicion);
            ps.setDate(3, fechaContratacion);
            ps.setInt(4, idEmpleado);
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Empleado actualizado exitosamente.");
                // Actualizar en la lista en memoria
                for (Empleado emp : empleados) {
                    if (emp.getIdEmpleado() == this.idEmpleado) {
                        emp.setNombre(this.nombre);
                        emp.setPosicion(this.posicion);
                        emp.setFechaContratacion(this.fechaContratacion);
                        break;
                    }
                }
            } else {
                System.out.println("No se encontró el empleado con el ID especificado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void eliminar() {
        Connection conn = Conexion.getConexion();
        String sql = "DELETE FROM empleado WHERE ID_Empleado = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idEmpleado);
            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Empleado eliminado exitosamente.");
                // Eliminar de la lista en memoria
                empleados.removeIf(emp -> emp.getIdEmpleado() == this.idEmpleado);
            } else {
                System.out.println("No se encontró el empleado con el ID especificado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void consultar() {
        Connection conn = Conexion.getConexion();
        String sql = "SELECT * FROM empleado WHERE ID_Empleado = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idEmpleado);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("ID Empleado: " + rs.getInt("ID_Empleado"));
                System.out.println("Nombre: " + rs.getString("Nombre"));
                System.out.println("Posición: " + rs.getString("Posicion"));
                System.out.println("Fecha de Contratación: " + rs.getDate("Fecha_Contratacion"));
                // Agregar el empleado a la lista en memoria si no existe
                Empleado emp = new Empleado(
                        rs.getInt("ID_Empleado"),
                        rs.getString("Nombre"),
                        rs.getString("Posicion"),
                        rs.getDate("Fecha_Contratacion")
                );
                if (!empleados.contains(emp)) {
                    empleados.add(emp);
                }
            } else {
                System.out.println("No se encontró el empleado con el ID especificado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void consultarTodos() {
        empleados.clear();
        Connection conn = Conexion.getConexion();
        String sql = "SELECT * FROM empleado";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Empleado emp = new Empleado(
                        rs.getInt("ID_Empleado"),
                        rs.getString("Nombre"),
                        rs.getString("Posicion"),
                        rs.getDate("Fecha_Contratacion")
                );
                empleados.add(emp);
            }
            System.out.println("Se han cargado todos los empleados en memoria.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para mostrar todos los empleados en la lista en memoria
    public static void mostrarEmpleadosEnMemoria() {
        if (empleados.isEmpty()) {
            System.out.println("No hay empleados almacenados en memoria.");
        } else {
            for (Empleado emp : empleados) {
                System.out.println("ID Empleado: " + emp.getIdEmpleado());
                System.out.println("Nombre: " + emp.getNombre());
                System.out.println("Posición: " + emp.getPosicion());
                System.out.println("Fecha de Contratación: " + emp.getFechaContratacion());
                System.out.println("-------------------------------");
            }
        }
    }




}

