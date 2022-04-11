package at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.util;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

/**
 * Utility class to create an empty border for Swing components.
 */
public final class Borders {

  // As all good utility classes this class is final, so no class can inherit from this class.
  // And because this class has a provate constructor no instances cam be created
  private Borders() {
  }

  private static final int WIDTH = 40;
  private static final int HEIGHT = 20;

  public static Border createEmptyBorder() {
    return BorderFactory.createEmptyBorder(HEIGHT, WIDTH, HEIGHT, WIDTH);
  }

}
