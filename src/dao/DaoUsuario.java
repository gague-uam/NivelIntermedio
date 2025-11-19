package dao;

import model.Usuario;

import java.util.List;
import java.util.Map;

public class DaoUsuario {
    private Map<String, Usuario> usuarios;

    public boolean agregarUsuario(Usuario usuario) {
        if (usuarios.containsKey(usuario.getEmail())) {
            return false; // Usuario ya existe
        }
        usuarios.put(usuario.getEmail(), usuario);
        return true;
    }

    public List<Usuario> getUsuarios_Alfabetico() {
        return usuarios.values().stream()
                .sorted((u1, u2) -> u1.getNombre().compareToIgnoreCase(u2.getNombre()))
                .toList();
    }

    public List<Usuario> filtrarTipoUsuario(String tipoUsuario) {
        return usuarios.values().stream()
                .filter(u -> u.getClass().getSimpleName().equalsIgnoreCase(tipoUsuario))
                .toList();
    }
}
