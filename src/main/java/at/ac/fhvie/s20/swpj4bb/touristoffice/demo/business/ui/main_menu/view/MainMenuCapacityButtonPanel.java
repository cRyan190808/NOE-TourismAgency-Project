package at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.ui.main_menu.view;

import at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.ui.shared.constants.Action;
import org.springframework.stereotype.Component;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridLayout;

/**
 * Panel with the buttons for the main frame
 */
@Component
public class MainMenuCapacityButtonPanel extends JPanel {

  private JButton createHotelButton;
  private JButton editHotelButton;

  public MainMenuCapacityButtonPanel() {
    setupPanel();
    initComponents();
  }


  private void setupPanel() {
    // set the layout to a GridLayout
    setLayout(new GridLayout());
  }

  /**
   * Create and add the buttons to this panel
   */
  private void initComponents() {

    createHotelButton = new JButton("Hotel anlegen");
    createHotelButton.setActionCommand(Action.CREATE.getAction());
    add(createHotelButton);

    editHotelButton = new JButton("Hotel bearbeiten");
    editHotelButton.setActionCommand(Action.EDIT.getAction());
    add(editHotelButton);
  }

  public JButton getCreateHotelButton() {
    return createHotelButton;
  }

  public JButton getEditHotelButton() {
    return editHotelButton;
  }

}
