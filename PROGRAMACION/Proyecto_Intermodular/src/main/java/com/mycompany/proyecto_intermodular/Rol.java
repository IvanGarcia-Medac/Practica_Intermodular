
package com.mycompany.proyecto_intermodular;


public class Rol {
  
    private String nombre;
    private String posicion;

    //Constructor
    public Rol(String nombre, String posicion) {
        this.nombre = nombre;
        this.posicion = posicion;
    }
    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getPosicion() {
        return posicion;
    }

    // Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    // toString
    @Override
    public String toString() {
        return "Rol{" + "nombre=" + nombre + ", posicion=" + posicion + '}';
    }
    
    
    
    
}
