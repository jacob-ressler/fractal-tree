package utilities;

// A static class with some useful debugging functions
public class Debug {
	
	public static <T> void printArray(T[] arr) {
		String log = "Printing array of type: " + arr.getClass().getName() + "\n"; // class name
		
		for (int i = 0; i < arr.length; i++) {
			log += "\t[" + i + "]\t" + arr[i].toString() + "\n";
		}
		
		System.out.println(log);
	}
	
}
