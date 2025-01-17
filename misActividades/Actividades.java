package misActividades;
import runApp.main;
import runApp.MiAplicacion;
import users.UsuarioPremium;
import users.Usuario;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 * Clase de Actividades padre que implementa la serialización para el uso de persistencia
* @author Lorena Almoguera Romero Dni: 48796558B
 *
 */
public class Actividades implements Serializable {
	
	// Práctica realizada por Lorena Almoguera Romero
	
	//		--------------- 		MENSAJES GENERALES			-----------------	//
	private static final String CADENA_DAME_NOMBRE_ACTIVIDAD ="Introduce el nombre de la actividad: ";
	private static final String CADENA_FECHA_INICIO ="Introduce la fecha de inicio: ";
	private static final String CADENA_FECHA_FINAL ="Introduce la fecha de fin: ";
	private static final String CADENA_HORA_INICIO ="Introduce la hora de inicio: ";
	private static final String CADENA_HORA_FINAL="Introduce la hora de fin: ";
	private static final String CADENA_FECHA_HORA_INICIO = "Fecha y hora de inicio: ";
	private static final String CADENA_FECHA_HORA_FIN = "Fecha y hora de finalización: ";
	private static final String CADENA_DURACION ="Duracion: ";
	private static final String CADENA_KCAL = "Kcal consum: ";
	private static final String CADENA_FC_MAX_MIN_MEDIA ="FcMax, FcMin, FcMedia: ";
	private static final String CADENA_CADENCIA_IMPR ="Cadencia: ";
	private static final String CADENA_HORA ="Indica la hora en formato 24h";
	private static final String CADENA_MINUTOS ="Indica los minutos";
	private static final String CADENA_SEGUNDOS ="Indica los segundos";
	private static final String CADENA_FORMATO_FECHA ="Recuerda que el formato es dd/mm/aaaa ";
	private static final String CADENA_DURACION_TOTAL ="Duración total de la actividad: ";
	private static final String CADENA_CYCLING ="Usted va a declarar una actividad Cycling.";
	private static final String CADENA_RUNNING ="Usted va a declarar una actividad Running.";
	private static final String CADENA_SWIMMING ="Usted va a declarar una actividad Swimming.";
	private static final String CADENA_ACTIVIDAD_NORMAL ="Usted va a declarar una actividad normal.";
	private static final String CADENA_PEDIR_FCMIN ="Por favor, introduzca su FcMin";
	private static final String CADENA_PEDIR_FCMAX ="Por favor, introduzca su FcMax";
	private static final String CADENA_DISTANCIA = "Por favor, introduzca la distancia recorrida.";
	private static final String CADENA_KCAL_CONSUM = "KCAL consumidas: ";

	
	// 		--------------- 		MENSAJES DE ERROR			-----------------	/
	private static final String CADENA_ERROR_NUM ="ERROR: Eso no es un número. Por favor, introduce de nuevo la opcion deseada";
	private static final String CADENA_ERROR_OPC = "ERROR: Opcion no valida";
	private static final String CADENA_ERROR_LENGTH = "ERROR: Longitud de cadena no valida";
	private static final String CADENA_ERROR_FORMATO_INCORRECTO ="ERROR: Formato incorrecto.";
	private static final String CADENA_ERROR_FORMATO_FECHA ="ERROR: Formato INCORRECTO. Por favor, vuelve a introducir la fecha.";
	private static final String CADENA_INTRODUCIDO_MENSAJE = "Has introducido: ";
	private static final String CADENA_ERROR_HORA_INVALIDA ="ERROR: Formato INCORRECTO. Por favor, vuelve a introducir la hora.";
	private static final String CADENA_ERROR_MINUTOS_INVALIDA ="ERROR: Formato INCORRECTO. Por favor, vuelve a introducir la hora";
	private static final String CADENA_ERROR_DISTANCIA = "La Distancia NO puede ser negativa. Por favor, intentelo de nuevo.";
	private static final String CADENA_ERROR_FC ="El FcMax es menor al FcMin. Por favor, inserte los dos valores de nuevo";
	private static final String CADENA_ERROR_INSTANTE ="ERROR: Por favor, asegurese de que el primer instante introducido es anterior al segundo.";
	
	
	// 		--------------- 		VARIABLES			-----------------	//
	private static LocalDate fechaHoy = LocalDate.now();
	SimpleDateFormat format_time = new SimpleDateFormat("HH:mm:ss");
	private double kcal_consum, FcMax, FcMin, FcMedia, distancia, duracion;
	private String fecha_inicio, fecha_fin, hora_inicio, hora_finalizada, alias;
	private String time = "([01]?[0-9]|2[0-3]):[0-5][0-9]";
	final static int KEY_SI = 1, KEY_NO = 2;
	
	/**
	 * Funcion que sirve para imprimir mensajes por pantalla
	 * @param mensaje a imprimir
	 */
	public static void imprimir(String mensaje) {
		System.out.println(mensaje);
	}
	
	
	/**
	 * Funcion que devuelve el tipo de actividad que es
	 * @param actividad objeto
	 * @return devuelve un int (1 running, 2 swimming, 3 cycling, 0 regular)
	 */
  	public int devolverTipoActividad(Actividades actividad) {
        
   		if (actividad instanceof ActividadesRunning) {
            return 1;
        } else if (actividad instanceof ActividadesSwimming) {
            return 2;
        } else if (actividad instanceof ActividadesCycling) {
            return 3;
        } else {
            return 0;
        }
    }
	
  	/**
  	 * Funcion que declara los datos genericos de la aplicaicon
  	 * @param MiUsuario objeto
  	 * @param correcion	factor dependiendo de el tipo de actividad de la persona
  	 */
	public void declarar_actividad(Usuario MiUsuario, double correcion) {
		double kcal_consum, FcMax, FcMin, FcMedia, factor, duracion, distancia;
		String fecha_inicio, fecha_final, hora_inicio, hora_final, alias;
	
				fecha_inicio = pedir_fecha(CADENA_FECHA_INICIO);
				fecha_final = pedir_fecha(CADENA_FECHA_FINAL);
				hora_inicio = pedir_hora(CADENA_HORA_INICIO);
				hora_final = pedir_hora(CADENA_HORA_FINAL);
				while(check_instant(fecha_inicio, fecha_final, hora_inicio, hora_final) == false) {
					imprimir(CADENA_ERROR_INSTANTE);
					fecha_inicio = pedir_fecha(CADENA_FECHA_INICIO);
					fecha_final = pedir_fecha(CADENA_FECHA_FINAL);
					hora_inicio = pedir_hora(CADENA_HORA_INICIO);
					hora_final = pedir_hora(CADENA_HORA_FINAL);
				}
				distancia = pedir_Distancia();
				duracion = calcular_duracion(fecha_inicio, fecha_final, hora_inicio, hora_final);
				imprimir(CADENA_DURACION_TOTAL + duracion);
				
				imprimir(CADENA_PEDIR_FCMAX);
				FcMax = pedirFc();
				imprimir(CADENA_PEDIR_FCMIN);
				FcMin = pedirFc();
				while(FcMax < FcMin) {
					imprimir(CADENA_ERROR_FC);
					imprimir(CADENA_PEDIR_FCMAX);
					FcMax = pedirFc();
					imprimir(CADENA_PEDIR_FCMIN);
					FcMin = pedirFc();
				}
				FcMedia = (FcMax+FcMin)/2;
				
				factor = MiUsuario.getActividad();
				kcal_consum = calc_kcal_consum(MiUsuario.getSexo(), MiUsuario.getEdad(), MiUsuario.getAltura(), MiUsuario.getPeso(), factor, FcMedia, duracion);
				setFecha_inicio(fecha_inicio);
				setFecha_fin(fecha_final);
				setHora_inicio(hora_inicio);
				setHora_finalizada(hora_final);
				setDistancia(distancia);
				setFcMedia(FcMedia);
				setFcMin(FcMin);
				setFcMax(FcMax);
				setKcal_consum(kcal_consum);
				alias = MiUsuario.getAlias();
				setAlias(alias);
				setDuracion(duracion);
	}
	
	/**
	 * Funcion que sirve para imprimir los datos genericos de la actividad
	 * @param MiActividad objeto
	 */
	public void imprimirDatosActividad(Actividades MiActividad) {
		imprimir(CADENA_FECHA_HORA_INICIO + MiActividad.getFecha_inicio() + " " + MiActividad.getHora_inicio());
		imprimir(CADENA_FECHA_HORA_FIN + MiActividad.getFecha_fin() + " " + MiActividad.getHora_finalizada());
		imprimir(CADENA_DURACION + MiActividad.getDuracion());
		imprimir(CADENA_DISTANCIA + MiActividad.getDistancia());
		imprimir(CADENA_KCAL + MiActividad.getKcal_consum());
		imprimir(CADENA_FC_MAX_MIN_MEDIA + MiActividad.getFcMax() + " " + MiActividad.getFcMin() + " " + MiActividad.getFcMedia());
	}

	/**
	 * Funcion que sirve para pedir la distancia
	 * @return distancia pedida
	 */
	public double pedir_Distancia() {
		double distancia;
		String input;
		imprimir(CADENA_DISTANCIA);
		
		input = main.scanIn.nextLine();
		
		while(Usuario.isNumericDouble(input)!= true) {
			imprimir(CADENA_ERROR_NUM);
			input = main.scanIn.nextLine();
		}
		distancia = Double.parseDouble(input);
		while(distancia < 0) {
			imprimir(CADENA_ERROR_DISTANCIA);
			input = main.scanIn.nextLine();
			while(Usuario.isNumericDouble(input)!= true) {
				imprimir(CADENA_ERROR_NUM);
				input = main.scanIn.nextLine();
			}
			distancia = Double.parseDouble(input);
		}
		distancia = Double.parseDouble(input);
		return distancia;
		
	}
	
	/**
	 * Funcion que sirve para calcular las kcal consumidas
	 * @param sexo del usuario
	 * @param edad del usuario
	 * @param altura del usuario
	 * @param peso del usuario
	 * @param factor dependiendo del usuario
	 * @param FcMedia del usuario
	 * @param tiempo de la actividad
	 * @return devuelve el peso
	 */
	public static double calc_kcal_consum(String sexo, int edad, double altura, double peso, double factor, double FcMedia, double tiempo) {
		double resultado = 0;
    	String hombre = "V", mujer = "M";
		 
    	if(sexo.equals(mujer) == true) {
    		resultado = ((edad * 0.074) - (peso*0.05741) + (FcMedia * 0.4472)-20.4022)*tiempo/4.184;
    		resultado = resultado * factor;
    		imprimir(CADENA_KCAL_CONSUM + resultado );
    		return resultado;
    	}else if(sexo.equals(hombre) == true) {
    		if(sexo.equals(mujer) == true) {
        		resultado = ((edad * 0.2017) - (peso*0.09036) + (FcMedia * 0.6309)-55.0969)*tiempo/4.184;
        		resultado = resultado * factor;
        		imprimir(CADENA_KCAL_CONSUM + resultado );
        		return resultado;
        	}
    	}
		return resultado;
	}
	
	/**
	 * Funcion que pide la freq cardiaca
	 * @return freqCardiaca
	 */
	public static int pedirFc() {
		int Fc, num_aux = 0;
		String input = main.scanIn.nextLine();
		while(num_aux > 104 || num_aux < 35) {
			while(isNumeric(input)!= true) {
				imprimir(CADENA_ERROR_NUM);
				input = main.scanIn.nextLine();
			}
			num_aux = Integer.parseInt(input);
			if(num_aux > 104 || num_aux < 35) {
				imprimir(CADENA_ERROR_OPC);
				input = main.scanIn.nextLine();
			}
		}
		Fc = num_aux;
		return Fc;
	}

    /**
	 * funcion que pide y comprueba si la fecha introducida por el usuario es valida.
	 * @param mensaje a imprimir por pantalla
	 * @return devuelve la fecha
	 */
	public static String pedir_fecha(String mensaje) {
		
		String fecha; 
		imprimir(mensaje);
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
	 * Función que calcula la duración de una actividad
	 * @param fecha_inicio de la actividad
	 * @param fecha_fin	de la actividad
	 * @param hora_inicio de la actividad
	 * @param hora_fin de la actividad
	 * @return devuelve el tiempo en segundos
	 */
	public static double calcular_duracion(String fecha_inicio, String fecha_fin, String hora_inicio, String hora_fin) {
		String fecha_aux1, fecha_aux2;
		fecha_aux1 = fecha_inicio.replace("/","-");
		fecha_aux2 = fecha_fin.replace("/", "-");
		fecha_aux1 = fecha_aux1 + " " + hora_inicio;
		fecha_aux2 = fecha_aux2 + " " + hora_fin;
		SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		
		 try {
		      Date date1 = formato.parse(fecha_aux1);
		      Date date2 = formato.parse(fecha_aux2);
		      double duration = date2.getTime() - date1.getTime();
		      return duration / (1000 * 60);  // conversión de milisegundos a minutos
		    } catch (ParseException e) {
		      // si hay error, devuelve -1
		      return -1;
		    }
	}
	
	/**
	 * Funcion que comprueba si la fecha de inicio ocurre antes que la fecha de finalización de la actividad
	 * @param fecha_inicio de la actividad
	 * @param fecha_fin de la actividad
	 * @param hora_inicio de la actividad
	 * @param hora_fin de la actividad
	 * @return devuelve si la condición es cierta o no es cierta
	 */
	public static boolean check_instant(String fecha_inicio, String fecha_fin, String hora_inicio, String hora_fin) {
		String fecha_aux1, fecha_aux2;
		fecha_aux1 = fecha_inicio.replace("/","-");
		fecha_aux2 = fecha_fin.replace("/", "-");
		fecha_aux1 = fecha_aux1 + " " + hora_inicio;
		fecha_aux2 = fecha_aux2 + " " + hora_fin;
		
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime startTime = LocalDateTime.parse(fecha_aux1, formato);
		LocalDateTime endTime = LocalDateTime.parse(fecha_aux2, formato);
		return startTime.isBefore(endTime);
	    
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
    
    /**
     * Funcion que pide la hora
     * @param mensaje a imprimir
     * @return la hora
     */
    public static String pedir_hora(String mensaje)
    {
    	String hora, minutos, segundos, tiempo, aux = "0";
    	int hora_int, minutos_int, segundos_int;
    	
    	imprimir(mensaje);
    	imprimir(CADENA_HORA);
    	hora = main.scanIn.nextLine();
    	while(isNumeric(hora)!=true) {
    		imprimir(CADENA_ERROR_NUM);
    		hora = main.scanIn.nextLine();
    	}
    	hora_int = Integer.parseInt(hora);
    	while(hora_int > 23 || hora_int < 0) {
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
    	while(minutos_int > 59 || minutos_int < 0) {
    		imprimir(CADENA_ERROR_MINUTOS_INVALIDA);
    		minutos = main.scanIn.nextLine();
    		minutos_int = Integer.parseInt(minutos);
    	}
   
    	imprimir(CADENA_SEGUNDOS);
    	segundos = main.scanIn.nextLine();
    	while(isNumeric(segundos)!= true) {
    		imprimir(CADENA_ERROR_NUM);
    		segundos = main.scanIn.nextLine();
    	}
    	segundos_int = Integer.parseInt(minutos);
    	while(segundos_int > 60 || segundos_int < 0) {
    		imprimir(CADENA_ERROR_MINUTOS_INVALIDA);
    		segundos = main.scanIn.nextLine();
    		segundos_int = Integer.parseInt(minutos);
    	}
    	if(segundos.length() == 1) {
    		segundos = aux + segundos;
    	}
    	
    	if(minutos.length() == 1) {
    		minutos = aux + minutos;
    	}
    	if(hora.length() == 1) {
    		hora = aux + hora;
    	}
    	
    	tiempo = hora + ":" + minutos + ":" + segundos;
    	return tiempo;
    }
	
    /**
     * Funcion que calcula las calorias necesarias
     * @param miUsuario objeto
     * @return doublecalorias necesarias
     */
    public static double calcular_calorias_necesarias(Usuario miUsuario) {
    	int edad, actividad;
    	double estatura, peso;
    	String sexo, hombre = "V", mujer = "M";
    	
    	
    	sexo = miUsuario.getSexo();
    	edad = miUsuario.getEdad();
    	estatura = miUsuario.getAltura();
    	peso = miUsuario.getPeso();
    	actividad = miUsuario.getActividad();
    	double aux_edad = edad, aux_estat = estatura, aux_peso = peso, factor_actividad = 0, resultado;
    	
    	if(sexo.equals(mujer)) {
    		
    		if(actividad == 1) {
        		factor_actividad = 1.2;
        		resultado = (655 + (9.6*aux_peso)+((1.8*aux_estat)-(4.7*aux_edad))*factor_actividad);
        		return resultado;
        	}else if(actividad == 2) {
        		factor_actividad = 1.375;
        		resultado = (655 + (9.6*aux_peso)+((1.8*aux_estat)-(4.7*aux_edad))*factor_actividad);
        		return resultado;
        	}else if(actividad == 3) {
        		factor_actividad = 1.55;
        		resultado = (655 + (9.6*aux_peso)+((1.8*aux_estat)-(4.7*aux_edad))*factor_actividad);
        		return resultado;
        	}else if(actividad == 4) {
        		factor_actividad = 1.725;
        		resultado = (655 + (9.6*aux_peso)+((1.8*aux_estat)-(4.7*aux_edad))*factor_actividad);
        		return resultado;
        	}else if(actividad == 5) {
        		factor_actividad = 1.9;
        		resultado = (655 + (9.6*aux_peso)+((1.8*aux_estat)-(4.7*aux_edad))*factor_actividad);
        		return resultado;
        	}
    	}else if(sexo.equals(hombre)) {
    		
    		if(actividad == 1) {
        		factor_actividad = 1.2;
        		resultado = (66 + (13.7*aux_peso)+((5*aux_estat)-(6.8*aux_edad))*factor_actividad);
        		return resultado;
        	}else if(actividad == 2) {
        		factor_actividad = 1.375;
        		resultado = (66 + (13.7*aux_peso)+((5*aux_estat)-(6.8*aux_edad))*factor_actividad);
        		return resultado;
        	}else if(actividad == 3) {
        		factor_actividad = 1.55;
        		resultado = (66 + (13.7*aux_peso)+((5*aux_estat)-(6.8*aux_edad))*factor_actividad);
        		return resultado;
        	}else if(actividad == 4) {
        		factor_actividad = 1.725;
        		resultado = (66 + (13.7*aux_peso)+((5*aux_estat)-(6.8*aux_edad))*factor_actividad);
        		return resultado;
        	}else if(actividad == 5) {
        		factor_actividad = 1.9;
        		resultado = (66 + (13.7*aux_peso)+((5*aux_estat)-(6.8*aux_edad))*factor_actividad);
        		return resultado;
        	}
    	}
    	return 0;
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



	// 		--------------- 		GETTERS Y SETTERS			-----------------	//
	
	/**
	 * Getter formateo del tiempo
	 * @return formatter
	 */
	public SimpleDateFormat getFormat_time() {
		return format_time;
	}

	/**
	 * Setter formateo del tiempo
	 * @param format_time establecido
	 */
	public void setFormat_time(SimpleDateFormat format_time) {
		this.format_time = format_time;
	}

	
	/**
	 * Getter duracion de la actividad
	 * @return duracion
	 */
	public double getDuracion() {
		return duracion;
	}
	
	/**
	 * Setter duracion de la actividad
	 * @param duracion establecida
	 */
	public void setDuracion(double duracion) {
		this.duracion = duracion;
	}

	/**
	 * Getter Kcal_Consumidas
	 * @return kcal_consum
	 */
	public double getKcal_consum() {
		return kcal_consum;
	}

	/**
	 * Setter KCAL_consumidas
	 * @param kcal_consum establecidas
	 */
	public void setKcal_consum(double kcal_consum) {
		this.kcal_consum = kcal_consum;
	}
	
	/**
	 * Getter FcMax
	 * @return fcmax
	 */
	public double getFcMax() {
		return FcMax;
	}
	
	/**
	 * Setter fcmax
	 * @param fcMax establecida
	 */
	public void setFcMax(double fcMax) {
		FcMax = fcMax;
	}

	/**
	 * Getter fcMin
	 * @return fcmin
	 */
	public double getFcMin() {
		return FcMin;
	}

	/**
	 * Setter fcmin
	 * @param fcMin establecida
	 */
	public void setFcMin(double fcMin) {
		FcMin = fcMin;
	}
	
	/**
	 * Getter distancia
	 * @return distancia
	 */
	public double getDistancia() {
		return distancia;
	}

	/**
	 * Setter distancia
	 * @param distancia establecida
	 */
	public void setDistancia(double distancia) {
		this.distancia = distancia;
	}
	
	/**
	 * Setter FcMedia
	 * @param fcMedia establecida
	 */
	public void setFcMedia(double fcMedia) {
		FcMedia = fcMedia;
	}

	/**
	 * Getter FcMedia
	 * @return fcmedia
	 */
	public double getFcMedia() {
		return FcMedia;
	}
	
	/**
	 * Getter de la fecha de inicio
	 * @return fecha inicio
	 */
	public String getFecha_inicio() {
		return fecha_inicio;
	}

	/**
	 * Setter de la fecha inicio
	 * @param fecha_inicio establecido
	 */
	public void setFecha_inicio(String fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}

	/**
	 * Getter de la fecha final
	 * @return fecha
	 */
	public String getFecha_fin() {
		return fecha_fin;
	}

	/**
	 * Setter de la fecha final
	 * @param fecha_fin establecida
	 */
	public void setFecha_fin(String fecha_fin) {
		this.fecha_fin = fecha_fin;
	}

	/**
	 * Getter de la hora de inicio
	 * @return horainicio
	 */
	public String getHora_inicio() {
		return hora_inicio;
	}

	/**
	 * Setter de la hora de inicio
	 * @param hora_inicio establecida
	 */
	public void setHora_inicio(String hora_inicio) {
		this.hora_inicio = hora_inicio;
	}

	/**
	 * Getter de la hora finalizada
	 * @return horafinalizada
	 */
	public String getHora_finalizada() {
		return hora_finalizada;
	}

	
	/**
	 * Setter de la hora finalizada
	 * @param hora_finalizada establecida
	 */
	public void setHora_finalizada(String hora_finalizada) {
		this.hora_finalizada = hora_finalizada;
	}

	/**
	 * Getter del alias
	 * @return alias
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * Setter del alias
	 * @param alias establecido
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

	/**
	 * Getter del tiempo
	 * @return tiempo
	 */
	public String getTime() {
		return time;
	}


	/**
	 * Setter del tiempo
	 * @param time establecido
	 */
	public void setTime(String time) {
		this.time = time;
	}


}

