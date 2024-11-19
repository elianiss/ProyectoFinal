package Models;

import Abstracts.VentalecheAbs;
import utils.Conexion;

import javax.swing.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Ventaleche extends VentalecheAbs {

    private static ArrayList<Ventaleche> ventas = new ArrayList<>();

    public Ventaleche(int idVenta, int idCliente, Date fechaVenta, double cantidadVendida, double precioPorLitro) {
        super();
        this.idVenta = idVenta;
        this.idCliente = idCliente;
        this.fechaVenta = fechaVenta;
        this.cantidadVendida = cantidadVendida;
        this.precioPorLitro = precioPorLitro;
        this.totalVenta = calcularTotalVenta();
    }

    private double calcularTotalVenta() {
        return cantidadVendida * precioPorLitro;
    }

    @Override
    public void insertar() {
        Connection conn = Conexion.getConexion();
        String sql = "INSERT INTO ventaleche (ID_Venta, ID_Cliente, Fecha_Venta, Cantidad_Vendida, Precio_Por_Litro, Total_Venta) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idVenta);
            ps.setInt(2, idCliente);
            ps.setDate(3, fechaVenta);
            ps.setDouble(4, cantidadVendida);
            ps.setDouble(5, precioPorLitro);
            ps.setDouble(6, totalVenta);
            ps.executeUpdate();
            System.out.println("Venta de leche insertada exitosamente.");
            ventas.add(this);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actualizar() {
        Connection conn = Conexion.getConexion();
        String sql = "UPDATE ventaleche SET ID_Cliente = ?, Fecha_Venta = ?, Cantidad_Vendida = ?, Precio_Por_Litro = ?, Total_Venta = ? WHERE ID_Venta = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idCliente);
            ps.setDate(2, fechaVenta);
            ps.setDouble(3, cantidadVendida);
            ps.setDouble(4, precioPorLitro);
            ps.setDouble(5, calcularTotalVenta());
            ps.setInt(6, idVenta);
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Venta de leche actualizada exitosamente.");
            } else {
                System.out.println("No se encontró la venta con el ID especificado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar() {
        Connection conn = Conexion.getConexion();
        String sql = "DELETE FROM ventaleche WHERE ID_Venta = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idVenta);
            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Venta de leche eliminada exitosamente.");
            } else {
                System.out.println("No se encontró la venta con el ID especificado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void consultar() {
        Connection conn = Conexion.getConexion();
        String sql = "SELECT * FROM ventaleche WHERE ID_Venta = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idVenta);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("ID Venta: " + rs.getInt("ID_Venta"));
                System.out.println("ID Cliente: " + rs.getInt("ID_Cliente"));
                System.out.println("Fecha Venta: " + rs.getDate("Fecha_Venta"));
                System.out.println("Cantidad Vendida: " + rs.getDouble("Cantidad_Vendida"));
                System.out.println("Precio por Litro: " + rs.getDouble("Precio_Por_Litro"));
                System.out.println("Total Venta: " + rs.getDouble("Total_Venta"));
            } else {
                System.out.println("No se encontró la venta con el ID especificado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Métodos para informes
    public static void mostrarInformeVentas() {
        Connection conn = Conexion.getConexion();
        String sql = "SELECT COUNT(*) AS Total_Ventas, SUM(Cantidad_Vendida) AS Total_Litros, SUM(Total_Venta) AS Total_Ingresos FROM ventaleche";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int totalVentas = rs.getInt("Total_Ventas");
                double totalLitros = rs.getDouble("Total_Litros");
                double totalIngresos = rs.getDouble("Total_Ingresos");

                DecimalFormat df = new DecimalFormat("#,###.00");

                String informe = "**** Informe General de Ventas de Leche ****\n\n"
                        + "Total de Ventas Realizadas: " + totalVentas + "\n"
                        + "Total de Litros Vendidos: " + df.format(totalLitros) + " litros\n"
                        + "Total de Ingresos: $" + df.format(totalIngresos) + "\n";

                int opcion = JOptionPane.showConfirmDialog(null, "¿Desea mostrar el informe en una ventana emergente?",
                        "Visualización del Informe", JOptionPane.YES_NO_OPTION);

                if (opcion == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null, informe, "Informe de Ventas", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    System.out.println(informe);
                }
            } else {
                JOptionPane.showMessageDialog(null, "No hay datos de ventas disponibles.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void verVentasDiarias(Date fecha) {
        Connection conn = Conexion.getConexion();
        String sql = "SELECT * FROM ventaleche WHERE Fecha_Venta = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, fecha);
            ResultSet rs = ps.executeQuery();
            StringBuilder informe = new StringBuilder("Ventas de leche del día " + fecha + ":\n");
            while (rs.next()) {
                informe.append("ID Venta: ").append(rs.getInt("ID_Venta")).append("\n")
                        .append("ID Cliente: ").append(rs.getInt("ID_Cliente")).append("\n")
                        .append("Cantidad Vendida: ").append(rs.getDouble("Cantidad_Vendida")).append("\n")
                        .append("Precio por Litro: ").append(rs.getDouble("Precio_Por_Litro")).append("\n")
                        .append("Total Venta: ").append(rs.getDouble("Total_Venta")).append("\n")
                        .append("-------------------------------\n");
            }
            JOptionPane.showMessageDialog(null, informe.toString(), "Ventas Diarias", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void verVentasSemanales(Date fechaInicio, Date fechaFin) {
        Connection conn = Conexion.getConexion();
        String sql = "SELECT * FROM ventaleche WHERE Fecha_Venta BETWEEN ? AND ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, fechaInicio);
            ps.setDate(2, fechaFin);
            ResultSet rs = ps.executeQuery();
            StringBuilder informe = new StringBuilder("Ventas de leche de la semana del " + fechaInicio + " al " + fechaFin + ":\n");
            while (rs.next()) {
                informe.append("ID Venta: ").append(rs.getInt("ID_Venta")).append("\n")
                        .append("ID Cliente: ").append(rs.getInt("ID_Cliente")).append("\n")
                        .append("Cantidad Vendida: ").append(rs.getDouble("Cantidad_Vendida")).append("\n")
                        .append("Precio por Litro: ").append(rs.getDouble("Precio_Por_Litro")).append("\n")
                        .append("Total Venta: ").append(rs.getDouble("Total_Venta")).append("\n")
                        .append("-------------------------------\n");
            }
            JOptionPane.showMessageDialog(null, informe.toString(), "Ventas Semanales", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void verVentasMensuales(int mes, int anio) {
        Connection conn = Conexion.getConexion();
        String sql = "SELECT * FROM ventaleche WHERE MONTH(Fecha_Venta) = ? AND YEAR(Fecha_Venta) = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, mes);
            ps.setInt(2, anio);
            ResultSet rs = ps.executeQuery();
            StringBuilder informe = new StringBuilder("Ventas de leche del mes " + mes + " del año " + anio + ":\n");
            while (rs.next()) {
                informe.append("ID Venta: ").append(rs.getInt("ID_Venta")).append("\n")
                        .append("ID Cliente: ").append(rs.getInt("ID_Cliente")).append("\n")
                        .append("Cantidad Vendida: ").append(rs.getDouble("Cantidad_Vendida")).append("\n")
                        .append("Precio por Litro: ").append(rs.getDouble("Precio_Por_Litro")).append("\n")
                        .append("Total Venta: ").append(rs.getDouble("Total_Venta")).append("\n")
                        .append("-------------------------------\n");
            }
            JOptionPane.showMessageDialog(null, informe.toString(), "Ventas Mensuales", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
