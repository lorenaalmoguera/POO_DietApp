package runApp;

import miPersistencia.Persistencia;
import misActividades.Actividades;
import misActividades.ActividadesCycling;
import misActividades.ActividadesRunning;
import misActividades.ActividadesSwimming;
import users.Usuario;
import users.UsuarioPremium;
import java.io.FileOutputStream;

/**
 * Clase que contiene los menus generales de la aplicacion
* @author Lorena Almoguera Romero Dni: 48796558B
 *
 */
public class MiAplicacion {

	// Práctica realizada por Lorena Almoguera Romero
	// 		--------------- !!!!!		MENU_APLICACION		!!!!!	-----------------	//
	private static final String CADENA_INICIO ="Por favor, indique si desea realizar alguna de las siguientes opciones";
	private static final String CADENA_INICIO_OPC_1 ="(1) - Darse de Alta";
	private static final String CADENA_INICIO_OPC_2 ="(2) - Iniciar Sesión";
	private static final String CADENA_INICIO_OPC_3 ="(3) - Exit";
	private static final String CADENA_OPC_MENU_1 ="(1) - Cambiar contraseña";
	private static final String CADENA_OPC_MENU_2 ="(2) - Registrar Actividad";
	private static final String CADENA_OPC_MENU_3 ="(3) - Cambiar Estado de Cuenta a Premium";
	private static final String CADENA_OPC_MENU_4 ="(4) - Actualizar peso";
	private static final String CADENA_OPC_MENU_5 ="(5) - Mostrar informe";
	private static final String CADENA_OPC_MENU_6 = "(6) - Salir de la aplicación";
	private static final String CADENA_OPC_MENU_7 = "(7) - Cerrar Sesión ";
	private static final String CADENA_SELECT_OPC ="Has seleccionado la opción: ";
	private static final String CADENA_DECLARAR_ACTIVIDAD ="Por favor, indique que tipo de actividad desea declarar: (1) - CYCLING, (2) - RUNNING, (3) - SWIMMING, (4) - OTRA";
	private static final String CADENA_SELECT_CYCLING ="Has seleccionado la actividad: Cycling";
	private static final String CADENA_SELECT_RUNNING ="Has seleccionado la actividad: Running";
	private static final String CADENA_SELECT_SWIMMING ="Has seleccionado la actividad: Swimming";
	private static final String CADENA_SELECT_GENERIC ="Has seleccionado la actividad: Generica";
	private static final String CADENA_IMPR_SWIMMING ="PRINT SWIMMING DATA";
	private static final String CADENA_IMPR_RUNNING ="PRINT RUNNING DATA";
	private static final String CADENA_IMPR_CYCLING ="PRINT CYCLING DATA";
	private static final String CADENA_IMPR_GENERIC ="PRINT GENERIC DATA";
	private static final String CADENA_INICIO_SESION_OTRA_VEZ ="Para volver a acceder como usuario premium, usted debe de iniciar sesión de nuevo";
	private static final String CADENA_OPC_INEXISTENTE ="Opcion inexistente. Intentelo de nuevo";
	//		--------------- 		MENSAJES ERROR			-----------------	//
	private static final String CADENA_ERROR_NUM ="ERROR: Eso no es un número. Por favor, introduce de nuevo la opcion deseada";
	private static final String CADENA_ERROR_OPC = "ERROR: Opcion no valida";
	private static final String CADENA_ERROR_LENGTH = "ERROR: Longitud de cadena no valida";
	
	// 		--------------- 		VARIABLES			-----------------	//
	final static int CADENA_OPC_1 = 1, CADENA_OPC_2 = 2, CADENA_OPC_3 = 3, CADENA_OPC_4 = 4, CADENA_OPC_5 = 5, CADENA_OPC_6 = 6, CADENA_OPC_7 = 7, CADENA_OPC_8 = 8;
	final static int KEY_CYCLING = 1, KEY_RUNNING = 2, KEY_SWIMMING = 3, KEY_GENERIC = 4;
	final static double KEY_RUNNING_FACTOR = 1.3, KEY_CYCLING_FACTOR = 1.2, KEY_SWIMMING_FACTOR = 1.1, KEY_OTRO_FACTOR = 1;
	
	/**
	 * Para impresion
	 * @param mensaje a imprimir
	 */
	public static void imprimir(String mensaje) {
		System.out.println(mensaje);
	}

	//	--------- Funciones menu ------------- //
	
	/**
	 * Funcion que imprime los mensajes de bienvenida
	 */
	public void bienvenida() {
		imprimir(CADENA_INICIO);
		imprimir(CADENA_INICIO_OPC_1);
		imprimir(CADENA_INICIO_OPC_2);
		imprimir(CADENA_INICIO_OPC_3);
	}
	
	/**
	 * MENU DE LA APLICACION
	 * @param MiUsuario	Pasamos por parametro el usuario
	 * @param ejercicio objeto
	 */
	public void mi_menu(Usuario MiUsuario, Actividades ejercicio) {
		//MiUsuario.impresionDatos(MiUsuario);
		int opcion_int, num_aux = 0;
		
		int tipousuario = MiUsuario.devolverTipoUsuario(MiUsuario.getAlias());
		
		if(tipousuario == 1) {
			UsuarioPremium miPremium = (UsuarioPremium) MiUsuario;	// hacemos una copia para poder acceder a las funciones premium
			while(miPremium.haExpiradoLaCuenta(miPremium) == true) {
				MiUsuario.upgradeToPremium(MiUsuario);
			}
		}
		
		boolean check = true;
		while(check == true) {
			imprimir(CADENA_INICIO);
			imprimir(CADENA_OPC_MENU_1);
			imprimir(CADENA_OPC_MENU_2);
			imprimir(CADENA_OPC_MENU_3);
			imprimir(CADENA_OPC_MENU_4);
			imprimir(CADENA_OPC_MENU_5);
			imprimir(CADENA_OPC_MENU_6);
			imprimir(CADENA_OPC_MENU_7);
			
			opcion_int = opcion();
			imprimir("");
			switch(opcion_int) {
			case CADENA_OPC_1: 
				imprimir(CADENA_SELECT_OPC + opcion_int);	// cambiar contraseña
				String password = MiUsuario.cambiarPassword(MiUsuario);
				MiUsuario.setPassword(password);
				Persistencia.save();
				break;
			case CADENA_OPC_2: 
				imprimir(CADENA_SELECT_OPC + opcion_int);	// registrar actividad
				int opcion_actividades = opcionActividades();
				if(opcion_actividades == KEY_CYCLING) {
					imprimir(CADENA_SELECT_CYCLING);
					ActividadesCycling actividades = new ActividadesCycling();	// creamos actividad
					actividades.declararActividadCycling(MiUsuario, KEY_CYCLING_FACTOR, actividades);	// ddeclaramos la nueva actividad
					imprimir(CADENA_IMPR_CYCLING);
					actividades.imprimirDatosCycling(actividades);	// declaramos adatos especificos
					MiUsuario.meterEnActividadesArray(actividades, MiUsuario);	// metemos en array
					Persistencia.save();	// guardamos
				}else if(opcion_actividades == KEY_RUNNING) {
					imprimir(CADENA_SELECT_RUNNING);
					ActividadesRunning actividades = new ActividadesRunning();	// creamos actividad
					actividades.declararActividadesRunning(MiUsuario, KEY_RUNNING_FACTOR, actividades);	// declaramos la nueva actividad
					imprimir(CADENA_IMPR_RUNNING);
					actividades.imprimirDatosRunning(actividades);	// declaramos los datos especificos
					MiUsuario.meterEnActividadesArray(actividades, MiUsuario);	// metemos en array
					Persistencia.save();	// guardamos
				}else if(opcion_actividades == KEY_SWIMMING) {
					imprimir(CADENA_SELECT_SWIMMING);
					ActividadesSwimming actividades = new ActividadesSwimming();	// creamos actividad
					actividades.declararActividadesSwimming(MiUsuario, KEY_SWIMMING_FACTOR, actividades);	// declaramos la nueva actividad
					imprimir(CADENA_IMPR_SWIMMING);
					actividades.imprimirDatosSwimming(actividades);	// declaramos los datos especificos
					MiUsuario.meterEnActividadesArray(actividades, MiUsuario);	// metemos en array
					Persistencia.save();	// guardamos
				}else if(opcion_actividades == KEY_GENERIC) {
					imprimir(CADENA_SELECT_GENERIC);
					Actividades actividades = new Actividades();	// creamos actividad
					actividades.declarar_actividad(MiUsuario, KEY_OTRO_FACTOR);
					imprimir(CADENA_IMPR_GENERIC);
					actividades.imprimirDatosActividad(actividades);	// declaramos la nueva actividad
					MiUsuario.meterEnActividadesArray(actividades, MiUsuario);	// metemos en array
					Persistencia.save();	// guardamos
				}else {
					imprimir(CADENA_OPC_INEXISTENTE);
				}
				break;
			case CADENA_OPC_3: 
				imprimir(CADENA_SELECT_OPC + opcion_int);	// cambiar estado a cuenta premium
				MiUsuario.upgradeToPremium(MiUsuario);	// cambiamos estado
				Persistencia.save();	// guardamos
				imprimir(CADENA_INICIO_SESION_OTRA_VEZ);
				check = false;
				break;
			case CADENA_OPC_4:
				imprimir(CADENA_SELECT_OPC + opcion_int);	// actualizar peso
				MiUsuario.actualizarPeso(MiUsuario);	// actualizamos
				Persistencia.save();	// guardamos
				break;
			case CADENA_OPC_5:
				imprimir(CADENA_SELECT_OPC + opcion_int);	// mostrar informe
				MiUsuario.mostrarInforme(MiUsuario, ejercicio);	// mostramos informe
				Persistencia.save();	// guardamos
				break;
			case CADENA_OPC_6: 
				imprimir(CADENA_SELECT_OPC + opcion_int);	//salir de la aplicación
				System.exit(-1);
				break;
			case CADENA_OPC_7:
				imprimir(CADENA_SELECT_OPC + opcion_int);
				check = false;
				break;
			default:
				imprimir(CADENA_OPC_INEXISTENTE);
				break;
			}
		}
		
	}
	
	/**
	 * Funcion que pide al usuario un numero y devuelve la opcion seleccionada
	 * @return opcion seleccionada
	 */
	public int opcion() {
		while (true) {
			String opcion = main.scanIn.nextLine();
	        try {
	            int number = Integer.parseInt(opcion);
	            return number;
	        } catch (NumberFormatException e) {
	           imprimir(CADENA_ERROR_NUM);
	        }
		}
			
	}
	
	/**
	 * Funcion para elegir el tipo de actividad que se desea realizar
	 * @return int
	 */
	public int opcionActividades() {
		
		imprimir(CADENA_DECLARAR_ACTIVIDAD);
		while (true) {
			String opcion = main.scanIn.nextLine();
	        try {
	            int number = Integer.parseInt(opcion);
	            return number;
	        } catch (NumberFormatException e) {
	           imprimir(CADENA_ERROR_NUM);
	        }
		}
	}
	
	//	-------- Funciones de comprobacion ---------- //
	/**
	 * Funcion que comprueba si el String es un int
	 * @param str string a comprobar
	 * @return si es correcto o no correcto
	 */
	public static boolean isNumeric(String str) { 
		  try {  
		    Integer.parseInt(str);  
		    return true;
		  } catch(NumberFormatException e){  
		    return false;  
		  }  
		}
		
}
