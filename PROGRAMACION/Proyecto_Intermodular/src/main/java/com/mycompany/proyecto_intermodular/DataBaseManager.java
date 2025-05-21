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
        ArrayList<Agente> agentes = new ArrayList<>(); // arraylist de agentes
        ArrayList<Rol> roles = new ArrayList<>();// arraylist de roles
        ArrayList<Habilidad> habilidades = new ArrayList<>();// arraylist de habilidades

        String cAgentes = "select nombre,descripcion,genero,rol from agentes";
        String cRoles = "select nombre,posicion from rol";
        String cHabilidades = "select idhabilidad,descripcion,IdAgente from habilidad";

        try {
            PreparedStatement stmt = conn.prepareStatement(cAgentes); //consulta que se hace (lanza la consulta)
            ResultSet rs = stmt.executeQuery(); // guarda los resultados de la consulta 

            while (rs.next()) {

                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                String genero = rs.getString("genero");
                int rol = rs.getInt("rol");
                
                Agente a = new Agente(nombre, descripcion, genero, rol);
            }

        } catch (SQLException e) {
            System.out.println("Error" + e.getMessage());
        }

    }

}
