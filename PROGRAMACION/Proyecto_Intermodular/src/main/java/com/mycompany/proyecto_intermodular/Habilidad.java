
package com.mycompany.proyecto_intermodular;


public class Habilidad {
    
    
    private String descripcion;

    
    // Constructor
    public Habilidad(String descripcion) {
        
        this.descripcion = descripcion;
        
    }
    
    // Getters

   

    
   

    public String getDescripcion() {
        return descripcion;
    }

    
    
    // Setters
    

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    

    // toString
    @Override
    public String toString() {
        return " descripcion=" + descripcion;
    }
    
    
    
}
