/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parcial1;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author duily
 */
public class Parcial1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here


    Scanner numero = new Scanner(System.in);
    float suma = 0;
    int contar = 0;
    System.out.println("Ingrese 6 numeros");
    for (int i = 0; i < 6; i++) {
        float num = numero.nextFloat();
            if (num > 0){
                suma = suma + num;  
                contar = contar + 1;
            }
    }
    float promedio = suma/contar;
    System.out.println(" El total de numeros positivos es:  " + contar);
    System.out.println(" El promedio de los numeros positivos es " + promedio);
    
}
}





    
    

