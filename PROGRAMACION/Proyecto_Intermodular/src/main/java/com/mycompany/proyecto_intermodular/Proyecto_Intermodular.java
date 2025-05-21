

package com.mycompany.proyecto_intermodular;

import java.util.Scanner;


public class Proyecto_Intermodular {

    public static void main(String[] args) {
        
        int opcion=0;// CONTROLAR OPCIONE DEL USUARIO
        
        Scanner sc=new Scanner(System.in);
                
               System.out.println("------------MENU------------"); 
               System.out.println("¿QUE QUIERES HACER?"); 
               System.out.println("1-VER TODOS LOS AGENTES"); 
               System.out.println("2-VER AGENTE CONCREO"); 
               System.out.println("3-VER ROLES Y HABILIDADES"); 
               
               opcion=Integer.parseInt(sc.nextLine());
                
         switch (opcion) {
            case "1":
                
                break;
            case "2";
            
                break;
                
            case "3";
            
                break;
                
            default:
                
        }
       
                
    }
}
