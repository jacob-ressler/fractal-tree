package data;

import utilities.Vector2;

/**
 * A data structure holding information about a branch of the fractal tree.
 * @author Jacob Ressler &amp; Anthony Lantz
 *
 */
public class Branch {

	private Branch[] children; // all branches that will come off this branch
	private Branch parent; // the branch responsible for creating this one
	private Vector2 end; // the end point of this branch
	private float angle; // the angle of this branch
	private int length; // the length of this branch
	private float strokeWeight; // the stroke weight of this branch
	private int generation; // the generation of this branch
	
	/**
	 * Creates a branch with the specified parent, end point and angle.
	 * Also kickstarts the creation of any children of this branch.
	 * <p>This constructor is only used by non-root branches.
	 * @param parent the parent of this branch
	 * @param end the end point of this branch
	 * @param angle the angle of this branch
	 */
	public Branch(Branch parent, Vector2 end, float angle) {
		this.parent = parent;
		this.end = end;
		this.angle = angle;
		this.length = calcLength(parent.length);
		this.strokeWeight = calcStrokeWeight();
		this.generation = parent.getGeneration() + 1;
		
		if (ParamManager.lastActiveTabIndex == 0) {
			if (generation < ParamManager.single[1]) {
				children = new Branch[ParamManager.single[0]];
				split(end, angle);
			}
		}
		else {
			if (generation < randomRange(1)) {
				children = new Branch[randomRange(0)];
				split(end, angle);
			}
		}
	}
	
	/**
	 * Creates a branch with the specified length.
	 * Also kickstarts the creation of any children of this branch.
	 * <p>This constructor is only used by the root branch.
	 * @param length length of this branch as a percentage of the canvas dimensions
	 */
	public Branch(int length) {
		this.parent = null;
		this.generation = 1;
		
		if (ParamManager.lastActiveTabIndex == 0) {
			this.angle = ParamManager.single[2];
			this.length = Math.round(ParamManager.single[4]/100f * length);
			this.end = new Vector2(0, this.length);
			this.strokeWeight = ParamManager.single[6];
			
			if (generation < ParamManager.single[1]) {
				children = new Branch[ParamManager.single[0]];
				split(end, angle);
			}
		}
		else {
			this.angle = randomRange(2);
			this.length = Math.round(randomRange(4)/100f * length);
			this.end = new Vector2(0, this.length);
			this.strokeWeight = randomRange(6);
			
			if (generation < randomRange(1)) {
				children = new Branch[randomRange(0)];
				split(end, angle);
			}
		}
	}
	
	/**
	 * Calculates the angles and endpoints for this Branch's children and creates them
	 * @param start the end point of this Branch, which is the start point of its children
	 * @param phi the angle of this Branch
	 */
	private void split(Vector2 start, float phi) {
		if (length == 0) {
			return; // we can't work with a length of 0
		}
		
		Vector2 ref; 
		
		if (ParamManager.lastActiveTabIndex == 0) {
			// start with most negatively angled branch and work clockwise through for calculations
			phi += (ParamManager.single[2]*getGeneration()) // tilt
					- (ParamManager.single[3] * ParamManager.single[0] * 0.5f) + (ParamManager.single[3] * 0.5f); // alignment
			
			for (int i = 0; i < children.length; i++) {
				ref = new Vector2(start.x, start.y + calcLength(length));
				children[i] = new Branch(this, ref.rotate(start, phi), phi);
				phi += ParamManager.single[3];
			}
		}
		else {
			// start with most negatively angled branch and work clockwise through for calculations
			phi = phi + (randomRange(2)*getGeneration()) - (randomRange(3) * randomRange(0) * 0.5f) + (randomRange(3) * 0.5f);
			
			for (int i = 0; i < children.length; i++) {
				ref = new Vector2(start.x, start.y + calcLength(length));
				children[i] = new Branch(this, ref.rotate(start, phi), phi);
				phi += randomRange(3);
			}
		}
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

	public float getStrokeWeight() { return strokeWeight; }
	
	@Override
	public String toString() {
		String s = "";
		s += "gen "+generation+" | "+angle+" deg | "+length+" long | ";
		s += children == null ? "0 children" : children.length + " children";
		s += " | ends "+end.toString();
		
		return s;
	}
	
	/**
	 * Calculate a length based on the given length and current Parameters values.
	 * @param len a length
	 * @return a new length
	 */
	private int calcLength(int len) {
			// treat the shrink rate as a percentage decrease
			int val = ParamManager.lastActiveTabIndex == 0 ?
					Math.round(len * ((100 - ParamManager.single[5]) * 0.01f)) :
					Math.round(len * ((100 - randomRange(5)) * 0.01f));
			return Math.max(val, 0);
	}
	
	/**
	 * Calculate a stroke weight for this branch based on current Parameters values.
	 * @return a stroke weight for this branch
	 */
	private float calcStrokeWeight() {
			// treat the shrink rate as a percentage decrease
			return ParamManager.lastActiveTabIndex == 0 ?
					Math.max(parent.strokeWeight * ((100 - ParamManager.single[7]) * 0.01f), 0) :
					Math.max(parent.strokeWeight * ((100 - randomRange(7)) * 0.01f), 0);
	}

	/**
	 * Used with {@link ParamManager}'s rangeMin and rangeMax arrays. Returns a random
	 * value between rangeMin[i] and rangeMax[i].
	 * @param i the index
	 * @return a value between the min and max
	 */
	private int randomRange(int i) {
		float f = (float) Math.random();
		f *= (ParamManager.rangeMax[i] - ParamManager.rangeMin[i]);
		f += ParamManager.rangeMin[i];
		return Math.round(f);
	}
}
