package at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.util;


import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 * Utility class to set the look and feel of the application
 */
public final class LookAndFeelUtils {

  private LookAndFeelUtils() {
  }

  /**
   * Set the Look and Feel of the application
   */
  public static void setWindowsLookAndFeel() {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (Exception exc) {
      JOptionPane.showMessageDialog(null,
          "There was an error while loading windows look an feel:"
          + " " + exc, "Alert", JOptionPane.ERROR_MESSAGE);
    }
  }

}
