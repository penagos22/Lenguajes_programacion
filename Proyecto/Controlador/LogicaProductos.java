/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;
import Modelo.*;
import Frame.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author duily
 */
public class LogicaProductos {
    ArrayList<Productos> listaProductos = new ArrayList<>();
    private Connection con = null;// creamos una variable conexion.
    private PreparedStatement sentencias;// creamos un objeto de sentencias
    private ResultSet rs;// creamos un objeto para traer los resultados de la consulta
    private Productos productos;// hacemos la conexion con la clase empleados
    
    
    public ArrayList<Productos> listaProductos (){// hacemos un metodo que devuelva los valores en la lista de empleados
        return listaProductos;
    }  
    

    public ArrayList<Productos> LlenarTabla(){
        int registros = 0;
        try {
            con = Conexion.getConnection();// traemos el metodo estatico getConnection y lo asignamos al objeto con
            rs = sentencias.executeQuery();
            while(rs.next())
            {
               productos = new Productos();
               productos.setIdProducto(rs.getInt(1));
               productos.setDescripcion(rs.getString(2));
               productos.setPrecio(rs.getDouble(3));
               productos.setCategoria(rs.getString(4));
               
               
               System.out.println(registros);
               registros= registros+1;
            }
            listaProductos.add(productos);
            
            
        } catch (Exception e) {
        }
        return listaProductos;   
    }

        
}
    

