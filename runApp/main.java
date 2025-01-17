package runApp;
import java.util.Scanner;

import miPersistencia.Persistencia;
import misActividades.Actividades;
import users.Usuario;
import users.UsuarioPremium;
/**
 * Main de la aplicacion
* @author Lorena Almoguera Romero Dni: 48796558B
 */
public class main {
	
	// Práctica realizada por Lorena Almoguera Romero
	// ---------------- MENSAJES ------------------- //
	
	private static final String CADENA_BIENVENIDA = "BIENVENIDO A LA APP HEALTH DE POO";
	private static final String CADENA_CASE_DARSE_ALTA = "Usted ha seleccionado la opcion 1 - Darse de Alta";
	private static final String CADENA_CASE_LOGIN = "Usted ha seleccioando la opcion 2 - Login";
	private static final String CADENA_SALIDA ="Usted ha seleccionado la opción 3. HASTA OTRA!";
	private static final String CADENA_ALIAS ="Indique el alias con el que desee iniciar sesión: ";
	private static final String CADENA_PREMIUM ="Va a iniciar sesión con un usuario premium";
	private static final String CADENA_NO_PREMIUM ="Va a iniciar sesión con un usuario normal";
	private static final String CADENA_NO_EXISTE = "Usuario no encontrado";
	private static final String CADENA_OPC_INEXISTENTE ="Opcion inexistente. Intentelo de nuevo";
	
	
	private static final String CADENA_ERROR_INICIO_SESION_PREMIUM ="ERROR: Al iniciar sesión con su cuenta de tipo premium";
	private static final String CADENA_ERROR_INCICIO_SESION ="ERROR: Al iniciar sesión con su cuenta de tipo normal";
	
	// ---------------- VARIABLES ------------------- //
	final static int KEY_DARSE_ALTA = 1, KEY_LOGIN = 2, KEY_EXIT = 3;
	final static int KEY_ES_PREMIUM = 1, KEY_NO_ES_PREMIUM = 2, KEY_NO_ENCONTRADO = -1;
	public static Scanner scanIn = new Scanner(System.in);

	/**
	 * Funcion para imprimir, sin necesidad de utilizar System.output.println()
	 * @param mensaje a imprimir por pantalla
	 */
	public static void imprimir(String mensaje) {
		System.out.println(mensaje);
	}
	/**
	 * Funcion donde se pide un imput y se pasa por parametros un mensaje a imprimir por pantalla, posteriormente devuelve el input en formato string
	 * @param Mensaje a imprimir
	 * @return string del input
	 */
	public static String input(String Mensaje) {
		imprimir(Mensaje);
		String input = main.scanIn.nextLine();
		return input;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String input;
		int int_input, opc;
		Boolean check = false;
		Persistencia.load();
		while(check == false) {
			MiAplicacion exeApp = new MiAplicacion();
			Usuario NoPremium = new Usuario();
			UsuarioPremium Premium = new UsuarioPremium();
			Actividades ejercicio = new Actividades();
			imprimir(" ");
			exeApp.bienvenida();
			int_input = exeApp.opcion();
			switch(int_input) {
				case KEY_DARSE_ALTA: 	// si selecciona darse de alta
						imprimir(CADENA_BIENVENIDA);
						imprimir(CADENA_CASE_DARSE_ALTA);
						NoPremium.pedir_datos(NoPremium);	// pedimos los datos
						Usuario.add_user(NoPremium);	// añadimos el usuario
						NoPremium.impresionDatos(NoPremium);	// imprimimos sus datos
						Persistencia.save();	// lo guardamos en la persistencia
					break;
				case KEY_LOGIN:
						imprimir(CADENA_BIENVENIDA	);
						imprimir(CADENA_CASE_LOGIN);
						//NoPremium.printAllUsers();	// funcion auxiliar para ver los usuarios registrados
						input = input(CADENA_ALIAS);
						opc = NoPremium.devolverTipoUsuario(input);
						if(opc == KEY_ES_PREMIUM) {	// en caso de si el alias es perteneciente a un usuario premium
							imprimir(CADENA_PREMIUM);
							Premium = Premium.iniciar_sesion_premium(input);	// iniciamos sesion
							if(Premium != null) {
								exeApp.mi_menu(Premium, ejercicio);	//si existe el usuario entramos
							}else {
								imprimir(CADENA_ERROR_INICIO_SESION_PREMIUM);
							}
						}else if(opc == KEY_NO_ES_PREMIUM) {	// en caso de si el usuario es perteneciente a un usuario normal
							imprimir(CADENA_NO_PREMIUM);
							NoPremium = NoPremium.iniciar_sesion(input);	// iniciamos sesion
							if(NoPremium != null) {
								exeApp.mi_menu(NoPremium, ejercicio);	// entramos en la app si existe
							}else {
								imprimir(CADENA_ERROR_INCICIO_SESION);
							}
						}else if(opc == KEY_NO_ENCONTRADO) {	// no existe el usuario
							imprimir(CADENA_NO_EXISTE);
						}						
					break;
				case KEY_EXIT:
					imprimir(CADENA_SALIDA);	// salimos de la aplicación
					System.exit(-1);
				default:
					imprimir(CADENA_OPC_INEXISTENTE);
					break;
			}
		}
	}
}
