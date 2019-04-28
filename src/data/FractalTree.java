package data;

import java.util.ArrayList;

/**
 * Data structure for holding and accessing all the {@link Branch}es of a fractal tree.
 * @author Jacob Ressler & Anthony Lantz
 *
 */
public class FractalTree {

	private Branch root; // reference to initial branch (equivalent to root of a typical tree data
							// structure)
	private ArrayList<Branch> branches; // all branches of the tree (including root)

	/**
	 * Create a new fractal tree with using the specified length
	 * @param length the length
	 */
	public FractalTree(int length) {
			this.root = new Branch(length);
	}

	/**
	 * Get all the branches of this tree
	 * @return an array with all {@link Branch}es of this tree
	 */
	public Branch[] getAllBranches() {
		if (branches == null) {
			branches = new ArrayList<Branch>();
			getAllBranches(root);
		}
		
		return branches.toArray(new Branch[1]);
	}

	/**
	 * Recursive method for getting all the {@link Branch}es of this tree.
	 * The current branch is added, with this method called for each of its children.
	 * @param curr the branch to be added
	 */
	private void getAllBranches(Branch curr) {
		if (curr == null) return;
		branches.add(curr); // add this to the list

		if (curr.getChildren() == null)
			return;

		for (int i = 0; i < curr.getChildren().length; i++) {
			getAllBranches(curr.getChildren()[i]);
		}

	}

	/**
	 * Get all {@link Branch}es with generation less than or equal to the specified generation.
	 * @param gen the generation cap
	 * @return an array of all {@link Branch}es up to the generation cap
	 */
	public Branch[] getBranchesUpToGen(int gen) {
		if (branches == null)
			getAllBranches();

		ArrayList<Branch> bs = new ArrayList<Branch>();
		for (Branch b : branches) {
			if (b.getGeneration() <= gen) {
				bs.add(b);
			}
		}

		return bs.toArray(new Branch[1]);
	}
	
	/**
	 * Gett all {@link Branch}es of the specified generation
	 * @param gen the generation
	 * @return an array of all {@link Branch}es of the specified generation
	 */
	public Branch[] getBranchesOfGen(int gen) {
		if (branches == null)
			getAllBranches();

		ArrayList<Branch> bs = new ArrayList<Branch>();
		for (Branch b : branches) {
			if (b.getGeneration() == gen) {
				bs.add(b);
			}
		}

		return bs.toArray(new Branch[1]);
	}
}
