/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estaticos;

import java.io.Serializable;

/**
 *
 * @author areka
 */
public class Habitacion implements Serializable  {
    /**
	 * 
	 */
	private static final long serialVersionUID = -8009767995534135276L;
	/**
	 * 
	 */
	
	private int numHabitacion;
    private String tipoHabitacion;
    private boolean disponible;

    public Habitacion(int numHabitacion, String tipoHabitacion) {
        this.numHabitacion = numHabitacion;
        this.tipoHabitacion = tipoHabitacion;
        disponible = true;
    }

    @Override
    public String toString() {
        return "Habitacion{" + "numHabitacion=" + numHabitacion + ", tipoHabitacion=" + tipoHabitacion + ", disponible=" + disponible + '}';
    }
    public int getNumHabitacion() {
    	return numHabitacion;
    }
    public boolean getDisponible() {
    	return disponible;
    }
    public void setHabitacionTrue() {
    	disponible=true;
    }
    public void reservarHabitacion() {
    	disponible = false;
    }
}
