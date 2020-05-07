package estaticos;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Usuario implements Serializable{
	private String nombreUsuario;
	private String password;
	private boolean esAdmin;
	@SuppressWarnings("unused")

	public Usuario(String nombreUsuario, String password, boolean esAdmin) {
		super();
		this.nombreUsuario = nombreUsuario;
		this.password = password;
		this.esAdmin = esAdmin;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public String getPassword() {
		return password;
	}

	public boolean isEsAdmin() {
		return esAdmin;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEsAdmin(boolean esAdmin) {
		this.esAdmin = esAdmin;
	}

	@Override
	public String toString() {
		return "\n"+ nombreUsuario + "-" + password + "-" + esAdmin;
	}
	

}