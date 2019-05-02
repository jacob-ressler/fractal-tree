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
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import data.ParamManager;

/**
 * The "Single" tab. Holds all single-value customization UI.
 * <p>Subclass of {@link Tab}
 * @author Jacob Ressler &amp; Anthony Lantz
 *
 */
public class SingleValueTab extends Tab {

	/**
	 * Create a new SingleValueTab.
	 */
	public SingleValueTab() {
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
	 * Creates a JPanel with 2 {@link JLabel}s and a {@link JSlider}.
	 * One JLabel displays "Value:", with the other changing dynamically
	 * to display the current slider value.
	 * @param min the lower bound of the slider
	 * @param max the upper bound of the slider
	 * @param i an index value used to link this slider with its corresponding {@link Integer}
	 * in {@link ParamManager}.single[]
	 * @return the JPanel
	 */
	private JPanel createSlider(int min, int max, int i) {
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
                ParamManager.single[i] = s.getValue();
                //System.out.println(ParamManager.single[i] + "  " + i);
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
        slider.setValue(ParamManager.single[i]);
        
        // Initialize value display.
        sliderValue.setText(String.valueOf(slider.getValue()));
        
        return p;
	}
	
	
	
	
}
