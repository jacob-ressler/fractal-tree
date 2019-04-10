package data;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;

import utilities.Vector2;

// Data structure for a branch of a FractalTree
public class Branch {

	private Branch[] children; // all Branches that will come off this branch
	public Branch parent;
	public Vector2 end;
	public float angle;
	public int length;
	public float strokeWeight;
	private int generation;
	
	
	// constructor for all branches except root
	public Branch(Branch parent, Vector2 end, float angle) {
		this.parent = parent;
		this.end = end;
		this.angle = angle;
		this.length = calcLength(parent.length);
		this.strokeWeight = calcStrokeWeight();
		this.generation = parent.getGeneration() + 1;
		if (generation < Parameters.depth) {
			children = new Branch[Parameters.branchingFactor];
			split(end, angle);
		}
	}
	



	// constructor for root
	public Branch(float angle) {
		this.parent = null;
		this.end = new Vector2(0, Parameters.branchLength);
		this.angle = angle;
		this.length = Parameters.branchLength;
		this.strokeWeight = Parameters.strokeWeight;
		this.generation = 1;
		if (generation < Parameters.depth) {
			children = new Branch[Parameters.branchingFactor];
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
		phi = phi - (Parameters.branchingAngle * Parameters.branchingFactor * 0.5f) + (Parameters.branchingAngle * 0.5f);
		
		
		for (int i = 0; i < children.length; i++) {
			ref = new Vector2(start.x, start.y + calcLength(length));
			children[i] = new Branch(this, ref.rotate(start, phi), phi);
			phi += Parameters.branchingAngle;
		}
		
//		String log = "Initial Angle:  " + angle + "\nChildren Angles: ";
//		for(Branch b : children)
//			log += "[" + b.angle + "] ";
//		
//		System.out.println(log);
	}
	
	public Graphics draw(Graphics2D g, int height) {
		if (length == 0 || strokeWeight == 0) return g;
		
		g.setStroke(new BasicStroke(strokeWeight));
		
		if (parent == null) {
			// this is the trunk
			g.drawLine(0,  height, end.x, height - end.y);
		}
		else {
			// this is a normal branch
			g.drawLine(parent.end.x, height - parent.end.y, end.x, height - end.y);
		}
		
		if (children != null) {
			for (Branch b : children) {
				b.draw(g, height);
			}
		}
		return g;
	}
	
	/* 
	 * ----------------------------
	 *          GETTERS
	 * ----------------------------
	 */
	
	public int getGeneration() { return generation; }
	
	public Branch[] getChildren() { return this.children; }
	
	public Branch getParent() { return this.parent; }
	
	public Vector2 getEnd() { return this.end; }
	
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
		if (Parameters.bsrIsPercent) {
			// treat the shrink rate as a percentage decrease
			int val = Math.round(len * ((100 - Parameters.branchShrinkRate) * 0.01f));
			return Math.max(val, 0);
		}
		else {
			// treat the shrink rate as a value decrease
			return Math.max(len - Parameters.branchShrinkRate, 0);
		}
	}
	
	// Calculate a stroke weight for this branch based on current Parameters values
	private float calcStrokeWeight() {
		if (Parameters.ssrIsPercent) {
			// treat the shrink rate as a percentage decrease
			return Math.max(parent.strokeWeight * ((100 - Parameters.strokeShrinkRate) * 0.01f), 0);
		}
		else {
			// treat the shrink rate as a value decrease
			return Math.max(parent.strokeWeight - Parameters.strokeShrinkRate, 0);
		}
	}

}
