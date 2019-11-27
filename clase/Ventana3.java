import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.sql.*;

public class Ventana3 {

 public void mostrarTabla(DefaultTableModel modelo) {
  modelo.setRowCount(0);
  Conexion2 conn = new Conexion2();
    
  Connection c = conn.miconexion();
  if (c != null) {
   try {
    Statement st = c.createStatement();
    ResultSet rs = st.executeQuery("SELECT * FROM students");

    while(rs.next()) {
     Object[] tablaSQL = new Object[5];

     for(int i=0; i<5; i++) {
     tablaSQL[i] = rs.getObject(i+1);
    }

    modelo.addRow(tablaSQL);
   }
   c.close();
   } catch(SQLException se) {
    JOptionPane.showMessageDialog(null, se);
   }
  }
 }

 public static void main(String[] args) {
 
  Ventana3 v3 = new Ventana3();
  JFrame v = new JFrame("Ventana 3");

  DefaultTableModel modelo = new DefaultTableModel();
  modelo.addColumn("studentid");
  modelo.addColumn("firstname");
  modelo.addColumn("lastname");
  modelo.addColumn("class");
  modelo.addColumn("age");
  JTable tabla1 = new JTable(modelo);
  JScrollPane scroll1 = new JScrollPane(tabla1);
  JButton boton1 = new JButton("Mostrar");
  JButton boton2 = new JButton("Select");
  JButton boton3 = new JButton("Insert");
  JButton boton4 = new JButton("Delete");

  boton1.setBounds(20,20,70,25);
  boton2.setBounds(90,20,70,25);
  boton3.setBounds(160,20,70,25);
  boton4.setBounds(230,20,60,25);
  tabla1.setBounds(20,55,250,180);
  scroll1.setBounds(20,55,250,180);

  ActionListener listener = new ActionListener() {
   @Override
   public void actionPerformed(ActionEvent e) {
    if(e.getSource() == boton1) {
     v3.mostrarTabla(modelo);
    } //fin de boton1

   if(e.getSource() == boton4) {
    Object id = "";
    boolean status_id = true;

    try {
     int linea_id = tabla1.getSelectedRow();
     id = tabla1.getValueAt(linea_id, 0);
    } catch(Exception e13) {
     status_id = false;
     JOptionPane.showMessageDialog(null, "No hay dato seleccionado");
    }

    if (status_id) {
     int confirmar = JOptionPane.showConfirmDialog(null, "Estas segurito, segurito?", "Confirmar", JOptionPane.YES_NO_OPTION);
     if(confirmar == 0) {
      //JOptionPane.showMessageDialog(null, "Si lo vas a eliminar");
      Conexion2 connn = new Conexion2();
      Connection c = connn.miconexion();

      if(c != null) {
       try {
        Statement std = c.createStatement();
        std.executeUpdate("DELETE FROM students WHERE studentid="+ id);
        v3.mostrarTabla(modelo);
        c.close();
        JOptionPane.showMessageDialog(null, "Borraste la informacion, chale!!");
        
       } catch(SQLException se9) {
        JOptionPane.showMessageDialog(null, se9);
       }
      }
     }
    }
   } //fin boton4

   if(e.getSource() == boton3) {
    //JOptionPane.showMessageDialog(null, "Clic en insertar");
    JDialog jdi = new JDialog(v, "Insertar");

    JLabel e2 = new JLabel("Firstname : ");
    JTextField t2 = new JTextField();
    JLabel e3 = new JLabel("Lastname : ");
    JTextField t3 = new JTextField();
   
    JRadioButton r1 = new JRadioButton("First", true);
    r1.setActionCommand("First");
    JRadioButton r2 = new JRadioButton("Second", false);
    r2.setActionCommand("Second");
    JRadioButton r3 = new JRadioButton("Third", false);
    r3.setActionCommand("Third");

    ButtonGroup bg = new ButtonGroup();
    bg.add(r1);
    bg.add(r2);
    bg.add(r3);

    JLabel e5 = new JLabel("Age : ");
    String[] edades = {"5", "6", "7"};
    JComboBox<String> combo = new JComboBox<>(edades);
    combo.setBounds(125,140,100,25);
    combo.setSelectedItem("5");

    JButton bt = new JButton("Insertar");

    e2.setBounds(20,20,100,25);
    t2.setBounds(125,20,100,25);
    e3.setBounds(20,60,100,25);
    t3.setBounds(125,60,100,25);
    r1.setBounds(20,100,100,25);
    r2.setBounds(125,100,100,25);
    r3.setBounds(230,100,100,25);
    e5.setBounds(20,140,100,25);
    bt.setBounds(125,200,100,25);
 
    bt.addActionListener(new ActionListener() {
     public void actionPerformed(ActionEvent ae6) {
      String nombre = t2.getText();
      String apellido = t3.getText();

      if (nombre.equals("") || apellido.equals("")) {
       JOptionPane.showMessageDialog(null, "No se aceptan datos nulos!");
      } else {
       Conexion2 con = new Conexion2();
       Connection c = con.miconexion();
      
       if (c != null) {
        String clase = bg.getSelection().getActionCommand();
        String edad = (String) combo.getSelectedItem();

        try {
         Statement st = c.createStatement();
         st.executeUpdate("INSERT INTO students (firstname, lastname, class, age) VALUES ('"+ nombre +"', '"+ apellido +"', '"+ clase +"', "+ edad +")");
         c.close();
         JOptionPane.showMessageDialog(null, "Dato agregado correctamente xD");
         t2.setText("");
         t3.setText("");
         r1.setSelected(true);
         combo.setSelectedItem("5");
        } catch(SQLException se10) {
         JOptionPane.showMessageDialog(null, se10);
        }
       }
	v3.mostrarTabla(modelo);
       //JOptionPane.showMessageDialog(null, "Todo bien");
      }
     }
    });

    

    /*jdi.addWindowListener(new WindowAdapter() {
     public void WindowClosing(WindowEvent we7) {
      v3.mostrarTabla(modelo);
     }
    });*/

    jdi.add(e2);
    jdi.add(t2);
    jdi.add(e3);
    jdi.add(t3);
    jdi.add(r1);
    jdi.add(r2);
    jdi.add(r3);
    jdi.add(combo);
    jdi.add(e5);
    jdi.add(bt);

    jdi.setLayout(null);
    jdi.setResizable(false);
    jdi.setSize(300,300);
    jdi.setModal(true);
    jdi.setLocationRelativeTo(null);
    jdi.setDefaultCloseOperation(jdi.DISPOSE_ON_CLOSE);
    jdi.setVisible(true);
   }

   if(e.getSource() == boton2) {

     int linea = tabla1.getSelectedRow();

     if(linea != -1) {
      JDialog d1 = new JDialog(v, "Update");

      d1.addWindowListener(new WindowAdapter() {
       public void windowClosing(WindowEvent we) {
        //JOptionPane.showMessageDialog(null, "Cerraste el JDialog");
	v3.mostrarTabla(modelo);
       }
      });
    
      JLabel e1 = new JLabel("Studentid : ");
      JTextField t1 = new JTextField(tabla1.getValueAt(linea,0).toString());
      JLabel e2 = new JLabel("Firstname : ");
      JTextField t2 = new JTextField(tabla1.getValueAt(linea,1).toString());
      JLabel e3 = new JLabel("Lastname : ");
      JTextField t3 = new JTextField(tabla1.getValueAt(linea,2).toString());
      //JLabel e4 = new JLabel("Class : ");
      //JTextField t4 = new JTextField(tabla1.getValueAt(linea,3).toString());

      String radioBooleano = tabla1.getValueAt(linea,3).toString();
      boolean primero = false, segundo = false, tercero = false;

      if(radioBooleano.equals("First")) {
       primero = true;
      }
      if(radioBooleano.equals("Second")) {
       segundo = true;
      }
      if(radioBooleano.equals("Third")) {
       tercero = true;
      }
      
      JRadioButton r1 = new JRadioButton("First", primero);
      r1.setActionCommand("First");
      JRadioButton r2 = new JRadioButton("Second", segundo);
      r2.setActionCommand("Second");
      JRadioButton r3 = new JRadioButton("Third", tercero);
      r3.setActionCommand("Third");
      
      ButtonGroup bg = new ButtonGroup();
      bg.add(r1);
      bg.add(r2);
      bg.add(r3);
      
      JLabel e5 = new JLabel("Age : ");
      //JTextField t5 = new JTextField(tabla1.getValueAt(linea,4).toString());

      String[] edades = {"5", "6", "7"};
      JComboBox<String> combo = new JComboBox<>(edades);
      combo.setBounds(125,180,100,25);
      combo.setSelectedItem(tabla1.getValueAt(linea,4).toString());

      JButton b4 = new JButton("Update");
      
      e1.setBounds(20,20,100,25);
      t1.setBounds(125,20,100,25);
      e2.setBounds(20,60,100,25);
      t2.setBounds(125,60,100,25);
      e3.setBounds(20,100,100,25);
      t3.setBounds(125,100,100,25);
      //e4.setBounds(20,140,100,25);
      //t4.setBounds(125,140,100,25);
      r1.setBounds(20,140,100,25);
      r2.setBounds(125,140,100,25);
      r3.setBounds(230,140,100,25);
      e5.setBounds(20,180,100,25);
      //t5.setBounds(125,180,100,25);
      b4.setBounds(125,220,100,25);

      t1.setEditable(false);

      b4.addActionListener(new ActionListener() {
       public void actionPerformed(ActionEvent ae1) {
        Conexion2 conn = new Conexion2();
        Connection c2 = conn.miconexion();
        String radioOpcion = bg.getSelection().getActionCommand();
        String comboOpcion = (String) combo.getSelectedItem();
        if(c2 != null) {
         try {
          Statement st2 = c2.createStatement();
          st2.executeUpdate("UPDATE students SET firstname='"+t2.getText()+"', lastname = '"+t3.getText()+"', class = '"+radioOpcion+"', age = '"+comboOpcion+"' WHERE studentid = "+t1.getText());
          //v3.mostrarTabla(modelo);
          c2.close();
          JOptionPane.showMessageDialog(null, "Actualizacion correcta xD");
         } catch(Exception ex10) {
          JOptionPane.showMessageDialog(null, ex10);
         }
        } //fin del if c2

        //JOptionPane.showMessageDialog(null, "Me diste clic JAJAJA");
       }
      });

      d1.add(e1);
      d1.add(t1);
      d1.add(e2);
      d1.add(t2);
      d1.add(e3);
      d1.add(t3);
      //d1.add(e4);
      //d1.add(t4);
      d1.add(r1);
      d1.add(r2);
      d1.add(r3);
      d1.add(e5);
      //d1.add(t5);
      d1.add(combo);
      d1.add(b4);

      d1.setLayout(null);
      d1.setResizable(false);
      d1.setSize(300,300);
      d1.setModal(true);
      d1.setLocationRelativeTo(null);
      d1.setDefaultCloseOperation(d1.DISPOSE_ON_CLOSE);
      d1.setVisible(true);
     } else {
      JOptionPane.showMessageDialog(null, "No has seleccionado un dato! :(");
     }
     
    /*try {
     int linea = tabla1.getSelectedRow();
     JOptionPane.showMessageDialog(null, tabla1.getValueAt(linea,1) + ", " + tabla1.getValueAt(linea,2) + ", " + tabla1.getValueAt(linea,3) + ", " + tabla1.getValueAt(linea,4));
    } catch(Exception ex2) {
     JOptionPane.showMessageDialog(null, "No has seleccionado un dato! :(");
    }*/
    
   }
    
   }
  };

  boton1.addActionListener(listener);
  boton2.addActionListener(listener);
  boton3.addActionListener(listener);
  boton4.addActionListener(listener);

  v.add(boton1);
  v.add(boton2);
  v.add(boton3);
  v.add(boton4);
  v.add(scroll1);

  v.setLayout(null);
  v.setResizable(false);
  v.setSize(300,300);
  v.setLocationRelativeTo(null);
  v.setDefaultCloseOperation(v.EXIT_ON_CLOSE);
  v.setVisible(true);

 }
}
