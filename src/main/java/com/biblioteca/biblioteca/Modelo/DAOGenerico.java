package com.biblioteca.biblioteca.Modelo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class DAOGenerico<T, ID> {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("biblioteca");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();
    Class<T> clase;
    Class<ID> claseId;

    public DAOGenerico(Class<T> clase, Class<ID> claseId) {
        this.clase = clase;
        this.claseId = claseId;
    }

    //SELECT BY ID

    public T selectById(ID id) {
        return em.find(clase, id);
    }

    //SELECT ALL

    public List<T> selectAll() {
        return em.createQuery("SELECT c FROM " + clase.getName() + " c").getResultList();
    }

    //INSERT

    public boolean add(T objeto) {
        tx.begin();
        em.persist(objeto);
        tx.commit();
        return true;
    }

    //UPDATE

    public T update(T objeto) {
        tx.begin();
        em.merge(objeto);
        tx.commit();
        return objeto;
    }

    public boolean delete(T objeto) {
        tx.begin();
        em.remove(objeto);
        tx.commit();
        return true;
    }


    @Override
    public String toString() {
        return "DAOGenerico{" +
                "emf=" + emf +
                ", em=" + em +
                ", tx=" + tx +
                ", clase=" + clase +
                ", claseId=" + claseId +
                '}';
    }
}
