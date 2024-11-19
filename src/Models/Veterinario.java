package Models;

import Abstracts.VeterinarioAbs;
import utils.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class Veterinario extends VeterinarioAbs  {
    public Veterinario(int idVeterinario, String nombre, String especialidad, String telefono) {
        super(idVeterinario, nombre, especialidad, telefono);
    }
    private static ArrayList<Veterinario> veterinarios = new ArrayList<>();
    @Override
    public void insertar() {
        Connection conn = Conexion.getConexion();
        String sql = "INSERT INTO veterinario (ID_Veterinario, Nombre, Especialidad, Telefono) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idVeterinario);
            ps.setString(2, nombre);
            ps.setString(3, especialidad);
            ps.setString(4, telefono);
            ps.executeUpdate();
            System.out.println("Veterinario insertado exitosamente.");
            veterinarios.add(this);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void actualizar() {
        Connection conn = Conexion.getConexion();
        String sql = "UPDATE veterinario SET Nombre = ?, Especialidad = ?, Telefono = ? WHERE ID_Veterinario = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setString(2, especialidad);
            ps.setString(3, telefono);
            ps.setInt(4, idVeterinario);
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Veterinario actualizado exitosamente.");
                // Actualizar en la lista en memoria
                for (Veterinario vet : veterinarios) {
                    if (vet.getIdVeterinario() == this.idVeterinario) {
                        vet.setNombre(this.nombre);
                        vet.setEspecialidad(this.especialidad);
                        vet.setTelefono(this.telefono);
                        break;
                    }
                }
            } else {
                System.out.println("No se encontró el veterinario con el ID especificado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void eliminar() {
        Connection conn = Conexion.getConexion();
        String sql = "DELETE FROM veterinario WHERE ID_Veterinario = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idVeterinario);
            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Veterinario eliminado exitosamente.");
                // Eliminar de la lista en memoria
                veterinarios.removeIf(vet -> vet.getIdVeterinario() == this.idVeterinario);
            } else {
                System.out.println("No se encontró el veterinario con el ID especificado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void consultar() {
        Connection conn = Conexion.getConexion();
        String sql = "SELECT * FROM veterinario WHERE ID_Veterinario = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idVeterinario);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("ID Veterinario: " + rs.getInt("ID_Veterinario"));
                System.out.println("Nombre: " + rs.getString("Nombre"));
                System.out.println("Especialidad: " + rs.getString("Especialidad"));
                System.out.println("Teléfono: " + rs.getString("Telefono"));
                // Agregar el veterinario a la lista en memoria si no existe
                Veterinario vet = new Veterinario(
                        rs.getInt("ID_Veterinario"),
                        rs.getString("Nombre"),
                        rs.getString("Especialidad"),
                        rs.getString("Telefono")
                );
                if (!veterinarios.contains(vet)) {
                    veterinarios.add(vet);
                }
            } else {
                System.out.println("No se encontró el veterinario con el ID especificado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void consultarTodos() {
        veterinarios.clear(); // Limpiamos la lista antes de llenarla
        Connection conn = Conexion.getConexion();
        String sql = "SELECT * FROM veterinario";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Veterinario vet = new Veterinario(
                        rs.getInt("ID_Veterinario"),
                        rs.getString("Nombre"),
                        rs.getString("Especialidad"),
                        rs.getString("Telefono")
                );
                veterinarios.add(vet);
            }
            System.out.println("Se han cargado todos los veterinarios en memoria.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para mostrar todos los veterinarios en la lista en memoria
    public static void mostrarVeterinariosEnMemoria() {
        if (veterinarios.isEmpty()) {
            System.out.println("No hay veterinarios almacenados en memoria.");
        } else {
            for (Veterinario vet : veterinarios) {
                System.out.println("ID Veterinario: " + vet.getIdVeterinario());
                System.out.println("Nombre: " + vet.getNombre());
                System.out.println("Especialidad: " + vet.getEspecialidad());
                System.out.println("Teléfono: " + vet.getTelefono());
                System.out.println("-------------------------------");
            }
        }
    }


}
