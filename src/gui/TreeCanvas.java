package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import data.Branch;
import data.FractalTree;
import data.ParamManager;
import utilities.Debug;

public class TreeCanvas extends JPanel {
	public static BufferedImage img;
	
	private FractalTree tree;
	private Graphics2D lastFrame;
	private int currgen;
	private boolean isAnimating = false;

	// Animation Timing Fields
	private final int fps = 10; // frames per second
	private final int mpf = 1000 / fps; // milliseconds per frame
	private long lastFrameTime; // time the last frame was drawn

	public TreeCanvas(int width, int height) {
		setName("Tree Canvas");
		setPreferredSize(new Dimension(width, height));
		setBackground(Color.white);
		setDoubleBuffered(true);
		tree = new FractalTree(Math.min(getHeight(), getWidth()));
		currgen = 1;
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				tree = new FractalTree(Math.min(getHeight(), getWidth()));
				isAnimating = true;
				currgen = 1;
				repaint();
			}
		});
	}


	@Override 
	protected void paintComponent(Graphics g) {
		
		if (isAnimating == false) {
			//draw the background
			super.paintComponent(g);
			
			// lastFrame will now hold a copy of the canvas graphics context
			lastFrame = (Graphics2D) g.create();
			
			// set the origin to the top center of the screen
			lastFrame.translate(getWidth() / 2, 0);
			
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
			if (currgen <= ParamManager.single[1]) {
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
		int w = ParamManager.xOffset;
		for (Branch b : branches) {
			g2.setStroke(new BasicStroke(b.getStrokeWeight()));
			g2.drawLine(b.getStart().x + w, h - b.getStart().y, b.getEnd().x + w, h - b.getEnd().y);

		}
		currgen++;

	}
	
	// Save the canvas to an image file (this is probably a horrible way to do this, but it works)
	public void saveImage(File f) {
		Rectangle r = getBounds();

        try {
            BufferedImage i = new BufferedImage(r.width, r.height, BufferedImage.TYPE_INT_ARGB);
            Graphics g = i.createGraphics();
            // draw the tree
            g.translate(r.width / 2, 0);
            drawNextFrame((Graphics2D) g, tree.getAllBranches());
            g.dispose();
            ImageIO.write(i, "png", f);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

}
