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
            System.out.println("1. Gestionar libros");
            System.out.println("2. Gestionar ejemplares");
            System.out.println("3. Gestionar usuarios");
            System.out.println("4. Gestionar prestamos");
            controlMenuAdmin = sc.nextInt();
            switch (controlMenuAdmin) {
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                case 1:
                    int controlMenuAnadirLibros;
                    System.out.println("---GESTION LIBROS---");
                    System.out.println("0. Salir");
                    System.out.println("1. Añadir libros");
                    System.out.println("2. Modificar libros");
                    System.out.println("3. Eliminar libros");
                    System.out.println("4. Ver libros");
                    System.out.println("5. Buscar libro");

                    //GESTION LIBROS, GESTION EJEMPLARES, GESTION PRESTAMOS, GESTION USUARIOS
                    //DIVIDIR EN SUBMENUS
                    controlMenuAnadirLibros = sc.nextInt();
                    switch (controlMenuAnadirLibros) {
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
                            case 2:
                                Scanner scModificar = new Scanner(System.in);
                                ControlLibros controlLibrosm = new ControlLibros();
                                System.out.println("Ingrese el isbn del libro");
                                String isbnm = scModificar.next();
                                scModificar.nextLine();
                                System.out.println("Ingrese el titulo del libro");
                                String titulom = scModificar.nextLine();
                                System.out.println("Ingrese el autor del libro");
                                String autorm = scModificar.nextLine();
                                controlLibrosm.modificarLibro(isbnm, titulom, autorm);
                                break;
                            case 3:
                                Scanner scEliminar = new Scanner(System.in);
                                ControlLibros controlLibrosd = new ControlLibros();
                                System.out.println("Ingrese el id del libro");
                                String isbnd = scEliminar.next();
                                controlLibrosd.eliminarLibro(isbnd);
                                break;
                        case 4:
                            ControlLibros controlLibrosv = new ControlLibros();
                            System.out.println(controlLibrosv.listarLibros());
                            break;
                        case 5:
                            Scanner scVista2 = new Scanner(System.in);
                            ControlLibros controlLibrosv2 = new ControlLibros();
                            System.out.println("Ingrese el isbn del libro");
                            String isbnv2= scVista2.next();
                            System.out.println(controlLibrosv2.getLibro(isbnv2));
                            break;
                            default:
                                System.out.println("Opcion no valida");
                                break;


                    }
                case 2:
                    int controlMenuAnadirEjemplares;
                    System.out.println("---GESTION EJEMPLARES---");
                    System.out.println("0. Salir");
                    System.out.println("1. Añadir ejemplares");
                    System.out.println("2. Modificar ejemplares");
                    System.out.println("3. Eliminar ejemplares");
                    System.out.println("4. Ver ejemplares");
                    System.out.println("5. Buscar ejemplares");

                    //GESTION LIBROS, GESTION EJEMPLARES, GESTION PRESTAMOS, GESTION USUARIOS
                    //DIVIDIR EN SUBMENUS
                    controlMenuAnadirLibros = sc.nextInt();
                    switch (controlMenuAnadirLibros) {
                        case 0:
                            System.out.println("Saliendo del menu...");
                            break;
                        case 1:
                            Scanner scAnadir = new Scanner(System.in);
                            ControlEjemplares controlEjemplares = new ControlEjemplares();
                            System.out.println("Ingrese el isbn del libro");
                            String isbn = scAnadir.nextLine();
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
                            controlEjemplares.addEjemplar(isbn, estado);
                            break;
                        case 2:
                            Scanner scModificar = new Scanner(System.in);
                            ControlEjemplares controlEjemplares1 = new ControlEjemplares();
                            System.out.println("Ingrese el id del ejemplar");
                            int idm= scModificar.nextInt();
                            System.out.println("Ingrese el isbn del ejemplar");
                            String isbnm = scModificar.next();
                            boolean controlEstadom = true;
                            Estado estadom = Estado.DISPONIBLE;
                            do {
                                int auxiliar;
                                System.out.println("Ingrese el estado del libro");
                                System.out.println("1. Disponible");
                                System.out.println("2. Prestado");
                                System.out.println("3. Dañado");
                                Scanner scEstadom = new Scanner(System.in);
                                auxiliar = scEstadom.nextInt();
                                switch (auxiliar) {
                                    case 1:
                                        estadom = Estado.DISPONIBLE;
                                        controlEstadom = false;
                                        break;
                                    case 2:
                                        estadom = Estado.PRESTADO;
                                        controlEstadom = false;
                                        break;
                                    case 3:
                                        estadom = Estado.DAÑADO;
                                        controlEstadom = false;
                                        break;
                                    default:
                                        System.out.println("Opcion no valida");
                                }
                            } while (controlEstadom);
                            controlEjemplares1.modificarEjemplar(idm, isbnm, estadom);
                            break;
                        case 3:
                            Scanner scEliminar = new Scanner(System.in);
                            ControlEjemplares controlEjemplaresd = new ControlEjemplares();
                            System.out.println("Ingrese el id del ejemplar");
                            int idd = scEliminar.nextInt();
                            controlEjemplaresd.eliminarEjemplar(idd);
                            break;
                        case 4:
                            ControlEjemplares controlEjemplaresv = new ControlEjemplares();
                            System.out.println(controlEjemplaresv.SelectAllEjemplares());
                            System.out.println("Stock total de ejemplares: " + controlEjemplaresv.controlStock(controlEjemplaresv.SelectAllEjemplares()));
                            break;
                        case 5:
                            Scanner scVista2 = new Scanner(System.in);
                            ControlEjemplares controlEjemplaresv2 = new ControlEjemplares();
                            System.out.println("Ingrese el isbn del libro");
                            String isbnv2= scVista2.next();
                            System.out.println(controlEjemplaresv2.buscarEjemplares(isbnv2) + "\nStock: " + controlEjemplaresv2.controlStockEjemplar(isbnv2));
                            break;
                        default:
                            System.out.println("Opcion no valida");
                            break;


                    }
            }
        } while (controlMenuAdmin != 0);
    }
}
