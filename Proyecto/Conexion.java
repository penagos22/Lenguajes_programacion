/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
import java.sql.*;

/**
 *
 * @author duily
 */
public class Conexion {
    static Connection c = null; // un atributo static quiere decir que esa variable le pertenece a la clase y no se puede instanciar con un objeto, para llamar esta variable seria Conexion.c
    public static final String URL = "jdbc:sqlserver://LAPTOP-JPU9GHOH:1433;databaseName=elejecutivo"; // aqui establecemos la conexión con la base de datos y se usta public static final por que es una constante.
    public static final String USERNAME = "usuariosql"; //aqui establecimos el usuario para conectarnos
    public static final String PASSWORD = "1234"; // aqui establecimos la contraseña de conexion
    public static Connection getConnection (){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");// aqui lo que estamos es cargando el driver de la conexion JDBC, cada base de datos tiene su propio nombre de clase para conectar.
            c = DriverManager.getConnection(URL,USERNAME,PASSWORD);// aquilo que hacemos con el drivermanager es darle los parametro de coneccion.
            System.out.println("conexion exitosa");
        }
        catch (ClassNotFoundException | SQLException e){
            System.out.println("No se conecto adecuadamente a la base de datos:( ");
            e.printStackTrace();
        }
      return c;  
    }
    public static void desconectarBd(){
        try{
            if(c!= null){
                c.commit();// el commit funciona para salvar todos los cambios antes que se cierre la conexion a bases de datos
                c.close();// cierra la conexion con la base de datos
            }
            c=null;
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void desconectarSentencia(PreparedStatement sentencia)throws SQLException{
        if(sentencia != null){
            sentencia.close();
        }
        sentencia = null;
    }
}
