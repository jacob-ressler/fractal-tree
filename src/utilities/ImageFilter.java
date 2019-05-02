package utilities;

import java.io.File;

import javax.swing.filechooser.FileFilter;

/**
 * A file dialog filter to only show directories and PNG/JPG files.<br>
 * A subclass of {@link FileFilter}
 * @author Jacob Ressler &amp; Anthony Lantz
 *
 */
public class ImageFilter extends FileFilter {

	@Override
	public String getDescription() {
		return "Images (JPG, PNG)";
	}
	
	@Override
	public boolean accept(File f) {
		if (f.isDirectory()) {
	        return true;
	    }

	    String ext = f.getName();
	    		ext = ext.substring(ext.lastIndexOf('.')).toLowerCase();
	    if (ext != null) {
	        if (ext.equals(".jpeg") || ext.equals(".jpg") || ext.equals(".png")) {
	                return true;
	        } else {
	            return false;
	        }
	    }

	    return false;
	}
}
