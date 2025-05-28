package com.mycompany.proyecto_intermodular;

public class Agente {

    // atributos
    private String nombre;
    private String descripcion;
    private String genero;
    private Rol rol;
    private Habilidad habilidad;

    //constructor
    public Agente(String nombre, String descripcion, String genero, Rol rol, Habilidad habilidad) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.genero = genero;
        this.rol = rol;
        this.habilidad = habilidad;
    }

    //getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Habilidad getHabilidad() {
        return habilidad;
    }

    public void setHabilidad(Habilidad habilidad) {
        this.habilidad = habilidad;
    }

    //to string
    @Override
    public String toString() {
        return "Agente{" + "nombre=" + nombre + ", descripcion=" + descripcion + ", genero=" + genero + ", rol=" + rol + ", habilidad=" + habilidad + '}';
    }

}
