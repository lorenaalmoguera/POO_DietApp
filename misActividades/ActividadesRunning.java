package misActividades;

import java.io.Serializable;

import runApp.main;
import users.Usuario;

/**
 * ActividadesRunning extiende Actividades e implementa Serializacion
* @author Lorena Almoguera Romero Dni: 48796558B
 *
 */
public class ActividadesRunning extends Actividades implements Serializable {
	
	// Práctica realizada por Lorena Almoguera Romero
	//	---------------		MENSAJES IMPRESION DE DATOS 				-----------------	//
	
	private static final String CADENA_FECHA_HORA_INICIO = "Fecha y hora de inicio: ";
	private static final String CADENA_FECHA_HORA_FIN = "Fecha y hora de finalización: ";
	private static final String CADENA_DURACION ="Duracion: ";
	private static final String CADENA_DISTANCIA ="Distancia: ";
	private static final String CADENA_KCAL = "Kcal consum: ";
	private static final String CADENA_FC_MAX_MIN_MEDIA ="FcMax, FcMin, FcMedia: ";
	private static final String CADENA_PASOS_IMPR ="Pasos: ";
	private static final String CADENA_ELEVACION_IMPR = "Elevacion: ";
	private static final String CADENA_CADENCIA ="Cadencia: ";
	private static final String CADENA_RITMO ="Ritmo: ";
	
	//	---------------		MENSAJES GENERALES		-----------------	//

	private static final String CADENA_PASOS = "Por favor, indique el número de pasos realizados";
	private static final String CADENA_PASOS_TOTAL ="Pasos totales: ";
	private static final String CADENA_ELEVACION_TOTAL ="Elevacion total";
	private static final String CADENA_CADENCIA_CALCULADA ="Cadencia calculada (peso/minuto): ";
	private static final String CADENA_ELEVACION ="Indica la elevación.";
	private static final String CADENA_HORA ="Indica la hora en formato 24h.";
	private static final String CADENA_MINUTOS ="Indica los minutos.";
	private static final String CADENA_RITMO_KM_H = "Indica las horas/minutos en realizar un KM de media.";
	private static final String CADENA_RITMO_TOTAL ="HH:MM en realizar un KM de media";
	private static final String CADENA_DATOS_RUNNING ="A continuación, vamos a declarar los datos especificos a la actividad running";

	//	---------------		MENSAJES ERROR			-----------------	//
	private static final String CADENA_ERROR_NUM ="ERROR: Dato introducido NO es un numero";
	private static final String CADENA_ERROR_NUM_NEGATIVE ="ERROR: Número introducido no puede ser de un valor negativo";
	private static final String CADENA_ERROR_HORA_INVALIDA ="ERROR: Formato INCORRECTO. Por favor, vuelve a introducir la hora.";
	private static final String CADENA_ERROR_MINUTOS_INVALIDA ="ERROR: Formato INCORRECTO. Por favor, vuelve a introducir la hora";
	//	---------------		VARIABLES A DECLARAR	-----------------	//
	
	int elevacion, totalpasos;
	double cadencia;
	String ritmo;
	
		/**
		 * Funcion que declara la actividad running
		 * @param MiUsuario objeto
		 * @param correccion factor
		 * @param Running objeto
		 * @return devuelve la actividad
		 */
		public ActividadesRunning declararActividadesRunning(Usuario MiUsuario, double correccion, ActividadesRunning Running) {
			int pasos, elevacion;
			String ritmo;
			double duracion, cadencia;
			Running.declarar_actividad(MiUsuario, correccion);
			imprimir(CADENA_DATOS_RUNNING);
			duracion = Running.getDuracion();
			pasos = Running.pedirPasos();
			elevacion = Running.pedirElevacion();
			ritmo = Running.pedirRitmo();
			cadencia = Running.calcularCadencia(pasos, duracion);
			setTotalpasos(pasos);
			setElevacion(elevacion);
			setRitmo(ritmo);
			setCadencia(cadencia);
			return Running;
		}
		
		/**
		 * Funcion que imprime los datos de la actividad running
		 * @param MiActividad objeto
		 */
		public void imprimirDatosRunning(ActividadesRunning MiActividad) {
			imprimir(CADENA_FECHA_HORA_INICIO + MiActividad.getFecha_inicio() + " " + MiActividad.getHora_inicio());
			imprimir(CADENA_FECHA_HORA_FIN + MiActividad.getFecha_fin() + " " + MiActividad.getHora_finalizada());
			imprimir(CADENA_DURACION + MiActividad.getDuracion());
			imprimir(CADENA_DISTANCIA + MiActividad.getDistancia());
			imprimir(CADENA_KCAL + MiActividad.getKcal_consum());
			imprimir(CADENA_FC_MAX_MIN_MEDIA + MiActividad.getFcMax() + " " + MiActividad.getFcMin() + " " + MiActividad.getFcMedia());
			imprimir("*** INI DATOS RUNNING ***");
			imprimir(CADENA_PASOS_IMPR + MiActividad.getTotalpasos());
			imprimir(CADENA_ELEVACION_IMPR + MiActividad.getElevacion());
			imprimir(CADENA_RITMO + MiActividad.getRitmo());
			imprimir(CADENA_CADENCIA + MiActividad.getCadencia());
			imprimir("*** FIN DATOS RUNNING ***");
		}
		
		/**
		 * Funcion que le pide los pasos realizados al usuario
		 * @return int pasos realizados
		 */
		public int pedirPasos () {
			int pasos;
			String input;
			imprimir(CADENA_PASOS);
			input = main.scanIn.nextLine();
			while(Usuario.isNumeric(input)!= true) {
				imprimir(CADENA_ERROR_NUM);
				input = main.scanIn.nextLine();
			}
			pasos = Integer.parseInt(input);
			while(pasos < 0) {
				imprimir(CADENA_ERROR_NUM_NEGATIVE);
				input = main.scanIn.nextLine();
				while(Usuario.isNumeric(input) != true) {
					imprimir(CADENA_ERROR_NUM);
					input = main.scanIn.nextLine();
				}
				pasos = Integer.parseInt(input);
			}
			imprimir(CADENA_PASOS_TOTAL +  pasos);
			return pasos;
	
		}
		
		/**
		 * Funcion que calcula la cadencia en funcion de los pasos y la duracion
		 * @param pasos realizados
		 * @param duracion calculada
		 * @return devuelve la cadencia
		 */
		public double calcularCadencia(int pasos, double duracion) {
			System.out.println("en cadencia (pasos)" + pasos);
			System.out.println("en cadencia (duracion)" + duracion);
			double cadencia;
			double pasos_aux = pasos;
			System.out.println("en cadencia (en cadencia (pasos_aux)" + pasos_aux);
			cadencia = pasos_aux/duracion;
			imprimir(CADENA_CADENCIA_CALCULADA + cadencia);
			return cadencia;
		}
		
		/**
		 * Funcion que pide la elevacion
		 * @return devuelve la elevacion
		 */
		public int pedirElevacion() {
			int elevacion;
			String input;
			imprimir(CADENA_ELEVACION);
			input = main.scanIn.nextLine();
			while(Usuario.isNumeric(input)!= true) {
				imprimir(CADENA_ERROR_NUM);
				input = main.scanIn.nextLine();
			}
			elevacion = Integer.parseInt(input);
			while(elevacion < 0) {
				imprimir(CADENA_ERROR_NUM_NEGATIVE);
				input = main.scanIn.nextLine();
				while(Usuario.isNumeric(input) != true) {
					imprimir(CADENA_ERROR_NUM);
					input = main.scanIn.nextLine();
				}
				elevacion = Integer.parseInt(input);
			}
			imprimir(CADENA_ELEVACION_TOTAL +  elevacion);
			return elevacion;
			}
	
		/**
		 * Funcion que pide el ritmo que llevaba el usuario
		 * @return devuelve string
		 */
		public String pedirRitmo() {
			imprimir(CADENA_RITMO_KM_H);
			String ritmo = pedir_hora();
			imprimir(CADENA_RITMO_TOTAL + ritmo);
			return ritmo;
		}
	
		/**
		 * Funcion que pide la hora y minutos
		 * @return String de la hora
		 */
	    public static String pedir_hora(){
	    	String hora, minutos, tiempo, aux = "0";
	    	int hora_int, minutos_int;
	    	imprimir(CADENA_HORA);
	    	hora = main.scanIn.nextLine();
	    	while(isNumeric(hora)!=true) {
	    		imprimir(CADENA_ERROR_NUM);
	    		hora = main.scanIn.nextLine();
	    	}
	    	hora_int = Integer.parseInt(hora);
	    	while(hora_int > 24 || hora_int < 0) {
	    		imprimir(CADENA_ERROR_HORA_INVALIDA);
	    		hora = main.scanIn.nextLine();
	    		hora_int = Integer.parseInt(hora);
	    	}
	    	imprimir(CADENA_MINUTOS);
	    	minutos = main.scanIn.nextLine();
	    	while(isNumeric(minutos)!= true) {
	    		imprimir(CADENA_ERROR_NUM);
	    		minutos = main.scanIn.nextLine();
	    	}
	    	minutos_int = Integer.parseInt(minutos);
	    	while(minutos_int > 60 || minutos_int < 0) {
	    		imprimir(CADENA_ERROR_MINUTOS_INVALIDA);
	    		minutos = main.scanIn.nextLine();
	    		minutos_int = Integer.parseInt(minutos);
	    	}
	    	
	    	if(minutos.length() == 1) {
	    		minutos = aux + minutos;
	    	}
	    	if(hora.length() == 1) {
	    		hora = aux + hora;
	    	}
	    	
	    	tiempo = hora + ":" + minutos;
	    	return tiempo;
	    }

    
    
    //				-----------------		GETTERS Y SETTERS			-------------------------------
		
	    /**
	     * Getter elevacion
	     * @return elevacion
	     */
	    public int getElevacion() {
			return elevacion;
		}
	
		/**
		 * Setter elevacion
		 * @param elevacion establecida
		 */
		public void setElevacion(int elevacion) {
			this.elevacion = elevacion;
		}
	
		/**
		 * Getter total de pasos
		 * @return total de pasos
		 */
		public int getTotalpasos() {
			return totalpasos;
		}
	
		/**
		 * setter Total de pasos
		 * @param totalpasos establecidos
		 */
		public void setTotalpasos(int totalpasos) {
			this.totalpasos = totalpasos;
		}
	
		/**
		 * Getter cadencia
		 * @return cadencia
		 */
		public double getCadencia() {
			return cadencia;
		}
		
		/**
		 * Setter cadencia
		 * @param cadencia establecida
		 */
		public void setCadencia(double cadencia) {
			this.cadencia = cadencia;
		}
		
		/**
		 * Getter ritmo
		 * @return ritmo
		 */
		public String getRitmo() {
			return ritmo;
		}
		
		/**
		 * Setter ritmo
		 * @param ritmo establecido
		 */
		public void setRitmo(String ritmo) {
			this.ritmo = ritmo;
		}
		
	    
	
	}
