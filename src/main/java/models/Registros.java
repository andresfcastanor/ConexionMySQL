package models;

import java.util.ArrayList;

import DAO.UsuarioDAOImpl;
import models.Usuario;

public class Registros {

    UsuarioDAOImpl dao = new UsuarioDAOImpl();

    public String agregarUsuario(Usuario user) {
        try {
            dao.insertar(user);
            return "Usuario creado exitosamente";
        } catch (Exception e) {
            e.printStackTrace();   // ahora sí verás el error en la consola
            return "No se pudo agregar al usuario: " + e.getMessage();
        }

    }

    public String editarUsuario(String nombre, double nota) {
        try {
            Usuario u = new Usuario(nombre, nota);
            boolean result = dao.actualizar(u);
            if (result) {
                return "Usuario editado exitosamente";
            }
            return "Usuario no fue encontrado";
        } catch (Exception e) {
            e.printStackTrace();   // ahora sí verás el error en la consola
            return "No se pudo agregar al usuario: " + e.getMessage();
        }

    }

    public String eliminarUsuario(String nombre) {
        try {
            boolean eliminado = dao.eliminar(nombre);
            if (eliminado) {
                return "Usuario eliminado con exito";
            }
            return "Usuario no encontrado";
        } catch (Exception e) {
            e.printStackTrace();   // ahora sí verás el error en la consola
            return "No se pudo agregar al usuario: " + e.getMessage();
        }

    }

    public ArrayList<Usuario> obtenerUsuarios() {
        try {
            return dao.listarTodos();
        } catch (Exception e) {
            e.printStackTrace();   // ahora sí verás el error en la consola
            System.out.println("No se pudo agregar al usuario: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
