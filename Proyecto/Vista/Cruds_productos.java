/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frame;
import Modelo.*;
import Controlador.*;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author duily
 */
public class Cruds_productos extends javax.swing.JPanel {
    Conexion  coneccion = new Conexion();
    Connection cn = coneccion.getConnection();
    private Productos productos;
    private Productos listaProductos;
    LogicaProductos logica2 = new LogicaProductos();
    private PreparedStatement sentencias;
    DecimalFormat formato = new DecimalFormat("###,###");
    
    

    /**
     * Creates new form cruds_menus
     */
    public Cruds_productos() {
        initComponents();
        cap_codigo.setEditable(false);
        cap_precio2.setEditable(false);
        
        mostrar();
        mostrarmenu();

        
    }
    
    

    


    public void filtrarDatos(String valor){
        String [] titulos={"Codigo","Descripcion","Precio","Categoria"};
        String [] registros = new String[4];
        DefaultTableModel modelo = new DefaultTableModel(null,titulos);
        String sql = "select * from productos where descripcion like '%"+valor+"%'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                registros[0] = rs.getString("idProducto");
                registros[1] = rs.getString("descripcion");
                registros[2] = rs.getString("precio");
                registros[3] = rs.getString("categoria");
                
                modelo.addRow(registros);
            }
            
            jtProductos.setModel(modelo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error");
        }
        
    }
        public void filtrarDatos2(String valor){
        String [] titulos={"Codigo","Descripcion","Precio","Categoria"};
        String [] registros = new String[4];
        DefaultTableModel modelo = new DefaultTableModel(null,titulos);
        String sql = "select * from productos where categoria = '"+valor+"'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                registros[0] = rs.getString("idProducto");
                registros[1] = rs.getString("descripcion");
                registros[2] = rs.getString("precio");
                registros[3] = rs.getString("categoria");
                
                modelo.addRow(registros);
            }
            
            jtProductos.setModel(modelo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error");
        }
        
    }
    
    public void  mostrar(){
        
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Codigo");
        modelo.addColumn("Descripción");
        modelo.addColumn("Precio");
        modelo.addColumn("Categoria");
        
        try {
            CallableStatement cmd = cn.prepareCall("{CALL lista_productos}");
            ResultSet rs = cmd.executeQuery();
            while(rs.next()){
                Object [] registros = new Object[4];
                for (int i = 0; i < 4; i++) {
                    registros[i]=rs.getString(i+1);   
                }
                modelo.addRow(registros);
            }
            jtProductos.setModel(modelo);
        } catch (Exception e) {
        }

        }
        
        
    public void anadir(String descripcion, double precio,String categoria){
        String insertar = "exec insertarproductos  ?,?,?";
        try{
            cn = Conexion.getConnection();
            sentencias = cn.prepareCall(insertar);
            sentencias.setString(1,descripcion);
            sentencias.setDouble(2,precio);
            sentencias.setString(3,categoria);
            int generar =  sentencias.executeUpdate();
            
        }
        catch(Exception e){
            System.out.println("Error en inserción de datos:"+e.getMessage());
        }
        mostrar();
    }
    
    public void delete(int codigo){
        String eliminar = "exec eliminarproductos  ?";
        try {
            cn = Conexion.getConnection();
            sentencias = cn.prepareCall(eliminar);
            sentencias.setInt(1, codigo);
            int execute = sentencias.executeUpdate();
        } catch (Exception e) {
        }
        mostrar();
        
    }
    
    public void modify(int codigo,String descripcion, double precio,String categoria){
        String modificar = "exec modificarproductos ?,?,?,?";
        try {
            cn = Conexion.getConnection();
            sentencias = cn.prepareCall(modificar);
            sentencias.setInt(1, codigo);
            sentencias.setString(2, descripcion);
            sentencias.setDouble(3, precio);
            sentencias.setString(4, categoria);
            int generar = sentencias.executeUpdate();
        } catch (Exception e) {
        }
        mostrar();
    }
    
    
    public void  mostrarmenu(){
        
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Nombre Menu ");
        modelo.addColumn("Codigo Producto");
        modelo.addColumn("Descripcion Porducto");
        modelo.addColumn("Cantidad");
        modelo.addColumn("precio");
        
        try {
            CallableStatement cmd = cn.prepareCall("{CALL listamenus}");
            ResultSet rs = cmd.executeQuery();
            while(rs.next()){
                Object [] registros = new Object[5];
                for (int i = 0; i < 5; i++) {
                    registros[i]=rs.getString(i+1);   
                }
                modelo.addRow(registros);
            }
            jTmenus.setModel(modelo);
            int contar = jTmenus.getRowCount();
            int suma = 0;
            for (int i = 0; i < contar; i++) {
                suma = suma + Integer.parseInt(jTmenus.getValueAt(i, 4).toString());
            }
            totalmenu.setText("El precio total del menus es: " + Integer.toString(suma));  
        } catch (Exception e) {
        }
        
    }
    public void agregaralMenu(String nombremenu, int codigoproducto, String nombreproducto, int cantidad, double precioproducto){
        String insertar = "exec insertarmenus  ?,?,?,?,?";
        try{
            cn = Conexion.getConnection();
            sentencias = cn.prepareCall(insertar);
            sentencias.setString(1,nombremenu);
            sentencias.setInt(2,codigoproducto);
            sentencias.setString(3,nombreproducto);
            sentencias.setInt(4,cantidad);
            sentencias.setDouble(5,precioproducto);
            int generar =  sentencias.executeUpdate();
            
        }
        catch(Exception e){
            System.out.println("Error en inserción de datos:"+e.getMessage());
        }
        mostrarmenu();
    }


    
    


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtProductos = new javax.swing.JTable();
        txtbuscar = new javax.swing.JTextField();
        lbaelbusqueda = new javax.swing.JLabel();
        lbaelbusqueda1 = new javax.swing.JLabel();
        lbaelbusqueda2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTmenus = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cap_codigo = new javax.swing.JTextField();
        jCom_Categoria = new javax.swing.JComboBox();
        Insertar = new javax.swing.JButton();
        Eliminar = new javax.swing.JButton();
        Modificar = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        cap_desc = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        cap_precio = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jcomMenu = new javax.swing.JComboBox();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        cap_precio2 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        cantidad = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        totalmenu = new javax.swing.JLabel();

        jMenuItem1.setText("jMenuItem1");

        setPreferredSize(new java.awt.Dimension(960, 410));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jtProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtProductosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtProductos);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 110, 370, 290));

        txtbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbuscarActionPerformed(evt);
            }
        });
        txtbuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbuscarKeyReleased(evt);
            }
        });
        add(txtbuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 20, 260, 30));

        lbaelbusqueda.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        lbaelbusqueda.setForeground(new java.awt.Color(71, 82, 94));
        lbaelbusqueda.setText("Categorias");
        add(lbaelbusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 60, 120, 40));

        lbaelbusqueda1.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        lbaelbusqueda1.setForeground(new java.awt.Color(71, 82, 94));
        lbaelbusqueda1.setText("Menu del día:");
        add(lbaelbusqueda1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 90, 30));

        lbaelbusqueda2.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        lbaelbusqueda2.setForeground(new java.awt.Color(71, 82, 94));
        lbaelbusqueda2.setText("Busqueda producto:");
        add(lbaelbusqueda2, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 10, 120, 40));

        jTmenus.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(jTmenus);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 440, 140));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setBackground(new java.awt.Color(153, 153, 153));
        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(68, 84, 106));
        jLabel4.setText("Gestión productos");
        jLabel4.setPreferredSize(new java.awt.Dimension(5, 20));
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 360, 40));
        jLabel4.getAccessibleContext().setAccessibleName("Añadir productos");

        jLabel6.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(71, 82, 94));
        jLabel6.setText("Categoria:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 130, 20));

        jLabel7.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(71, 82, 94));
        jLabel7.setText("Codigo:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 160, 20));

        cap_codigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cap_codigoActionPerformed(evt);
            }
        });
        jPanel1.add(cap_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 50, 140, 20));

        jCom_Categoria.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "principio", "protenina", "acompañamiento", "bebida", "sopas" }));
        jCom_Categoria.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCom_CategoriaItemStateChanged(evt);
            }
        });
        jPanel1.add(jCom_Categoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 140, 140, -1));

        Insertar.setText("Insertar");
        Insertar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                InsertarMouseClicked(evt);
            }
        });
        jPanel1.add(Insertar, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 50, 90, -1));

        Eliminar.setText("Eliminar");
        Eliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EliminarMouseClicked(evt);
            }
        });
        jPanel1.add(Eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 90, 90, -1));

        Modificar.setText("Modificar");
        Modificar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ModificarMouseClicked(evt);
            }
        });
        jPanel1.add(Modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 130, 90, -1));

        jLabel10.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(71, 82, 94));
        jLabel10.setText("Descripción");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 160, 20));

        cap_desc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cap_descActionPerformed(evt);
            }
        });
        cap_desc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cap_descKeyReleased(evt);
            }
        });
        jPanel1.add(cap_desc, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 80, 140, -1));

        jLabel11.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(71, 82, 94));
        jLabel11.setText("Precio:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 160, 20));

        cap_precio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cap_precioActionPerformed(evt);
            }
        });
        jPanel1.add(cap_precio, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 110, 140, -1));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 610, 180));

        jLabel5.setBackground(new java.awt.Color(153, 153, 153));
        jLabel5.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(153, 153, 153));
        jLabel5.setText("Gestión de Menús");
        jLabel5.setPreferredSize(new java.awt.Dimension(10, 31));
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 440, 40));

        jcomMenu.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Menu 1", "Menu 2", "Menu 3", "Menu 4" }));
        add(jcomMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, 120, 30));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Todos", "principio", "protenina", "acompañamiento", "bebida", "sopas" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 70, 260, -1));

        jLabel8.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(71, 82, 94));
        jLabel8.setText("Precio Total ");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 100, 100, 20));

        cap_precio2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cap_precio2ActionPerformed(evt);
            }
        });
        add(cap_precio2, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 120, 80, 20));

        jLabel13.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(71, 82, 94));
        jLabel13.setText("Cantidad");
        add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 100, 60, 20));

        cantidad.setText("1");
        cantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cantidadActionPerformed(evt);
            }
        });
        cantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cantidadKeyReleased(evt);
            }
        });
        add(cantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 120, 60, 20));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/logook.png"))); // NOI18N
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 110, 30, 30));

        totalmenu.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        add(totalmenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 70, 220, 20));
    }// </editor-fold>//GEN-END:initComponents

    private void txtbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbuscarActionPerformed

    private void txtbuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarKeyReleased
        cap_desc.setText(null);
        cap_codigo.setText(null);
        cap_precio.setText(null);
        filtrarDatos(txtbuscar.getText());
    }//GEN-LAST:event_txtbuscarKeyReleased

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        if(jComboBox1.getSelectedItem()!= "Todos"){
            filtrarDatos2((String) jComboBox1.getSelectedItem());
        }
        else{
            mostrar();
        }
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void cap_precio2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cap_precio2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cap_precio2ActionPerformed

    private void cantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cantidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cantidadActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        Integer codigoProducto = Integer.parseInt(cap_codigo.getText());
        String descripcion = cap_desc.getText();
        Integer unidades = Integer.parseInt(cantidad.getText());
        String nombremenu = (String) jcomMenu.getSelectedItem();
        Double precioProducto = Double.parseDouble(cap_precio.getText()) * unidades;
        agregaralMenu(nombremenu, codigoProducto, descripcion, unidades,precioProducto);
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cantidadKeyReleased
        Integer unidades = Integer.parseInt(cantidad.getText());
        Double precioProducto = Double.parseDouble(cap_precio.getText());
        cap_precio2.setText(String.valueOf(unidades*precioProducto));
    }//GEN-LAST:event_cantidadKeyReleased

    private void cap_precioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cap_precioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cap_precioActionPerformed

    private void cap_descKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cap_descKeyReleased
        txtbuscar.setText(null);
        String descrip = cap_desc.getText();
        filtrarDatos(descrip);
        cap_codigo.setText(null);
    }//GEN-LAST:event_cap_descKeyReleased

    private void cap_descActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cap_descActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cap_descActionPerformed

    private void ModificarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ModificarMouseClicked
        Integer codigo = Integer.parseInt(cap_codigo.getText());
        String descripcion = cap_desc.getText();
        Double precio = Double.parseDouble(cap_precio.getText());
        String categoria = (String) jCom_Categoria.getSelectedItem();
        modify(codigo, descripcion, precio, categoria);
    }//GEN-LAST:event_ModificarMouseClicked

    private void EliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EliminarMouseClicked
        int codigo = Integer.parseInt(cap_codigo.getText());
        delete(codigo);
        cap_desc.setText(null);
        cap_codigo.setText(null);
        cap_precio.setText(null);

    }//GEN-LAST:event_EliminarMouseClicked

    private void InsertarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_InsertarMouseClicked
        String descripcion = cap_desc.getText();
        Double precio = Double.parseDouble(cap_precio.getText());
        String categoria = (String) jCom_Categoria.getSelectedItem();
        anadir(descripcion, precio, categoria);
    }//GEN-LAST:event_InsertarMouseClicked

    private void jCom_CategoriaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCom_CategoriaItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jCom_CategoriaItemStateChanged

    private void cap_codigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cap_codigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cap_codigoActionPerformed

    private void jtProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtProductosMouseClicked
        int fila =  jtProductos.rowAtPoint(evt.getPoint());
        cap_codigo.setText(String.valueOf(jtProductos.getValueAt(fila, 0)));
        cap_desc.setText(String.valueOf(jtProductos.getValueAt(fila, 1)));
        cap_precio.setText(String.valueOf(jtProductos.getValueAt(fila, 2)));
        jCom_Categoria.setSelectedItem(jtProductos.getValueAt(fila, 3));
        Integer unidades = Integer.parseInt(cantidad.getText());
        Double precioProducto = Double.parseDouble(cap_precio.getText()) * unidades;
        cap_precio2.setText(String.valueOf(unidades*precioProducto));

    }//GEN-LAST:event_jtProductosMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Eliminar;
    private javax.swing.JButton Insertar;
    private javax.swing.JButton Modificar;
    private javax.swing.JTextField cantidad;
    private javax.swing.JTextField cap_codigo;
    private javax.swing.JTextField cap_desc;
    private javax.swing.JTextField cap_precio;
    private javax.swing.JTextField cap_precio2;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jCom_Categoria;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTmenus;
    private javax.swing.JComboBox jcomMenu;
    private javax.swing.JTable jtProductos;
    private javax.swing.JLabel lbaelbusqueda;
    private javax.swing.JLabel lbaelbusqueda1;
    private javax.swing.JLabel lbaelbusqueda2;
    private javax.swing.JLabel totalmenu;
    private javax.swing.JTextField txtbuscar;
    // End of variables declaration//GEN-END:variables





}


