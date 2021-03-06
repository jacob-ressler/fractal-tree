package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.plaf.basic.BasicSplitPaneUI;

import data.ParamManager;

/**
 * The main application window.
 * @author Jacob Ressler &amp; Anthony Lantz
 *
 */
public class App extends JFrame {
	
	public static TreeCanvas canvas; // the canvas
	
	/**
	 * The main content pane
	 */
	private JSplitPane content;
	private CustomizationPanel custom;
	
	private final int bgcolor = 0x333338;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					App app = new App();
					app.pack();
					app.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application window and all necessary components.
	 */
	public App() {
		ParamManager.reset();
		initFont();
		customizeUI();
		initFrame();
		initCanvas();
		initCustomizationPanel();
		validate();
	}

	/**
	 * Initialize the external font for use in the application.
	 */
	private void initFont() {
		try {
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Muli-Black.ttf")));
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * Set up the initial properties for the frame and content pane
	 */
	private void initFrame() {
		setTitle("Fractal Tree Generator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(400, 400));
		
		content = new JSplitPane();
		content.setBorder(new EmptyBorder(5, 5, 5, 5));
		content.setBackground(new Color(bgcolor));
		
		// This customizes the divider to match the background and removes its border.
		content.setUI(new BasicSplitPaneUI() {
            @Override
			public BasicSplitPaneDivider createDefaultDivider() {
            	return new BasicSplitPaneDivider(this) {
	                @Override
					public void setBorder(Border b) { /* no border */ }
	
	                @Override
	                public void paint(Graphics g) {
	            		super.paint(g);
	                    g.setColor(new Color(bgcolor));
	                    int w = getWidth(), h = getHeight(), d = getWidth()/2;
	                    g.fillRect(0, 0, w, h);
	                    // draw little circles to indicate the divider
	                    g.setColor(new Color(0x777791));
	                    g.fillOval(w/2 - d/2, h/2 - d/2, d, d);
	                    g.fillOval(w/2 - d/2,  h/2 - 3*d - d/2, d, d);
	                    g.fillOval(w/2 - d/2, h/2 + 3*d - d/2, d, d);
	                }
            	};
            }
        });
		
		setContentPane(content);
	}

	/**
	 * Set up the TreeCanvas
	 */
	private void initCanvas() {
		// Put a label above the canvas with instructions
		JPanel p = new JPanel(new BorderLayout(0, 2));
		p.setBackground(new Color(bgcolor));
		JLabel l = new JLabel("Click on the Canvas to draw a Fractal Tree using the active tab's values.", JLabel.CENTER);
		l.setFont(new Font("Muli-Black", Font.PLAIN, 14));
		l.setOpaque(true);
		l.setBackground(new Color(0xeeeeee));
		p.add(l, BorderLayout.NORTH);
		canvas = new TreeCanvas(500, 500);
		p.add(canvas, BorderLayout.CENTER);
		content.add(p, JSplitPane.LEFT);
	}
	
	/**
	 * Set up the CustomizationPanel
	 */
	private void initCustomizationPanel() {
		custom = new CustomizationPanel(250, 500);
		content.add(custom, JSplitPane.RIGHT);
	}
	
	/**
	 * Customize the UI to use system look and feel with slight modifications.
	 */
	private void customizeUI() { 
		// use the system look and feel, since it almost definitely 
		// will look better than the default Swing one
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// get rid of dashed focus rectangles
		UIManager.put("TabbedPane.focus", Color.white); 
		UIManager.put("Slider.focus", Color.white);
		
		// set the insets (the only insets we want are tabInsets, since they act like padding)
		UIManager.put("TabbedPane.contentBorderInsets", new Insets(0, 0, 0, 0));
		UIManager.put("TabbedPane.tabAreaInsets", new Insets(0, 0, 0, 0));
		UIManager.put("TabbedPane.tabInsets", new Insets(5, 5, 5, 5));
		UIManager.put("TabbedPane.selectedTabPadInsets", new Insets(0, 0, 0, 0));
		
	}
}
