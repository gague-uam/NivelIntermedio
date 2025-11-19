package model;

import java.util.HashSet;
import java.util.Set;

public class Lector extends Usuario {
    private Set<String> temasFavoritos = new HashSet<>();
    public Set<String> getTemasFavoritos() {
        return temasFavoritos;
    }
    public void setTemasFavoritos(Set<String> temasFavoritos) {
        this.temasFavoritos = temasFavoritos;
    }


}
