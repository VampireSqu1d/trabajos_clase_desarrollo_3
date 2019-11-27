import java.sql.*;

public class Conexion {

	public static void main(String[] args){
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Nice driver bro");
		}
 		catch(ClassNotFoundException ex){
			System.out.println(ex);
		}

		try {
			Connection c = DriverManager.getConnection("jdbc:mysql://localhost/school", "vamp" , "Vampire1");			
			System.out.println("Nice connection bro");
		} catch(SQLException s){
			System.out.println(s);
		}

		
	}


}
