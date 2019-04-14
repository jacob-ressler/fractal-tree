package data;

import java.util.ArrayList;

// Data structure for the fractal tree
public class FractalTree {

	private Branch root; // reference to initial branch (equivalent to root of a typical tree data
							// structure)
	private ArrayList<Branch> branches; // all branches of the tree (including root)

	public FractalTree() {
		this.root = new Branch(Parameters.tilt);
	}

//	public void draw(Graphics2D g, int height) {
//		root.draw(g, height);
//	}

	// get all branches in this tree
	public Branch[] getAllBranches() {
		if (branches == null) {
			branches = new ArrayList<Branch>();
			getAllBranches(root);

			//Debug.logArray((Branch[]) branches.toArray());
		}
		
		return branches.toArray(new Branch[1]);
	}

	// recursively add all branches to the list
	private void getAllBranches(Branch curr) {
		branches.add(curr); // add this to the list

		if (curr.getChildren() == null)
			return;

		for (int i = 0; i < curr.getChildren().length; i++) {
			getAllBranches(curr.getChildren()[i]);
		}

	}

	public Branch[] getBranchesUpToGen(int gen) {
		if (branches == null)
			getAllBranches();

		ArrayList<Branch> bs = new ArrayList<Branch>();
		for (Branch b : branches) {
			if (b.getGeneration() <= gen) {
				//Debug.log(b.toString());
				bs.add(b);
			}
		}

		return bs.toArray(new Branch[1]);
	}
}
