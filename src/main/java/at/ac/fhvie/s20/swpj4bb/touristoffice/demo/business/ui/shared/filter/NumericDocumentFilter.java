package at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.ui.shared.filter;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;

/**
 * Filter for text fields in the GUI to allow only numeric values
 */

public class NumericDocumentFilter extends DocumentFilter {

  @Override
  public void insertString(final FilterBypass fb, final int offset, final String string,
                           final AttributeSet attr) throws BadLocationException {

    // retrieve the current content of the text field/document and append the entered string
    // then test if it is a number
    Document doc = fb.getDocument();
    StringBuilder sb = new StringBuilder();
    sb.append(doc.getText(0, doc.getLength()));
    sb.insert(offset, string);


    if (test(sb.toString())) {
      // insert only if the test of the string is successful
      super.insertString(fb, offset, string, attr);
    } else {
      // warn the user and don't allow the insert
      // or do nothing
    }
  }

  /**
   * Test a string if it is numeric
   * @param text String to test
   * @return true if the field is numeric
   */
  private boolean test(final String text) {
    try {
      // try to parse the text string. if the test does not throw an exception this text string
      // contains a number
      Integer.parseInt(text);
      return true;
    } catch (NumberFormatException exc) {
      return false;
    }
  }

  @Override
  public void replace(final FilterBypass fb, final int offset, final int length, final String text,
                      final AttributeSet attrs) throws BadLocationException {

    Document doc = fb.getDocument();
    StringBuilder sb = new StringBuilder();
    sb.append(doc.getText(0, doc.getLength()));
    sb.replace(offset, offset + length, text);

    if (test(sb.toString())) {
      super.replace(fb, offset, length, text, attrs);
    } else {
      // warn the user and don't allow the insert
      // or do nothing
    }

  }

  @Override
  public void remove(final FilterBypass fb, final int offset, final int length)
      throws BadLocationException {
    Document doc = fb.getDocument();
    StringBuilder sb = new StringBuilder();
    sb.append(doc.getText(0, doc.getLength()));
    sb.delete(offset, offset + length);

    if (test(sb.toString())) {
      super.remove(fb, offset, length);
    } else {
      // warn the user and don't allow the insert
      // or do nothing
    }

  }


}
