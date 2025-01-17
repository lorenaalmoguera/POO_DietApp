package misActividades;

import java.io.Serializable;

import runApp.main;
import users.Usuario;

/**
 * ActividadesSwimming extiende Actividades implementa serializacion
* @author Lorena Almoguera Romero Dni: 48796558B
 *
 */
public class ActividadesSwimming extends Actividades implements Serializable {
	
	// Práctica realizada por Lorena Almoguera Romero
	//	---------------		MENSAJES IMPRESION DE DATOS 				-----------------	//
	
	private static final String CADENA_FECHA_HORA_INICIO = "Fecha y hora de inicio: ";
	private static final String CADENA_FECHA_HORA_FIN = "Fecha y hora de finalización: ";
	private static final String CADENA_DURACION ="Duracion: ";
	private static final String CADENA_DISTANCIA ="Distancia: ";
	private static final String CADENA_KCAL = "Kcal consum: ";
	private static final String CADENA_FC_MAX_MIN_MEDIA ="FcMax, FcMin, FcMedia: ";
	private static final String CADENA_TIPO = "Tipo de natacion: ";
	//	---------------		MENSAJES GENERALES		-----------------	//

	private static final String CADENA_LARGOS = "Por favor, indique el número de largos realizado";
	private static final String CADENA_LARGOS_TOTAL ="Largos (largos por minuto): ";
	private static final String CADENA_INTROD_KEY ="Indica si los largos se han realizado en: (1)- PISCINA, (2)- MAR";
	private static final String CADENA_OPC_SELECT ="Opción seleccionada: ";
	//	---------------		MENSAJES ERROR			-----------------	//
	private static final String CADENA_ERROR_NUM ="ERROR: Dato introducido NO es un numero";
	private static final String CADENA_ERROR_NUM_NEGATIVE ="ERROR: Número introducido no puede ser de un valor negativo";
	private static final String CADENA_ERROR_OPC = "ERROR: Esa opción, no existe";
	
	//	---------------		VARIABLES A DECLARAR	-----------------	//
	final static int PISCINA = 1, MAR = 2 ;
	
	int largos;
	String tiponatacion;
	
	/**
	 * Funcion que declara los datos especificos de una actividad swimming
	 * @param MiUsuario objeto
	 * @param factor a multiplicar
	 * @param miActividad actividad
	 * @return devuelve la actividad
	 */
	public ActividadesSwimming declararActividadesSwimming(Usuario MiUsuario, double factor, ActividadesSwimming miActividad) {
		String tiponatacion;
		int largos;
		miActividad.declarar_actividad(MiUsuario, factor);
		tiponatacion = pedirTipoNatacion();
		largos = pedirLargos();
		setLargos(largos);
		setTiponatacion(tiponatacion);
		return miActividad;
	}
	
	/**
	 * Funcion que pide los largos
	 * @return devuelve los largos
	 */
	public int pedirLargos () {
		int largos;
		String input;
		imprimir(CADENA_LARGOS);
		input = main.scanIn.nextLine();
		while(Usuario.isNumeric(input)!= true) {
			imprimir(CADENA_ERROR_NUM);
			input = main.scanIn.nextLine();
		}
		largos = Integer.parseInt(input);
		
		while(largos < 0) {
			imprimir(CADENA_ERROR_NUM_NEGATIVE);
			input = main.scanIn.nextLine();
			while(Usuario.isNumeric(input) != true) {
				imprimir(CADENA_ERROR_NUM);
				input = main.scanIn.nextLine();
			}

			largos = Integer.parseInt(input);
		}
		imprimir(CADENA_LARGOS_TOTAL +  largos);
		return largos;
	}
	
	/**
	 * Funcion que pide el tipo de natacion que se ha realizado
	 * @return String del tipo de natacion
	 */
	public String pedirTipoNatacion() {
		
		String input, piscina = "PISCINA", mar = "MAR";
		int opcion;
		imprimir(CADENA_INTROD_KEY);
		
		input = main.scanIn.nextLine();
		while(Usuario.isNumeric(input)!= true) {
			imprimir(CADENA_ERROR_NUM);
			input = main.scanIn.nextLine();
		}
		opcion = Integer.parseInt(input);
		while(opcion < 1 || opcion > 2) {
			imprimir(CADENA_ERROR_OPC);
			input = main.scanIn.nextLine();
			while(Usuario.isNumeric(input) != true) {
				imprimir(CADENA_ERROR_NUM);
				input = main.scanIn.nextLine();
				opcion = Integer.parseInt(input);
			}
		}
		switch(opcion) {
			case PISCINA:
				imprimir(CADENA_OPC_SELECT);
				return piscina;
			case MAR:
				imprimir(CADENA_OPC_SELECT);
				return mar;
		}
		return null;
	}
	
	/**
	 * Funcion que imprime los datos swimming
	 * @param MiActividad objeto
	 */
	public void imprimirDatosSwimming(ActividadesSwimming MiActividad) {
		imprimir(CADENA_FECHA_HORA_INICIO + MiActividad.getFecha_inicio() + " " + MiActividad.getHora_inicio());
		imprimir(CADENA_FECHA_HORA_FIN + MiActividad.getFecha_fin() + " " + MiActividad.getHora_finalizada());
		imprimir(CADENA_DURACION + MiActividad.getDuracion());
		imprimir(CADENA_DISTANCIA + MiActividad.getDistancia());
		imprimir(CADENA_KCAL + MiActividad.getKcal_consum());
		imprimir(CADENA_FC_MAX_MIN_MEDIA + MiActividad.getFcMax() + " " + MiActividad.getFcMin() + " " + MiActividad.getFcMedia());
		imprimir("*** INI DATOS SWIMMING ***");
		imprimir(CADENA_LARGOS_TOTAL + MiActividad.getLargos());
		imprimir(CADENA_TIPO + MiActividad.getTiponatacion());;
		imprimir("*** FIN DATOS SWIMMING ***");
	}

	
	//					---------------			GETTERS Y SETTERS		--------------------		//
	
	/**
	 * Getter largos
	 * @return largos
	 */
	public int getLargos() {
		return largos;
	}

	/**
	 * Setter Largos
	 * @param largos realizados
	 */
	public void setLargos(int largos) {
		this.largos = largos;
	}

	/**
	 * Getter Tipo natacion
	 * @return tiponatacion
	 */
	public String getTiponatacion() {
		return tiponatacion;
	}

	/**
	 * Setter tiponatacion
	 * @param tiponatacion del usuario
	 */
	public void setTiponatacion(String tiponatacion) {
		this.tiponatacion = tiponatacion;
	}
	
}
