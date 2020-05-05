package InputOutput;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import estaticos.Reserva;

public class OperacionReserva {
	ArrayList<Reserva> listaReservas;
	
	@SuppressWarnings("unchecked")
	/** Carga todas las reservas de un archivo en local */
	public void cargarReservas() {
        File fichero = new File(".\\recursos\\reservas.dat");
            try {
                if (!fichero.exists()) {
                    fichero.createNewFile();
                    listaReservas = new ArrayList<Reserva>();
                } else {
                	 FileInputStream fi = new FileInputStream(fichero);
                     ObjectInputStream oi = new ObjectInputStream(fi);
                     listaReservas = (ArrayList<Reserva>) oi.readObject();
                     oi.close();
                }
            } catch (EOFException ef) {
            	listaReservas = new ArrayList<Reserva>();
            } 
            catch (IOException ex) {
                Logger.getLogger(OperacionHabitacion.class.getName()).log(Level.SEVERE, null, ex);
                
        } 
            catch (ClassNotFoundException ex) {
            Logger.getLogger(OperacionHabitacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    };
    /** Método para añadir reserva realiza en el array (no guarda en local) */
    public void anadirReserva(Reserva reserva) {
    	listaReservas.add(reserva);
    }
    
    /** Carga el toString del array de reservas entero del cliente */
    
    public String cargarArray(String usuario) {
    	String contenido = "";
    	Reserva usu;
    	Iterator it = listaReservas.iterator();
        while (it.hasNext()) {
        	usu = (Reserva) it.next();
        	if (usu.getUsuario().equals(usuario))
        		contenido += usu + "\n";
        }
        System.out.println(contenido);
        return contenido;
    }
    
    public String cargarArrayHotel() {
    	String contenido ="";
    	Iterator it = listaReservas.iterator();
    	while (it.hasNext()) {
    		contenido += it.next() + "\n";
    	}
    	return contenido;
    }
    
    public ArrayList<Reserva> devolverArray() {
    	return listaReservas;
    }
    /** Método para guardar las reservas realizadas en un archivo con el nombre de usuario */
    public void guardarReservas() {
        File fichero = new File(".\\recursos\\reservas.dat");
            try {
                if (!fichero.exists()) {
                    fichero.createNewFile();
                }
                FileOutputStream fo = new FileOutputStream(fichero);
                ObjectOutputStream oo = new ObjectOutputStream(fo);
                oo.writeObject(listaReservas);
                oo.close();
            } catch (IOException ex) {
                Logger.getLogger(OperacionHabitacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    };
    
	
}
