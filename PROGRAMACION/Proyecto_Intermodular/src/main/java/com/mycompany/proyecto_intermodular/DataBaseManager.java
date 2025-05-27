package com.mycompany.proyecto_intermodular;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataBaseManager {

    //atributos
    String url;  // localizacion de mi base de datos 
    String user; // usuario
    String password; // contraseña de mi base de datos
    Connection conn; // conexion a la base de datos

    // variables necesarias
    Rol arol; // clase rol
    Habilidad aHabilidad; // calse habilidad
    int idRol;

    ArrayList<Agente> agentes = new ArrayList<>(); // arraylist de agentes

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

        String cAgentes = "select idAgente,nombre,descripcion,genero,Idrol from Agentes"; // consulta para los agentes

        try {// cargar agentes
            PreparedStatement stmt = conn.prepareStatement(cAgentes); //consulta que se hace (lanza la consulta)
            ResultSet rs = stmt.executeQuery(); // guarda los resultados de la consulta 

            while (rs.next()) {
                int idAgente = rs.getInt("idAgente");
                idRol = rs.getInt("idrol");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                String genero = rs.getString("genero");

                //consulta con where y crear el objeto
                String consultaRoles = "SELECT * FROM Rol WHERE idRol = " + idRol + ";";

                PreparedStatement stmt2 = conn.prepareStatement(consultaRoles); //consulta que se hace (lanza la consulta)
                ResultSet rs2 = stmt2.executeQuery(); // guarda los resultados de la consulta 
                if (rs2.next()) {
                    String nombreR = rs2.getString("nombre");
                    String posicion = rs2.getString("posicion");

                    arol = new Rol(idRol, nombreR, posicion);

                } else {
                    System.out.println("No se encontraron roles para el agente.");
                }

                //consulta para las habilidades y cargarlas
                String consultaHabilidades = "select Habilidad.descripcion from Habilidad inner join  Agente_Habilidad on Habilidad.idHabilidad = Agente_Habilidad.idHabilidad \n"
                        + "inner join Agentes on Agentes.idAgente=Agente_Habilidad.idAgente where Agentes.idAgente= " + idAgente + ";";

                PreparedStatement stmt3 = conn.prepareStatement(consultaHabilidades); //consulta que se hace (lanza la consulta)
                ResultSet rs3 = stmt3.executeQuery(); // guarda los resultados de la consulta 
                if (rs3.next()) {

                    String hDescripcion = rs3.getString("descripcion");

                    aHabilidad = new Habilidad(hDescripcion);

                } else {
                    System.out.println("No se encontraron habilidades para el agente");
                }

                Agente a = new Agente(nombre, descripcion, genero, arol, aHabilidad);
                agentes.add(a);
                stmt2.close();
                rs2.close();

                stmt3.close();
                rs3.close();
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
            System.out.println("nombre:" + agentes.get(i).getNombre() + " Descripcion:" + agentes.get(i).getDescripcion()
                    + " Genero:" + agentes.get(i).getGenero() + " Rol:" + agentes.get(i).getRol().getNombre() + " Habilidad:" + agentes.get(i).getHabilidad().getDescripcion());

        }
    }

    public void seleccionAgente() {
        try {
            Scanner sc1 = new Scanner(System.in);

            for (int i = 0; i < agentes.size(); i++) {
                System.out.println(agentes.get(i).getNombre());

            }

            System.out.println("Que agente quieres ver");
            String input = sc1.nextLine(); // capturar el agente que quiere el usuario

            Boolean resultado = false; // variable para comprobar si se a encontrado el agente

            for (int i = 0; i < agentes.size(); i++) {
                if (input.equalsIgnoreCase(agentes.get(i).getNombre())) {

                    System.out.println("nombre:" + agentes.get(i).getNombre() + " Descripcion:" + agentes.get(i).getDescripcion()
                            + " Genero:" + agentes.get(i).getGenero() + " Rol:" + agentes.get(i).getRol().getNombre() + " Habilidad:" + agentes.get(i).getHabilidad().getDescripcion());
                    resultado = true; // resultado en true si se encuentra al agente

                }
            }
            if (!resultado) {
                System.out.println("Ese agente no existe");
            }
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }

    }

    public void mostrarAtributos() {

        int nRol;
        Scanner sc3 = new Scanner(System.in);

        for (int i = 0; i < agentes.size(); i++) {

            System.out.println(i + "-" + agentes.get(i).getRol().getNombre());
        }
        System.out.println("Elige un rol:");
        nRol = Integer.parseInt(sc3.nextLine());
        boolean encontrado = false;
        for (int i = 0; i < agentes.size(); i++) {

            if (nRol == agentes.get(i).getRol().getIdrol()) {
                System.out.println("Ese rol pertenece a " + agentes.get(i).getNombre());
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("Ese rol no existe");
        }

    }

}


