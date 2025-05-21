
package com.mycompany.proyecto_intermodular;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataBaseManager {
  
    //atributos
    String url;  // localizacion de mi base de datos 
    String user; // usuario
    String password; // contraseña de mi base de datos
    Connection conn;
    

    //constructor 
    public DataBaseManager() {

        try {
            this.url = "jdbc:mysql://localhost:3306/Valorant";
            this.user = "VAL";
            this.password = "MEDAC";
            this.conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        }
    
        
        
     
}
    public void cargarDatos(){
            ArrayList<Agente>agentes = new ArrayList<>();
            ArrayList<Roles>agentes = new ArrayList<>();
            ArrayList<habilidad>agentes = new ArrayList<>();
            
            
        }
    
    
    
}

