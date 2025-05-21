
package com.mycompany.proyecto_intermodular;


public class Rol {
  
    private int idRol;
    private String nombre;
    private String posicion;

    //Constructor
    public Rol(int idRol, String nombre, String posicion) {
        this.idRol = idRol;
        this.nombre = nombre;
        this.posicion = posicion;
    }
    // Getters
    public int getIdRol() {
        return idRol;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPosicion() {
        return posicion;
    }

    // Setters
    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    // toString
    @Override
    public String toString() {
        return "Rol{" + "idRol=" + idRol + ", nombre=" + nombre + ", posicion=" + posicion + '}';
    }
    
    
    
}
