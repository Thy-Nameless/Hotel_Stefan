/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InputOutput;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import estaticos.Habitacion;

/**
 *
 * @author areka
 */
public class OperacionHabitacion {
	private Habitacion[] vHabitaciones = new Habitacion[60];

	/**
	 * Metodo previamente usado para crear el array de habitaciones, usado
	 * s√≥lamente durante la primera ejecuci√≥n
	 */

	  /**public void guardarHabitaciones(){ for (int i=0;i<5;i++) { vHabitaciones[i] =
	  new Habitacion(101+i,"DUI"); } for (int i=0;i<5;i++) { vHabitaciones[i+5] =
	  new Habitacion(106+i,"DB"); } for (int i=0;i<5;i++) { vHabitaciones[i+10] =
	  new Habitacion(111+i,"TR"); } for (int i=0;i<5;i++) { vHabitaciones[i+15] =
	  new Habitacion(116+i,"SUITE"); } for (int i=0;i<5;i++) { vHabitaciones[i+20]
	  = new Habitacion(201+i,"DUI"); } for (int i=0;i<5;i++) { vHabitaciones[i+25]
	  = new Habitacion(206+i,"DB"); } for (int i=0;i<5;i++) { vHabitaciones[i+30] =
	  new Habitacion(211+i,"TR"); } for (int i=0;i<5;i++) { vHabitaciones[i+35] =
	  new Habitacion(216+i,"SUITE"); } for (int i=0;i<5;i++) { vHabitaciones[i+40]
	  = new Habitacion(301+i,"DUI"); } for (int i=0;i<5;i++) { vHabitaciones[i+45]
	  = new Habitacion(306+i,"DB"); } for (int i=0;i<5;i++) { vHabitaciones[i+50] =
	  new Habitacion(311+i,"TR"); } for (int i=0;i<5;i++) { vHabitaciones[i+55] =
	  new Habitacion(316+i,"SUITE"); } for (int i=0;i<vHabitaciones.length;i++) {
	  System.out.println(vHabitaciones[i]); } File fichero = new
	  File(".\\recursos\\habitaciones.dat"); try { if (!fichero.exists()) {
	  fichero.createNewFile(); } FileOutputStream fo = new
	  FileOutputStream(fichero); ObjectOutputStream oo = new
	  ObjectOutputStream(fo); oo.writeObject(vHabitaciones); oo.close(); } catch
	  (IOException ex) {
	  Logger.getLogger(OperacionHabitacion.class.getName()).log(Level.SEVERE, null,
	  ex); }
	  
	  }*/

	/** MÈtodo para cargas todas las habitaciones con su estado de local */
	public void resetHabitaciones() {
		for (int i=0;i<vHabitaciones.length;i++) {
			vHabitaciones[i].setHabitacionTrue();
		}
	}
	public void cargarHabitaciones() {
		File fichero = new File(".\\recursos\\habitaciones.dat");
		try {
			if (!fichero.exists()) {
				fichero.createNewFile();
			}
			FileInputStream fi = new FileInputStream(fichero);
			ObjectInputStream oi = new ObjectInputStream(fi);
			vHabitaciones = (Habitacion[]) oi.readObject();
			oi.close();
		} catch (IOException ex) {
			Logger.getLogger(OperacionHabitacion.class.getName()).log(Level.SEVERE, null, ex);
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(OperacionHabitacion.class.getName()).log(Level.SEVERE, null, ex);
		}
	};

	/** Comprobar si una habitaciÛn est· disponible */
	public boolean comprobarHabitacion(int pos) {
		return vHabitaciones[pos].getDisponible();
	}

	/** Cambiar el estado de una habitaciÛn a reservada */
	public void reservarHabitacion(int pos) {
		vHabitaciones[pos].reservarHabitacion();
	}

	/** Nos da el n˙mero de habitaciÛn dando la posiciÛn */
	public int sacarNumeroHabitacion(int pos) {
		return vHabitaciones[pos].getNumHabitacion();
	}

	/** Guarda todas las habitaciones en local */
	public void guardarHabitaciones() {
		File fichero = new File(".\\recursos\\habitaciones.dat");
		try {
			if (!fichero.exists()) {
				fichero.createNewFile();
			}
			FileOutputStream fo = new FileOutputStream(fichero);
			ObjectOutputStream oo = new ObjectOutputStream(fo);
			oo.writeObject(vHabitaciones);
			oo.close();
		} catch (IOException ex) {
			Logger.getLogger(OperacionHabitacion.class.getName()).log(Level.SEVERE, null, ex);
		}
	};

	/** Imprime todos los datos por consola de las habitaciones */
	public void imprimirHabitaciones() {
		for (int i = 0; i < vHabitaciones.length; i++) {
			System.out.println(vHabitaciones[i]);
		}
	}

	public boolean comprobarHabitacionTest(int pos) {
		if (vHabitaciones[pos] == null) {
			return true;
		}
		return false;
	}
}
