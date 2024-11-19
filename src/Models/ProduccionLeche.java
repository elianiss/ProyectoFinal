package Models;

import Abstracts.ProduccionLecheABS;
import utils.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProduccionLeche extends ProduccionLecheABS {
    public ProduccionLeche(int idProduccion, int idGanado, java.sql.Date fecha, String turno, double cantidadLeche) {
        super(idProduccion, idGanado, fecha, turno, cantidadLeche);
    }

    @Override
    public void insertar() {
        Connection conn = Conexion.getConexion();
        String sql = "INSERT INTO produccionleche (ID_Produccion, ID_Ganado, Fecha, Turno, CantidadLeche) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idProduccion);
            ps.setInt(2, idGanado);
            ps.setDate(3, fecha);
            ps.setString(4, turno);
            ps.setDouble(5, cantidadLeche);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actualizar() {
        Connection conn = Conexion.getConexion();
        String sql = "UPDATE produccionleche SET ID_Ganado = ?, Fecha = ?, Turno = ?, CantidadLeche = ? WHERE ID_Produccion = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idGanado);
            ps.setDate(2, fecha);
            ps.setString(3, turno);
            ps.setDouble(4, cantidadLeche);
            ps.setInt(5, idProduccion);
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Producción de leche actualizada exitosamente.");
            } else {
                System.out.println("No se encontró el registro de producción con el ID especificado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar() {
        Connection conn = Conexion.getConexion();
        String sql = "DELETE FROM produccionleche WHERE ID_Produccion = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idProduccion);
            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Producción de leche eliminada exitosamente.");
            } else {
                System.out.println("No se encontró el registro de producción con el ID especificado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void consultar() {
        Connection conn = Conexion.getConexion();
        String sql = "SELECT * FROM produccionleche WHERE ID_Produccion = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idProduccion);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("ID Producción: " + rs.getInt("ID_Produccion"));
                System.out.println("ID Ganado: " + rs.getInt("ID_Ganado"));
                System.out.println("Fecha: " + rs.getDate("Fecha"));
                System.out.println("Turno: " + rs.getString("Turno"));
                System.out.println("Cantidad Leche: " + rs.getDouble("CantidadLeche"));
            } else {
                System.out.println("No se encontró el registro de producción con el ID especificado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Ganado obtenerGanadoAsociado() {
        Connection conn = Conexion.getConexion();
        String sql = "SELECT * FROM ganado WHERE ID_Ganado = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idGanado);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Ganado(
                        rs.getInt("ID_Ganado"),
                        rs.getString("Nombre"),
                        rs.getDate("Fecha_Nacimiento"),
                        rs.getString("Raza"),
                        rs.getString("Estado_Salud"),
                        rs.getDouble("Peso"),
                        rs.getInt("ID_Finca")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static ArrayList<ProduccionLeche> listarProduccionesPorGanado(int idGanado) {
        ArrayList<ProduccionLeche> lista = new ArrayList<>();
        Connection conn = Conexion.getConexion();
        String sql = "SELECT * FROM produccionleche WHERE ID_Ganado = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idGanado);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new ProduccionLeche(
                        rs.getInt("ID_Produccion"),
                        rs.getInt("ID_Ganado"),
                        rs.getDate("Fecha"),
                        rs.getString("Turno"),
                        rs.getDouble("CantidadLeche")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }


    public static void mostrarProduccionesPorGanado(int idGanado) {
        ArrayList<ProduccionLeche> producciones = listarProduccionesPorGanado(idGanado);
        if (producciones.isEmpty()) {
            System.out.println("No hay producciones registradas para el ganado con ID: " + idGanado);
        } else {
            for (ProduccionLeche produccion : producciones) {
                System.out.println("ID Producción: " + produccion.idProduccion);
                System.out.println("Fecha: " + produccion.fecha);
                System.out.println("Turno: " + produccion.turno);
                System.out.println("Cantidad Leche: " + produccion.cantidadLeche);
                System.out.println("-------------------------------");
            }
        }
    }
}
