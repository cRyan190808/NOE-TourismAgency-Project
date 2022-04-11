package at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.ui.main_menu.view.modal;

import at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.util.Borders;
import at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.entity.Hotel;
import at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.service.serviceinterface.HotelServiceInterface;
import at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.ui.shared.constants.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;

/**
 * Dialog box for adding or editing hotel data
 * This is the one and only contact of the controller. The controller never calls methods
 * of the panels that are embedded in this dialogbox directly.
 */
@Component
public class AddHotelDialog extends JDialog {

  private AddHotelDialogPanel addHotelDialogPanel;
  private AddHotelDialogButtonPanel addHotelDialogButtonPanel;

  @Autowired
  public AddHotelDialog(final AddHotelDialogPanel addHotelDialogPanel,
                        final AddHotelDialogButtonPanel addHotelDialogButtonPanel) {
    this.addHotelDialogPanel = addHotelDialogPanel;
    this.addHotelDialogButtonPanel = addHotelDialogButtonPanel;

    // Setup the components used in the dialog box
    setupFrame();
    initComponents();
    pack();
  }

  /**
   * Setup the GUI of the dialog box
   */
  private void setupFrame() {
    // Create an empty border around the Swing Components
    getRootPane().setBorder(Borders.createEmptyBorder());
    setTitle("Hotel anlegen");
    // When closing the window it is automatically disposed i.e. thrown away
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    setLocationRelativeTo(null);
    setResizable(false);
    // A modal window means, that this window stays in the foreground until it is closed.
    // No other window of the application can be selected
    setModal(true);
    // The Swing components that are located in dis dialog box are ordered in a Border Layout
    // https://docs.oracle.com/javase/tutorial/uiswing/layout/border.html
    setLayout(new BorderLayout());
  }

  /**
   * Implement the panels of the GUI into the outer frame
   */
  private void initComponents() {
    add(addHotelDialogPanel, BorderLayout.CENTER);
    add(addHotelDialogButtonPanel, BorderLayout.SOUTH);
  }

  /**
   * Create the panel with the ok/cancel button
   * @return AddHotelDialogButtonPanel Panel with the Ok/Cancel button
   */
  public AddHotelDialogButtonPanel getAddHotelDialogButtonPanel() {
    return addHotelDialogButtonPanel;
  }

  /**
   * Create the panel with the components to enter a new hotel
   * @return AddHotelDialogButtonPanel Panel with the conponents to add a hotel
   */
  public AddHotelDialogPanel getAddHotelDialogPanel() {
    return addHotelDialogPanel;
  }

  /**
   * Set the controller which is responsible for the handling of the events of this GUI
   * @param controller Controller responible for the action handling
   */
  public void setController(final HotelServiceInterface controller) {
    addHotelDialogPanel.setHotelService(controller);
  }


  /**
   * Set type of dialog box i.e. add hotel or edit
   * @param action Add or Edit
   */
  public void setAction(final Action action) {
    addHotelDialogPanel.setAction(action);

    // Recreate the dialog box
    revalidate();
    repaint();
    pack();
  }

  /**
   * Getter
   * @return Action
   */
  public Action getAction() {
    return addHotelDialogPanel.getAction();
  }

  /**
   * Retrieves the data from the this dialog box
   * @return Hotel Instance with the data entered in the dialog box
   */
  public Hotel getDataFromForm() {
    return addHotelDialogPanel.getDataFromForm();
  }

  public JButton getSaveButton() {
    return addHotelDialogButtonPanel.getSaveButton();
  }

  public JButton getCancelButton() {
    return addHotelDialogButtonPanel.getCancelButton();
  }
}
