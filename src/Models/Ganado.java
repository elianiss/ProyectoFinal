package Models;
import Abstracts.GanadoABS;
import utils.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Ganado extends GanadoABS {
    // Estructura de datos para almacenar los objetos Ganado en memoria
    private static ArrayList<Ganado> ganadoEnMemoria = new ArrayList<>();

    public Ganado(int idGanado, String nombre, java.sql.Date fechaNacimiento, String raza, String estadoSalud, double peso, int idFinca) {
        super(idGanado, nombre, fechaNacimiento, raza, estadoSalud, peso, idFinca);
    }

    @Override
    public void insertar() {
        Connection conn = Conexion.getConexion();
        String sql = "INSERT INTO ganado (ID_Ganado, Nombre, Fecha_Nacimiento, Raza, Estado_Salud, Peso, ID_Finca) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idGanado);
            ps.setString(2, nombre);
            ps.setDate(3, fechaNacimiento);
            ps.setString(4, raza);
            ps.setString(5, estadoSalud);
            ps.setDouble(6, peso);
            ps.setInt(7, idFinca);
            ps.executeUpdate();
            System.out.println("Ganado insertado exitosamente.");
            ganadoEnMemoria.add(this); // Agregar a la lista en memoria
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void actualizar() {
        Connection conn = Conexion.getConexion();
        String sql = "UPDATE ganado SET Nombre = ?, Fecha_Nacimiento = ?, Raza = ?, Estado_Salud = ?, Peso = ?, ID_Finca = ? WHERE ID_Ganado = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setDate(2, fechaNacimiento);
            ps.setString(3, raza);
            ps.setString(4, estadoSalud);
            ps.setDouble(5, peso);
            ps.setInt(6, idFinca);
            ps.setInt(7, idGanado);
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Ganado actualizado exitosamente.");
                // Actualizar en la lista en memoria
                for (Ganado g : ganadoEnMemoria) {
                    if (g.getIdGanado() == this.idGanado) {
                        g.setNombre(this.nombre);
                        g.setFechaNacimiento(this.fechaNacimiento);
                        g.setRaza(this.raza);
                        g.setEstadoSalud(this.estadoSalud);
                        g.setPeso(this.peso);
                        g.setIdFinca(this.idFinca);
                        break;
                    }
                }
            } else {
                System.out.println("No se encontró el ganado con el ID especificado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminar() {
        Connection conn = Conexion.getConexion();
        String sql = "DELETE FROM ganado WHERE ID_Ganado = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idGanado);
            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Ganado eliminado exitosamente.");
                // Eliminar de la lista en memoria
                ganadoEnMemoria.removeIf(g -> g.getIdGanado() == this.idGanado);
            } else {
                System.out.println("No se encontró el ganado con el ID especificado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void consultar() {
        Connection conn = Conexion.getConexion();
        String sql = "SELECT * FROM ganado WHERE ID_Ganado = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idGanado);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("ID Ganado: " + rs.getInt("ID_Ganado"));
                System.out.println("Nombre: " + rs.getString("Nombre"));
                System.out.println("Fecha Nacimiento: " + rs.getDate("Fecha_Nacimiento"));
                System.out.println("Raza: " + rs.getString("Raza"));
                System.out.println("Estado Salud: " + rs.getString("Estado_Salud"));
                System.out.println("Peso: " + rs.getDouble("Peso"));
                System.out.println("ID Finca: " + rs.getInt("ID_Finca"));
                // Agregar el ganado a la lista en memoria si no existe
                Ganado g = new Ganado(
                        rs.getInt("ID_Ganado"),
                        rs.getString("Nombre"),
                        rs.getDate("Fecha_Nacimiento"),
                        rs.getString("Raza"),
                        rs.getString("Estado_Salud"),
                        rs.getDouble("Peso"),
                        rs.getInt("ID_Finca")
                );
                if (!ganadoEnMemoria.contains(g)) {
                    ganadoEnMemoria.add(g);
                }
            } else {
                System.out.println("No se encontró el ganado con el ID especificado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para consultar todos los registros de ganado y almacenarlos en la lista en memoria
    public static void consultarTodos() {
        ganadoEnMemoria.clear(); // Limpiar la lista antes de llenarla
        Connection conn = Conexion.getConexion();
        String sql = "SELECT * FROM ganado";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Ganado g = new Ganado(
                        rs.getInt("ID_Ganado"),
                        rs.getString("Nombre"),
                        rs.getDate("Fecha_Nacimiento"),
                        rs.getString("Raza"),
                        rs.getString("Estado_Salud"),
                        rs.getDouble("Peso"),
                        rs.getInt("ID_Finca")
                );
                ganadoEnMemoria.add(g);
            }
            System.out.println("Se han cargado todos los registros de ganado en memoria.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para mostrar todos los registros de ganado en la lista en memoria
    public static void mostrarGanadoEnMemoria() {
        if (ganadoEnMemoria.isEmpty()) {
            System.out.println("No hay registros de ganado almacenados en memoria.");
        } else {
            for (Ganado g : ganadoEnMemoria) {
                System.out.println("ID Ganado: " + g.getIdGanado());
                System.out.println("Nombre: " + g.getNombre());
                System.out.println("Fecha Nacimiento: " + g.getFechaNacimiento());
                System.out.println("Raza: " + g.getRaza());
                System.out.println("Estado Salud: " + g.getEstadoSalud());
                System.out.println("Peso: " + g.getPeso());
                System.out.println("ID Finca: " + g.getIdFinca());
                System.out.println("-------------------------------");
            }
        }
    }

    // Getters y setters
    public int getIdGanado() {
        return idGanado;
    }

    public void setIdGanado(int idGanado) {
        this.idGanado = idGanado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public java.sql.Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(java.sql.Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getEstadoSalud() {
        return estadoSalud;
    }

    public void setEstadoSalud(String estadoSalud) {
        this.estadoSalud = estadoSalud;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public int getIdFinca() {
        return idFinca;
    }

    public void setIdFinca(int idFinca) {
        this.idFinca = idFinca;
    }
}

