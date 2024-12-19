package com.biblioteca.biblioteca.Modelo;

public enum Estado {
    DISPONIBLE("Disponible"),
    PRESTADO("Prestado"),
    DAÑADO("Dañado");

    private final String descripcion;

    Estado(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
