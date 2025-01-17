package miPersistencia;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import misActividades.Actividades;
import users.Usuario;

/**
 * Persistencia del usuario
 * @author Lorena Almoguera Romero Dni: 48796558B
 *
 */
public class Persistencia {
	private static final String ficheroPersistencia = "files/data.ser";
	
	
	/**
	 * Funcion para guardar los datos de la persistencia
	 */
	public static void save() {
		try {
            FileOutputStream fileOut = new FileOutputStream(ficheroPersistencia);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(Usuario.users);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
		
	}
	
	/**
	 * Funcion para cargar los datos en cada ejecución 
	 */
	public static void load() {
        try {
        	System.out.println("Cargando Usuarios de la ruta: " + ficheroPersistencia );
            FileInputStream fis = new FileInputStream(ficheroPersistencia);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Usuario.users = (ArrayList<Usuario>) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
