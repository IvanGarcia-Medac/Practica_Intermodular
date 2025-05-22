package com.mycompany.proyecto_intermodular;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataBaseManager {

    //atributos
    String url;  // localizacion de mi base de datos 
    String user; // usuario
    String password; // contraseña de mi base de datos
    Connection conn;

    
    ArrayList<Agente> agentes = new ArrayList<>(); // arraylist de agentes
    ArrayList<Rol> roles = new ArrayList<>();// arraylist de roles
    ArrayList<Habilidad> habilidades = new ArrayList<>();// arraylist de habilidades
    //constructor 
    public DataBaseManager() {

        try {
            this.url = "jdbc:mysql://localhost:3306/Valorant";
            this.user = "root";
            this.password = "Med@c";
            this.conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        }

    }

    public void cargarDatos() {
        

        String cAgentes = "select nombre,descripcion,genero,Idrol from agentes"; // consulta para los agentes
        String cRoles = "select idRol,nombre,posicion from rol"; // consulta para toles
        String cHabilidades = "select idhabilidad,descripcion,IdAgente from habilidad"; // consultas para habilidades

        String consultaRoles = "select nombre,posion from rol inner join agentes on IdRol=IdRol where rol.Idrol=agentes.IdRol";

        try {// cargar agentes
            PreparedStatement stmt = conn.prepareStatement(cAgentes); //consulta que se hace (lanza la consulta)
            ResultSet rs = stmt.executeQuery(); // guarda los resultados de la consulta 

            while (rs.next()) {

                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                String genero = rs.getString("genero");
                
                
                //consulta con where y crear el objeto
                PreparedStatement stmt2 = conn.prepareStatement(consultaRoles); //consulta que se hace (lanza la consulta)
                ResultSet rs2 = stmt2.executeQuery(); // guarda los resultados de la consulta 
                int idRol = rs.getInt("IdRol");
                String nombreR = rs.getString("nombre");
                String posicion = rs.getString("posicion");

                Rol arol = new Rol( idRol,nombreR, descripcion);//Objeto rol
                
                Agente a = new Agente(nombre, descripcion, genero, arol);

                agentes.add(a); // meto los agentes en 
            }

     } catch (SQLException e) {
    System.out.println("Error de SQL: " + e.getMessage());

    } catch (IndexOutOfBoundsException e) {
    System.out.println("Índice fuera de rango: " + e.getMessage());

    } catch (NumberFormatException e) {
    System.out.println("Formato de número inválido: " + e.getMessage());

    } catch (Exception e) {
    System.out.println("Ocurrió un error inesperado: " + e.getMessage());
}

    }
    
    public void imprimirAgentes(){
        for (int i = 0; i < agentes.size(); i++) {
            System.out.println(agentes.get(i));
            
        }
    }
    
}
