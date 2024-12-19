package com.biblioteca.biblioteca.Controlador;

import com.biblioteca.biblioteca.Modelo.DAOGenerico;
import com.biblioteca.biblioteca.Modelo.Libro;

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

    public Libro getLibro(String isbn) {
        return daoLibro.selectById(isbn);
    }
}
