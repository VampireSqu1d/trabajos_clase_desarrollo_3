import javax.swing.*;
import java.awt.event.*;
import  java.sql.*;

public class ventana2 {

  public static void main(String[] args) {
      JFrame v = new JFrame("Conexion");
      JButton boton1 = new JButton("Conectar");
      JLabel etiqueta = new JLabel("No te haz conectado :(");
      JTextField host = new JTextField();
      JLabel etiHost = new JLabel("host");
      JTextField user = new JTextField();
      JLabel etiUser = new JLabel("user");
      JTextField base = new JTextField();
      JLabel etiBase = new JLabel("base");
      JPasswordField pass = new JPasswordField();
      JLabel etiPass = new  JLabel("password");



      boton1.setBounds(20, 20, 100, 30);
      etiqueta.setBounds(20, 65, 170, 30);
      host.setBounds(20, 105, 150, 30);
      etiHost.setBounds(180, 105, 150, 30);
      base.setBounds(20, 150, 150, 30);
      etiBase.setBounds(180, 150, 150, 30);
      user.setBounds(20, 195, 150, 30);
      etiUser.setBounds(180, 195, 150, 30);
      pass.setBounds(20, 235, 150, 30);
      etiPass.setBounds(180, 235, 150, 30);

      ActionListener listener = new ActionListener(){

        @Override
        public void actionPerformed(ActionEvent e){

          try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Nice driver bro");
		}
 		catch(ClassNotFoundException ex){
			System.out.println(ex);
		}

		try {
			Connection c = DriverManager.getConnection(
      "jdbc:mysql://" + host.getText() + "/" + base.getText(),
      user.getText() ,
      String.valueOf(pass.getPassword()));

			System.out.println("Nice connection bro");
      etiqueta.setText("anoma yastas connected bro");
		} catch(SQLException s){
      etiqueta.setText("Big off bro");
			System.out.println(s);
      JOptionPane.showMessageDialog(null, s);
		}
        }
      };

      boton1.addActionListener(listener);

      v.add(boton1);
      v.add(etiqueta);
      v.add(base);
      v.add(host);
      v.add(pass);
      v.add(user);
      v.add(etiBase);
      v.add(etiUser);
      v.add(etiPass);
      v.add(etiHost);

      v.setResizable(false);
      v.setSize(600,350);
      v.setLayout(null);
      v.setDefaultCloseOperation(v.EXIT_ON_CLOSE);
      v.setLocationRelativeTo(null);
      v.setVisible(true);

  }

}
