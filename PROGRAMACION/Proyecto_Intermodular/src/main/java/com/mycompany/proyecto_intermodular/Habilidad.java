
package com.mycompany.proyecto_intermodular;


public class Habilidad {
    
    private int idHabilidad;
    private String descripcion;
    private int idAgente;
    
    // Constructor
    public Habilidad(int idHabilidad, String descripcion, int idAgente) {
        this.idHabilidad = idHabilidad;
        this.descripcion = descripcion;
        this.idAgente = idAgente;
    }
    
    // Getters

    public int getIdHabilidad() {
        return idHabilidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getIdAgente() {
        return idAgente;
    }
    
    // Setters
    public void setIdHabilidad(int idHabilidad) {
        this.idHabilidad = idHabilidad;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setIdAgente(int idAgente) {
        this.idAgente = idAgente;
    }

    // toString
    @Override
    public String toString() {
        return "Habilidad{" + "idHabilidad=" + idHabilidad + ", descripcion=" + descripcion + ", idAgente=" + idAgente + '}';
    }
    
    
    
}
