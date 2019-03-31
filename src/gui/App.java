package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import data.Parameters;

public class App extends JFrame {
	public static final boolean TESTING = true; // enable/disable certain things while testing
	
	private JPanel content; // the content pane
	private TreeCanvas canvas; // the canvas
	private ParamPanel params; // the parameters
	
	private int bgcolor = 0x333338; // content pane background color
	private int panelcolor = 0x222226; // default panel background color
	
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

	public App() {
		Parameters.reset();
		initFrame();
		initCanvas();
		initParamPanel();
		validate();
	}

	

	// set up some initial properties for the frame.
	private void initFrame() {
		setTitle("Fractal Tree Generator");
		if (TESTING)
			setBounds(800, 400, 450, 300);
		else
			setBounds(100, 100, 450, 300);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		content = new JPanel(new BorderLayout(5, 5));
		content.setBorder(new EmptyBorder(5, 5, 5, 5));
		content.setBackground(new Color(bgcolor));
		setContentPane(content);
	}

	
	private void initCanvas() {
		canvas = new TreeCanvas(500, 500);
		content.add(canvas, BorderLayout.WEST);
	}
	
	
	private void initParamPanel() {
		params = new ParamPanel(250, 500);
		params.setBackground(new Color(panelcolor));
		content.add(params, BorderLayout.EAST);
	}
}
