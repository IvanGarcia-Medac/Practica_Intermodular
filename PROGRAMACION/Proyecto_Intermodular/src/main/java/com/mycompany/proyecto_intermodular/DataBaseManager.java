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
    public DataBaseManager() { //constructor del database

        try {
            this.url = "jdbc:mysql://database-1.cv0omsasqijz.us-east-1.rds.amazonaws.com/Valorant"; //url de la base de datos
            this.user = "admin"; // usuario de la base de datos
            this.password = "rootMedac"; //contraseña de la base de datos
            this.conn = DriverManager.getConnection(url, user, password); //conexion a la bse de datos
        } catch (SQLException e) {  //excepcion por si da fallo al conectar 
            System.out.println("Error " + e.getMessage());
        }

    }
//metodo para cargar datos de la base de datos

    public void cargarDatos() {

        String cAgentes = "select idAgente,nombre,descripcion,genero,Idrol from Agentes"; // consulta para los agentes

        try {// cargar agentes
            PreparedStatement stmt = conn.prepareStatement(cAgentes); //consulta que se hace (lanza la consulta)
            ResultSet rs = stmt.executeQuery(); // guarda los resultados de la consulta 

            while (rs.next()) { // bucle para recorer todos los datos y sacar los datos del agente
                int idAgente = rs.getInt("idAgente");
                idRol = rs.getInt("idrol");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                String genero = rs.getString("genero");

                //consulta con where y crear el objeto para que me de los roles
                String consultaRoles = "SELECT * FROM Rol WHERE idRol = " + idRol + ";"; //consulta para sacar los roles

                PreparedStatement stmt2 = conn.prepareStatement(consultaRoles); //consulta que se hace (lanza la consulta)
                ResultSet rs2 = stmt2.executeQuery(); // guarda los resultados de la consulta 
                if (rs2.next()) {  // saco los datos de los roles
                    String nombreR = rs2.getString("nombre");
                    String posicion = rs2.getString("posicion");

                    arol = new Rol(idRol, nombreR, posicion); //creo el objeto rol

                } else {
                    System.out.println("No se encontraron roles para el agente.");
                }

                //consulta para las habilidades y cargarlas
                String consultaHabilidades = "select Habilidad.descripcion from Habilidad inner join  Agente_Habilidad on Habilidad.idHabilidad = Agente_Habilidad.idHabilidad "
                        + "inner join Agentes on Agentes.idAgente=Agente_Habilidad.idAgente where Agentes.idAgente= " + idAgente + ";"; //consulta para cargar las habilidades

                PreparedStatement stmt3 = conn.prepareStatement(consultaHabilidades); //consulta que se hace (lanza la consulta)
                ResultSet rs3 = stmt3.executeQuery(); // guarda los resultados de la consulta 
                if (rs3.next()) {// saco los datos de habilidades

                    String hDescripcion = rs3.getString("descripcion");

                    aHabilidad = new Habilidad(hDescripcion); //creo el objeto habilidad

                } else {
                    System.out.println("No se encontraron habilidades para el agente");
                }

                Agente a = new Agente(nombre, descripcion, genero, arol, aHabilidad); // creo el agente con los datos recogidos
                agentes.add(a); //añado el agente a el arraylist
                stmt2.close();
                rs2.close();

                stmt3.close();
                rs3.close();
            }
            stmt.close();
            rs.close();
// excepciones para controlar fallos
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
//metodo para imprimir agentes
    public void imprimirAgentes() {
        for (int i = 0; i < agentes.size(); i++) { //imprimo todos los agentes
            System.out.println("////////////////////////////////////////////");
            System.out.println("NOMBRE:" + agentes.get(i).getNombre());
            System.out.println(" DESCRIPCION:" + agentes.get(i).getDescripcion());
            System.out.println(" GENERO:" + agentes.get(i).getGenero());
            System.out.println(" ROL:" + agentes.get(i).getRol().getNombre());
            System.out.println(" HABILIDAD:" + agentes.get(i).getHabilidad().getDescripcion());
            System.out.println("////////////////////////////////////////////");
        }
    }
//metodo para ver un agente concreto
    public void seleccionAgente() {
        try {
            Scanner sc1 = new Scanner(System.in);

            for (int i = 0; i < agentes.size(); i++) {
                System.out.println("AGENTE:" + agentes.get(i).getNombre());

            }

            System.out.println("Que agente quieres ver");
            String input = sc1.nextLine(); // capturar el agente que quiere el usuario
            Boolean resultado = false; // variable para comprobar si se a encontrado el agente

            for (int i = 0; i < agentes.size(); i++) { // imprimir agente seleccionado
                if (input.equalsIgnoreCase(agentes.get(i).getNombre())) {

                    System.out.println("////////////////////////////////////////////");
                    System.out.println("NOMBRE:" + agentes.get(i).getNombre());
                    System.out.println(" DESCRIPCION:" + agentes.get(i).getDescripcion());
                    System.out.println(" GENERO:" + agentes.get(i).getGenero());
                    System.out.println(" ROL:" + agentes.get(i).getRol().getNombre());
                    System.out.println(" HABILIDAD:" + agentes.get(i).getHabilidad().getDescripcion());
                    System.out.println("////////////////////////////////////////////");
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

    //METODO MOSTRAR ROLES
    public void mostrarRol() {

        try {
            int nRol;
            Scanner sc3 = new Scanner(System.in);

            for (int i = 0; i < agentes.size(); i++) {

                System.out.println(i + 1 + "-" + agentes.get(i).getRol().getNombre()); //imprime nombre roles
            }
            System.out.println("Elige un rol:");
            nRol = Integer.parseInt(sc3.nextLine());
            boolean encontrado = false; //booleano para comprobar si se encuentra
            for (int i = 0; i < agentes.size(); i++) {

                if (nRol == agentes.get(i).getRol().getIdrol()) {
                    System.out.println("Ese rol pertenece a - " + agentes.get(i).getNombre());
                    encontrado = true; // resultado en true si se encuentra el rol
                }
            }
            if (!encontrado) {
                System.out.println("Ese rol no esta disponible");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error de formato es necesario un numero:" + e.getMessage());
        }

    }

    public void mostrarHabilidades() {
        try {
            Scanner sc = new Scanner(System.in);
            int nHabilidad = 0;
            boolean encontrado = false;

            for (int i = 0; i < agentes.size(); i++) {
                System.out.println(i + 1 + "-" + agentes.get(i).getHabilidad());

            }
            System.out.println("Selecciona una habilidad");
            nHabilidad = Integer.parseInt(sc.nextLine());
            for (int i = 0; i < agentes.size(); i++) {
                if (nHabilidad == agentes.get(i).getRol().getIdrol()) {
                    System.out.println("Esa habilidad pertenece a - " + agentes.get(i).getNombre());
                    encontrado = true;
                }

            }
            if (!encontrado) {
                System.out.println("Esa Habilidad no esta disponible");
            }

        } catch (NumberFormatException e) {
            System.out.println("Error de formato es necesario un numero:" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
    }

}
