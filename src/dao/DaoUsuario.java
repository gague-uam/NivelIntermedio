package dao;

import model.Usuario;

import java.util.*;
import java.util.stream.Collectors;

public class DaoUsuario {
    // Map clave: id del usuario
    private Map<Integer, Usuario> usuarios = new HashMap<>();

    // Agrega usuario si el id no existe
    public boolean agregarUsuario(Usuario usuario) {
        if (usuarios.containsKey(usuario.getId())) {
            return false; // id duplicado
        }
        usuarios.put(usuario.getId(), usuario);
        return true;
    }

    // Lista usuarios ordenados alfabéticamente por nombre (ignorando mayúsculas)
    public List<Usuario> getUsuariosAlfabetico() {
        return usuarios.values().stream()
                .sorted(Comparator.comparing(u -> u.getNombre().toLowerCase(Locale.ROOT)))
                .collect(Collectors.toList());
    }

    // Cantidad de usuarios por tipo (Administrador, Editor, Lector)
    public Map<String, Long> getReportePorTipo() {
        return usuarios.values().stream()
                .collect(Collectors.groupingBy(u -> u.getClass().getSimpleName(), Collectors.counting()));
    }

    // Devuelve todos (por si se requiere en otro lugar)
    public Collection<Usuario> getTodos() {
        return usuarios.values();
    }
}
