package utilities;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 * A document filter for limiting the number of characters 
 * that can be entered in a text field. <br>
 * A subclass of {@link DocumentFilter}
 * @author Jacob Ressler &amp; Anthony Lantz
 *
 */
public class LimitDocumentFilter extends DocumentFilter {

    private int limit;

    /**
     * Create a new LimitDocumentFilter with the specified character limit
     * @param limit the character limit
     */
    public LimitDocumentFilter(int limit) {
        if (limit <= 0) {
            throw new IllegalArgumentException("Limit can not be <= 0");
        }
        this.limit = limit;
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        int currentLength = fb.getDocument().getLength();
        int overLimit = (currentLength + text.length()) - limit - length;
        if (overLimit > 0) {
            text = text.substring(0, text.length() - overLimit);
        }
        if (text.length() > 0 || length > 0) {
            super.replace(fb, offset, length, text, attrs); 
        }
    }

}
