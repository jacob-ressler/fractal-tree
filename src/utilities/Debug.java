package utilities;

/**
 * A static class with some useful debugging methods.
 * @author Jacob Ressler &amp; Anthony Lantz
 *
 */
public class Debug {
	
	/**
	 * Prints an array to the console using the elements' toString method.
	 * @param arr any object array
	 */
	public static <T> void logArray(T[] arr) {
		String log = "Printing array...\n";
		
		for (int i = 0; i < arr.length; i++) {
			log += "\t[" + i + "]\t" + arr[i].toString() + "\n";
		}
		
		System.out.println(log);
	}
	
	/**
	 * Print a string to the console
	 * @param str the string
	 */
	public static void log(String str) {
		System.out.println(str);
	}
}
