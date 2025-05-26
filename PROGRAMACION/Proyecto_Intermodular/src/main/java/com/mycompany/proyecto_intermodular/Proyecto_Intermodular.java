package com.mycompany.proyecto_intermodular;

import java.util.Scanner;

public class Proyecto_Intermodular {

    public static void main(String[] args) {

        int opcion = 0;// CONTROLAR OPCIONE DEL USUARIO
        DataBaseManager gestor = new DataBaseManager(); // crear objeto para llamar a sus metodos
        gestor.cargarDatos(); // llamo a funcion cargardatos para que al comienzo cargue los datos de la base de datos
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("------------MENU------------");
            System.out.println("¿QUE QUIERES HACER?");
            System.out.println("1-VER TODOS LOS AGENTES");
            System.out.println("2-VER AGENTE CONCREO");
            System.out.println("3-VER ROLES Y HABILIDADES");

            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1:
                    gestor.imprimirAgentes();
                    break;
                case 2:
                    gestor.seleccionAgente();
                default:
                    throw new AssertionError();
            }
        } while (opcion != 4);

    }

}
