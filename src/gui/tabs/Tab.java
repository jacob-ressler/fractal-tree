package gui.tabs;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import gui.CustomizationPanel;

/**
 * An abstract class that represents a tab of {@link CustomizationPanel}.
 * Contains general methods and properties to be used by its concrete extensions.
 * <p>Subclass of {@link JScrollPane}
 * @author Jacob Ressler & Anthony Lantz
 *
 */
public abstract class Tab extends JScrollPane {
	
	protected ArrayList<JComponent> items;
	protected JComponent content;
	
	/**
	 * Set up a general tab.
	 */
	public Tab() {
		setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
		items = new ArrayList<JComponent>();
		content = new JPanel(new GridLayout(0, 1, 0, 5));
		setViewportView(content);
		setViewportBorder(new EmptyBorder(1, 1, 1, 1));
		setLayout(new ScrollPaneLayout());
		
	}
	
	/**
	 * Sets up a {@link JPanel} with a {@link JLabel} and a {@link JComponent}
	 * and adds it to the tab. Used by {@link SingleValueTab} and {@link RangedValueTab}.
	 * @param label the string for the JLabel
	 * @param item the JComponent to be added
	 * @return item
	 */
	public JComponent addItem(String label, JComponent item) {
		JPanel p = new JPanel();
		JLabel l = new JLabel(label, JLabel.CENTER);
		l.setFont(new Font("Muli-Black", Font.BOLD, 16));
		p.setBackground(new Color(0xe4e4e4));
		p.setBorder(new LineBorder(new Color(0x333333), 1));
		item.setBackground(new Color(0xe4e4e4));
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		p.setAlignmentX(0.5f);
		l.setAlignmentX(0.5f);
		p.add(l);
		p.add(item);
		content.add(p);
		items.add(p);
		content.revalidate();
		revalidate();
		return item;
	}
	

	/**
	 * Sets up a {@link JPanel} with a {@link JLabel} and a {@link JComponent}
	 * and adds it to the tab. The JPanel is laid out according to the layoutInfo.
	 * @param label the string for the JLabel
	 * @param item the JComponent to be added
	 * @param layoutInfo the layout info for the JPanel
	 * @return item
	 */
	public JComponent addItem(String label, JComponent item, String layoutInfo) {
		JPanel p = new JPanel();
		if (label != null && !label.equals("")) {
			JLabel l = new JLabel(label, JLabel.CENTER);
			l.setFont(new Font("Muli-Black", Font.BOLD, 16));
			l.setAlignmentX(0.5f);
			p.add(l);
		}
		p.setBackground(new Color(0xe4e4e4));
		p.setBorder(new LineBorder(new Color(0x333333), 1));
		item.setBackground(new Color(0xe4e4e4));
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		p.setAlignmentX(0.5f);

		p.add(item);
		content.add(p, layoutInfo);
		items.add(p);
		content.revalidate();
		revalidate();
		return item;
	}
	
	/**
	 * Remove the child component at the specified index.
	 * 
	 * <p>Currently, this is not used, but will likely be used in a future implementation
	 * of {@link MiscTab} with a less restricted color pattern selection UI.
	 * @param index the index
	 */
	public void removeItemAt(int index) {
		items.remove(index);
		remove(index);
		content.revalidate();
		revalidate();
	}
}
