/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadora;

/**
 *
 * @author duily
 */
public class Calculadora {
        public static int  sumar (int a, int b) {
        return a+b;    
    }
    
    public static int  resta (int a, int b) {
        return a-b;    
    }
    
    public static int  multiplicacion (int a, int b) {
        return a*b;    
    }
    
    public static float  division (float a, float b) {
        float div=0;
        try {
            div= a/b;
        } catch (ArithmeticException e) {
            System.out.print("no se puede dividir entre cero");
        }
        return div;    
    }
    


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("Suma= "+ sumar(10, 20));
        System.out.println("Resta= "+resta(10, 20));
        System.out.println("Multiplicacion= "+ multiplicacion(10, 20));
        System.out.println("Division= " + division(10, 5));

    }
    
}
