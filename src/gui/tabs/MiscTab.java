package gui.tabs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileFilter;

import data.ParamManager;
import gui.App;
import utilities.Debug;
import utilities.ImageFilter;
import utilities.JTextFieldLimit;

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
		JPanel p = new JPanel(new GridLayout(5, 4, 2, 2));
		for (int i = 0; i < 5; i++) {
			p.add(colorItem(i));
		}
		
		
		return p;
	}
	
	
	private JPanel colorItem(int i) {
		JPanel p = new JPanel(new GridLayout(3, 4, 2, 2));
		JLabel l = new JLabel();
		JTextField colorInput = new JTextField(15);
		
		colorInput.setDocument(new JTextFieldLimit(6));
		
		colorInput.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
            	getColor();
            }
			@Override
			public void insertUpdate(DocumentEvent e) {
                getColor();
            }
			@Override
			public void removeUpdate(DocumentEvent e) {
				getColor();
			}
			
			public void getColor() {
				String text = colorInput.getText();
				if(text.equals("")) {
					p.setBackground(Color.black);
					ParamManager.colors.set(i, Color.black);
					return;
				}

				String hexVal = ("0x" + colorInput.getText());
				p.setBackground(Color.decode(hexVal));
				ParamManager.colors.set(i, Color.decode(hexVal));
			}
		});
            
		l.setText("Hex: ");
        l.setHorizontalAlignment(JLabel.LEFT);
		p.setPreferredSize(new Dimension(getWidth(), 50)); // may need to change
		p.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
		
		p.add(l);
		p.add(colorInput);
		return p;
	}

	
}
