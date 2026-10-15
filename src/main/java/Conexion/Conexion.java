
package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String HOST = "localhost";
    private static final String PUERTO = "3306";
    private static final String BASE_DATOS = "universidad"; // <-- cambia por el nombre de tu base
    private static final String USUARIO = "root";
    private static final String CLAVE = "root123";

    private static final String URL = "jdbc:mysql://" + HOST + ":" + PUERTO + "/" + BASE_DATOS
            + "?useSSL=false"
            + "&allowPublicKeyRetrieval=true"
            + "&serverTimezone=America/Bogota";

    private static Connection conexion;

    // Constructor privado: se usa solo a través de getConexion()
    private Conexion() {
    }

    /**
     * Devuelve una conexión abierta (la crea si no existe o si se cerró).
     */
    public static Connection getConexion() throws SQLException {
        if (conexion == null || conexion.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException("No se encontró el driver de MySQL. Revisa la dependencia en el pom.xml", e);
            }
            conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
        }
        return conexion;
    }

    /**
     * Cierra la conexión si está abierta.
     */
    public static void cerrar() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }

    // Prueba rápida
    public static void main(String[] args) {
        try {
            Connection con = Conexion.getConexion();
            System.out.println("Conexión exitosa a: " + con.getMetaData().getURL());
        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e.getMessage());
        } finally {
            Conexion.cerrar();
        }
    }
}