package com.biblioteca.biblioteca.Vista;

import com.biblioteca.biblioteca.Controlador.ControlEjemplares;
import com.biblioteca.biblioteca.Controlador.ControlLibros;
import com.biblioteca.biblioteca.Controlador.ControlUsuarios;
import com.biblioteca.biblioteca.Modelo.Estado;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int controlMenu;
        do {
            System.out.println("---MENU---");
            System.out.println("¿Quien eres?");
            System.out.println("0. Salir");
            System.out.println("1. Administrador");
            System.out.println("2. Usuario");

            controlMenu = sc.nextInt();

            switch (controlMenu) {
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                case 1:
                    System.out.println("---MENU ADMINISTRADOR---");
                    ControlUsuarios controlUsuarios = new ControlUsuarios();
                    System.out.println("Introduzca un usuario");
                    String usuario = sc.next();
                    System.out.println("Introduzca un password");
                    String password = sc.next();
                    if(controlUsuarios.verificarUsuario(usuario, password)==1){
                        System.out.println("Bienvenido " + controlUsuarios.buscarUsuario(usuario).getNombre());
                        menuAdmin();
                    }
                    if (controlUsuarios.verificarUsuario(usuario, password)==2){
                        System.out.println("Contraseña incorrecta, intentelo de nuevo");

                    }
                    if(controlUsuarios.verificarUsuario(usuario, password)==0){
                        System.out.println("Datos incorrectos, revise usuario o cree uno nuevo si no esta registrado");

                    }
                    break;

                case 2:
                    System.out.println("Bienvenido usuario");
                    break;
            }
        } while (controlMenu != 0);

    }

    //MENU DE ADMINISTRADOR
    public static void menuAdmin() {
        Scanner sc = new Scanner(System.in);
        int controlMenuAdmin;
        Scanner scAdmin = new Scanner(System.in);
        do {
            System.out.println("Elija una opción");
            System.out.println("0. Salir");
            System.out.println("1. Añadir libros");
            controlMenuAdmin = sc.nextInt();
            switch (controlMenuAdmin) {
                case 0:
                    System.out.println("Saliendo del menu...");
                    break;
                case 1:
                    Scanner scAnadir = new Scanner(System.in);
                    ControlLibros controlLibros = new ControlLibros();
                    ControlEjemplares controlEjemplares = new ControlEjemplares();
                    System.out.println("Ingrese el isbn del libro");
                    String isbn = scAnadir.nextLine();
                    System.out.println("Ingrese el titulo del libro");
                    String titulo = scAnadir.nextLine();
                    System.out.println("Ingrese el autor del libro");
                    String autor = scAnadir.nextLine();
                    boolean controlEstado = true;
                    Estado estado = Estado.DISPONIBLE;
                    do {
                        int auxiliar;
                        System.out.println("Ingrese el estado del libro");
                        System.out.println("1. Disponible");
                        System.out.println("2. Prestado");
                        System.out.println("3. Dañado");
                        Scanner scEstado = new Scanner(System.in);
                        auxiliar = scEstado.nextInt();
                        switch (auxiliar) {
                            case 1:
                                estado = Estado.DISPONIBLE;
                                controlEstado = false;
                                break;
                            case 2:
                                estado = Estado.PRESTADO;
                                controlEstado = false;
                                break;
                            case 3:
                                estado = Estado.DAÑADO;
                                controlEstado = false;
                                break;
                            default:
                                System.out.println("Opcion no valida");
                        }
                    } while (controlEstado);
                    controlLibros.addLibro(isbn, titulo, autor);
                    controlEjemplares.addEjemplar(isbn, estado);
                    break;
            }
        } while (controlMenuAdmin != 0);
    }
}
