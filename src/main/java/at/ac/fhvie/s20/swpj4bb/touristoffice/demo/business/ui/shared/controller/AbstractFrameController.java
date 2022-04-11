package at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.ui.shared.controller;

import javax.swing.JButton;
import java.awt.event.ActionListener;

public abstract class AbstractFrameController {

  public abstract void prepareAndOpenFrame();

  /**
   * Register a button and the according listener
   * @param button Button to register
   * @param listener Listener class for the button actions
   */
  protected void registerAction(final JButton button, final ActionListener listener) {
    button.addActionListener(listener);
  }

}
