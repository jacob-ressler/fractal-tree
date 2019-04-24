package utilities;

import java.io.File;

import javax.swing.filechooser.FileFilter;

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
