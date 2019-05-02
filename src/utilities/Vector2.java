package utilities;

/**
 * A simple data structure for storing two-dimensional coordinates
 * @author Jacob Ressler &amp; Anthony Lantz
 *
 */
public class Vector2 {
	public int x, y;
	
	/**
	 * Create a new Vector2 with the specified x and y
	 * @param x the x-coordinate
	 * @param y the y-coordinate
	 */
	public Vector2(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Rotate this Vector2 about another by an angle
	 * @param origin the rotational origin
	 * @param angle the angle of rotation
	 * @return this Vector2
	 */
	public Vector2 rotate(Vector2 origin, float angle) {

		angle = (float) Math.toRadians(angle);
		Vector2 rot = clone();
		float cos = (float) Math.cos(angle);
		float sin = (float) Math.sin(angle);
		x = Math.round((rot.x * cos) + (rot.y * -sin) + (-origin.x * cos + origin.y * sin + origin.x));
		y = Math.round((rot.x * sin) + (rot.y * cos) + (-origin.x * sin - origin.y * cos + origin.y));
		
		return this;
	}

	/**
	 * Creates a zero-vector
	 * @return a new Vector2 with coordinates (0, 0)
	 */
	public static Vector2 zero() {
		return new Vector2(0, 0);
	}
	
	/**
	 * Return the Euclidean distance between 2 Vector2s
	 * @param start the starting point
	 * @param end the ending point
	 * @return the Euclidean distance as an integer
	 */
	public static int distance(Vector2 start, Vector2 end) {
		int xdif = (start.x - end.x) * (start.x - end.x);
		int ydif = (start.y - end.y) * (start.y - end.y);
		return (int) Math.round(Math.sqrt(xdif + ydif));
	}
	
	/**
	 * Create a new Vector2 that is a copy of this one.
	 */
	@Override
	public Vector2 clone() {
		return new Vector2(this.x, this.y);
	}
	
	@Override
	public String toString() {
		return "("+x+", "+y+")";
	}
}
