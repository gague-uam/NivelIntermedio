package run;

import dao.DaoUsuario;

import java.util.Scanner;


public class main {
    public static agregar(){
        DaoUsuario dao = new DaoUsuario();
        Scanner sc = new Scanner(System.in);

    }
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("1. Agregar Usuario");
            System.out.println("2. Listar Usuarios");
            System.out.println("3. Mostrar Reporte");
            System.out.println("4. Salir");
        }
        String opcion = sc.nextLine();
        switch (opcion) {
            case "1":
                // Lógica para agregar usuario
                break;
            case "2":
                // Lógica para listar usuarios
                break;
            case "3":
                // Lógica para mostrar reporte
                break;
            case "4":
                System.out.println("Saliendo del programa...");
                sc.close();
                System.exit(0);
                break;
            default:
                System.out.println("Opción no válida. Intente de nuevo.");
        }
    }
}
