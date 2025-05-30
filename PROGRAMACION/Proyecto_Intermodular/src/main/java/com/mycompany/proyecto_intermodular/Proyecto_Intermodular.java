package com.mycompany.proyecto_intermodular;

import java.util.Scanner;

public class Proyecto_Intermodular {

    public static void main(String[] args) {

        int opcion = 0;// CONTROLAR OPCIONE DEL USUARIO
        DataBaseManager gestor = new DataBaseManager(); // crear objeto para llamar a sus metodos
        gestor.cargarDatos(); // llamo a funcion cargardatos para que al comienzo cargue los datos de la base de datos
        Scanner sc = new Scanner(System.in);

        try {

            do {

                System.out.println("------------MENU------------");
                System.out.println("¿QUE QUIERES HACER?");
                System.out.println("1-VER TODOS LOS AGENTES");
                System.out.println("2-VER AGENTE CONCREO");
                System.out.println("3-VER ROLES");
                System.out.println("4-VER HABILIDADES");
                System.out.println("5-SALIR");

                opcion = Integer.parseInt(sc.nextLine());

                switch (opcion) {
                    case 1:
                        gestor.imprimirAgentes();
                        break;
                    case 2:
                        gestor.seleccionAgente();
                        break;
                    case 3:
                        gestor.mostrarRol();
                        break;
                    case 4:
                        gestor.mostrarHabilidades();
                        break;

                }
            } while (opcion != 5);
        } catch (NumberFormatException e) {
            System.out.println("Error" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
        sc.close();
    }

}
