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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import gui.vendor.ernieyu.RangeSlider;

public class RangedValueTab extends Tab {

	
	public RangedValueTab() {
		super();
		items = new ArrayList<JComponent>();
		// create the parameter sliders
		addItem("Branching Factor", createRangeSlider(0, 10, 2, 5));
		addItem("Generations", createRangeSlider(0, 10, 2, 5));
		addItem("Tilt", createRangeSlider(0, 10, 2, 5));
		addItem("Branching Angle", createRangeSlider(0, 10, 2, 5));
		addItem("Branch Length", createRangeSlider(0, 10, 2, 5));
		addItem("Branch Shrink Rate", createRangeSlider(0, 10, 2, 5));
		addItem("Stroke Weight", createRangeSlider(0, 10, 2, 5));
		addItem("Branch Shrink Rate", createRangeSlider(0, 10, 2, 5));
	}
	
	
	// Creates a JPanel with a RangeSlider and 2 JLabels
	private JPanel createRangeSlider(int min, int max, int lower, int upper) {
		JLabel rangeSliderLabel1 = new JLabel();
	    JLabel rangeSliderValue1 = new JLabel();
	    JLabel rangeSliderLabel2 = new JLabel();
	    JLabel rangeSliderValue2 = new JLabel();
	    RangeSlider rangeSlider = new RangeSlider(min, max);
		JPanel p = new JPanel();
		
		p.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
        p.setLayout(new GridBagLayout());
        
        rangeSliderLabel1.setText("Min: ");
        rangeSliderLabel2.setText("Max:      ");
        rangeSliderValue1.setHorizontalAlignment(JLabel.LEFT);
        rangeSliderValue2.setHorizontalAlignment(JLabel.LEFT);
        
        rangeSlider.setPreferredSize(new Dimension(240, rangeSlider.getPreferredSize().height));
        
        // Add listener to update display.
        rangeSlider.addChangeListener(new ChangeListener() {
            @Override
			public void stateChanged(ChangeEvent e) {
                RangeSlider slider = (RangeSlider) e.getSource();
                rangeSliderValue1.setText(String.valueOf(slider.getValue()));
                rangeSliderValue2.setText(String.valueOf(slider.getUpperValue()));
            }
        });

        p.add(rangeSliderLabel1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
            GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        p.add(rangeSliderValue1, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
            GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        p.add(rangeSliderLabel2, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
            GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        p.add(rangeSliderValue2, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
            GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        p.add(rangeSlider      , new GridBagConstraints(0, 2, 3, 1, 0.0, 0.0,
            GridBagConstraints.SOUTH, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        
        // Initialize values.
        rangeSlider.setValue(lower);
        rangeSlider.setUpperValue(upper);
        
        // Initialize value display.
        rangeSliderValue1.setText(String.valueOf(rangeSlider.getValue()));
        rangeSliderValue2.setText(String.valueOf(rangeSlider.getUpperValue()));
        
        return p;
	}
}
