
package DAO;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import models.Usuario;

/**
 * Contrato del DAO: QUÉ operaciones existen sobre los usuarios,
 * sin decir CÓMO se guardan (MySQL, archivo, memoria...).
 */
public interface UsuarioDAO {

    void insertar(Usuario usuario) throws SQLException;

    Optional<Usuario> buscarPorNombre(String nombre) throws SQLException;

    List<Usuario> listarTodos() throws SQLException;

    boolean actualizar(Usuario usuario) throws SQLException;

    boolean eliminar(String nombre) throws SQLException;
}