package com.biblioteca.biblioteca.Controlador;

import com.biblioteca.biblioteca.Modelo.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ControlPrestamo {
    private DAOGenerico<Prestamo, Integer> daoPrestamo;
    private DAOGenerico<Ejemplar, Integer> daoEjemplar;
    private DAOGenerico<Usuario, Integer> daoUsuario;


    public ControlPrestamo() {
        this.daoPrestamo= new DAOGenerico<>(Prestamo.class, Integer.class);
    }

    public int addPrestamo(int usuarioId, int ejemplarId) {
        LocalDate fechaInicio = LocalDate.now();
        LocalDate fechaFin = LocalDate.now().plusDays(15);
        Prestamo prestamo = new Prestamo();
        Ejemplar ejemplar = daoEjemplar.selectById(ejemplarId);
        Usuario usuario = daoUsuario.selectById(usuarioId);
        if(getNumeroPrestamos(usuarioId).size()<=3 && ejemplar.getEstado().equals(Estado.DISPONIBLE.getDescripcion()) && usuario.getPenalizacionHasta()==null){
            prestamo.setEjemplar(ejemplar);
            prestamo.setUsuario(usuario);
            prestamo.setFechaInicio(fechaInicio);
            prestamo.setFechaDevolucion(fechaFin);
            daoPrestamo.add(prestamo);
            System.out.println("Prestamo agregado con éxito");
            return 1;
        }else{
            System.out.println("No se puede agregar un prestamo nuevo a este usuario");
            return -1;
        }
    }

    public void eliminarPrestamos(List<Prestamo> prestamos) {
        for (Prestamo prestamo : prestamos) {
            daoPrestamo.delete(prestamo);
        }
        System.out.println("Prestamos eliminados con exito");
    }

    public void eliminarPrestamo(int id) {
        Prestamo prestamo = daoPrestamo.selectById(id);
        daoPrestamo.delete(prestamo);
        System.out.println("Prestamo eliminado");
    }

    public List<Prestamo> buscarPrestamos(int usuarioId) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("biblioteca");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        List<Prestamo> prestamos = em.createQuery("select p from Prestamo p where p.usuario.id = :usuarioId", Prestamo.class).getResultList();
        tx.commit();
        em.close();
        return prestamos;
    }

    public List<Prestamo> getNumeroPrestamos(int usuarioId) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("biblioteca");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        List <Prestamo> prestamos;
        prestamos = em.createQuery("SELECT p FROM Prestamo p WHERE p.usuario.id = :usuarioId", Prestamo.class).setParameter("usuarioId", usuarioId).getResultList();
        tx.commit();
        em.close();
        return prestamos;


    }
}