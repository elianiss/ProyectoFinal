package Models;

import Abstracts.ClienteAbs;
import utils.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Cliente extends ClienteAbs {

    private static ArrayList<Cliente> clientes = new ArrayList<>();

    public Cliente(int idCliente, String nombre, String telefono, String direccion) {
        super(direccion, idCliente, nombre, telefono);
    }

    @Override
    public void insertar() {
        Connection conn = Conexion.getConexion();
        String sql = "INSERT INTO cliente (ID_Cliente, Nombre, Telefono, Direccion) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idCliente);
            ps.setString(2, nombre);
            ps.setString(3, telefono);
            ps.setString(4, direccion);
            ps.executeUpdate();
            System.out.println("Cliente insertado exitosamente.");
            // Agregar el cliente a la lista en memoria
            clientes.add(this);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actualizar() {
        Connection conn = Conexion.getConexion();
        String sql = "UPDATE cliente SET Nombre = ?, Telefono = ?, Direccion = ? WHERE ID_Cliente = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setString(2, telefono);
            ps.setString(3, direccion);
            ps.setInt(4, idCliente);
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Cliente actualizado exitosamente.");
                // Actualizar en la lista en memoria
                for (Cliente cliente : clientes) {
                    if (cliente.getIdCliente() == this.idCliente) {
                        cliente.setNombre(this.nombre);
                        cliente.setTelefono(this.telefono);
                        cliente.setDireccion(this.direccion);
                        break;
                    }
                }
            } else {
                System.out.println("No se encontró el cliente con el ID especificado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar() {
        Connection conn = Conexion.getConexion();
        String sql = "DELETE FROM cliente WHERE ID_Cliente = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idCliente);
            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Cliente eliminado exitosamente.");
                // Eliminar de la lista en memoria
                clientes.removeIf(cliente -> cliente.getIdCliente() == this.idCliente);
            } else {
                System.out.println("No se encontró el cliente con el ID especificado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void consultar() {
        Connection conn = Conexion.getConexion();
        String sql = "SELECT * FROM cliente WHERE ID_Cliente = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idCliente);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("ID Cliente: " + rs.getInt("ID_Cliente"));
                System.out.println("Nombre: " + rs.getString("Nombre"));
                System.out.println("Teléfono: " + rs.getString("Telefono"));
                System.out.println("Dirección: " + rs.getString("Direccion"));
                // Agregar el cliente a la lista en memoria si no existe
                Cliente cliente = new Cliente(
                        rs.getInt("ID_Cliente"),
                        rs.getString("Nombre"),
                        rs.getString("Telefono"),
                        rs.getString("Direccion")
                );
                if (!clientes.contains(cliente)) {
                    clientes.add(cliente);
                }
            } else {
                System.out.println("No se encontró el cliente con el ID especificado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void consultarTodos() {
        clientes.clear();
        Connection conn = Conexion.getConexion();
        String sql = "SELECT * FROM cliente";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getInt("ID_Cliente"),
                        rs.getString("Nombre"),
                        rs.getString("Telefono"),
                        rs.getString("Direccion")
                );
                clientes.add(cliente);
            }
            System.out.println("Se han cargado todos los clientes en memoria.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para mostrar todos los clientes en la lista en memoria
    public static void mostrarClientesEnMemoria() {
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes almacenados en memoria.");
        } else {
            for (Cliente cliente : clientes) {
                System.out.println("ID Cliente: " + cliente.getIdCliente());
                System.out.println("Nombre: " + cliente.getNombre());
                System.out.println("Teléfono: " + cliente.getTelefono());
                System.out.println("Dirección: " + cliente.getDireccion());
                System.out.println("-------------------------------");
            }
        }
    }
}