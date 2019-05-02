package data;

import java.awt.Color;
import java.util.ArrayList;

/**
 * A public static class for managing all parameter values used in
 * creating and drawing the fractal trees.
 * @author Jacob Ressler &amp; Anthony Lantz
 *
 */
public class ParamManager {
	/**
	 * Holds the 8 single-value parameters.
	 * <ol>
	 * <li><u>Branching Factor:</u> number of branches created at every split point
	 * <li><u>Generations:</u> number of branches from start branch to any end branch
	 * <li><u>Tilt:</u> whether tree will lean right(-), left(+) or not at all
	 * <li><u>Branching Angle:</u> angle between adjacent branches at the same split point
	 * <li><u>Branch Length:</u> how long the branches should be drawn (% of canvas width/height)
	 * <li><u>Branch Shrink Rate:</u> rate at which branch length is reduced per split (%)
	 * <li><u>Stroke Weight:</u> how thick branches should be drawn
	 * <li><u>Stroke Shrink Rate:</u> rate at which stroke weight is reduced per split (%)
	 * </ol>
	 */
	public static Integer[] single = new Integer[8];
	
	/**
	 * Holds the 8 lower ranged-value parameters.
	 * <ol>
	 * <li><u>Branching Factor:</u> number of branches created at every split point
	 * <li><u>Generations:</u> number of branches from start branch to any end branch
	 * <li><u>Tilt:</u> whether tree will lean right(-), left(+) or not at all
	 * <li><u>Branching Angle:</u> angle between adjacent branches at the same split point
	 * <li><u>Branch Length:</u> how long the branches should be drawn (% of canvas width/height)
	 * <li><u>Branch Shrink Rate:</u> rate at which branch length is reduced per split (%)
	 * <li><u>Stroke Weight:</u> how thick branches should be drawn
	 * <li><u>Stroke Shrink Rate:</u> rate at which stroke weight is reduced per split (%)
	 * </ol>
	 */
	public static Integer[] rangeMin = new Integer[8];
	
	/**
	 * Holds the 8 upper ranged-value parameters.
	 * <ol>
	 * <li><u>Branching Factor:</u> number of branches created at every split point
	 * <li><u>Generations:</u> number of branches from start branch to any end branch
	 * <li><u>Tilt:</u> whether tree will lean right(-), left(+) or not at all
	 * <li><u>Branching Angle:</u> angle between adjacent branches at the same split point
	 * <li><u>Branch Length:</u> how long the branches should be drawn (% of canvas width/height)
	 * <li><u>Branch Shrink Rate:</u> rate at which branch length is reduced per split (%)
	 * <li><u>Stroke Weight:</u> how thick branches should be drawn
	 * <li><u>Stroke Shrink Rate:</u> rate at which stroke weight is reduced per split (%)
	 * </ol>
	 */
	public static Integer[] rangeMax = new Integer[8];
	
	/**
	 * The index of the current active tab (excluding MiscTab)
	 * <p><b>0</b> - if "Single" tab was active more recently than "Ranged" tab<br>
	 * <b>1</b> - otherwise
	 */
	public static Integer lastActiveTabIndex;		
	
	/**
	 * A list of colors to be used for coloring the drawn tree.
	 * Currently fixed at 5 colors, but will ideally be variable in the future.
	 * <p>The index used for a branch is determined by its generation - 1, modulo the size of this list.
	 */
	public static ArrayList<Color> colors;
	
	/**
	 * Initialize all parameters and set their default values. Called on application startup.
	 */
	public static void reset() {
		lastActiveTabIndex = 0;
		
		colors = new ArrayList<Color>();
		for (int i = 0; i < 5; i++) {
			colors.add(Color.black);
		}
		
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
