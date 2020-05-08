package InputOutput;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import estaticos.Reserva;
import estaticos.Usuario;

public class IoReservas {
	private Connection con;
	private Date hoyArray = Calendar.getInstance().getTime();
	private Calendar calHoyArray = Calendar.getInstance();
	private int diaHoy;
	private int mesHoy;
	private int anoHoy;
	public IoReservas() {
		con = null;
		calHoyArray.setTime(hoyArray);
		mesHoy = calHoyArray.get(Calendar.MONTH) + 1;
		diaHoy = calHoyArray.get(Calendar.DAY_OF_MONTH);
		System.out.println(diaHoy);
		anoHoy = calHoyArray.get(Calendar.YEAR);
	}
	
	public void conectar() {
        try {
        	String sURL = "jdbc:mysql://hotel-stefan.cciptjamgvdf.us-east-1.rds.amazonaws.com:3306/administracion_hotel_stefan?useSSL=false";
	       	 String user = "eclipse";
	       	 String passwd = "Hotel2020!";
	       	con = DriverManager.getConnection(sURL,user,passwd);
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
    
	public String devolverArrayReservasString() {
    	conectar();
    	String reservas = "";
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
				//String[] parts = fechaEntrada.split("/");
				//if (anoHoy > Integer.parseInt(parts[2]) || mesHoy > Integer.parseInt(parts[1]) || diaHoy >= Integer.parseInt(parts[0])) {
				Reserva res = new Reserva(usuario, nombreReserva, apellidosReserva, fechaEntrada, fechaSalida, tipoHabitacion, regimen, sexo, precio, numeroNoches);
				reservas += res.toString() + "\n";
				//}
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
    
	public void iniciarLogReservas() {
		conectar();
		int codigoReserva;
		String usuario;String nombreReserva; String apellidosReserva; String fechaEntrada;
		String fechaSalida; String tipoHabitacion; String regimen; String sexo; double precio; int numeroNoches; int numHabitacion=0;
		PreparedStatement pt = null;
		ResultSet rs = null;
		try {
			pt = con.prepareStatement("SELECT * FROM reserva");
			
			rs = pt.executeQuery();
			while (rs.next()){
				codigoReserva = rs.getInt("codReserva");
				fechaSalida = rs.getString("fechaSalida");
				usuario = rs.getString("usuario");
				nombreReserva = rs.getString("nombreReserva");
				apellidosReserva = rs.getString("apellidoReserva");
				fechaEntrada = rs.getString("fechaEntrada");
				regimen = rs.getString("regimen");
				sexo = rs.getString("sexo");
				tipoHabitacion = rs.getString("tipoHabitacion");
				precio = rs.getInt("precio");
				numeroNoches = rs.getInt("numeroNoches");
				numHabitacion = rs.getInt("numHabitacion");
				
				Reserva res = new Reserva(usuario, nombreReserva, apellidosReserva, fechaEntrada, fechaSalida, tipoHabitacion, regimen, sexo, precio, numeroNoches);
				String[] parts = fechaSalida.split("/");
				if (anoHoy > Integer.parseInt(parts[2]) || mesHoy > Integer.parseInt(parts[1]) || diaHoy > Integer.parseInt(parts[0])) {
					System.out.println("ok");
					eliminarUsuario(codigoReserva, res, numHabitacion);
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
	}
	
	private void eliminarUsuario(int codReserva, Reserva res, int numHabitacion) {
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("INSERT INTO reserva_historico(codReserva,usuario,nombreReserva,apellidoReserva,fechaEntrada,fechaSalida,regimen,sexo,tipoHabitacion,precio,numeroNoches) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
			ps.setInt(1, codReserva);
            ps.setString(2, res.getUsuario());
            ps.setString(3, res.getNombreReserva());
            ps.setString(4, res.getApellidosReserva());
            ps.setString(5, res.getFechaEntrada());
            ps.setString(6, res.getFechaSalida());
            ps.setString(7, res.getRegimen());
            ps.setString(8, res.getSexo());
            ps.setString(9, res.getTipoHabitacion());
            ps.setInt(10, (int)res.getPrecio());
            ps.setInt(11, res.getNumeroNoches());
            ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ps = null;
		try {
			ps = con.prepareStatement("DELETE FROM reserva WHERE codReserva = ?");
			ps.setInt(1, codReserva);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
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
	
	public void registrarUsuario(Usuario usu) throws com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException {
        conectar();

        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement("INSERT INTO usuario VALUES (?,?,?);");
            ps.setString(1, usu.getNombreUsuario());
            ps.setString(2, usu.getPassword());
            if (usu.isEsAdmin()) {
            	ps.setInt(3, 1);
            } else {
            	ps.setInt(3, 0);
            }
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "El nuevo usuario fue introducido");
            ps.close();
        } catch (SQLException e) {
        	JOptionPane.showMessageDialog(null, "El usuario indicado ya fue introducido anteriormente");
        } finally {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        desconectar();
    }
	
    public ArrayList<Reserva> devolverReservas() {
    	System.out.println(mesHoy);
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
				if (anoHoy > Integer.parseInt(parts[2]) || mesHoy > Integer.parseInt(parts[1]) || diaHoy >= Integer.parseInt(parts[0])) {
				Reserva res = new Reserva(usuario, nombreReserva, apellidosReserva, fechaEntrada, fechaSalida, tipoHabitacion, regimen, sexo, precio, numeroNoches);
				System.out.println(res.toString());
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
            ps.setString(8, tipoHabitacion);
            ps.setString(6, regimen);
            ps.setString(7, sexo);
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
			pt.setString(1, usu);
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
