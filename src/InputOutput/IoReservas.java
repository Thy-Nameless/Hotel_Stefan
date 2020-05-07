package InputOutput;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import estaticos.Reserva;
import estaticos.Usuario;

public class IoReservas {
	private Connection con;
	public IoReservas() {
		con = null;
	}
	
	public void conectar() {
        try {
        	String sURL = "jdbc:mysql://hotel-stefan.cciptjamgvdf.us-east-1.rds.amazonaws.com:3306/administracion_hotel_stefan?useSSL=false";
	       	 String user = "eclipse";
	       	 String passwd = "Hotel2020!";
	       	con = DriverManager.getConnection(sURL,user,passwd);
            System.out.println("conectado");
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
    
	public String devolverArrayReservasString(int dia, int mes, int ano) {
    	conectar();
    	String reservas = null;
    	String usuario;String nombreReserva; String apellidosReserva; String fechaEntrada;
		String fechaSalida; String tipoHabitacion; String regimen; String sexo; double precio; int numeroNoches;
		PreparedStatement pt = null;
		ResultSet rs = null;
		
		try {
			pt = con.prepareStatement("SELECT * FROM reserva");
			
			rs = pt.executeQuery();
			while (rs.next()){
				usuario = rs.getString("usuario");
				nombreReserva = rs.getString("nombreReserva");
				apellidosReserva = rs.getString("apellidoReserva");
				fechaEntrada = rs.getString("fechaEntrada");
				fechaSalida = rs.getString("fechaSalida");
				regimen = rs.getString("regimen");
				sexo = rs.getString("sexo");
				tipoHabitacion = rs.getString("tipoHabitacion");
				precio = rs.getInt("precio");
				numeroNoches = rs.getInt("numeroNoches");
				String[] parts = fechaEntrada.split("/");
				if (ano > Integer.parseInt(parts[2]) || mes > Integer.parseInt(parts[1]) || dia >= Integer.parseInt(parts[0])) {
				Reserva res = new Reserva(usuario, nombreReserva, apellidosReserva, fechaEntrada, fechaSalida, tipoHabitacion, regimen, sexo, precio, numeroNoches);
				reservas += res.toString() + "\n";
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		desconectar();
		return reservas;
    }
    
	public ArrayList<Usuario> devolverUsuarios() {
    	conectar();
    	String usuario;String password; int booleano;
    	ArrayList<Usuario> usus = new ArrayList<Usuario>();
    	
		PreparedStatement pt = null;
		ResultSet rs = null;
		Usuario usu = null;
		
		try {
			pt = con.prepareStatement("SELECT * FROM usuario");
			
			rs = pt.executeQuery();
			while (rs.next()){
				usuario = rs.getString("usuario");
				password = rs.getString("password");
				booleano = rs.getInt("esAdmin");
				if (booleano == 1) {
					usu = new Usuario(usuario, password, true);
				} else {
					usu = new Usuario(usuario, password, false);
				}
				usus.add(usu);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		desconectar();
		return usus;
    }
	
	public void registrarUsuario(Usuario usu) {
        conectar();

        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement("INSERT INTO reserva VALUES (?,?,?);");
            ps.setString(1, usu.getNombreUsuario());
            ps.setString(2, usu.getPassword());
            if (usu.isEsAdmin()) {
            	ps.setInt(3, 1);
            } else {
            	ps.setInt(3, 0);
            }
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
	
    public ArrayList<Reserva> devolverReservas(int dia, int mes, int ano) {
    	ArrayList<Reserva> reservas = new ArrayList<Reserva>();
    	String usuario;String nombreReserva; String apellidosReserva; String fechaEntrada;
		String fechaSalida; String tipoHabitacion; String regimen; String sexo; double precio; int numeroNoches;
		conectar();
		PreparedStatement pt = null;
		ResultSet rs = null;
		
		try {
			pt = con.prepareStatement("SELECT * FROM reserva");
			
			rs = pt.executeQuery();
			if (rs.next()){
				usuario = rs.getString("usuario");
				nombreReserva = rs.getString("nombreReserva");
				apellidosReserva = rs.getString("apellidoReserva");
				fechaEntrada = rs.getString("fechaEntrada");
				fechaSalida = rs.getString("fechaSalida");
				regimen = rs.getString("regimen");
				sexo = rs.getString("sexo");
				tipoHabitacion = rs.getString("tipoHabitacion");
				precio = rs.getInt("precio");
				numeroNoches = rs.getInt("numeroNoches");
				String[] parts = fechaEntrada.split("/");
				if (ano > Integer.parseInt(parts[2]) || mes > Integer.parseInt(parts[1]) || dia >= Integer.parseInt(parts[0])) {
				Reserva res = new Reserva(usuario, nombreReserva, apellidosReserva, fechaEntrada, fechaSalida, tipoHabitacion, regimen, sexo, precio, numeroNoches);
				reservas.add(res);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		desconectar();
		return reservas;
	}

    public void registrarReserva(String usuario, String nombre, String apellido, String fechaEntrada, String fechaSalida, String tipoHabitacion, String regimen, String sexo, double importe, int numNoches) {
        conectar();

        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement("INSERT INTO reserva(codReserva,usuario,nombreReserva,apellidoReserva,fechaEntrada,fechaSalida,regimen,sexo,tipoHabitacion,precio,numeroNoches) VALUES (0,?,?,?,?,?,?,?,?,?,?);");
            ps.setString(1, usuario);
            ps.setString(2, nombre);
            ps.setString(3, apellido);
            ps.setString(4, fechaEntrada);
            ps.setString(5, fechaSalida);
            ps.setString(6, tipoHabitacion);
            ps.setString(7, regimen);
            ps.setString(8, sexo);
            ps.setInt(9, (int)importe);
            ps.setInt(10, numNoches);
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
    
    public String cargarArrayPorUsu(String usu, int dia, int mes, int ano) {
    	conectar();
    	String reservas = "";
    	String usuario;String nombreReserva; String apellidosReserva; String fechaEntrada;
		String fechaSalida; String tipoHabitacion; String regimen; String sexo; double precio; int numeroNoches;
		PreparedStatement pt = null;
		ResultSet rs = null;
		
		try {
			pt = con.prepareStatement("SELECT * FROM reserva WHERE usuario LIKE ?");
			pt.setString(0, usu);
			rs = pt.executeQuery();
			while (rs.next()){
				usuario = rs.getString("usuario");
				nombreReserva = rs.getString("nombreReserva");
				apellidosReserva = rs.getString("apellidoReserva");
				fechaEntrada = rs.getString("fechaEntrada");
				fechaSalida = rs.getString("fechaSalida");
				regimen = rs.getString("regimen");
				sexo = rs.getString("sexo");
				tipoHabitacion = rs.getString("tipoHabitacion");
				precio = rs.getInt("precio");
				numeroNoches = rs.getInt("numeroNoches");
				String[] parts = fechaEntrada.split("/");
				if (ano > Integer.parseInt(parts[2]) || mes > Integer.parseInt(parts[1]) || dia >= Integer.parseInt(parts[0])) {
				Reserva res = new Reserva(usuario, nombreReserva, apellidosReserva, fechaEntrada, fechaSalida, tipoHabitacion, regimen, sexo, precio, numeroNoches);
				reservas += res.toString() + "\n";
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		desconectar();
		return reservas;
    }
}
