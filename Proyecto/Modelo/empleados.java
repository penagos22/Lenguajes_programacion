/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

public class empleados{
    private String Nombre;
    private String Cargo;
    private String Codigo;
    private String Autorizado;
    private String Contrasena;
    
    public empleados(String Nombre, String Cargo, String Codigo, String Contrasena, String Autorizado){// aqui realizamos nuestro contructor de empleados
        this.Nombre = Nombre;
        this.Cargo = Cargo;
        this.Codigo = Codigo;
        this.Contrasena = Contrasena;
        this.Autorizado = Autorizado;
        
    }
    public empleados(){
        
    }
    
    public empleados( String Codigo){
        this.Codigo = Codigo;
    }

    public String getAutorizado() {
        return Autorizado;
    }

    public void setAutorizado(String Autorizado) {
        this.Autorizado = Autorizado;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getCargo() {
        return Cargo;
    }

    public void setCargo(String Cargo) {
        this.Cargo = Cargo;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    public String getContrasena() {
        return Contrasena;
    }

    public void setContrasena(String Contrasena) {
        this.Contrasena = Contrasena;
    }
    
    

    
}