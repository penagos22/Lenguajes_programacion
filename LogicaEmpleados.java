/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Conexion;
import Modelo.empleados;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 *
 * @author duily
 */
public class LogicaEmpleados {
    ArrayList<empleados> listaEmpleados = new ArrayList<>(); // creamos un array con los atributos de la clase empleados

    private Connection con = null;// creamos una variable conexion.
    private PreparedStatement sentencias;// creamos un objeto de sentencias
    private ResultSet rs;// creamos un objeto para traer los resultados de la consulta
    private empleados empleados;// hacemos la conexion con la clase empleados
    
    public ArrayList<empleados> obtenerListaEmpleados (){// hacemos un metodo que devuelva los valores en la lista de empleados
        return listaEmpleados;
    }

    
    public empleados iniciarSesion (String codigo, String contrasena){
        try{
            con = Conexion.getConnection();// traemos el metodo estatico getConnection y lo asignamos al objeto con
            sentencias = con.prepareStatement("select * from empleados where codigo = ? and contrasena = ?");
            sentencias.setString(1,codigo);
            sentencias.setString(2, contrasena);
            rs = sentencias.executeQuery();
            
            if (rs!=null){
                while(rs.next()){
                    empleados = new empleados();// creamos el objeto empleados
                    empleados.setNombre(rs.getString(1));
                    empleados.setCodigo(rs.getString(2));
                    empleados.setCargo(rs.getString(3));
                    empleados.setContrasena(rs.getString(4));
                    empleados.setAutorizado(rs.getString(5));
                    
                    listaEmpleados.add(empleados);
                }   
            }
        return empleados;
        }
        catch(SQLException e){
            System.out.println("error en la consulta de base de datos:" +e.getMessage());
        }
        return null;
    }
    public String validar (String codigo){
        try{
            
            con = Conexion.getConnection();// traemos el metodo estatico getConnection y lo asignamos al objeto con
            sentencias = con.prepareStatement("select codigo from empleados where codigo = ?");
            sentencias.setString(1,codigo);
            rs = sentencias.executeQuery();
            
            while(rs.next()){
                empleados = new empleados();// creamos el objeto empleados
                empleados.setCodigo(rs.getString(1));   
            }
            if(rs.getString(1).equals("")){
                empleados = new empleados();
                empleados.setCodigo("");  
            }
            return empleados.getCodigo();
        }
        catch(SQLException e){
            System.out.println("error en la consulta de base de datos:" +e.getMessage());
        }
        return null;
    }
            
    public void registro (String nombre, String cargo, String codigo,String contrasena){
        try{
            con = Conexion.getConnection();
            sentencias = con.prepareStatement("insert into empleados  values(?,?,?,?,?)");
            sentencias.setString(1,nombre);
            sentencias.setString(2,cargo);
            sentencias.setString(3,codigo);
            sentencias.setString(4,contrasena);
            sentencias.setString(5,"NO");
            sentencias.executeQuery();
        }
        
        catch(Exception e){
            System.out.println("Error en inserción de datos:"+e.getMessage());
        }
    }

}
