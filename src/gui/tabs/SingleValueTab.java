package gui.tabs;

import data.ParamManager;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SingleValueTab extends Tab {

	
	public SingleValueTab() {
		super();
		items = new ArrayList<JComponent>();
		// create the parameter sliders
		addItem("Branching Factor", createSlider(0, 10, 2));
		addItem("Generations", createSlider(0, 10, 2));
		addItem("Tilt", createSlider(0, 10, 2));
		addItem("Branching Angle", createSlider(0, 10, 2));
		addItem("Branch Length", createSlider(0, 10, 2));
		addItem("Branch Shrink Rate", createSlider(0, 10, 2));
		addItem("Stroke Weight", createSlider(0, 10, 2));
		addItem("Stroke Shrink Rate", createSlider(0, 10, 2));
	}

	
	// Creates a panel with a JSlider and JLabel
	private JPanel createSlider(int min, int max, int val) {
		JLabel sliderLabel = new JLabel();
	    JLabel sliderValue = new JLabel();
	    JSlider slider = new JSlider(min, max);
		JPanel p = new JPanel();
		
		p.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
        p.setLayout(new GridBagLayout());
        
        sliderLabel.setText("Value: ");
        sliderValue.setHorizontalAlignment(JLabel.LEFT);
        
        slider.setPreferredSize(new Dimension(240, slider.getPreferredSize().height));
        
        // Add listener to update display.
        slider.addChangeListener(new ChangeListener() {
            @Override
			public void stateChanged(ChangeEvent e) {
                JSlider s = (JSlider) e.getSource();
                sliderValue.setText(String.valueOf(s.getValue()));
            }
        });

        p.add(sliderLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
            GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        p.add(sliderValue, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
            GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        p.add(slider      , new GridBagConstraints(0, 2, 2, 1, 0.0, 0.0,
            GridBagConstraints.SOUTH, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        
        // Initialize values.
        slider.setValue(val);
        
        // Initialize value display.
        sliderValue.setText(String.valueOf(slider.getValue()));
        
        return p;
	}
	
	
	
	
}
