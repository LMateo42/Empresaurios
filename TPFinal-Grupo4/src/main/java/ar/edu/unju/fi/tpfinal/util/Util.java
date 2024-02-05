package ar.edu.unju.fi.tpfinal.util;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Util {
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	/**
	 * Transforma LocalDate a String.
	 */
	public static String fechaToString(LocalDate fecha) {
		return fecha.format(formatter);
	}
	
	/**
	 * Transforma un String a LocalDate.
	 */
	public static LocalDate stringToLocalDate(String fecha) {
		return LocalDate.parse(fecha, formatter);
	}
}
