package gamelib;

final public class Pulse {
	private static int counter = 1;
	
	public static void increment() {
		counter++;
	}
	
	public static int getCount() {
		return counter;
	}
}
