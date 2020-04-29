/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author duily
 */
public class Mascota {
    private String Mascota;
    private String Raza;
    private int edad;
    private String Nombre_dueno;
    private int celular;
    
    public Mascota (String Mascota, String Raza, int edad, String Nombre_dueno, int celular){
        this.Mascota = Mascota;
        this.Raza = Raza;
        this.edad = edad;
        this.Nombre_dueno = Nombre_dueno;
        this.celular = celular;
    }

    public String getMascota() {
        return Mascota;
    }

    public void setMascota(String Mascota) {
        this.Mascota = Mascota;
    }

    public String getRaza() {
        return Raza;
    }

    public void setRaza(String Raza) {
        this.Raza = Raza;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNombre_dueno() {
        return Nombre_dueno;
    }

    public void setNombre_dueno(String Nombre_dueno) {
        this.Nombre_dueno = Nombre_dueno;
    }

    public int getCelular() {
        return celular;
    }

    public void setCelular(int celular) {
        this.celular = celular;
    }
    
    
}
