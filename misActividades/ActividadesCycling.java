package misActividades;

import java.io.Serializable;

import runApp.main;
import users.Usuario;

/**
 * ActividadesCycling extiende a la clase padre Actividades e implementa la serializacion
* @author Lorena Almoguera Romero Dni: 48796558B
 *
 */
public class ActividadesCycling extends Actividades implements Serializable {
	// Práctica realizada por Lorena Almoguera Romero
	//	---------------		MENSAJES IMPRESION DE DATOS 				-----------------	//
	
	private static final String CADENA_FECHA_HORA_INICIO = "Fecha y hora de inicio: ";
	private static final String CADENA_FECHA_HORA_FIN = "Fecha y hora de finalización: ";
	private static final String CADENA_DURACION ="Duracion: ";
	private static final String CADENA_DISTANCIA ="Distancia: ";
	private static final String CADENA_KCAL = "Kcal consum: ";
	private static final String CADENA_FC_MAX_MIN_MEDIA ="FcMax, FcMin, FcMedia: ";
	private static final String CADENA_CADENCIA_IMPR ="Cadencia: ";
	
//	---------------		MENSAJES GENERALES		-----------------	//

	private static final String CADENA_CADENCIA = "Por favor, indique sus pedales por minuto!";
	private static final String CADENA_CADENCIA_MIN ="Cadencia (pedaladas por minuto): ";
	
//	---------------		MENSAJES ERROR			-----------------	//
	private static final String CADENA_ERROR_NUM ="ERROR: Dato introducido NO es un numero";
	private static final String CADENA_ERROR_NUM_NEGATIVE ="ERROR: Número introducido no puede ser de un valor negativo";
	
//	---------------		VARIABLES A DECLARAR	-----------------	//
	
	double cadencia;
	
	/**
	 * Funcion para declarar la actividad tipo cycling
	 * @param MiUsuario objeto
	 * @param factor correcion
	 * @param MiActividad objeto
	 * @return devolvemos la actividad
	 */
	public ActividadesCycling declararActividadCycling(Usuario MiUsuario, double factor, ActividadesCycling MiActividad) {
		double cadencia;
		MiActividad.declarar_actividad(MiUsuario, factor);
		cadencia = calcularcadencia();
		setCadencia(cadencia);
		MiActividad.imprimirDatosCycling(MiActividad);
		return MiActividad;
	}
	
	/**
	 * Funcion que imprime los datos cycling
	 * @param MiActividad objeto
	 */
	public void imprimirDatosCycling(ActividadesCycling MiActividad) {
		imprimir(CADENA_FECHA_HORA_INICIO + MiActividad.getFecha_inicio() + " " + MiActividad.getHora_inicio());
		imprimir(CADENA_FECHA_HORA_FIN + MiActividad.getFecha_fin() + " " + MiActividad.getHora_finalizada());
		imprimir(CADENA_DURACION + MiActividad.getDuracion());
		imprimir(CADENA_DISTANCIA + MiActividad.getDistancia());
		imprimir(CADENA_KCAL + MiActividad.getKcal_consum());
		imprimir(CADENA_FC_MAX_MIN_MEDIA + MiActividad.getFcMax() + " " + MiActividad.getFcMin() + " " + MiActividad.getFcMedia());
		imprimir("*** INI DATOS CYCLING ***");
		imprimir(CADENA_CADENCIA_IMPR + MiActividad.getCadencia());
		imprimir("*** FIN DATOS CYCLING ***");
	}
	
	/**
	 * Funcion para calcular la cadencia
	 * @return cadencia
	 */
	public double calcularcadencia () {
		double cadencia;
		String input;
		imprimir(CADENA_CADENCIA);
		input = main.scanIn.nextLine();
		while(Usuario.isNumeric(input)!= true) {
			imprimir(CADENA_ERROR_NUM);
			input = main.scanIn.nextLine();
		}
		cadencia = Double.parseDouble(input);
		
		while(cadencia < 0) {
			imprimir(CADENA_ERROR_NUM_NEGATIVE);
			input = main.scanIn.nextLine();
			while(Usuario.isNumeric(input) != true) {
				imprimir(CADENA_ERROR_NUM);
				input = main.scanIn.nextLine();
			}
			cadencia = Double.parseDouble(input);
		}
		imprimir(CADENA_CADENCIA_MIN +  cadencia);
		return cadencia;
	}

	// ------------		GETTERS Y SETTERS		-------------- //	
	
	/**
	 * Getter Cadencia
	 * @return cadencia 
	 */
	public double getCadencia() {
		return cadencia;
	}

	/**
	 * Setter Cadencia
	 * @param cadencia establecida
	 */
	public void setCadencia(double cadencia) {
		this.cadencia = cadencia;
	}
	
	
}
