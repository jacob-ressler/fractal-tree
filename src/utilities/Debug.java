package utilities;

// A static class with some useful debugging functions
public class Debug {
	
	public static <T> void logArray(T[] arr) {
		String log = "Printing array...\n";
		
		for (int i = 0; i < arr.length; i++) {
			log += "\t[" + i + "]\t" + arr[i].toString() + "\n";
		}
		
		System.out.println(log);
	}
	
	public static void log(String str) {
		System.out.println(str);
	}
}
