package gui.tabs;

import java.awt.Color;
import java.awt.Dimension;
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

public abstract class Tab extends JScrollPane {
	
	protected ArrayList<JComponent> items;
	protected JComponent content;
	
	public Tab() {
		setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
		
		items = new ArrayList<JComponent>();
		content = new JPanel(new GridLayout(0, 1, 0, 5));
		setViewportView(content);
		setViewportBorder(new EmptyBorder(1, 1, 1, 1));
		setLayout(new ScrollPaneLayout());
		
	}
	
	// add an item to the list of items and give it the specified label
	// TODO? Add a third argument is a direct reference to the slider/toggle/selector
	// 		 Idea: we can just make sure the component we need is always the last to be added to item
	//			   then we can just get the child at index <number of children> - 1.
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
		// p.setMaximumSize(new Dimension(getWidth(), 200)); // ignored by GridLayout, maybe we should switch to a GridBagLayout?
		content.add(p);
		items.add(p);
		content.revalidate();
		revalidate();
		return item;
	}
	

	// Used by MiscTab, since it requires a different layout
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
		// p.setMaximumSize(new Dimension(getWidth(), 200)); // ignored by GridLayout, maybe we should switch to a GridBagLayout?
		content.add(p, layoutInfo);
		items.add(p);
		content.revalidate();
		revalidate();
		return item;
	}
	public void removeItemAt(int index) {
		// since we changed layout information, we have to revalidate.
		content.revalidate();
		revalidate();
	}
	
	public void setPreferredSize(int w, int h) {
		setPreferredSize(new Dimension(w, h));
	}
}
