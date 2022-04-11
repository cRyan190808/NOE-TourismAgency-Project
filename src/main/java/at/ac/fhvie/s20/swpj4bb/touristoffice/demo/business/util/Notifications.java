package at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.util;


import javax.swing.JOptionPane;

/**
 * Utility Class to show an Error in a dialog box
 */
public final class Notifications {

  private Notifications() {
  }

  /**
   * Show message in a dialog box
   * @param message Message to be displayed
   */
  public static void showFormValidationError(final String message) {
    JOptionPane.showMessageDialog(null,
        message,
        "Fehler",
        JOptionPane.INFORMATION_MESSAGE);
  }

}