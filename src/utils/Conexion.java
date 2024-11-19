package utils;

import java.sql.*;

public class Conexion {

    private static final String URL = "jdbc:mysql://localhost:3306/bd_ganado";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Error al cargar el driver MySQL: " + e.getMessage());
        }
    }

    public static Connection getConexion() {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            if (conn != null && !conn.isClosed()) {
                System.out.println("Conexión exitosa.");
            }
            return conn;
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos. Verifica los siguientes puntos:");
            System.err.println("1. Que el servidor MySQL esté activo.");
            System.err.println("2. Que la base de datos 'bd_ganado' exista.");
            System.err.println("3. Que las credenciales sean correctas.");
            System.err.println("Mensaje de error: " + e.getMessage());
            return null;
        }
    }

    public static boolean validarUsuario(String usuario, String password) {
        String sql = "SELECT * FROM login WHERE usuario = ? AND pass = ?";
        try (Connection conn = getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, usuario);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next(); // Retorna true si hay un registro
            }
        } catch (Exception e) {
            System.err.println("Error al validar el usuario: " + e.getMessage());
            return false;
        }
    }


}

