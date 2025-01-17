package users;
import runApp.main;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import runApp.MiAplicacion;
// Práctica realizada por Lorena Almoguera Romero
/**
 * Clase del usuarioPremium que extiende al usuario e implementa Serializable para la persistencia
* @author Lorena Almoguera Romero Dni: 48796558B
 *
 */
public class UsuarioPremium extends Usuario implements Serializable{
	
	
	// Práctica realizada por Lorena Almoguera Romero
		//	---------------		MENSAJES IMPRESION DE DATOS 				-----------------	//
		
		private static final String CADENA_FECHA_DE_ALTA ="La fecha de alta es la de hoy: ";
		private static final String CADENA_OPC_SELECT ="Usted ha seleccionado la opción: ";
		private static final String CADENA_TIPO_CUENTA ="Indique si desea una suscripción (1) - Mensual , (2) - Anual, (3) - Indefinida";
		private static final String CADENA_TIPO_CUENTA_IMPR ="Suscripción: ";
		private static final String CADENA_FECHA_ALTA_IMPR ="Fecha de alta: ";
		private static final String CADENA_ACTUALIZAR_CONTRASENA ="Por favor, introduzca una nueva contraseña de min 8 caracteres, alfanumérica con carácteres especiales";
		private static final String CADENA_FIN_SUSCRIBCION = "Su suscribción ha llegado a su fin. Por favor, renuevela";
		private static final String CADENA_TIEMPO_HASTA_RENOVAR ="Tiempo restante de su cuenta: ";
		private static final String CADENA_SUB_INDEF ="Al ser indefinida, su suscribción no caducará de momento";
//		---------------		MENSAJES ERROR			-----------------	//
		private static final String CADENA_ERROR_NUM ="ERROR: Dato introducido NO es un numero";
		private static final String CADENA_ERROR_LENGTH ="ERROR: Demasiados digitos introducidos";
		private static final String CADENA_ERROR_OPC = "ERROR: Opcion invalida";
		private static final String CADENA_ERROR_PATRON = "ERROR: Formato de la contraseña incorrecto. Por favor, intentelo de nuevo.";
		
		
//		---------------		VARIABLES A DECLARAR	-----------------	//
		private static final String TipoCuenta_mensual = "Mensual", TipoCuenta_anual = "Anual", TipoCuenta_Indef ="Indefinida";
		
	final static int KEY_MENSUAL = 1, KEY_ANUAL = 2, KEY_INDEF = 3;
	String fechaalta, TipoCuenta;

	/**
	 * Funcion que copia la informacion del usuario viejo
	 * @param miusuario objeto normal
	 * @param mipremium objeto premium
	 * @return UsuarioPremium objeto del usuario
	 */
	public UsuarioPremium gatherInformation(Usuario miusuario, UsuarioPremium mipremium) {
		
		String password;
		mipremium.setNombre(miusuario.getNombre());
		mipremium.setApellido(miusuario.getApellido());
		mipremium.setAlias(miusuario.getAlias());
		mipremium.setFecha_nacimiento(miusuario.getFecha_nacimiento());
		mipremium.setSexo(miusuario.getSexo());
		mipremium.setEdad(miusuario.getEdad());
		mipremium.setActividad(miusuario.getActividad());
		mipremium.setIMC(miusuario.getIMC());
		mipremium.setAltura(miusuario.getAltura());
		mipremium.setPeso(miusuario.getPeso());
		mipremium.setWeightHistory(miusuario.getWeightHistory());
		mipremium.setMisactividades(miusuario.getMisactividades());
		mipremium.setKcal(miusuario.getKcal());
		mipremium.tipo_cuenta(mipremium);
		imprimir(CADENA_ACTUALIZAR_CONTRASENA);
		password = establecer_password_premium();
		while(Usuario.isValidPassword(password) == false) {
			imprimir(CADENA_ERROR_PATRON);
			password = establecer_password_premium();
		}
		mipremium.setPassword(password);
		return mipremium;	// devuelve el usuario premium
	}
	
	
	/**
	 * Funcion que pregunta por el tipo de la suscribcion
	 * @param Premium objeto
	 */
	public void tipo_cuenta(UsuarioPremium Premium){
		
		int opc;
		String fecha = fechaHoy();
		String tiposub;
		imprimir(CADENA_FECHA_DE_ALTA + fecha);
		Premium.setFechaalta(fecha);
		opc = opcionAlta();
		
		if(opc == KEY_MENSUAL) {
			imprimir(CADENA_OPC_SELECT + TipoCuenta_mensual);
			tiposub = TipoCuenta_mensual;
			Premium.setTipoCuenta(tiposub);
		}else if(opc == KEY_ANUAL) {
			imprimir(CADENA_OPC_SELECT + TipoCuenta_anual);
			tiposub = TipoCuenta_anual;
			Premium.setTipoCuenta(tiposub);
		}else if(opc == KEY_INDEF) {
			imprimir(CADENA_OPC_SELECT + TipoCuenta_Indef);
			tiposub = TipoCuenta_Indef;
			Premium.setTipoCuenta(tiposub);
		}
		
	}
	
	/**
	 * Funcion que imprime los datos dle usuario premium
	 * @param Premium usuario
	 */
	public void imprimirDatosPremium(UsuarioPremium Premium) {
		
		imprimir(CADENA_FECHA_ALTA_IMPR + Premium.getFechaalta());
		imprimir(CADENA_TIPO_CUENTA_IMPR + Premium.getTipoCuenta());
	}
	
	/**
	 * Pedimos la opcion
	 * @return int opcion de alta
	 */
	public int opcionAlta() {
		int opcion_int, num_aux = 0;
		
		imprimir(CADENA_TIPO_CUENTA);
		String opcion = main.scanIn.nextLine();
		while(num_aux > 3 || num_aux < 1) {
			
			while(isNumeric(opcion)!= true) {
				imprimir(CADENA_ERROR_NUM);
				opcion = main.scanIn.nextLine();
				
				while(opcion.length()>1) {
					imprimir(CADENA_ERROR_LENGTH);
					opcion = main.scanIn.nextLine();
				}
			}
			num_aux = Integer.parseInt(opcion);
			if(num_aux > 3 || num_aux <1) {
				imprimir(CADENA_ERROR_OPC);
				opcion = main.scanIn.nextLine();
			}
		}
		opcion_int = Integer.parseInt(opcion);
		return opcion_int;
	}
	
	/**
	 * Seteamos la fecha de hoy
	 * @return fecha en formatto string
	 */
	public String fechaHoy() {
	    LocalDate hoy = LocalDate.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	    return hoy.format(formatter);
	}
	
	/**
	 * Funcion que comprueba si ha expirado la cuenta
	 * @param miUsuario usuario
	 * @return boolean
	 */
	public boolean haExpiradoLaCuenta(UsuarioPremium miUsuario) {
		
		if(miUsuario.getTipoCuenta().equals(TipoCuenta_mensual) == true) {
			if(hanPasado6Meses(miUsuario.getFechaalta()) == true) {
				return true;
			}
		}else if(miUsuario.getTipoCuenta().equals(TipoCuenta_anual) == true) {
			if(haPasadounAnio(miUsuario.getFechaalta()) == true) {
				return true;
			}
		}else if(miUsuario.getTipoCuenta().equals(TipoCuenta_Indef) == true){
			imprimir(CADENA_SUB_INDEF);
			return false;
		}
		return false;
	}
	
	/**
	 * 
	 * Funcion que han expirado los 6 meses
	 * @param fecha de entrega
	 * @return boolean
	 */
	public static boolean hanPasado6Meses(String fecha) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate inputDate = LocalDate.parse(fecha, formatter);
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(inputDate, currentDate);
        if (period.getMonths() >= 6) {
            imprimir(CADENA_FIN_SUSCRIBCION + fecha);
            return true;
        } else {
            int meses = 6 - period.getMonths();
            int dias = 30 - period.getDays();
            imprimir(CADENA_TIEMPO_HASTA_RENOVAR + meses + " meses y " + dias + " dias");
            return false;
        }
	}
	
	/**
	 * Funcion que comprueba que han expirado los 12 meses
	 * @param date fecha a comprobar
	 * @return return true
	 */
	public static boolean haPasadounAnio(String date) {
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	    LocalDate inputDate = LocalDate.parse(date, formatter);
	    LocalDate currentDate = LocalDate.now();
	    Period period = Period.between(inputDate, currentDate);
	    if (period.getYears() >= 1) {
	        imprimir(CADENA_FIN_SUSCRIBCION);
	    	return true;
	    }else {
	    	Period remaining = Period.between(currentDate, inputDate.plusYears(1));
            System.out.println(CADENA_TIEMPO_HASTA_RENOVAR + remaining.getYears() + " años, " + remaining.getMonths() + " meses y " + remaining.getDays() + " dias.");
            return false;
        }
	    
	    
	}
	
	
	//				---------------------------		GETTERS Y SETTERS		------------------------------	//
	
	/**
	 * Getter fecha alta
	 * @return fechaalta del usuario
	 */
	public String getFechaalta() {
		return fechaalta;
	}

	/**
	 * Setter fecha alta
	 * @param fechaalta del usuario
	 */
	public void setFechaalta(String fechaalta) {
		this.fechaalta = fechaalta;
	}

	/**
	 * Getter tipo cuenta
	 * @return TipoCuenta del usuario
	 */
	public String getTipoCuenta() {
		return TipoCuenta;
	}

	/**
	 * Setter Tipo cuenta
	 * @param tipoCuenta del usuario
	 */
	public void setTipoCuenta(String tipoCuenta) {
		TipoCuenta = tipoCuenta;
	}
	
	


}
