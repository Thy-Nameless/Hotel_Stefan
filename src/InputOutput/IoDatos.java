package InputOutput;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import estaticos.Usuario;

public class IoDatos {

	
	//Guardar los usuarios en un fichero binario 
	public static void guardarUsusarios(ArrayList<Usuario> vUsuarios) {
		
		
		File usuarios = new File(".\\recursos\\usuarios.dat");
		FileOutputStream fo = null;
		ObjectOutputStream escribir = null;
		
		if (!usuarios.exists()) {
			try {
				usuarios.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			fo = new FileOutputStream(usuarios);
			escribir = new ObjectOutputStream(fo);
			
			escribir.writeObject(vUsuarios);
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				fo.close();
				escribir.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	//Leer los datos de los usuarios introducidos en el fichero
	@SuppressWarnings("unchecked")
	public static ArrayList<Usuario> leerDatos() {
		
		File usuarios = new File(".\\recursos\\usuarios.dat");
		FileInputStream fi = null;
		ObjectInputStream leer = null;
		ArrayList<Usuario> vUsuarios = new ArrayList<Usuario>(); 
		
		if (!usuarios.exists()) {
			try {
				usuarios.createNewFile();
				return vUsuarios;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		try {
			fi = new FileInputStream(usuarios);
			leer = new ObjectInputStream(fi);
			
			
			vUsuarios = (ArrayList<Usuario>) leer.readObject();
			
		
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Fin de lectura");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vUsuarios;
		
		
	}
	
	// Comprobar que el usuario introducido está registrado 
	public static boolean comprobarUser(String usuario, String passwd) {
		boolean correcto = false;
		

		ArrayList<Usuario> vUsuarios = leerDatos();
		
		for (Usuario usu : vUsuarios) {
			if (usu.getNombreUsuario().equalsIgnoreCase(usuario) && usu.getPassword().equalsIgnoreCase(passwd)) {
				return true;
			}
		}
		
		return false;
		
	}
}