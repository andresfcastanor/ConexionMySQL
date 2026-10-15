package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import models.Usuario;

import Conexion.Conexion;

/**
 * Implementación del DAO para MySQL usando JDBC. Aquí (y solo aquí) vive el SQL
 * de la tabla "estudiante".
 */
public class UsuarioDAOImpl implements UsuarioDAO {

    private static final String INSERTAR = "INSERT INTO estudiante VALUES (?, ?)";
    private static final String BUSCAR_NOMBRE = "SELECT nombre, nota FROM estudiante WHERE nombre = ?";
    private static final String LISTAR = "SELECT nombre, nota FROM estudiante ORDER BY nombre";
    private static final String ACTUALIZAR = "UPDATE estudiante SET nota = ? WHERE nombre = ?";
    private static final String ELIMINAR = "DELETE FROM estudiante WHERE nombre = ?";

    @Override
    public void insertar(Usuario e) throws SQLException {
        Connection con = Conexion.getConexion();
        System.out.println("Conexión exitosa pintelas mi papa");
        try (PreparedStatement ps = con.prepareStatement(INSERTAR, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, e.nombre);
            ps.setDouble(2, e.nota);
            ps.executeUpdate();
        }
    }

    @Override
    public Optional<Usuario> buscarPorNombre(String nombre) throws SQLException {
        Connection con = Conexion.getConexion();
        try (PreparedStatement ps = con.prepareStatement(BUSCAR_NOMBRE)) {
            ps.setString(1, nombre);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapear(rs));
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public ArrayList<Usuario> listarTodos() throws SQLException {
        ArrayList<Usuario> lista = new ArrayList<>();
        Connection con = Conexion.getConexion();
        try (PreparedStatement ps = con.prepareStatement(LISTAR); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        }
        return lista;
    }

    @Override
    public boolean actualizar(Usuario e) throws SQLException {
        Connection con = Conexion.getConexion();
        try (PreparedStatement ps = con.prepareStatement(ACTUALIZAR)) {
            ps.setDouble(1, e.nota);
            ps.setString(2, e.nombre);
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean eliminar(String nombre) throws SQLException {
        Connection con = Conexion.getConexion();
        try (PreparedStatement ps = con.prepareStatement(ELIMINAR)) {
            ps.setString(1, nombre);
            return ps.executeUpdate() > 0;
        }
    }

    /**
     * Convierte la fila actual del ResultSet en un objeto Usuario.
     */
    private Usuario mapear(ResultSet rs) throws SQLException {
        return new Usuario(
                rs.getString("nombre"),
                rs.getDouble("nota"));
    }
}
