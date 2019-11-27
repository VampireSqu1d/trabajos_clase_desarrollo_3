import java.sql.*;


public class Conexion2 {

	public static void main(String[] args){}
		
		

	public static Connection miconexion(){
		String URL = "localhost";
		String db = "school";
		String user = "vamp";
		String pass = "Vampire1";

		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Nice driver bro");
		}
 		catch(ClassNotFoundException ex){
			System.out.println(ex);
		}

		try {
			Connection c = DriverManager.getConnection("jdbc:mysql://" + URL + "/" + db, user , pass);			
			System.out.println("Nice connection bro");
			return c;
		} catch(SQLException s){
			System.out.println(s);
		}

		return null;
	}
	
}
