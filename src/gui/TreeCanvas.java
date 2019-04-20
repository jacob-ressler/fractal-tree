package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import data.Branch;
import data.FractalTree;
import data.Parameters;
import utilities.Debug;

public class TreeCanvas extends JPanel {

	private FractalTree tree;
	private Graphics2D lastFrame;
	private int currgen;
	private boolean isAnimating = false;

	// Animation Timing Fields
	private final int fps = 10; // frames per second
	private final int mpf = 1000 / fps; // milliseconds per frame
	private long lastFrameTime; // time the last frame was drawn

	public TreeCanvas(int width, int height) {
		setPreferredSize(new Dimension(width, height));
		setBackground(Color.white);
		setDoubleBuffered(true);
		tree = new FractalTree();
		currgen = 1;
		
		// FIXME: THIS SHOULD BE REMOVED ONCE UI BUTTONS FOR RENDERING ARE IMPLEMENTED
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				//repaint();
				isAnimating = true;
				currgen = 1;
				repaint();
			}
		});
	}


	/*	**FIXME**
	 *  The entire tree has to be redrawn each frame. However, trying to optimize 
	 *  the algorithm to only draw the new branches produced the following errors:
	 *  1. Canvas shrinks on left and top after repainting 
	 *  	- something to do with not calling super() in the elseif
	 *  	- if we call super() there, we have to redraw the entire tree each frame
	 *  2. Single tab gets duplicated in upper left corner of canvas
	 *  	- possibly due to the shrinking in the top and left?
	 *  	- only occurs if window has not been resized (no idea why)
	 *  Another note: this appears to either be a Windows or OracleJDK issue.
	 *  When I run this in Ubuntu with OpenJDK on my laptop, both issues do not occur.
	 */
	@Override 
	protected void paintComponent(Graphics g) {
		
		if (isAnimating == false) {
			//draw the background
			super.paintComponent(g);
			
			// lastFrame will now hold a copy of the canvas graphics context
			lastFrame = (Graphics2D) g.create();
			
			// set the origin to the top center (+ offset) of the screen
			lastFrame.translate(getWidth() / 2 + Parameters.xOffset, 0);
			
			// Get the time for this first frame
			lastFrameTime = System.currentTimeMillis();
			

		} else {
			if (System.currentTimeMillis() - lastFrameTime >= mpf) {
				// paint background
				super.paintComponent(g);
				
				// draw the next Frame, based off the last frame
				drawNextFrame(lastFrame, tree.getBranchesUpToGen(currgen));
				
				// this is now the time of the last frame
				lastFrameTime = System.currentTimeMillis();
			}
			
			// let's see if we still have more to animate
			if (currgen <= Parameters.generations) {
				// we still have more frames to draw
				repaint();
			} else {
				// we have finished drawing
				isAnimating = false;
			}
		}
	}

	


	// draw the next frame based on the current frame
	private void drawNextFrame(Graphics2D g2, Branch[] branches) {
		Debug.log(String.valueOf(branches.length));
		g2.setColor(Color.black); // TODO: feed custom colors to this based on Parameters
		int h = getHeight();
		int w = Parameters.xOffset;
		for (Branch b : branches) {
			// FIXME: for now this just draw each generation entirely per cycle
			// It should instead draw up to a certain length of each currgen branch per frame
			g2.drawLine(b.getStart().x + w, h - b.getStart().y, b.getEnd().x + w, h - b.getEnd().y);

		}
		currgen++;

	}

}
