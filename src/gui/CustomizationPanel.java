package gui;

import java.awt.Dimension;

import javax.swing.JTabbedPane;

import gui.tabs.MiscTab;
import gui.tabs.RangedValueTab;
import gui.tabs.SingleValueTab;
import gui.tabs.Tab;

public class CustomizationPanel extends JTabbedPane {

	Tab svt, rvt, misc;
	
	public CustomizationPanel(int w, int h) {
		
		setPreferredSize(new Dimension(w, h));
		setMinimumSize(new Dimension(250, 250));
		
		//create 3 tabs
		svt = new SingleValueTab();
		addTab("Single", null, svt, "Use single values for all parameters");
		
		rvt = new RangedValueTab();
		addTab("Ranged", null, rvt, "Use ranged values for all parameters");
		
		misc = new MiscTab();
		addTab("Misc.", null, misc, "Set color pattern, toggle animation");
		
	}
	
	
		
}
