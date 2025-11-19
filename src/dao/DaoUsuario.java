package dao;

import model.Usuario;

import java.util.*;
import java.util.stream.Collectors;

public class DaoUsuario {

    private Map<Integer, Usuario> usuarios = new HashMap<>();

    public boolean agregarUsuario(Usuario usuario) {
        if (usuarios.containsKey(usuario.getId())) {
            return false; // id duplicado
        }
        usuarios.put(usuario.getId(), usuario);
        return true;
    }

    public List<Usuario> getUsuariosAlfabetico() {
        return usuarios.values().stream()
                .sorted(Comparator.comparing(u -> u.getNombre().toLowerCase(Locale.ROOT)))
                .collect(Collectors.toList());
    }

    public Map<String, Long> getReportePorTipo() {
        return usuarios.values().stream()
                .collect(Collectors.groupingBy(u -> u.getClass().getSimpleName(), Collectors.counting()));
    }

    public Collection<Usuario> getTodos() {
        return usuarios.values();
    }
}
