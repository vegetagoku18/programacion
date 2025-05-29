package programacion;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Ejercicio16 {

    public static File seleccionarArchivo() {
        Scanner sc = new Scanner(System.in);
        String ruta = "";

        while (ruta.isBlank()) {
            System.out.println("Dime la ruta del archivo:");
            ruta = sc.nextLine().trim();
            if (ruta.isBlank()) {
                System.out.println("Ruta vacía. Intenta de nuevo.");
            }
        }

        return new File(ruta);
    }

    public static File seleccionarArchivoValido() {
        File archivo = seleccionarArchivo();
        while (!archivo.exists() || archivo.isDirectory() || !archivo.isFile()) {
            System.out.println("La ruta no corresponde a un archivo válido. Intenta de nuevo.");
            archivo = seleccionarArchivo();
        }
        return archivo;
    }

    public static File seleccionarRutaExistente() {
        File archivo = seleccionarArchivo();
        while (!archivo.exists()) {
            System.out.println("La ruta no existe. Intenta de nuevo.");
            archivo = seleccionarArchivo();
        }
        return archivo;
    }

    public static void main(String[] args) {
        // C:\Users\Carlos\Desktop\prueba.txt
        // C:\Users\Carlos\Desktop\pruebaPermisos.txt
        // "C:\Windows\Temp\
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        while (opcion != 5) {
            System.out.println("¿Qué opción quieres hacer?");
            System.out.println("1º Mostrar información");
            System.out.println("2º Mostrar archivo");
            System.out.println("3º Añadir contenido");
            System.out.println("4º Borrar archivo");
            System.out.println("5º Salir");
            try {
                opcion = sc.nextInt();
            } catch (java.util.InputMismatchException e) {
                sc.nextLine();
                opcion = 0;
            }
            switch (opcion) {
                case 1: 
                    File archivo = seleccionarRutaExistente();

                    if (archivo.isFile()) {
                        System.out.println();
                        System.out.println("Nombre: " + archivo.getName());
                        System.out.println("Directorio origen: " + archivo.getParent());
                        System.out.println();
                    } else {
                        File[] archivos = archivo.listFiles();
                        if (archivos == null) {
                            System.out.println("No tienes permisos");
                        } else {
                            for (File file : archivos) {
                                if (file.isFile()) {
                                    System.out.println("Nombre: " + file.getName());
                                    System.out.printf("Tamaño: %d bytes \n", file.length());
                                    System.out.println();
                                }
                            }
                        }
                    }
                    break;
                case 2:
                    archivo = seleccionarArchivoValido();
                    try (Scanner f = new Scanner(archivo);) {
                        while (f.hasNext()) {
                            System.out.println(f.nextLine());
                        }
                    } catch (IOException e) {
                        System.out.println("No tienes permisos para leer el archivo seleccionado");
                    }
                    break;
                case 3:
                    archivo = seleccionarArchivoValido();
                    System.out.println("¿Qué texto quieres añadir al archivo?");
                    sc.nextLine();
                    String texto = sc.nextLine();
                    try (PrintWriter pw = new PrintWriter(new FileWriter(archivo, true))) {
                        archivo.createNewFile();
                        pw.println(texto);
                    } catch (IOException e) {
                        System.out.println("Ha sucedido un error");
                    }
                    break;
                case 4: 
                    archivo = seleccionarArchivoValido();
                    System.out.println("¿Estás seguro de que quieres borrar el archivo? (Si/No)");
                    sc.nextLine();
                    String respuesta = sc.nextLine();
                    if (respuesta.toLowerCase().trim().equals("si")) {
                        if (archivo.delete()) {
                            System.out.println("Se ha borrado correctamente el archivo");
                        } else {
                            System.out.println("No se ha podido borrar el archivo");
                        }
                    }
                    break;
                case 5:
                    System.out.println("Hasta la vista baby");
                    sc.close();
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        }
    }
}
