package data;

import utilities.Vector2;

// Data structure for a branch of a FractalTree
public class Branch {

	private Branch[] children; // all Branches that will come off this branch
	private Branch parent;
	private Vector2 end;
	private float angle;
	private int length;
	private float strokeWeight;
	private int generation;
	
	//single[] rangedMin[] rangedMax[]
	
	// constructor for all branches except root
	public Branch(Branch parent, Vector2 end, float angle) {
		this.parent = parent;
		this.end = end;
		this.angle = angle;
		this.length = calcLength(parent.length);
		this.strokeWeight = calcStrokeWeight();
		this.generation = parent.getGeneration() + 1;
		if (generation < ParamManager.single[1]) {
			children = new Branch[ParamManager.single[0]];
			split(end, angle);
		}
	}
	

	// constructor for root
	public Branch(float angle, int height) {
		this.parent = null;
		
		this.angle = angle;
		this.length = Math.round(ParamManager.single[4]/100f * height);
		this.end = new Vector2(0, length);
		this.strokeWeight = ParamManager.single[6];
		this.generation = 1;
		if (generation < ParamManager.single[1]) {
			children = new Branch[ParamManager.single[0]];
			split(end, angle);
		}
	}
	
	// creates the children of this branch
	private void split(Vector2 start, float phi) {
		if (length == 0) {
			return; // we can't work with a length of 0
		}
		
		Vector2 ref; 
		
		// start with most negatively angled branch and work clockwise through for calculations
		phi = phi - (ParamManager.single[3] * ParamManager.single[0] * 0.5f) + (ParamManager.single[3] * 0.5f);
		
		
		for (int i = 0; i < children.length; i++) {
			ref = new Vector2(start.x, start.y + calcLength(length));
			children[i] = new Branch(this, ref.rotate(start, phi), phi);
			phi += ParamManager.single[3];
		}
		
		String log = "Initial Angle:  " + angle + "\nChildren Angles: ";
		for(Branch b : children)
			log += "[" + b.angle + "] ";
		
		System.out.println(log);
	}
	
	/* 
	 * ----------------------------
	 * -         GETTERS          -
	 * ----------------------------
	 */
	
	public int getGeneration() { return generation; }
	
	public Branch[] getChildren() { return this.children; }
	
	public Branch getParent() { return this.parent; }
	
	public Vector2 getEnd() { return this.end; }
	
	public Vector2 getStart() { return (this.parent == null ? Vector2.zero() : this.parent.getEnd()); }
	
	public float getAngle() { return this.angle; }
	
	public int length() { return this.length; }

	
	@Override
	public String toString() {
		String s = "";
		s += "gen "+generation+" | "+angle+" deg | ";
		s += children == null ? "0 children" : children.length + " children";
		s += " | ends "+end.toString();
		
		return s;
	}
	
	// Calculate a length based on the given length and current Parameters values.
	private int calcLength(int len) {
			// treat the shrink rate as a percentage decrease
			int val = Math.round(len * ((100 - ParamManager.single[7]) * 0.01f));
			return Math.max(val, 0);
	}
	
	// Calculate a stroke weight for this branch based on current Parameters values
	private float calcStrokeWeight() {
			// treat the shrink rate as a percentage decrease
			return Math.max(parent.strokeWeight * ((100 - ParamManager.single[7]) * 0.01f), 0);
	}

}
