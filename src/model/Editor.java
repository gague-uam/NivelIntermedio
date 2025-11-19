package model;

import java.util.List;

public class Editor extends Usuario {
    private List<String> seccionesAsignadas;
    public List<String> getSeccionesAsignadas() {
        return seccionesAsignadas;
    }
    public void setSeccionesAsignadas(List<String> seccionesAsignadas) {
        this.seccionesAsignadas = seccionesAsignadas;
    }
}
