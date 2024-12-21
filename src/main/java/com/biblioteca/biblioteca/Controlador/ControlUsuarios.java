package com.biblioteca.biblioteca.Controlador;

import com.biblioteca.biblioteca.Modelo.DAOGenerico;
import com.biblioteca.biblioteca.Modelo.Tipo;
import com.biblioteca.biblioteca.Modelo.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class ControlUsuarios {
    private DAOGenerico<Usuario, String> daoUsuario;

    public ControlUsuarios() {
        this.daoUsuario = new DAOGenerico<>(Usuario.class, String.class);
    }

    public void anadirUsuario(String dni, String nombre, String email, String password) {
        Usuario usuario = new Usuario();
        usuario.setDni(dni);
        usuario.setNombre(nombre);
        usuario.setEmail(email);
        usuario.setPassword(password);
        usuario.setTipo(Tipo.NORMAL.getDescripcion());
        daoUsuario.add(usuario);
    }

    public List<Usuario> listarUsuarios() {
        return daoUsuario.selectAll();
    }

    public Usuario buscarUsuario(String email) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("biblioteca");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Usuario usuario = null;
        usuario = em.createQuery("select u from Usuario u where u.email = :email ", Usuario.class).setParameter("email", email).getSingleResult();
        tx.commit();
        em.close();
        emf.close();
        return usuario;
    }

    public int verificarUsuario(String email, String password) {
        int variableControl = 0;
        for (Usuario usuario : listarUsuarios()) {
            if (usuario.getEmail().equals(email) && usuario.getPassword().equals(password)) {
                variableControl=1;
            } else if (usuario.getEmail().equals(email) && !usuario.getPassword().equals(password)) {
                variableControl=2;
            }else{
                variableControl=0;
            }
        }
        return variableControl;
    }
}

