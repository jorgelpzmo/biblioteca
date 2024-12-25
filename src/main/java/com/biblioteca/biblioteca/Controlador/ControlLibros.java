package com.biblioteca.biblioteca.Controlador;

import com.biblioteca.biblioteca.Modelo.DAOGenerico;
import com.biblioteca.biblioteca.Modelo.Libro;

import java.util.List;

public class ControlLibros {
    private DAOGenerico<Libro, String> daoLibro;

    public ControlLibros() {
        this.daoLibro = new DAOGenerico<>(Libro.class, String.class);
    }

    public void addLibro(String isbn, String titulo, String autor) {
        Libro libro = new Libro();
        libro.setIsbn(isbn);
        libro.setTitulo(titulo);
        libro.setAutor(autor);
        if (daoLibro.selectById(libro.getIsbn()) == null) {
            daoLibro.add(libro);
            System.out.println("Libro agregado exitosamente");
        } else {
            System.out.println("Libro existente");
        }
    }

    public void modificarLibro(String isbn, String titulo, String autor) {
        try {
            Libro libro = daoLibro.selectById(isbn);
            ControlEjemplares controlEjemplares = new ControlEjemplares();
            libro.setIsbn(isbn);
            libro.setTitulo(titulo);
            libro.setAutor(autor);
        }catch(Exception e) {
            System.out.println("Error al modificar el libro");
        }
        System.out.println("Libro modificado exitosamente");

    }

    public void eliminarLibro(String isbn) {
        Libro libro = daoLibro.selectById(isbn);
        ControlEjemplares ejemplar = new ControlEjemplares();
        daoLibro.delete(libro);
        try {
            List ejemplares = ejemplar.buscarEjemplares(isbn);
            ejemplar.eliminarEjemplares(ejemplares);
        }
        catch(Exception e){
            System.out.println("No hay ejemplares asociados al libro");
            }
        System.out.println("Libro eliminado exitosamente");
    }

    public List<Libro> listarLibros() {
        List<Libro> libros = daoLibro.selectAll();
        return libros;
    }

    public Libro getLibro(String isbn) {
        return daoLibro.selectById(isbn);
    }
}
