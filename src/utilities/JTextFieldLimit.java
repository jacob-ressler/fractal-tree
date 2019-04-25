package utilities;

import javax.print.attribute.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

public class JTextFieldLimit extends PlainDocument {
  private int limit;

   public JTextFieldLimit(int limit) {
   super();
   setDocumentFilter(new DocumentFilter() {

		@Override
		public void insertString(FilterBypass fb, int offset, String string, javax.swing.text.AttributeSet attr)
				throws BadLocationException {
			if (fb.getDocument().getLength() + string.length() > limit) {
				return;
			}
			
			super.insertString(fb, offset, string, attr);
		}
	
		@Override
		public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
			super.remove(fb, offset, length);
		}
	
		@Override
		public void replace(FilterBypass fb, int offset, int length, String text, javax.swing.text.AttributeSet attrs)
				throws BadLocationException {
			super.replace(fb, offset, length, text, attrs);
		}
	   
   });
   this.limit = limit;
   }

  public void insertString( int offset, String  str, AttributeSet attr ) throws BadLocationException {
    if (str == null) return;

    if ((getLength() + str.length()) <= limit) {
      super.insertString(offset, str, (javax.swing.text.AttributeSet) attr);
    }
  }
}