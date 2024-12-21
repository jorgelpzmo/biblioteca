package com.biblioteca.biblioteca.Modelo;

public enum Tipo {
    NORMAL("normal"),
    ADMINISTRADOR("administrador");
    private final String descripcion;
    Tipo(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getDescripcion() {
        return descripcion;
    }
}
