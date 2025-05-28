package com.mycompany.proyecto_intermodular;

public class Rol {

// atributos 
    private int idrol;
    private String nombre;
    private String posicion;

    // constructor
    public Rol(int idrol, String nombre, String posicion) {
        this.idrol = idrol;
        this.nombre = nombre;
        this.posicion = posicion;
    }

    //ggeters y setters
    public int getIdrol() {
        return idrol;
    }

    public void setIdrol(int idrol) {
        this.idrol = idrol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    //to string
    @Override
    public String toString() {
        return "Rol{" + "idrol=" + idrol + ", nombre=" + nombre + ", posicion=" + posicion + '}';
    }

}
