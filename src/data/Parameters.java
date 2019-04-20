package data;

// Responsible for holding all of the current parameter values
public class Parameters {
	
	/* BOOLEAN FIELDS */
	public static boolean useRanges; // toggles whether to use single values or ranges of values when generating the tree
	
	public static boolean bsrIsPercent; // toggles whether branchShrinkRate should be interpreted as a value or percentage
	public static boolean ssrIsPercent; // toggles whether strokeShrinkRate should be interpreted as a value or percentage
	
	
	/* NUMERICAL FIELDS */
	public static int branchingFactor; // number of branches created at every split point
	public static int generations; // number of branches from start branch to any end branch
	public static float tilt; // whether tree will lean right(+), left(-) or not at all
	public static float branchingAngle; // angle between adjacent branches at the same split point
	public static int branchLength; // how long the branches should be drawn
	public static int branchShrinkRate; // rate at which branch length is reduced per split
	public static float strokeWeight; // how thick branches should be drawn
	public static float strokeShrinkRate; // rate at which stroke weight is reduced per split
	public static int xOffset; // the horizontal offset of the canvas (for trees that lean)
	
	// set the parameters to their default values
	public static void reset() {
		bsrIsPercent = true;
		ssrIsPercent = true;
		branchingFactor = 13;
		generations = 6;
		tilt = 0f;
		branchingAngle = 10;
		branchLength = 200;
		branchShrinkRate = 45;
		strokeWeight = 5;
		strokeShrinkRate = 25;
		xOffset = 0;
	}
	
}
