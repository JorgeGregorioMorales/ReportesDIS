package mx.com.nmp.dis.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

	public static String getCurrentTimeStamp() {
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
		Date now = new Date();
		String strDate = sdfDate.format(now);
		return strDate;
	}

	public static String getDateTimeStamp() {
		SimpleDateFormat sdfDate = new SimpleDateFormat("d-MM-yyyy");//dd/MM/yyyy
		Date now = new Date();
		String strDate = sdfDate.format(now);
		return strDate;
	}

	public static String getTimestamp(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}

	public static String getTime(Date date) {
		return new SimpleDateFormat("d-MM-yyyy").format(date);
	}

	public static String getDate(Date date, String format) {
		return new SimpleDateFormat(format).format(date);
	}

	public static String getDate(Date date, String format, Locale locale) {
		return new SimpleDateFormat(format, locale).format(date);
	}

//	public static Date getTime(String date) {
//		DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
//		Date result = null;
//
//		try {
//			result = df.parse(date);
//			result.setHours(0);
//			result.setMinutes(0);
//			result.setSeconds(0);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			//e.printStackTrace();
//		}
//
//		return result;
//
//	}

	/**
	 * Método que valida si una cadena representa un formato de fecha válido.
	 *
	 * @param cadenaFecha - cadena a validar.
	 *
	 * @return - <code>true</code> si es válida en otro caso <code>false</code>.
	 */
	public static boolean validarCadenaFecha(final String cadenaFecha) {
		boolean valida = true;

		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(false);

		try {
			sdf.parse(cadenaFecha);
		} catch (Exception e) {
			valida = false;
		}

		return valida;
	}

	/**
	 * Constructor privado de la clase.
	 */
	private DateUtil() {
		// do nothing.
	}

}
