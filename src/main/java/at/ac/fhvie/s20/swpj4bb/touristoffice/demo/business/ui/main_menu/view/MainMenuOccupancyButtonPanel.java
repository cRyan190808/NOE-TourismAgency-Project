package at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.ui.main_menu.view;

import at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.ui.shared.constants.Action;
import org.springframework.stereotype.Component;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridLayout;

/**
 * Panel for the Occupancy buttons
 */
@Component
public class MainMenuOccupancyButtonPanel extends JPanel {

  private JButton editOccupancyButton;

  public MainMenuOccupancyButtonPanel() {
    setupPanel();
    initComponents();
  }


  private void setupPanel() {
    setLayout(new GridLayout());
  }

  private void initComponents() {

    editOccupancyButton = new JButton("Belegung bearbeiten");
    editOccupancyButton.setActionCommand(Action.EDIT.getAction());
    add(editOccupancyButton);
  }


  public JButton getEditOccupancyButton() {
    return editOccupancyButton;
  }

}
