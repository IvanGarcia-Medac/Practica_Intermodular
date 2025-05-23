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
            this.url = "jdbc:mysql://database-1.cv0omsasqijz.us-east-1.rds.amazonaws.com/Valorant";
            this.user = "admin";
            this.password = "rootMedac";
            this.conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        }

    }

    public void cargarDatos() {

        String cAgentes = "select nombre,descripcion,genero,Idrol from Agentes"; // consulta para los agentes
        String cHabilidades = "select idhabilidad,descripcion,IdAgente from habilidad"; // consultas para habilidades

        String consultaRoles = "SELECT Rol.idRol AS idRol, Rol.nombre, Rol.posicion FROM Rol INNER JOIN Agentes ON Rol.IdRol = Agentes.idRol WHERE Rol.idRol = Agentes.IdRol";

        String consultaHabilidades = "select Habilidad.descripcion from Habilidad inner join  Agente_Habilidad on Habilidad.idHabilidad = Agente_Habilidad.idHabilidad \n"
                + "inner join Agentes on Agentes.idAgente=Agente_Habilidad.idAgente where Agentes.idAgente=Agente_Habilidad.idHabilidad;";
        
        
        
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
                if (rs2.next()) {
                    int idRol = rs2.getInt("idRol");
                    String nombreR = rs2.getString("nombre");
                    String posicion = rs2.getString("posicion");

                    Rol arol = new Rol(idRol, nombreR, posicion);

                } else {
                    System.out.println("No se encontraron roles para el agente.");
                }

                //consulta para las habilidades y cargarlas
                PreparedStatement stmt3 = conn.prepareStatement(consultaHabilidades); //consulta que se hace (lanza la consulta)
                ResultSet rs3 = stmt3.executeQuery(); // guarda los resultados de la consulta 
                if (rs3.next()) {

                    String hDescripcion = rs3.getString("descripcion");

                    Habilidad aHabilidad = new Habilidad(hDescripcion);

                } else {
                    System.out.println("No se encontraron habilidades para el agente");
                }

                Agente a = new Agente(nombre, descripcion, genero, arol, aHabilidad);
                agentes.add(a);
                stmt2.close();
                rs2.close();

                stmt2.close();
                rs2.close();
            }
            stmt.close();
            rs.close();

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

    public void imprimirAgentes() {
        for (int i = 0; i < agentes.size(); i++) {
            System.out.println(agentes.get(i));

        }
    }

}
