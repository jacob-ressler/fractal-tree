package data;

import java.awt.Graphics2D;

// Data structure for the fractal tree
public class FractalTree {
	
	private Branch trunk; // reference to initial branch (equivalent to root of a typical tree data structure)
	
	
	public FractalTree() {
		this.trunk = new Branch(Parameters.tilt);
	}

	
	public void draw(Graphics2D g, int height) {
		trunk.draw(g, height);
		//System.out.println("Tree drawn");
	}
}
