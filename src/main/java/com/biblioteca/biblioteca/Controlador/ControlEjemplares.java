package com.biblioteca.biblioteca.Controlador;

import com.biblioteca.biblioteca.Modelo.DAOGenerico;
import com.biblioteca.biblioteca.Modelo.Ejemplar;
import com.biblioteca.biblioteca.Modelo.Estado;
import com.biblioteca.biblioteca.Modelo.Libro;

import java.util.List;

public class ControlEjemplares {
    private DAOGenerico<Ejemplar, Integer> daoEjemplar;
    private DAOGenerico<Libro, String> daoLibro;

    public ControlEjemplares() {
        this.daoEjemplar = new DAOGenerico<>(Ejemplar.class, Integer.class);
        this.daoLibro = new DAOGenerico<>(Libro.class, String.class);
    }

    public void addEjemplar(String isbn, Estado estado) {
        Ejemplar ejemplar = new Ejemplar();
        Libro libro = daoLibro.selectById(isbn);
        ejemplar.setIsbn(libro);
        ejemplar.setEstado(estado.getDescripcion());
        daoEjemplar.add(ejemplar);
    }

    public List<Ejemplar> SelectAllEjemplares(){
        List<Ejemplar> ejemplares = daoEjemplar.selectAll();
        return ejemplares;
    }

    public int controlStock(List<Ejemplar>ejemplares){
        int contador=0;
        for(Ejemplar ejemplar : ejemplares){
            if(ejemplar.getEstado().equals(Estado.DISPONIBLE.getDescripcion())){
                contador++;
            }
        }
        return contador;
    }

}
