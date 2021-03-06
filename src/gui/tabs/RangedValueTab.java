package gui.tabs;

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

import data.ParamManager;
import gui.vendor.ernieyu.RangeSlider;

/**
 * The "Ranged" tab. Holds all ranged-value customization UI.
 * <p>Subclass of {@link Tab}
 * @author Jacob Ressler &amp; Anthony Lantz
 *
 */
public class RangedValueTab extends Tab {

	/**
	 * Create a new RangedValueTab.
	 */
	public RangedValueTab() {
		super();
		items = new ArrayList<JComponent>();
		// create the parameter sliders
		int i = 0;
		addItem("Branching Factor", createSlider(0, 10, i++));
		addItem("Generations", createSlider(1, 10, i++));
		addItem("Tilt", createSlider(-30, 30, i++));
		addItem("Branching Angle", createSlider(10, 135, i++));
		addItem("Branch Length", createSlider(10, 60, i++));
		addItem("Branch Shrink Rate", createSlider(25, 75, i++));
		addItem("Stroke Weight", createSlider(1, 30, i++));
		addItem("Stroke Shrink Rate", createSlider(0, 50, i++));
	}
	
	
	/**
	 * Creates a JPanel with 4 {@link JLabel}s and a {@link RangeSlider}.
	 * One JLabel displays "Min:", with another changing dynamically
	 * to display the current lower slider value.
	 * The same is done for the upper slider value with the other 2 JLabels.
	 * @param min the lower bound of the slider
	 * @param max the upper bound of the slider
	 * @param i an index value used to link this slider with its corresponding {@link Integer}s
	 * in {@link ParamManager}.rangeMin[] and {@link ParamManager}.rangeMax[]
	 * @return the JPanel
	 */
	private JPanel createSlider(int min, int max, int i) {
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
        // Initialize values.
        rangeSlider.setValue(ParamManager.rangeMin[i]);
        rangeSlider.setUpperValue(ParamManager.rangeMax[i]);
        
        // Add listener to update display.
        rangeSlider.addChangeListener(new ChangeListener() {
            @Override
			public void stateChanged(ChangeEvent e) {
                RangeSlider slider = (RangeSlider) e.getSource();
                rangeSliderValue1.setText(String.valueOf(slider.getValue()));
                rangeSliderValue2.setText(String.valueOf(slider.getUpperValue()));
                ParamManager.rangeMin[i] = slider.getValue();
                ParamManager.rangeMax[i] = slider.getUpperValue();
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
        
        // Initialize value display.
        rangeSliderValue1.setText(String.valueOf(rangeSlider.getValue()));
        rangeSliderValue2.setText(String.valueOf(rangeSlider.getUpperValue()));
        
        return p;
	}
}
