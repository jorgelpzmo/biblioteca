package com.biblioteca.biblioteca.Controlador;

import com.biblioteca.biblioteca.Modelo.DAOGenerico;
import com.biblioteca.biblioteca.Modelo.Tipo;
import com.biblioteca.biblioteca.Modelo.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
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

    public void eliminarUsuario(String id) {
        Usuario usuario = daoUsuario.selectById(id);
        daoUsuario.delete(usuario);
        System.out.println("Usuario eliminado");
    }

    public void modificarUsuario(String dni, String nombre, String email, String password) {
        Usuario usuario = daoUsuario.selectById(dni);
        usuario.setNombre(nombre);
        usuario.setEmail(email);
        usuario.setPassword(password);
        System.out.println("Usuario modificado");
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

    public void anadirPenalizacion(int idUsuario) {
        ControlPrestamo prestamo = new ControlPrestamo();
        Usuario usuario = daoUsuario.selectById(String.valueOf(idUsuario));
        int numeroPrestamos = prestamo.getNumeroPrestamos(idUsuario).size();
        LocalDate fecha = LocalDate.now().plusDays(numeroPrestamos * 15);
        usuario.setPenalizacionHasta(fecha);
        System.out.println("Penalizacion aplicada");
    }

    public void eliminarPenalizacion(int idUsuario) {
        ControlPrestamo prestamo = new ControlPrestamo();
        Usuario usuario = daoUsuario.selectById(String.valueOf(idUsuario));
        usuario.setPenalizacionHasta(null);
        System.out.println("Penalizacion aplicada");
    }

    public void elimnarUsuario(int idUsuario) {
        Usuario usuario = daoUsuario.selectById(String.valueOf(idUsuario));
        ControlPrestamo prestamo = new ControlPrestamo();
        List prestamos=prestamo.buscarPrestamos(idUsuario);
        prestamo.eliminarPrestamos(prestamos);
        System.out.println("Usuario eliminado");
    }


}

