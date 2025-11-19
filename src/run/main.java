package run;

import dao.DaoUsuario;
import model.Administrador;
import model.Editor;
import model.Lector;
import model.Usuario;

import java.util.*;

public class main {
    // Dao único para toda la ejecución
    private static final DaoUsuario dao = new DaoUsuario();
    private static final Scanner sc = new Scanner(System.in);

    private static void agregarUsuario() {
        try {
            System.out.print("Ingrese id (entero): ");
            int id = Integer.parseInt(sc.nextLine().trim());
            System.out.print("Nombre: ");
            String nombre = sc.nextLine().trim();
            System.out.print("Email: ");
            String email = sc.nextLine().trim();
            System.out.println("Tipo de usuario: 1=Administrador 2=Editor 3=Lector");
            String tipo = sc.nextLine().trim();

            Usuario u;
            switch (tipo) {
                case "1":
                    Administrador a = new Administrador();
                    System.out.print("Nivel de acceso (int): ");
                    int nivel = 0;
                    try { nivel = Integer.parseInt(sc.nextLine().trim()); } catch (NumberFormatException ignored) {}
                    a.setNivelAcceso(nivel);
                    u = a;
                    break;
                case "2":
                    Editor e = new Editor();
                    System.out.print("Secciones asignadas (separadas por coma): ");
                    String secLinea = sc.nextLine();
                    List<String> secciones = Arrays.stream(secLinea.split(","))
                            .map(String::trim)
                            .filter(s -> !s.isEmpty())
                            .toList();
                    e.setSeccionesAsignadas(secciones);
                    u = e;
                    break;
                case "3":
                    Lector l = new Lector();
                    System.out.print("Temas favoritos (separados por coma): ");
                    String temasLinea = sc.nextLine();
                    Set<String> temas = new HashSet<>();
                    for (String t : temasLinea.split(",")) {
                        String tt = t.trim();
                        if (!tt.isEmpty()) temas.add(tt);
                    }
                    l.setTemasFavoritos(temas);
                    u = l;
                    break;
                default:
                    System.out.println("Tipo inválido.");
                    return;
            }
            u.setId(id);
            u.setNombre(nombre);
            u.setEmail(email);
            boolean ok = dao.agregarUsuario(u);
            System.out.println(ok ? "Usuario agregado." : "Ya existe un usuario con ese id.");
        } catch (NumberFormatException ex) {
            System.out.println("Id inválido. Operación cancelada.");
        }
    }

    private static void listarUsuarios() {
        List<Usuario> lista = dao.getUsuariosAlfabetico();
        if (lista.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
            return;
        }
        System.out.println("Usuarios ordenados por nombre:");
        for (Usuario u : lista) {
            System.out.println(u.getId() + " - " + u.getClass().getSimpleName() + " - " + u.getNombre() + " <" + u.getEmail() + ">");
        }
    }

    private static void mostrarReporte() {
        Map<String, Long> rep = dao.getReportePorTipo();
        long admins = rep.getOrDefault("Administrador", 0L);
        long eds = rep.getOrDefault("Editor", 0L);
        long lecs = rep.getOrDefault("Lector", 0L);
        long total = admins + eds + lecs;
        System.out.println("Reporte por tipo:");
        System.out.println("Administradores: " + admins);
        System.out.println("Editores: " + eds);
        System.out.println("Lectores: " + lecs);
        System.out.println("Total: " + total);
    }

    public static void main(String[] args) {
        // Bucle principal
        while (true) {
            System.out.println();
            System.out.println("===== MENÚ =====");
            System.out.println("1. Agregar Usuario");
            System.out.println("2. Listar Usuarios");
            System.out.println("3. Mostrar Reporte");
            System.out.println("4. Salir");
            System.out.print("Opción: ");
            String opcion = sc.nextLine().trim();
            switch (opcion) {
                case "1" -> agregarUsuario();
                case "2" -> listarUsuarios();
                case "3" -> mostrarReporte();
                case "4" -> {
                    System.out.println("Saliendo...");
                    sc.close();
                    return;
                }
                default -> System.out.println("Opción no válida.");
            }
        }
    }
}