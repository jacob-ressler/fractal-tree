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
import javax.swing.text.AbstractDocument;

import data.ParamManager;
import gui.App;
import gui.TreeCanvas;
import utilities.Debug;
import utilities.ImageFilter;
import utilities.LimitDocumentFilter;

/**
 * The "Misc" tab. Holds all miscellaneous customization UI.
 * <p>Subclass of {@link Tab}
 * @author Jacob Ressler &amp; Anthony Lantz
 *
 */
public class MiscTab extends Tab {

	JFileChooser saver;
	JPanel coloringPanel;
	
	/**
	 * Create a new MiscTab.
	 */
	public MiscTab() {
		super();
		saver = new JFileChooser();
		content.setLayout(new BorderLayout(0, 5));
		addItem("", createSaveButton(), BorderLayout.NORTH);
		addItem("Color Pattern", createColoringPanel(), BorderLayout.CENTER);
	}
	
	/**
	 * Create a {@link JButton} that when pressed will create a {@link JFileChooser}
	 * dialog for saving the {@link TreeCanvas} graphics context to an image file.
	 * @return the created JButton
	 */
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
	
	/**
	 * Create a JPanel to hold all tree coloring-related UI.
	 * <p>Currently, this is hard-coded to only create 5 children panels for
	 * color customization. In the future, we hope to improve this to 
	 * support a variable number.
	 * @return the JPanel
	 */
	private JPanel createColoringPanel() {
		JPanel p = new JPanel(new GridLayout(5, 4, 3, 3));
		for (int i = 0; i < 5; i++) {
			p.add(colorItem(i));
		}
		return p;
	}
	
	/**
	 * Create a color customization panel and tie it to a {@link Color} 
	 * in {@link ParamManager}.colors
	 * @param i the index ofthe {@link ParamManager}.colors {@link Color} this item is linked to
	 * @return the created JPanel
	 */
	private JPanel colorItem(int i) {
		JPanel p = new JPanel(new GridLayout(4, 3, 3, 3));
		JLabel l = new JLabel();
		JTextField colorInput = new JTextField(10);
		
		// limit the number of characters to 6
		((AbstractDocument) colorInput.getDocument()).setDocumentFilter(new LimitDocumentFilter(6));
		
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
        l.setHorizontalAlignment(JLabel.CENTER);
        l.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
        l.setOpaque(true);
        l.setBackground(new Color(0xececec));
		p.setPreferredSize(new Dimension(getWidth(), 100)); // may need to change
		p.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
		JPanel fill = new JPanel();
		fill.setOpaque(false);
		p.add(fill);
		p.add(l);
		p.add(colorInput);
		fill = new JPanel();
		fill.setOpaque(false);
		p.add(fill);
		return p;
	}

	
}
