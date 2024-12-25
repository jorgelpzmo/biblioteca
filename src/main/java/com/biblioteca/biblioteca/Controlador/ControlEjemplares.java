package com.biblioteca.biblioteca.Controlador;

import com.biblioteca.biblioteca.Modelo.DAOGenerico;
import com.biblioteca.biblioteca.Modelo.Ejemplar;
import com.biblioteca.biblioteca.Modelo.Estado;
import com.biblioteca.biblioteca.Modelo.Libro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

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

    public List<Ejemplar> buscarEjemplares(String isbn){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("biblioteca");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        List<Ejemplar> ejemplares = em.createQuery("select e from Ejemplar e where e.isbn = :isbn", Ejemplar.class).getResultList();
        tx.commit();
        em.close();
        return ejemplares;
    }

    public void eliminarEjemplar(int id) {
        Ejemplar ejemplar = daoEjemplar.selectById(id);
        daoEjemplar.delete(ejemplar);
        System.out.println("Ejemplar eliminado");
    }

    public void eliminarEjemplares(List<Ejemplar> ejemplares) {
        for (Ejemplar ejemplar : ejemplares) {
            daoEjemplar.delete(ejemplar);
        }
        System.out.println("Ejemplares eliminados");
    }

    public void modificarEjemplar(int id, String isbn, Estado estado) {
        Ejemplar ejemplar = daoEjemplar.selectById(id);
        Libro libro = daoLibro.selectById(isbn);
        ejemplar.setIsbn(libro);
        ejemplar.setEstado(estado.getDescripcion());
        daoEjemplar.update(ejemplar);
        System.out.println("Ejemplar modificado");

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
