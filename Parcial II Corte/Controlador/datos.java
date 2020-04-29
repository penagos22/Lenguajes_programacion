/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author duily
 */
public class datos extends JFrame {
    public datos(JTable tabla, JTextField Mascota,JTextField Raza, JTextField Edad, JTextField Nombre_dueno,JTextField Celular){
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Mascota");
        modelo.addColumn("Raza");
        modelo.addColumn("Edad");
        modelo.addColumn("Nombre Dueño");
        modelo.addColumn("Celular");
        
        Object dato [] = new Object [5];
        dato[0]= Mascota;
        dato[1]=Raza;
        dato[1]=Edad;
        dato[1]=Nombre_dueno;
        dato[1]=Celular;
        modelo.addRow(dato);
        tabla.setModel(modelo);
    }

    public datos(JTable tabla) {
    }
            
            
        }
