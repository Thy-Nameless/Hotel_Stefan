package InputOutput;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ioReserva {
	private Connection con;
	
	public ioReserva() {
		con = null;
	}
	
	public void conectar() {
		try {
	    	 String sURL = "jdbc:mysql://hotel-stefan.cciptjamgvdf.us-east-1.rds.amazonaws.com:3306/administracion_hotel_stefan?useSSL=false";
	    	 String user = "eclipse";
	    	 String passwd = "Hotel2020!";
	         con = DriverManager.getConnection(sURL,user,passwd);
	        
	        System.out.println("conectado bien");
	    } catch (SQLException e) {
	    	e.printStackTrace();
	    }
	}
	
	private void desconectar() {
		try {
        	con.close();
        	con = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	public void obtener() {
		 conectar();
		 PreparedStatement pt = null;
 		 ResultSet rs = null;
         try {
			pt = con.prepareStatement("SELECT * FROM seguimiento_logIn");
			rs = pt.executeQuery();
			 if (rs.next()){
				 System.out.println(rs.getString("usuario"));
			 }
			rs.close();
		pt.close();
		desconectar();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
	}

}
