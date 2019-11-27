import javax.swing.*;
import javax.swing.table.*;

public class ventana3 {
  public static void main(String[] args) {
      JFrame v = new JFrame("ventana 3");

      DefaultTableModel modelo = new DefaultTableModel();
      modelo.addColumn("studentid");
      modelo.addColumn("firstname");
      modelo.addColumn("lastname");
      modelo.addColumn("class");
      modelo.addColumn("age");

      JTable tabla1 = new JTable(modelo);
      JScrollPane scroll = new  JScrollPane(tabla1);

      tabla1.setBounds(20,20,350,200);
      scroll.setBounds(20,20,350,200);

      String[] alumno1 = {"1", "German", "Fernandez", "Factorial", "21"};
      String[] alumno2 = {"2", "Kevin", "Sandoval", "Factorial", "22"};
      modelo.addRow(alumno1);
      modelo.addRow(alumno2);

      v.add(scroll);


      v.setResizable(false);
      v.setSize(600,350);
      v.setLayout(null);
      v.setDefaultCloseOperation(v.EXIT_ON_CLOSE);
      v.setLocationRelativeTo(null);
      v.setVisible(true);

  }


}
