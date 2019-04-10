package data;

import java.awt.Graphics2D;
import java.util.ArrayList;

import utilities.Debug;

// Data structure for the fractal tree
public class FractalTree {
	
	private Branch root; // reference to initial branch (equivalent to root of a typical tree data structure)
	private Branch[] branches; // all branches of the tree (including root)
	
	public FractalTree() {
		this.root = new Branch(Parameters.tilt);
	}

	
	public void draw(Graphics2D g, int height) {
		root.draw(g, height);
	}
	
	// get all branches in this tree
	public Branch[] getAllBranches() {
		if (branches != null) return branches; // this has all the branches
		
		branches = (Branch[]) getAllBranches(root, new ArrayList<Branch>()).toArray();
		
		Debug.printArray(branches);
		
		return branches;
	}
	
	// recursively add all branches to the list
	private ArrayList<Branch> getAllBranches(Branch curr, ArrayList<Branch> list) {
		list.add(curr); // add this to the list
		
		if (curr.getChildren() == null)
			return list;
		
		for (int i = 0; i < curr.getChildren().length; i++) {
			getAllBranches(curr.getChildren()[i], list);
		}
		
		return list;
	}
}
