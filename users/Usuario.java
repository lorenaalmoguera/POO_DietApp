package users;
import runApp.main;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import misActividades.Actividades;
import misActividades.ActividadesCycling;
import misActividades.ActividadesRunning;
import misActividades.ActividadesSwimming;

/**
 * Clase padre Usuario que implementa la serializacion
* @author Lorena Almoguera Romero Dni: 48796558B
 *
 */
public class Usuario implements Serializable{
	
	// Práctica realizada por Lorena Almoguera Romero
	//	--------------		ARRAY			-----------------	//
	public static ArrayList<Usuario> users = new ArrayList<Usuario>();
	public ArrayList<String> weightHistory;
	public ArrayList<Actividades> misactividades = new ArrayList<Actividades>();
	//	---------------		MENSAJES 		-----------------	//
	
	private static final String CADENA_INTRODUCIDO_MENSAJE = "Has introducido: ";
	private static final String CADENA_NOMBRE = "Introduce tu nombre: ";
	private static final String CADENA_APELLIDO = "Introduce tu Apellido: ";
	private static final String CADENA_ALIAS = "Introduce tu Alias: ";
	private static final String CADENA_FECHA_NACIMIENTO = "Introduce tu fecha de nacimiento: ";
	private static final String CADENA_PESO ="Introduce tu peso: ";
	private static final String CADENA_ALTURA = "Introduce tu altura: ";
	private static final String CADENA_INTROD_CONTRASENA ="Introduce tu contraseña: ";
	private static final String CADENA_REPITE_CONTRASENA ="Introduce tu contraseña una vez más: ";
	private static final String CADENA_CONTRASENA_NO_COINCIDEN ="Las contraseñas introducidas no coinciden.";
	private static final String CADENA_FORMATO_FECHA ="Recuerda que el formato es dd/mm/aaaa ";
	private static final String CADENA_ACTIVIDAD = "Indica si eres: (1)- Persona Sedentaria, (2)- Actividad Ligera [1 a 3 dias], (3)- Actividad Moderada [3 a 5 dias], (4)- Actividad Intensa [6 a 7 dias], (5)- Actividad Extremadamente Alta [Atleta]";
	private static final String CADENA_SEDENTARIA = "Has indicado que eres una persona sedentaria.";
	private static final String CADENA_LIGERA = "Has indicado que realizas actividades de 1 a 3 veces por semana";
	private static final String CADENA_MODERADA ="Has indicado que realizas actividades de 3 a 5 veces por semana";
	private static final String CADENA_INTENSA ="Has indicado que realizas actividades de 6 a 7 veces por semana";
	private static final String CADENA_EXTREMA ="Has indicado que realizas actividades con una frequencia muy alta. (Atleta)";
	private static final String CADENA_AVISO_PREMIUM = "Si deseas darte de alta como usuario premium, lo podrás hacer más adelante.";
	private static final String CADENA_MENU_SEXO = "Indica si eres: (1)- Hombre, (2)- Mujer";
	private static final String CADENA_OPC_SELECT = "Usted ha seleccionado la opcion: ";
	private static final String CADENA_BAJO_PESO = "Usted se encuentra por debajo de su peso.";
	private static final String CADENA_EN_SU_LINEA = "Usted se encuentra en su linea.";
	private static final String CADENA_SOBREPESO = "Usted tiene sobrepeso.";
	private static final String CADENA_OBESIDAD = "Usted tiene obesidad.";
	private static final String CADENA_DOS_APELLIDOS ="Indique la cantidad de apellidos que posee: (1)- Usted tiene 1 apellido, (2)- Usted tiene 2 apellidos";
	private static final String CADENA_PASSWORD_ACTUAL ="Por favor, introduzca su contraseña actual: ";
	private static final String CADENA_YA_PREMIUM = "Esta cuenta ya es una cuenta premium!";
	private static final String CADENA_SUCCESS_PREMIUM = "ÉXITO AL ACTUALIZAR EL ESTADO DE SU CUENTA A UNA CUENTA PREMIUM!";
	private static final String CADENA_FECHA_PESO ="Introduce la fecha en la cual te pesaste: ";
	private static final String CADENA_HA_EXPIRADO ="Su suscribción premium ha expirado";
	private static final String CADENA_NO_HA_EXPIRADO ="Su suscribción premium no ha expirado";
	private static final String CADENA_PATRON_PASSWORD ="Recuerda que tu contraseña solo puede incluir letras y números";

	//	---------------		MENSAJES ERROR 		-----------------	//
	private static final String CADENA_ERROR_NOMBRE = "ERROR: Formato INCORRECTO. Tu nombre solo puede contener letras.";
	private static final String CADENA_ERROR_APELLIDO = "ERROR: Formato INCORRECTO. Tu apellido solo puede contener letras.";
	private static final String CADENA_ERROR_USUARIO_EXISTENTE = "ERROR: El usuario introducido ya existe. Por favor, introduzca otro alias.";
	private static final String CADENA_ERROR_FORMATO_FECHA ="ERROR: Formato INCORRECTO. Por favor, vuelve a introducir la fecha.";
	private static final String CADENA_ERROR_NUM ="ERROR: Eso no es un número. Por favor, introduce de nuevo la opcion deseada";
	private static final String CADENA_ERROR_OPC = "ERROR: Opcion no valida";
	private static final String CADENA_ERROR_LENGTH = "ERROR: Longitud de cadena no valida";
	private static final String CADENA_ERROR_LOGIN = "ERROR: Error al introducir el alias o la contraseña. Intentos restantes: ";
	private static final String CADENA_ERROR_PATRON = "ERROR: Formato de la contraseña incorrecto. Por favor, intentelo de nuevo.";
	private static final String CADENA_ERROR_VALOR_BAJO ="ERROR: Valor númerico demasiado pequeño";
	private static final String CADENA_ERROR_CREDENCIALES_INCORRECTAS ="ERROR: Credenciales incorrectas";
	//	---------------		IMPRESION INFORME	-------------	//
	private static final String CADENA_PESO_INICIAL = "Peso inicial: ";
	private static final String CADENA_PESO_ACTUAL = "Peso actual: ";
	private static final String CADENA_HISTORIAL_PESO = "Historial de peso: ";
	private static final String CADENA_LISTADO_ACTIVIDADES = "Actividades realizadas: ";
	private static final String CADENA_TIPO_PREMIUM ="Tipo de cuenta: PREMIUM ";
	private static final String CADENA_TIPO_NORMAL ="Tipo de cuenta: NO PREMIUM";
	private static final String CADENA_ACTIVIDAD_GENERICA ="Tipo de actividad realizada: Genérica";
	private static final String CADENA_ACTIVIDAD_RUNNING ="Tipo de actividad realizada: Running";
	private static final String CADENA_ACTIVIDAD_SWIMMING ="Tipo de actividad realizada: Swimming";
	private static final String CADENA_ACTIVIDAD_CYCLING ="Tipo de actividad realizada: Cycling";
	private static final String CADENA_0_ACTIVIDADES ="No hay actividades registradas";
	private static final String CADENA_TOTAL_PESOS_INTROD = "Total de pesos introducidos: ";
	private static final String CADENA_TOTA_ACTIVIDADES_INTROD = "Total de actividades registradas: ";
	private static final String CADENA_FIN_INFORME = "**** FIN DEL INFORME ****";
	
	//	---------------		VARIABLES 		-----------------	//
	private static LocalDate fechaHoy = LocalDate.now();
	private String nombre, apellido, alias , password, fecha_nacimiento, sexo;
	private int edad, actividad;
	private double IMC, altura, peso, kcal;
	final static int KEY_SEDENTARIO = 1, KEY_LIGERA = 2, KEY_MODERADA = 3, KEY_INTENSA = 4, KEY_EXTREMA = 5, KEY_V = 1, KEY_M = 2;
	final static int KEY_ES_PREMIUM = 1, KEY_USUARIO = 2;
	
	//	---------------		FUNCIONES RELACIONADAS CON EL REGISTRO DEL USUARIO 		-----------------	//
	
	/**
	 * Funcion que sirve para la impresion de mensajes por pantalla
	 * @param mensaje a imprimir
	 */
	public static void imprimir(String mensaje) {
		System.out.println(mensaje);
	}
	
	/**
	 * Funcion que sirve para pedirle los datos al usuario
	 * @param miUsuario objeto
	 */
	public void pedir_datos(Usuario miUsuario) {
		String nombre, apellido_final, apellido1, apellido2, input, password, alias, fecha, sexo, weightndate;
		int edad, actividad, num_aux = 0, opcion_int;
		double imc, peso, altura, kcal;
		
		imprimir(CADENA_AVISO_PREMIUM);
		
		nombre = pedir_nombre();
		
		imprimir(CADENA_DOS_APELLIDOS);
		input = main.scanIn.nextLine();
    	
    	while(num_aux > 2 || num_aux < 1) {
    		while(isNumeric(input) != true) {
    			imprimir(CADENA_ERROR_NUM);
    			input = main.scanIn.nextLine();
    		}
    		while(input.length()>1) {
    			imprimir(CADENA_ERROR_LENGTH);
    			input = main.scanIn.nextLine();
    		}
    		num_aux = Integer.parseInt(input);
        	if(num_aux > 2 || num_aux <1) {
    			imprimir(CADENA_ERROR_OPC);
    			input = main.scanIn.nextLine();
    		}
    	}
 
    	System.out.println(CADENA_OPC_SELECT + input);
    	opcion_int = Integer.parseInt(input);
		
		if(opcion_int == 1) {
			apellido_final = pedir_apellido();
			setApellido(apellido_final);
		}else if(opcion_int == 2) {
			apellido1 = pedir_apellido();
			apellido2 = pedir_apellido();
			apellido_final = apellido1 + " " + apellido2;
			setApellido(apellido_final);
		}

		sexo = pedir_sexo();
		alias = pedir_alias();
		while(check_existing(alias) == true) {
			imprimir(CADENA_ERROR_USUARIO_EXISTENTE);
			alias = pedir_alias();
		}		
		imprimir(CADENA_FECHA_NACIMIENTO);
		fecha = pedir_fecha();
		edad = pillar_edad(fecha, fechaHoy);
		altura = pedir_altura();
		peso = pedir_peso();
		imc = calcular_imc(altura, peso);
		actividad = pedir_actividad();
		imprimir(CADENA_PATRON_PASSWORD);
		password = establecer_password();
		while(isValidPasswordRegular(password) == false) {
			imprimir(CADENA_ERROR_PATRON);
			password = establecer_password();
		}
		
		setNombre(nombre);
		setSexo(sexo);
		setFecha_nacimiento(fecha);
		setEdad(edad);
		setAlias(alias);
		setActividad(actividad);
		setPeso(peso);
		setAltura(altura);
		setIMC(imc);
		setPassword(password);
		setWeightHistory(new ArrayList<String>());
		setMisactividades(new ArrayList<Actividades>());
		kcal = Actividades.calcular_calorias_necesarias(miUsuario);
		setKcal(kcal);
		weightndate = PesoYFecha(peso);
		meterEnWeightArray(weightndate, miUsuario);
	}
	

    /**
     * Funcion que le pide el nombre al usuario
     * @return String
     */
    public static String pedir_nombre() {
    	
    	String name;
    	imprimir(CADENA_NOMBRE);
    	name = main.scanIn.nextLine();
    	
    	while(Pattern.matches("[a-zA-Z]+",name) != true) { 
    		imprimir(CADENA_ERROR_NOMBRE);
    		  name = main.scanIn.nextLine();
    		}    	
    	return name;
    }
    
    /**
     * Funcion que pide el sexo del usuario
     * @return devuelve el sexo del usuario
     */
    public static String pedir_sexo() {
    	
    	int opcion_int, num_aux = 0;
    	String gender, hombre = "V", mujer = "M";
    	
    	imprimir(CADENA_MENU_SEXO);
    	
    	gender = main.scanIn.nextLine();
    	
    	while(num_aux > 2 || num_aux < 1) {
    		while(isNumeric(gender) != true) {
    			imprimir(CADENA_ERROR_NUM);
    			gender = main.scanIn.nextLine();
    		}
    		while(gender.length()>1) {
    			imprimir(CADENA_ERROR_LENGTH);
    			gender = main.scanIn.nextLine();
    		}
    		num_aux = Integer.parseInt(gender);
        	if(num_aux > 2 || num_aux <1) {
    			imprimir(CADENA_ERROR_OPC);
    			gender = main.scanIn.nextLine();
    		}
    	}
    	imprimir(gender);
    	opcion_int = Integer.parseInt(gender);
    	switch(opcion_int) {
    		case KEY_V: 
    			imprimir(CADENA_OPC_SELECT + opcion_int);
    			gender = hombre;
    			break;
    		case KEY_M: 
    			imprimir(CADENA_OPC_SELECT + opcion_int);
    			gender = mujer;
    			break;
    	}
    	return gender;
    }
    /**
     * Funcion que le pide el apellido al usuario
     * @return String
     */
    public static String pedir_apellido() {
    	
    	String lastname;
    	imprimir(CADENA_APELLIDO);
    	lastname = main.scanIn.nextLine();
    	
    	while(Pattern.matches("[a-zA-Z]+",lastname) != true) { 
    		imprimir(CADENA_ERROR_APELLIDO);
    		  lastname = main.scanIn.nextLine();
    		}    	
    	return lastname;
    }
    
    /**
     * Funcion que pide el Alias del Usuario
     * @return devuelve el alias
     */
    public String pedir_alias() {
    	
    	String alias;
    	imprimir(CADENA_ALIAS);
    	alias = main.scanIn.nextLine();
    	
    	while(alias.length()<1 == true) {
    		imprimir(CADENA_ALIAS);
    		alias = main.scanIn.nextLine();
    	}
    	return alias;
    }
    
    /**
	 * funcion que pide y comprueba si la fecha introducida por el usuario es valida.
	 * @return devuelve la fecha
	 */
	public static String pedir_fecha() {
		
		String fecha; 
		imprimir(CADENA_FORMATO_FECHA);
		fecha = main.scanIn.nextLine();
		
		imprimir(CADENA_INTRODUCIDO_MENSAJE + fecha);
		
		while(validarfecha(fecha) == false) {
			imprimir(CADENA_ERROR_FORMATO_FECHA);
			imprimir(CADENA_FORMATO_FECHA);
			fecha = main.scanIn.nextLine();
			imprimir(CADENA_INTRODUCIDO_MENSAJE + fecha);
		}
		while(validarhoy(fecha) == false) {
			imprimir(CADENA_ERROR_FORMATO_FECHA);
			imprimir(CADENA_FORMATO_FECHA);
			fecha = main.scanIn.nextLine();			
			imprimir(CADENA_INTRODUCIDO_MENSAJE + fecha);
		}
		return fecha;
	}
	
	/**
	 * Funcion que calcula la edad del usuario y devuelve la edad
	 * @param fecha_string nacimiento
	 * @param fechaHoy actual
	 * @return int edad
	 */
	public static int pillar_edad(String fecha_string, LocalDate fechaHoy) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate fecha = LocalDate.parse(fecha_string,formatter);
		
		int age = Period.between(fecha, fechaHoy).getYears();
		
		return age;
	}
	
	/**
	 * Funcion que le pide el peso al usuario
	 * @return devuelve el peso tras validarlo
	 */
	public static double pedir_peso() {
		double peso;
		
		imprimir(CADENA_PESO);
		String input = main.scanIn.nextLine();
		
		while(isNumericDouble(input)!= true) {
			imprimir(CADENA_ERROR_NUM);
			input = main.scanIn.nextLine();
		}
		peso = Double.parseDouble(input);
		
		while(peso < 30) {
			imprimir(CADENA_ERROR_VALOR_BAJO);
			while(isNumericDouble(input)!= true) {
				imprimir(CADENA_ERROR_NUM);
				input = main.scanIn.nextLine();
			}
			peso = Double.parseDouble(input);
		}
		
		peso = Double.parseDouble(input);
		return peso;
		
	}
	
	/**
	 * Funcion que pide la altura al usuario
	 * @return devuelve la altura tras validarla
	 */
	public static double pedir_altura() {
		double altura;
		
		imprimir(CADENA_ALTURA);
		String input = main.scanIn.nextLine();
		
		while(isNumericDouble(input)!= true) {
			imprimir(CADENA_ERROR_NUM);
			input = main.scanIn.nextLine();
		}
		altura = Double.parseDouble(input);
		while(altura < 30) {
			imprimir(CADENA_ERROR_VALOR_BAJO);
			while(isNumericDouble(input)!= true) {
				imprimir(CADENA_ERROR_NUM);
				input = main.scanIn.nextLine();
			}
			altura = Double.parseDouble(input);
		}
		return altura;
		
	}
	

    /**
     * Funcion que calcula el IMC
     * @param altura del usuario
     * @param peso del usuario
     * @return double imc calculado
     */
	public static double calcular_imc(double altura, double peso) {
		
		double IMC, altura_val = altura/100;
		IMC = peso/(altura_val*altura_val);
		System.out.println("Su IMC es: " + IMC);
		
		if(IMC < 18.5) {
			imprimir(CADENA_BAJO_PESO);
		}else if( 18.5 <= IMC  && IMC < 25 ) {
			imprimir(CADENA_EN_SU_LINEA);
		}else if(25 <= IMC && IMC < 30) {
			imprimir(CADENA_SOBREPESO);
		}else if(30 <= IMC) {
			imprimir(CADENA_OBESIDAD);
		}
		return IMC;
	}
	
	/**
	 * Funcion que pide el tipo de actividad al usuario
	 * @return int para declarar tipo de actividad del usuario
	 */
	public static int pedir_actividad() {
		int actividad, opcion = 0;
		String input;
		
		imprimir(CADENA_ACTIVIDAD);
		input = main.scanIn.nextLine();
		
		while(opcion > 5 || opcion < 1) {
			while(isNumeric(input)!= true) {
				imprimir(CADENA_ERROR_NUM);
				input = main.scanIn.nextLine();
			
				while(input.length()>1) {
					imprimir(CADENA_ERROR_LENGTH);
				input = main.scanIn.nextLine();
				}
			}
			opcion = Integer.parseInt(input);
			if(opcion > 5 || opcion < 1) {
				imprimir(CADENA_ERROR_OPC);
				input = main.scanIn.nextLine();
			}
		}
		opcion = Integer.parseInt(input);
		System.out.println("");
		actividad = opcion;
		
		switch(actividad) {
		case KEY_SEDENTARIO:
			imprimir(CADENA_SEDENTARIA);
			return actividad;
		case KEY_LIGERA:
			imprimir(CADENA_LIGERA);
			return actividad;
		case KEY_MODERADA:
			imprimir(CADENA_MODERADA);
			return actividad;
		case KEY_INTENSA:
			imprimir(CADENA_INTENSA);
			return actividad;
		case KEY_EXTREMA:
			imprimir(CADENA_EXTREMA);
			return actividad;
		}
		return actividad;
	}

	
	/**
	 * Funcion que devuelve un string con el peso y la fecha actual
	 * @param peso del usuario
	 * @return string de peso y fecha resultante
	 */
	public static String PesoYFecha(double peso) {
		imprimir(CADENA_FECHA_PESO);
		String resultante, fecha = pedir_fecha();
		resultante = "Peso: " + peso + " KG " + "  " + "(" + fecha + ")";
		return resultante;
	}
	
	/**
	 * Funcion que sirve para actualizar el peso, y recalcular el imc. añade el peso al array
	 * @param miUsuario objeto
	 */
	public void actualizarPeso(Usuario miUsuario) {
		double imc;
		double peso = pedir_peso();
		miUsuario.setPeso(peso);
		String pesoYFecha = PesoYFecha(peso);
		meterEnWeightArray(pesoYFecha, miUsuario);
		imc = calcular_imc(miUsuario.getAltura(), miUsuario.getPeso());
		miUsuario.setIMC(imc);
	}

	
	/**
	 * Funcion que veuvele la fecha de hoy
	 * @return devuelve la fecha como un string
	 */
	public static String DameLaFecha() {
		SimpleDateFormat formatter = new SimpleDateFormat ("dd-MM-yyyy");
		Date date = new Date();
		return formatter.format(date);
	}
	
  


	//	-------------		FUNCIONES RELACIONADAS CON EL MANEJO DE USUARIOS Y ARRAYS				---------------		//
	 
	
	/**
	 * Funcion que permite al usuario meter el peso en el array de su peso
	 * @param WeightnDate fecha y peso a meter
	 * @param usuario objeto
	 */
	public void meterEnWeightArray(String WeightnDate, Usuario usuario) {
		ArrayList<String> weightHistory = usuario.getWeightHistory();
		weightHistory.add(WeightnDate);
	}
	
	/**
	 * Funcion que permite al usuario meter la actividad realizada en su array de actividades
	 * @param ejercicio objeto
	 * @param usuario objeto
	 */
	public void meterEnActividadesArray(Actividades ejercicio, Usuario usuario) {
		ArrayList <Actividades> historialActividades = usuario.getMisactividades();
		historialActividades.add(ejercicio);
	}
	
	 /**
     * Funcion que añade al usuario al array de usuarios
     * @param miusuario objeto
     */
    public static void add_user(Usuario miusuario) {
    	users.add(miusuario);
    }
	
	 /**
     * Funcion que comprueba si el usuario ya es existente
     * @param alias del usuario
     * @return devuelve un booleano
     */
   	 public boolean check_existing(String alias) {
   		 for(Usuario usuario: users) {
   			 if(usuario.getAlias().equals(alias)) {
   				 return true;
   			 }
   		 }
   		 return false;
  
    }
   	 
   	/**
   	 * Funcion que comprueba si el usuario es un usuario normal, o si el usuario es un usuario de tipo premium
   	 * @param alias del usuario
   	 * @return devuelve un int (1 es premium, 2 es normal, -1 no existe)
   	 */
   	public int devolverTipoUsuario(String alias) {
        for (Usuario user : users) {
            if (user.getAlias().equals(alias)) {
                if (user instanceof UsuarioPremium) {
                    return 1;
                } else {
                    return 2;
                }
            }
        }
        return -1;
    }
	
	/**
	 * Funcion para actualizar el estado de cuenta de no premium a premium
	 * @param miuser objeto
	 */
	public void upgradeToPremium(Usuario miuser) {
		Usuario miusuario = buscarUsuario(miuser.getAlias());
		int i;
		if(miusuario == null) {
			return;
		}if(miusuario instanceof UsuarioPremium) {
			imprimir(CADENA_YA_PREMIUM);
		}
		i = miuser.devolverTipoUsuario(miuser.getAlias());
		if(i == 1) {
			UsuarioPremium premium = (UsuarioPremium) miuser;
			if(premium.haExpiradoLaCuenta(premium) == true) {
				imprimir(CADENA_HA_EXPIRADO);
			}else {
				imprimir(CADENA_NO_HA_EXPIRADO);
				return;
			}
		}
		UsuarioPremium mipremium = new UsuarioPremium();
		mipremium.gatherInformation(miusuario, mipremium);
		users.add(mipremium);
		users.remove(miuser);
		imprimir(CADENA_SUCCESS_PREMIUM);
	}
	
	/**
	 * Funcion para buscar la existencia de un usuario
	 * @param alias
	 * @return devuelve el usuario
	 */
	private Usuario buscarUsuario(String alias) {
	    for (Usuario miusuario : users) {
	        if (miusuario.getAlias().equals(alias)) {
	            return miusuario;
	        }
	    }
	    return null;
	}
	
	
	//	--------------		FUNCIONES RELACIONADAS CON LA IMPRESION DE LOS DATOS					----------------	//
    /**
     * Funcion auxiliar que sirve para imprimir todos los alias registrados
     */
    public void printAllUsers() {
    	for(int i = 0; i < users.size(); i++) {
    		Usuario usuario = users.get(i);
    		String alias = usuario.getAlias();
            System.out.println(alias);
        }
    	System.out.println("Total de usuarios: " + users.size());
    }
    
	/**
	 * Funcion que imprime los datos del usuario
	 * @param miUsuario objeto
	 */
	public void impresionDatos(Usuario miUsuario) {
		String password_aux = "";
		for( int i = 0 ; i < miUsuario.getPassword().length() ; i++) {
			password_aux = password_aux + "*";
		}
		
		imprimir("Gracias por darte de alta con nosotros: " + miUsuario.getAlias());
		
		imprimir("------------------------------------");
		
		imprimir("Nombre: " + miUsuario.getNombre());
		imprimir("Apellido(s): " + miUsuario.getApellido());
		imprimir("Sexo: " + miUsuario.getSexo());
		imprimir("Contraseña: " + password_aux + " - longitud (" + miUsuario.getPassword().length() + ")" );
		imprimir("Fecha de Nacimiento: " + miUsuario.getFecha_nacimiento());
		imprimir("Edad: " + miUsuario.getEdad());
		imprimir("Altura: " + miUsuario.getAltura() + "CM");
		imprimir("Peso: " + miUsuario.getPeso() + "KG");
		imprimir("IMC: " + miUsuario.getIMC());
		imprimir("Nivel de actividad: " + miUsuario.getActividad());	
		imprimir("KCAL necesarias: "  + miUsuario.getKcal());
	}
	
	

	/**
	 * Funcion que te imprime el informe del usuario por pantalla
	 * @param usuario objeto
	 * @param ejercicio objeto
	 */
	public void mostrarInforme(Usuario usuario, Actividades ejercicio) {
		
		ArrayList<String> historialpeso = usuario.getWeightHistory();
		ArrayList<Actividades> historialactividades = usuario.getMisactividades();
		//imprimir("historialpeso: " + historialpeso);
		//imprimir("historialactividades: " + historialactividades);
		String primer_peso = historialpeso.get(0);
		String ultimo_peso = historialpeso.get(historialpeso.size()-1);
		int tipoactividad, tipousuario;
		
		tipousuario = devolverTipoUsuario(usuario.getAlias());	// comprobamos si es premium o normal
		if(tipousuario == 1) {	// si es premium
			imprimir(CADENA_TIPO_PREMIUM);
			UsuarioPremium premium = (UsuarioPremium) usuario;
			premium.impresionDatos(premium);
			premium.imprimirDatosPremium(premium);
			imprimir(" ");
		}else if(tipousuario == 2) {	// si es normal
			imprimir(CADENA_TIPO_NORMAL);
			usuario.impresionDatos(usuario);
			imprimir(" ");
		}
		imprimir(CADENA_TOTAL_PESOS_INTROD + historialpeso.size());	// total de pesos introducidos
		imprimir(CADENA_PESO_INICIAL + primer_peso);	// peso inicial
		imprimir(CADENA_PESO_ACTUAL + ultimo_peso);	// peso actual
		imprimir(CADENA_HISTORIAL_PESO);	// imprimimos el historial de pesos
		for (int i = 0; i < historialpeso.size(); i++) {
			  imprimir("Peso " + i + ": " + historialpeso.get(i));
			}
		imprimir(CADENA_TOTA_ACTIVIDADES_INTROD + historialactividades.size());	// total de actividades introducidas

		imprimir(CADENA_LISTADO_ACTIVIDADES);	
		if(historialactividades.size() == 0) {	
			imprimir(CADENA_0_ACTIVIDADES);	// si no hay actividades
		}else {	// si hay actividades
			for(int i = 0 ; i <historialactividades.size();i++) {	// imprimimos el historial de todas las actividades realizadas
				tipoactividad = ejercicio.devolverTipoActividad(historialactividades.get(i)); // descubrimos que tipo de actividad es
				Actividades actividad = historialactividades.get(i);	
				if(tipoactividad == 0) {
					imprimir(CADENA_ACTIVIDAD_GENERICA);	
					actividad.imprimirDatosActividad(actividad);
					imprimir(" ");
				}else if(tipoactividad == 1) {
					imprimir(CADENA_ACTIVIDAD_RUNNING);
					ActividadesRunning running = (ActividadesRunning) actividad;	// si es running convertimos el objeto a running
					running.imprimirDatosRunning(running);	// imprimimos sus datos
					imprimir(" ");
				}else if(tipoactividad == 2) {
					imprimir(CADENA_ACTIVIDAD_SWIMMING);
					ActividadesSwimming swimming = (ActividadesSwimming) actividad; // si es swimming convertimos el objeto a swimming
					swimming.imprimirDatosSwimming(swimming);
					imprimir(" ");
				}else if(tipoactividad == 3) {
					imprimir(CADENA_ACTIVIDAD_CYCLING);
					ActividadesCycling cycling = (ActividadesCycling) actividad; // si es cycling convertimos el objeto a cycling
					cycling.imprimirDatosCycling(cycling);
					imprimir(" ");
				}
				
			}
			imprimir(CADENA_FIN_INFORME );
		}
	}

    
//	---------------		FUNCIONES RELACIONADAS CON EL INICIO DE SESIÓN Y LAS CONTRASEÑAS 		-----------------	//
    
	/**
	 * Funcion para cambiar la contraseña
	 * @param miusuario (valida para premium y no premium)
	 * @return devuelve la contraseña para setearla
	 */
	public String cambiarPassword(Usuario miusuario) {
		String alias = miusuario.getAlias();
		String passwordactual = miusuario.getPassword();
		String newpassword, currentpassword;
		int intentos = 3, tipo;
		
		tipo = devolverTipoUsuario(alias);
		
		while(intentos > 0) {
			
			imprimir(CADENA_PASSWORD_ACTUAL);
			currentpassword = main.scanIn.nextLine();
			if(currentpassword.equals(passwordactual)) {
				break;
			}else {
				imprimir(CADENA_ERROR_CREDENCIALES_INCORRECTAS + intentos);
				
				intentos = intentos -1;
				imprimir("intentos: " + intentos);
			}
		}
		
		if(intentos == 0) {
			return null;
		}
		
		// comprobamos si es premium
		if(tipo == KEY_ES_PREMIUM) {
			System.out.println("hola");
			newpassword = establecer_password_premium();	// establecemos la contraseña con el criterio premium
			while(isValidPassword(newpassword) == false) {	// validamos la contraseña
				imprimir(CADENA_ERROR_PATRON);
				newpassword = establecer_password_premium();
			}
			return newpassword;
		}else if(tipo == KEY_USUARIO) {	// establecemos la contraseña con el criterio normal
			newpassword = establecer_password();
			while(isValidPasswordRegular(newpassword) == false) {	// validamos la contraseña
				imprimir(CADENA_ERROR_PATRON);
				newpassword = establecer_password();
			}
			return newpassword;
		}
		
		return null;
	}
    
	/**
	 * Funcion que establece la contraseña string
	 * @return devuelve la contraseña tras validarla en formato string
	 */
	public static String establecer_password() {
		String password, password_check;
		
		imprimir(CADENA_INTROD_CONTRASENA);
		password = main.scanIn.nextLine();
		imprimir(CADENA_REPITE_CONTRASENA);
		password_check = main.scanIn.nextLine();
		
			while(password.length()<6 || password_check.length()<6) {
				imprimir(CADENA_ERROR_LENGTH);
				imprimir(CADENA_INTROD_CONTRASENA);
	    		password = main.scanIn.nextLine();
	    		imprimir(CADENA_REPITE_CONTRASENA);
	        	password_check = main.scanIn.nextLine();
	    		while(password.equals(password_check) != true) {
	    			imprimir(CADENA_CONTRASENA_NO_COINCIDEN);
	    			imprimir(CADENA_INTROD_CONTRASENA);
	    			password = main.scanIn.nextLine();
	    			imprimir(CADENA_REPITE_CONTRASENA);
	    			password_check = main.scanIn.nextLine();
	    		}
			}
			while(password_check.equals(password)!= true) {
				imprimir(CADENA_CONTRASENA_NO_COINCIDEN);
				imprimir(CADENA_INTROD_CONTRASENA);
				password = main.scanIn.nextLine();
				imprimir(CADENA_REPITE_CONTRASENA);
				password_check = main.scanIn.nextLine();
				while(password.length()<6 || password_check.length()<6) {
					imprimir(CADENA_ERROR_LENGTH);
					imprimir(CADENA_INTROD_CONTRASENA);
					password = main.scanIn.nextLine();
					imprimir(CADENA_REPITE_CONTRASENA);
					password_check = main.scanIn.nextLine();
					while(password.equals(password_check) != true) {
						imprimir(CADENA_CONTRASENA_NO_COINCIDEN);
						imprimir(CADENA_INTROD_CONTRASENA);
						password = main.scanIn.nextLine();
						imprimir(CADENA_REPITE_CONTRASENA);
	    				password_check = main.scanIn.nextLine();
					}
			}	
		}
			return password;
	}
	
	/**
	 * Funcion que establece la contraseña para usuarios premium
	 * @return string de la contraseña
	 */
	public static String establecer_password_premium() {
		String password, password_check;
		
		imprimir(CADENA_INTROD_CONTRASENA);
		password = main.scanIn.nextLine();
		imprimir(CADENA_REPITE_CONTRASENA);
		password_check = main.scanIn.nextLine();
		
			while(password.length()<8 || password_check.length()<8) {
				imprimir(CADENA_ERROR_LENGTH);
				imprimir(CADENA_INTROD_CONTRASENA);
	    		password = main.scanIn.nextLine();
	    		imprimir(CADENA_REPITE_CONTRASENA);
	        	password_check = main.scanIn.nextLine();
	    		while(password.equals(password_check) != true) {
	    			imprimir(CADENA_CONTRASENA_NO_COINCIDEN);
	    			imprimir(CADENA_INTROD_CONTRASENA);
	    			password = main.scanIn.nextLine();
	    			imprimir(CADENA_REPITE_CONTRASENA);
	    			password_check = main.scanIn.nextLine();
	    		}
			}
			while(password_check.equals(password)!= true) {
				imprimir(CADENA_CONTRASENA_NO_COINCIDEN);
				imprimir(CADENA_INTROD_CONTRASENA);
				password = main.scanIn.nextLine();
				imprimir(CADENA_REPITE_CONTRASENA);
				password_check = main.scanIn.nextLine();
				while(password.length()<8 || password_check.length()<8) {
					imprimir(CADENA_ERROR_LENGTH);
					imprimir(CADENA_INTROD_CONTRASENA);
					password = main.scanIn.nextLine();
					imprimir(CADENA_REPITE_CONTRASENA);
					password_check = main.scanIn.nextLine();
					while(password.equals(password_check) != true) {
						imprimir(CADENA_CONTRASENA_NO_COINCIDEN);
						imprimir(CADENA_INTROD_CONTRASENA);
						password = main.scanIn.nextLine();
						imprimir(CADENA_REPITE_CONTRASENA);
	    				password_check = main.scanIn.nextLine();
					}
			}	
		}
			return password;
	}
    
    /**
     * Funcion que permite al usuairo iniciar sesión
     * @param miusuario alias
     * @return usuario premium
     */
    public Usuario iniciar_sesion(String miusuario) {
    
    	String mipassword = null;
    	int intentos = 3;
    	
    	while(intentos > 0) {
			System.out.println(CADENA_INTROD_CONTRASENA);
			mipassword = main.scanIn.nextLine();
			
			if(login(miusuario,mipassword)==null) {
				
				intentos = intentos - 1;
				imprimir(CADENA_ERROR_LOGIN + intentos);
			}else {
				
				return login(miusuario, mipassword);
			}
			
		}
    	miusuario = null;
    	return login(miusuario, mipassword);
    	
    }
    
    /**
     * Funcion para iniciar sesion como usuario premium
     * @param miusuario objeto
     * @return devuelve el usuario premium
     */
    public UsuarioPremium iniciar_sesion_premium(String miusuario) {

    	String mipassword = null;
    	int intentos = 3;
    	
    	while(intentos > 0) {
    		System.out.println(CADENA_INTROD_CONTRASENA);
    		mipassword = main.scanIn.nextLine();
    		
    		if(login(miusuario,mipassword)==null) {
    			intentos = intentos - 1;
    			imprimir(CADENA_ERROR_LOGIN + intentos);
    		}else {
    			
    			return login_premium(miusuario, mipassword);
    		}
    		
    	}
    	miusuario = null;
    	return login_premium(miusuario, mipassword);
    }
    
    /**
     * Funcion que comprueba si el usuario y la contraseña existen
     * @param username usuario
     * @param password contraseña
     * @return devuelve un booleano
     */
    public static boolean check_existing_password(String username, String password) {
    	String mipassword;
    	String miusuario;
    	//System.out.println("este es mi arraysize: " + misjugadores.size());
    	
    	for( int i = 0 ; i < users.size() ; i++) {
    		Usuario usuario = users.get(i);
    		miusuario = usuario.getAlias();
    		mipassword = usuario.getPassword();
    		//System.out.println("miusuario" + miusuario);
    		//System.out.println("que pilla" + mipassword);
    		
    		if(mipassword.equals(password) == true && miusuario.equals(username) == true){
    			return true;
    		}
    	}
    	return false;
    }
    
    /**
     * Funcion que sirve para iniciar sesión, devolviendo el usuario 
     * @param alias del usuario
     * @param password del usuario
     * @return usuario encontrado
     */
    public Usuario login(String alias, String password) {
    	 for(Usuario user: users) {
    		 if(user.getAlias().equals(alias) && user.getPassword().equals(password)) {
    			 return user;
    		 }
    	 }
    	 return null;
    }
    
    /**
     * Funcion que sirve para iniciar seison para premium, evolviendo el usuario premium
     * @param alias del usuario
     * @param password del usuario
     * @return objeto del usuario
     */
    public UsuarioPremium login_premium(String alias, String password) {
    	for(Usuario user: users) {
   		 if(user instanceof UsuarioPremium && user.getAlias().equals(alias) && user.getPassword().equals(password)) {
   			 return (UsuarioPremium) user;
   		 }
   	 }
   	 return null;
   }
  
	
//	---------------		FUNCIONES PARA VALIDAR 		-----------------	//
	
	/**
	 * Funcion que sirve para validar su formato
	 * @param password del usuario
	 * @return boolean
	 */
    public static boolean isValidPassword(String password) {
        boolean hasLowercase = false;	// tiene minuscula
        boolean hasUppercase = false;	// tiene mayuscula
        boolean hasNumber = false;		//tiene numeros
        boolean hasSpecialChar = false;	//tiene caracter especial
        
        // tiene uno de cada
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (Character.isLowerCase(c)) {
                hasLowercase = true;
            } else if (Character.isUpperCase(c)) {
                hasUppercase = true;
            } else if (Character.isDigit(c)) {
                hasNumber = true;
            } else if (!Character.isLetterOrDigit(c)) {
                hasSpecialChar = true;
            }
        }
        
        return hasLowercase && hasUppercase && hasNumber && hasSpecialChar;
    }
	
    /**
     * Funcion que comprueba si cumple las condiciones para una contraseña normal
     * @param password contraseña del usuario
     * @return booleano
     */
    public static boolean isValidPasswordRegular(String password) {
        boolean hasLowercase = false;
        boolean hasUppercase = false;
        boolean hasNumber = false;
        
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (Character.isLowerCase(c)) {
                hasLowercase = true;
            } else if (Character.isUpperCase(c)) {
                hasUppercase = true;
            } else if (Character.isDigit(c)) {
                hasNumber = true;
            } else if (!Character.isLetterOrDigit(c)) {
                return false;  // Return false if any special character is found
            }
        }
        
        return hasLowercase && hasUppercase && hasNumber;  // Return true if it has upper, lower and numbers
    }
	
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
	
	/**
	 * Funcion identica a isNumeric, para datos tipo double
	 * @param str string a comprobar
	 * @return si es correcto o no correcto
	 */
	public static boolean isNumericDouble(String str) {
		try {  
		    Double.parseDouble(str);  
		    return true;
		  } catch(NumberFormatException e){  
		    return false;  
		  }  
	}
	/**
     * Funcion que valida que la fecha es correcta
     * @param fecha en formato string
     * @return true / false
     */
    public static boolean validarfecha(String fecha) {
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            formatoFecha.setLenient(false);
            formatoFecha.parse(fecha);
        } 
        catch (ParseException e){
            return false;
        }
        return true;
    }
    
    /**
     * Funcion que verifica si la fecha es menor que la actual
     * @param fecha_string pasamos la fecha como string
     * @return true/false
     */
    public static boolean validarhoy(String fecha_string) {
        
        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
        Date fecha = null;
        try {
            fecha = date.parse(fecha_string);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Date fechaactual = new Date(System.currentTimeMillis());
        
        if(fechaactual.before(fecha) == true) {
            return false;
        }
        else {
            return true;
        }
    }
    //	---------------		GETTERS Y SETTERS		-----------------	//
	
    /**
     * Getter del nombre
     * @return nombre
     */
    public String getNombre() {
		return nombre;
	}

	/**
	 * Setter del nombre
	 * @param nombre del usuario
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Getter del apellido
	 * @return apellido
	 */
	public String getApellido() {
		return apellido;
	}

	/**
	 * Setter del apellido
	 * @param apellido del usuario
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	/**
	 * Getter del Alias
	 * @return alias
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * Setter del Alias
	 * @param alias del usuario
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

	/**
	 * Getter de la contraseña
	 * @return contraseña
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Setea la contraseña
	 * @param password del usuario
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Devuelve la fecha de nacimiento
	 * @return fecha_nacimiento
	 */
	public String getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	/**
	 * Setea la fecha de nacimiento
	 * @param fecha_nacimiento del usuario
	 */
	public void setFecha_nacimiento(String fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}

	/**
	 * Getter de la edad
	 * @return edad
	 */
	public int getEdad() {
		return edad;
	}

	/**
	 * Setter de la edad
	 * @param edad del usuario
	 */
	public void setEdad(int edad) {
		this.edad = edad;
	}

	/**
	 * Getter para la actividad
	 * @return tipo de actividad de la persona
	 */
	public int getActividad() {
		return actividad;
	}

	/**
	 * Setea el tipo de actividad de la perosna
	 * @param actividad del usuario
	 */
	public void setActividad(int actividad) {
		this.actividad = actividad;
	}

	/**
	 * Setter del sexo
	 * @param sexo del usuario
	 */
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	/**
	 * Getter del sexo
	 * @return sexo
	 */
	public String getSexo() {
		return sexo;
	}
	
	/**
	 * Getter de la altura
	 * @return altura
	 */
	public double getAltura() {
		return altura;
	}

	/**
	 * Setter de la altura
	 * @param altura del usuario
	 */
	public void setAltura(double altura) {
		this.altura = altura;
	}

	/**
	 * Getter del peso
	 * @return peso
	 */
	public double getPeso() {
		return peso;
	}

	/**
	 * Setter del peso
	 * @param peso del usuario
	 */
	public void setPeso(double peso) {
		this.peso = peso;
	}

	/**
	 * Getter para el imc
	 * @return imc
	 */
	public double getIMC() {
		return IMC;
	}

	/**
	 * Settea el imc
	 * @param iMC del usuario
	 */
	public void setIMC(double iMC) {
		IMC = iMC;
	}
	
	/**
	 * Devuelve el historial de actividades
	 * @return misActividades;
	 */
	public ArrayList<Actividades> getMisactividades() {
		return misactividades;
	}

	/**
	 * Setter de MisAcctividades
	 * @param misactividades historial
	 */
	public void setMisactividades(ArrayList<Actividades> misactividades) {
		this.misactividades = misactividades;
	}

	/**
	 * Setter para el historial de peso
	 * @param weightHistory historial del peso
	 */
	public void setWeightHistory(ArrayList<String> weightHistory) {
		this.weightHistory = weightHistory;
	}
	
	/**
	 * Getter para le historial de peso
	 * @return weightHistory
	 */
	public ArrayList<String> getWeightHistory(){
		return weightHistory;
	}

	/**
	 * Getter para las kcal a consumir diariamente
	 * @return kcal
	 */
	public double getKcal() {
		return kcal;
	}
	
	/**
	 * Settea las kcal a consumir diariamente
	 * @param kcal a settear
	 */
	public void setKcal(double kcal) {
		this.kcal = kcal;
	}
	
	
	
}
