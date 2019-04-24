package data;

import java.util.ArrayList;

// Responsible for holding all of the current parameter values
public class ParamManager {
	
	/* GENERAL FIELDS - These are not treated as tree parameters */
	public static Integer lastActiveTabIndex;	// if 0, use single; if 1, use range
	public static Integer xOffset;				// the horizontal offset of the canvas (for trees that lean)		
	
	/* COLORING FIELDS */
	public static ArrayList<Integer> colors;	// a list of colors (as hex values) used for the tree
	
	/* SINGLE VALUE FIELDS */
	public static Integer branchingFactor;	// number of branches created at every split point
	public static Integer generations; 		// number of branches from start branch to any end branch
	public static Integer tilt; 			// whether tree will lean right(+), left(-) or not at all
	public static Integer branchingAngle; 	// angle between adjacent branches at the same split point
	public static Integer branchLength; 	// how long the branches should be drawn (% of canvas height)
	public static Integer branchShrinkRate;	// rate at which branch length is reduced per split (%)
	public static Integer strokeWeight;		// how thick branches should be drawn
	public static Integer strokeShrinkRate;	// rate at which stroke weight is reduced per split (%)
	
	/* RANGE VALUE FIELDS (MINS) */
	public static Integer minBranchingFactor;
	public static Integer minGenerations;
	public static Integer minTilt;
	public static Integer minBranchingAngle;
	public static Integer minBranchLength;
	public static Integer minBranchShrinkRate;
	public static Integer minStrokeWeight;
	public static Integer minStrokeShrinkRate;
	
	/* RANGE VALUE FIELDS (MAXES) */
	public static Integer maxBranchingFactor;
	public static Integer maxGenerations;
	public static Integer maxTilt;
	public static Integer maxBranchingAngle;
	public static Integer maxBranchLength;
	public static Integer maxBranchShrinkRate;
	public static Integer maxStrokeWeight;
	public static Integer maxStrokeShrinkRate;
	
	// set the parameters to their default values
	public static void reset() {
		lastActiveTabIndex = 0;
		xOffset = 0;
		
		colors = new ArrayList<Integer>();
		colors.add(0x000000);
		
		branchingFactor = 2;
		generations = 5;
		tilt = 20;
		branchingAngle = 90;
		branchLength = 50;
		branchShrinkRate = 50;
		strokeWeight = 5;
		strokeShrinkRate = 25;
		
		minBranchingFactor = 2;
		minGenerations = 3;
		minTilt = -45;
		minBranchingAngle = 45;
		minBranchLength = 40;
		minBranchShrinkRate = 25;
		minStrokeWeight = 3;
		minStrokeShrinkRate = 30;
		
		maxBranchingFactor = 5;
		maxGenerations = 7;
		maxTilt = 45;
		maxBranchingAngle = 135;
		maxBranchLength = 60;
		maxBranchShrinkRate = 75;
		maxStrokeWeight = 7;
		maxStrokeShrinkRate = 60;
		
	}
	
}
