package at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.ui.main_menu.view.modal;

import at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.ui.shared.constants.Action;
import org.springframework.stereotype.Component;

import javax.swing.JButton;
import javax.swing.JPanel;

@Component
public class AddHotelDialogButtonPanel extends JPanel {
  private JButton saveButton;
  private JButton cancelButton;

  public AddHotelDialogButtonPanel() {
    initComponents();
  }

  // Create the components and add them to the panel
  private void initComponents() {
    saveButton = new JButton("Save");
    saveButton.setActionCommand(Action.SAVE.toString());
    add(saveButton);

    cancelButton = new JButton("Cancel");
    saveButton.setActionCommand(Action.CANCEL.toString());
    add(cancelButton);
  }

  public JButton getSaveButton() {
    return saveButton;
  }

  public JButton getCancelButton() {
    return cancelButton;
  }
}
