package estaticos;

import java.io.Serializable;

public class Reserva implements Serializable {
	/**
	 * Clase de reserva, para guardar todos los datos de las reservas realizadas por los clientes
	 */
	private static final long serialVersionUID = -2002972975913738373L;
	private String usuario;
	private String nombreReserva;
	private String apellidosReserva;
	private String fechaEntrada;
	private String fechaSalida;
	private String tipoHabitacion;
	private String regimen;
	private String sexo;
	private double precio;
	private int numeroNoches;

	public Reserva(String usuario,String nombreReserva, String apellidosReserva, String fechaEntrada,
			String fechaSalida, String tipoHabitacion, String regimen, String sexo, double precio, int numeroNoches) {
		this.usuario = usuario;
		this.nombreReserva = nombreReserva;
		this.apellidosReserva = apellidosReserva;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.tipoHabitacion = tipoHabitacion;
		this.regimen = regimen;
		this.sexo = sexo;
		this.precio = precio;
		this.numeroNoches = numeroNoches;
	}


	public String getUsuario() {
		return usuario;
	}
	public String getNombreReserva() {
		return nombreReserva;
	}

	public String getApellidosReserva() {
		return apellidosReserva;
	}

	public String getFechaEntrada() {
		return fechaEntrada;
	}

	public String getFechaSalida() {
		return fechaSalida;
	}

	public String getTipoHabitacion() {
		return tipoHabitacion;
	}

	public String getRegimen() {
		return regimen;
	}

	public String getSexo() {
		return sexo;
	}

	public double getPrecio() {
		return precio;
	}

	public int getNumeroNoches() {
		return numeroNoches;
	}

	public void setNombreReserva(String nombreReserva) {
		this.nombreReserva = nombreReserva;
	}

	public void setApellidosReserva(String apellidosReserva) {
		this.apellidosReserva = apellidosReserva;
	}

	public void setFechaEntrada(String fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public void setFechaSalida(String fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public void setTipoHabitacion(String tipoHabitacion) {
		this.tipoHabitacion = tipoHabitacion;
	}

	public void setRegimen(String regimen) {
		this.regimen = regimen;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}


	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public void setNumeroNoches(int numeroNoches) {
		this.numeroNoches = numeroNoches;
	}

	@Override
	public String toString() {
		return  nombreReserva + " " + apellidosReserva + " " + fechaEntrada + " " + fechaSalida + " " + tipoHabitacion + " " + regimen + " "
				+ sexo + " " + precio + " " + numeroNoches;
	}

}