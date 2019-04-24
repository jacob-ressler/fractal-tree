package gui.tabs;

import java.awt.BorderLayout;
import java.awt.Component;
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
	FileFilter ff;
	public MiscTab() {
		super();
		saver = new JFileChooser();
		content.setLayout(new BorderLayout(0, 5));
		addItem("", createSaveButton());
		
	}
	
	// Image Saving
	private JButton createSaveButton() {
		JButton btn = new JButton("Save Image");
		
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {		

				
				
				ff = new ImageFilter();
				saver.addChoosableFileFilter(ff);
				saver.setAcceptAllFileFilterUsed(false);
				saver.setFileFilter(ff);
				Debug.log("Saving Image...");
				if (saver.showSaveDialog((Component) e.getSource()) == JFileChooser.APPROVE_OPTION) {

					  File f = saver.getSelectedFile();
					  
					  Debug.log(f.getAbsolutePath());
					  
					  App.canvas.saveImage(f);
				}
				
			}
		});
		return btn;
	}
	
	// Coloring
	private JPanel createColoringPanel() {
		return null;
	}
	
	
}
