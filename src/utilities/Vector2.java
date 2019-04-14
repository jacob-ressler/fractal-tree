package utilities;

// data structure for holding 2 integer values
public class Vector2 {
	public int x, y;
	
	public Vector2(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	// rotate this Vector2 about an origin point by the specified angle value
	public Vector2 rotate(Vector2 origin, float angle) {
		//float deg = angle;
		angle = (float) Math.toRadians(angle);
		Vector2 rot = clone();
		float cos = (float) Math.cos(angle);
		float sin = (float) Math.sin(angle);
		// rotation math goes here
		x = Math.round((rot.x * cos) + (rot.y * -sin) + (-origin.x * cos + origin.y * sin + origin.x));
		y = Math.round((rot.x * sin) + (rot.y * cos) + (-origin.x * sin - origin.y * cos + origin.y));
		rot.x = x;
		rot.y = y;
		
		//System.out.printf("Rotated (%d, %d) by %.1f deg about (%d, %d) to (%d, %d)\tDistance between = %d\n", 
		//		this.x, this.y, deg, origin.x, origin.y, rot.x, rot.y, distance (origin, rot));
		
		return rot;
	}

	public static Vector2 zero() {
		return new Vector2(0, 0);
	}
	
	// return Euclidean distance as an int
	public static int distance(Vector2 start, Vector2 end) {
		int xdif = (start.x - end.x) * (start.x - end.x);
		int ydif = (start.y - end.y) * (start.y - end.y);
		return (int) Math.round(Math.sqrt(xdif + ydif));
	}
	
	@Override
	public Vector2 clone() {
		return new Vector2(this.x, this.y);
	}
	
	@Override
	public String toString() {
		return "("+x+", "+y+")";
	}
}
