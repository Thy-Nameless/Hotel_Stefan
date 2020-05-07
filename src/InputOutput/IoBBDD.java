package InputOutput;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class IoBBDD {
	private Connection connection;

	public IoBBDD() {
		connection = null;
	}

	private void conectar() {
		try {
			connection = DriverManager.getConnection(
					"jdbc:mysql://hotel-stefan.cciptjamgvdf.us-east-1.rds.amazonaws.com:3306/administracion_hotel_stefan?useSSL=false",
					"eclipse", "Hotel2020!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void desconectar() {
		try {
			connection.close();
			connection = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void registrarLogIn(String usuario, String password, String time, boolean admin) {
		conectar();

		PreparedStatement ps = null;

		try {
			ps = connection.prepareStatement("INSERT INTO seguimiento_logIn VALUES (?,?,?,?);");
			ps.setString(1, usuario);
			ps.setString(2, password);
			ps.setString(3, time);
			ps.setBoolean(4, admin);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		desconectar();
	}
}
