package gui;

import java.awt.Dimension;

import javax.swing.JTabbedPane;

import gui.tabs.MiscTab;
import gui.tabs.RangedValueTab;
import gui.tabs.SingleValueTab;
import gui.tabs.Tab;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import data.ParamManager;

public class CustomizationPanel extends JTabbedPane {

	Tab svt, rvt, misc;
	
	
	public CustomizationPanel(int w, int h) {
		
		setPreferredSize(new Dimension(w, h));
		setMinimumSize(new Dimension(290, 250)); // needs to be big enough to see full slider (do not change)
		
		//create 3 tabs
		svt = new SingleValueTab();
		addTab("Single", null, svt, "Use single values for all parameters");
		
		rvt = new RangedValueTab();
		addTab("Ranged", null, rvt, "Use ranged values for all parameters");
		
		misc = new MiscTab();
		addTab("Misc.", null, misc, "Set color pattern, toggle animation");
		
		//add listener to keep track of selected tab.
		addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
            	if(getSelectedIndex() != 2)
            		ParamManager.lastActiveTabIndex = getSelectedIndex();
            }
        });
		
	}
	
	
		
}
