package data;

import java.awt.Color;
import java.util.ArrayList;

// Responsible for holding all of the current parameter values
public class ParamManager {
	
	public static Integer[] single = new Integer[8];
	public static Integer[] rangeMin = new Integer[8];
	public static Integer[] rangeMax = new Integer[8];
	
	/* GENERAL FIELDS - These are not treated as tree parameters */
	public static Integer lastActiveTabIndex;	// if 0, use single; if 1, use range
	public static Integer xOffset;				// the horizontal offset of the canvas (for trees that lean)		
	
	/* COLORING FIELDS */
	public static ArrayList<Color> colors;	// a list of colors (as hex values) used for the tree
	
	/* SINGLE VALUE FIELDS */
	// Branching Factor: number of branches created at every split point
	// Generations: number of branches from start branch to any end branch
	// Tilt: whether tree will lean right(+), left(-) or not at all
	// Branching Angle: angle between adjacent branches at the same split point
	// Branch Length: how long the branches should be drawn (% of canvas height)
	// Branch Shrink Rate: rate at which branch length is reduced per split (%)
	// Stroke Weight: how thick branches should be drawn
	// Stroke Shrink Rate: rate at which stroke weight is reduced per split (%)
	
	
	// set the parameters to their default values
	public static void reset() {
		lastActiveTabIndex = 0;
		xOffset = 0;
		
		colors = new ArrayList<Color>();
		colors.add(Color.black);
		colors.add(Color.black);
		colors.add(Color.black);
		colors.add(Color.black);
		colors.add(Color.black);
		
		single[0] = 2;	// branching factor
		single[1] = 5;	// generations
		single[2] = 0;	// tilt
		single[3] = 90;	// branching angle
		single[4] = 50;	// branch length
		single[5] = 50;	// branch shrink rate
		single[6] = 5;	// stroke weight
		single[7] = 25;	// stroke shrink rate
		
		rangeMin[0] = 3;
		rangeMin[1] = 4;
		rangeMin[2] = -10;
		rangeMin[3] = 20;
		rangeMin[4] = 25;
		rangeMin[5] = 40;
		rangeMin[6] = 5;
		rangeMin[7] = 20;
		
		rangeMax[0] = 6;
		rangeMax[1] = 7;
		rangeMax[2] = 10;
		rangeMax[3] = 70;
		rangeMax[4] = 50;
		rangeMax[5] = 60;
		rangeMax[6] = 15;
		rangeMax[7] = 40;
		
	}
	
}
