package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import data.FractalTree;
import data.Parameters;

public class TreeCanvas extends JPanel {
	
	private FractalTree tree;
	
	public TreeCanvas(int width, int height) {
		setPreferredSize(new Dimension(width, height));
		this.tree = new FractalTree();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		// set the background color
		g2.setColor(Color.white);
		g2.fillRect(0, 0, getWidth(), getHeight());
		g2.setColor(Color.black);
		
		g2.translate(getWidth()/2 + Parameters.xOffset, 0); // set the origin to the top center (+ offset) of the screen
		
		//TODO: All drawing should be moved here from the data classes.
		// They should be strictly data and not directly access/affect 
		// the UI in any way
		tree.draw(g2, getHeight());
	}
	
}
