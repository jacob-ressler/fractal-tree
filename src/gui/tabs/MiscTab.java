package gui.tabs;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;

import gui.App;
import utilities.Debug;
import utilities.ImageFilter;

public class MiscTab extends Tab {

	JFileChooser saver;
	JPanel coloringPanel;
	
	public MiscTab() {
		super();
		saver = new JFileChooser();
		content.setLayout(new BorderLayout(0, 5));
		addItem("", createSaveButton(), BorderLayout.NORTH);
		addItem("Color Pattern", createColoringPanel(), BorderLayout.CENTER);
	}
	
	// Image Saving
	private JButton createSaveButton() {
		JButton btn = new JButton("Save Image");
		btn.setAlignmentX(0.5f);
		
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// only display image files and directories
				FileFilter ff = new ImageFilter();
				saver.addChoosableFileFilter(ff);
				saver.setAcceptAllFileFilterUsed(false);
				saver.setFileFilter(ff);
				
				Debug.log("Saving Image...");
				if (saver.showSaveDialog((Component) e.getSource()) == JFileChooser.APPROVE_OPTION) {

					  File f = saver.getSelectedFile();
					  
					  String ext = f.getName();
					  int i = ext.lastIndexOf(".");
					  ext = i == -1 ? "" : ext.substring(i);
					  System.out.println(ext);
					  if (ext.equals("") || (!ext.equals(".png") && !ext.equals(".jpg") && !ext.equals(".jpeg"))) {
						  f = new File(f.getAbsoluteFile() + ".png");
					  }
					  
					  App.canvas.saveImage(f);
					  
					  Debug.log("File saved to:" + f.getAbsolutePath() + " " + f.getName());
				}
				
			}
		});
		return btn;
	}
	
	// Coloring
	private JPanel createColoringPanel() {
		JPanel p = new JPanel(new GridLayout(1, 4, 2, 2));
		p.setPreferredSize(new Dimension(getWidth(), 200)); // may need to change
		return p;
	}
	
	
}
