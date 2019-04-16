package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JComponent;

public class ParamPanel extends JPanel {

	
	public ParamPanel(int w, int h) {
		
		JTabbedPane tabbedPane = new JTabbedPane();
		//setPreferredSize(new Dimension(w, h));
		
		//create 3 tabs
		JComponent panel1 = makeTextPanel("Panel 1");
		panel1.setPreferredSize(new Dimension(w, h));
		tabbedPane.addTab("Single", null, panel1, "Tab 1");
		
		JComponent panel2 = makeTextPanel("Panel 2");
		panel2.setPreferredSize(new Dimension(w, h));
		tabbedPane.addTab("Ranged", null, panel2, "Tab 2");
		
		JComponent panel3 = makeTextPanel("Panel 3");
		panel3.setPreferredSize(new Dimension(w, h));
		tabbedPane.addTab("Misc.", null, panel3, "Tab 3");
		
		add(tabbedPane);
	}
	
	protected JComponent makeTextPanel(String text) {
		JPanel panel = new JPanel(false);
		JLabel filler = new JLabel(text);
		filler.setHorizontalAlignment(JLabel.CENTER);
		panel.setLayout(new GridLayout(1,1));
		panel.add(filler);
		return panel;
	}
	
	
		
}
