package at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.ui.main_menu.view.modal;

import at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.util.AutoCompletion;
import at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.entity.Hotel;
import at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.service.serviceinterface.HotelServiceInterface;
import at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.ui.shared.constants.Action;
import at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.constants.Category;
import at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.ui.shared.filter.NumericDocumentFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.PlainDocument;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

/**
 * Panel with the components to enter data for adding or editing a hotel
 */
@Component
public class AddHotelDialogPanel extends JPanel {

  private static final int SMALL_TEXT_FIELD_COLUMNS = 10;
  private static final int MEDIUM_TEXT_FIELD_COLUMNS = 20;
  private static final int LARGE_TEXT_FIELD_COLUMNS = 40;

  private static final int ONE = 1;
  private static final int TWO = 2;
  private static final int THREE = 3;

  private static final int LEFT_LABEL_MARGIN = 5;

  private Action action;

  private JLabel lblSelectHotel;
  private JLabel lblNewHotel;

  private int id;
  private JComboBox<String> cbxSelectHotel;
  private JComboBox<Category> cbxSelectCategory;
  private JTextField txtName;
  private JTextField txtAddress;
  private JTextField txtZip;
  private JTextField txtCity;
  private JTextField txtPhone;
  private JTextField txtOwner;
  private JTextField txtContact;
  private JTextField txtNoRooms;
  private JTextField txtNoBeds;

  private Insets defaultInsets = new Insets(0, LEFT_LABEL_MARGIN, 0, 0);

  private HotelServiceInterface hotelService;

  /**
   * Setup panel
   */
  @Autowired
  public AddHotelDialogPanel() {
    setupPanel();
    initComponents();
  }

  /**
   * Set layout for the panel
   */
  private void setupPanel() {
    setLayout(new GridBagLayout());
  }

  /**
   * Initialize the components for the panel
   */
  private void initComponents() {
    int line = 0;

    // Initialize the components used in this panel
    lineNewHotel(line++);
    lineSelection(line++);
    lineCategory(line++);
    lineName(line++);
    lineAddress(line++);
    lineZipAndTown(line++);
    lineOwner(line++);
    linePhoneAndContact(line++);
    lineBedAndRoom(line++);
  }

  /**
   * Reset the contents of all Swing components used. Textfields are emptied, ComboBoxes
   * are reset to the first entry
   */
  public void clearForm() {
    cbxSelectCategory.setSelectedIndex(0);
    txtName.setText("");
    txtAddress.setText("");
    txtZip.setText("");
    txtCity.setText("");
    txtPhone.setText("");
    txtOwner.setText("");
    txtContact.setText("");
    txtNoRooms.setText("1");
    txtNoBeds.setText("1");
  }

  /**
   * Set the label to enter a new hotel or show a dialogbox for selecting a hotel to edit
   * depending on the action.
   * @param action Add a new hotel or edit a hotel
   */
  public void setAction(final Action action) {

    this.action = action;
    clearForm();

    // Remove the line that shows the label to add ahotel
    deleteLineNewHotel();
    // Remove the lines that show a label to select a hotel to edit and the combobox to
    // select the hotel to edit
    deleteLineSelection();

    // Depending on the action show the label to add a hotel or label and combobox to
    // edit a hotel
    switch (action) {
      case CREATE:
        lineNewHotel(0);
        break;
      case EDIT:
        lineSelection(0);
        break;
      default:
        lineNewHotel(0);
    }

  }

  public Action getAction() {
    return action;
  }

  /**
   * Set the class that is responsible for the handling of the actions that this
   * panel generates
   * @param hotelService Class that is responsible for handling the actions of this panel
   */
  public void setHotelService(final HotelServiceInterface hotelService) {
    this.hotelService = hotelService;
  }

  /**
   *
   * @param line Line number for GridBag Layout
   * @return Constraints
   */
  private GridBagConstraints createDefaultConstraint(final int line) {
    GridBagConstraints constraint = new GridBagConstraints();
    constraint.anchor = GridBagConstraints.LINE_START;
    constraint.gridx = 0;
    constraint.gridy = line;
    constraint.gridwidth = ONE;
    return constraint;
  }

  /**
   * Delete the controls for selecting a hotel. Removes Label and Combobox
   */
  private void deleteLineSelection() {
    remove(lblSelectHotel);
    remove(cbxSelectHotel);
  }

  /**
   * Creates the label and the Combobox for selcting a hotel
   * @param line Line of the GridBag where wo locate the label and ComboBox
   */
  private void lineSelection(final int line) {
    GridBagConstraints constraint = createDefaultConstraint(line);

    constraint.insets = defaultInsets;
    lblSelectHotel = new JLabel("Auswahl:");
    cbxSelectHotel = new JComboBox<>();

    // Add the label to the panel
    add(lblSelectHotel, constraint);

    // Set the data model for the ComboBox
    DefaultComboBoxModel<String> selectHotelModel;
    // Retrieve the names of all hotels only when there is a valid controller
    if (hotelService != null) {
      // Retrieve all hotel names from the controller and load them into the model
      selectHotelModel = new DefaultComboBoxModel<>(
          hotelService.findAllHotelNames().toArray(new String[1]));
    } else {
      // If the controller is not available set the data model to no entries
      selectHotelModel = new DefaultComboBoxModel<>();
    }

    // Create the combobox and assign a listener for the actions taken by the
    // combobox.
    cbxSelectHotel = new JComboBox<>(selectHotelModel);
    cbxSelectHotel.addActionListener(this::hotelSelected);
    // Enable the AutoCompletion for the combobox
    AutoCompletion.enable(cbxSelectHotel);

    // Set the constraints and add the combobox to the panel
    constraint.gridx = ONE;
    constraint.gridwidth = THREE;
    constraint.fill = GridBagConstraints.HORIZONTAL;
    add(cbxSelectHotel, constraint);
  }


  /**
   * Delete the label of the panl for adding a NEW hotel
   */
  private void deleteLineNewHotel() {
    remove(lblNewHotel);
  }


  /**
   * Create the label for adding a new hotel
   * @param line Line of the GridBag where the label whould be displayed
   */
  private void lineNewHotel(final int line) {
    GridBagConstraints constraint = createDefaultConstraint(line);
    constraint.gridwidth = THREE;
    constraint.anchor = GridBagConstraints.LINE_START;
    constraint.fill = GridBagConstraints.HORIZONTAL;
    constraint.insets = defaultInsets;

    lblNewHotel = new JLabel("Neues Hotel erfassen");
    add(lblNewHotel, constraint);
  }


  /**
   * Create the component for the Category
   * @param line Line of the Gridbag where the component should be shown
   */
  private void lineCategory(final int line) {
    GridBagConstraints constraint = createDefaultConstraint(line);
    constraint.fill = GridBagConstraints.NONE;
    constraint.insets = defaultInsets;
    add(new JLabel("Kategorie:"), constraint);

    cbxSelectCategory = new JComboBox<>(Category.values());
    constraint.gridx = ONE;
    constraint.gridwidth = ONE;
    add(cbxSelectCategory, constraint);

  }

  /**
   * Create the component for the name
   * @param line Line of the Gridbag where the component should be shown
   */
  private void lineName(final int line) {
    GridBagConstraints constraint = createDefaultConstraint(line);
    constraint.insets = defaultInsets;

    add(new JLabel("Name:"), constraint);

    txtName = new JTextField(LARGE_TEXT_FIELD_COLUMNS);
    constraint.gridx = ONE;
    constraint.gridwidth = THREE;
    add(txtName, constraint);
  }

  /**
   * Create the component for the address
   * @param line Line of the Gridbag where the component should be shown
   */
  private void lineAddress(final int line) {
    GridBagConstraints constraint = createDefaultConstraint(line);
    constraint.anchor = GridBagConstraints.LINE_START;
    constraint.insets = defaultInsets;

    add(new JLabel("Adresse:"), constraint);

    txtAddress = new JTextField(LARGE_TEXT_FIELD_COLUMNS);
    constraint.gridx = ONE;
    constraint.gridwidth = THREE;
    add(txtAddress, constraint);
  }

  /**
   * Create the components for the zip code and the town
   * @param line Line of the Gridbag where the component should be shown
   */
  private void lineZipAndTown(final int line) {
    GridBagConstraints constraint = createDefaultConstraint(line);
    constraint.insets = defaultInsets;
    add(new JLabel("Plz:"), constraint);

    txtZip = new JTextField(SMALL_TEXT_FIELD_COLUMNS);
    constraint.gridx = ONE;
    add(txtZip, constraint);

    constraint.gridx = TWO;
    constraint.insets = defaultInsets;
    add(new JLabel("Stadt:"), constraint);

    txtCity = new JTextField(MEDIUM_TEXT_FIELD_COLUMNS);
    constraint.gridx = THREE;
    constraint.fill = GridBagConstraints.HORIZONTAL;
    add(txtCity, constraint);


  }

  /**
   * Create the component for the owner
   * @param line Line of the Gridbag where the component should be shown
   */
  private void lineOwner(final int line) {
    GridBagConstraints constraint = createDefaultConstraint(line);
    constraint.insets = defaultInsets;
    add(new JLabel("Besitzer:"), constraint);

    txtOwner = new JTextField(LARGE_TEXT_FIELD_COLUMNS);
    constraint.gridx = ONE;
    constraint.gridwidth = THREE;
    add(txtOwner, constraint);


  }

  /**
   * Create the components for the phone number and the contact
   * @param line Line of the Gridbag where the component should be shown
   */
  private void linePhoneAndContact(final int line) {
    GridBagConstraints constraint = createDefaultConstraint(line);
    constraint.insets = defaultInsets;
    add(new JLabel("Telefon:"), constraint);

    txtPhone = new JTextField(SMALL_TEXT_FIELD_COLUMNS);
    constraint.gridx = ONE;
    add(txtPhone, constraint);

    constraint.gridx = TWO;
    constraint.insets = defaultInsets;
    add(new JLabel("Kontakt:"), constraint);

    txtContact = new JTextField(MEDIUM_TEXT_FIELD_COLUMNS);
    constraint.gridx = THREE;
    constraint.fill = GridBagConstraints.HORIZONTAL;
    add(txtContact, constraint);

  }


  /**
   * Create the components for the number of rooms and beds
   * @param line Line of the Gridbag where the component should be shown
   */
  private void lineBedAndRoom(final int line) {
    GridBagConstraints constraint = createDefaultConstraint(line);
    constraint.insets = defaultInsets;
    constraint.anchor = GridBagConstraints.LINE_START;
    add(new JLabel("Raumanzahl:"), constraint);

    txtNoRooms = new JTextField(SMALL_TEXT_FIELD_COLUMNS);
    txtNoRooms.setHorizontalAlignment(SwingConstants.RIGHT);
    PlainDocument document = (PlainDocument) txtNoRooms.getDocument();
    // set a filter for the document that only numbers can be entered
    document.setDocumentFilter(new NumericDocumentFilter());
    constraint.gridx = ONE;
    add(txtNoRooms, constraint);

    constraint.gridx = TWO;
    constraint.insets = defaultInsets;
    add(new JLabel("Bettenanzahl:"), constraint);

    txtNoBeds = new JTextField(SMALL_TEXT_FIELD_COLUMNS);
    txtNoBeds.setHorizontalAlignment(SwingConstants.RIGHT);
    document = (PlainDocument) txtNoBeds.getDocument();
    // set a filter for the document that only numbers can be entered
    document.setDocumentFilter(new NumericDocumentFilter());

    constraint.gridx = THREE;
    add(txtNoBeds, constraint);
  }

  /**
   * Handler for the event when a hotel in the combobox is selected
   * @param event Event of the ComboBox of the hotel selection
   */
  private void hotelSelected(final ActionEvent event) {
    // Only continue if there is a controller set
    if (hotelService == null) {
      return;
    }
    // Get the data of the hotel. Retrieve the name of the hotel from the selected
    // entry of the combobox
    String stub = cbxSelectHotel.getItemAt(cbxSelectHotel.getSelectedIndex());
    // Get the hotel data from the controller.
    Hotel selectedHotel = hotelService.findHotelByName(stub);

    // Store the data into the text fields
    id = selectedHotel.getId();
    cbxSelectCategory.setSelectedItem(selectedHotel.getCategory());
    txtName.setText(selectedHotel.getName());
    txtOwner.setText(selectedHotel.getOwner());
    txtContact.setText(selectedHotel.getContact());
    txtAddress.setText(selectedHotel.getAddress());
    txtCity.setText(selectedHotel.getCity());
    txtZip.setText(selectedHotel.getCityCode());
    txtPhone.setText(selectedHotel.getPhone());
    txtNoRooms.setText(Integer.toString(selectedHotel.getNoRooms()));
    txtNoBeds.setText(Integer.toString(selectedHotel.getNoBeds()));

  }

  /**
   * Retrieve the entered data from the panel
   * @return Hotel instance with the entered data
   */
  public Hotel getDataFromForm() {

    // If nothing is entered in the bed or room textfield then set to 0
    if (txtNoBeds.getText().isEmpty()) {
      txtNoBeds.setText("0");
    }

    if (txtNoRooms.getText().isEmpty()) {
      txtNoRooms.setText("0");
    }

    // Create a bilder for creating a new instance of Hotel
    Hotel.HotelBuilder hotelbuilder = new Hotel.HotelBuilder();
    if (action == Action.CREATE) {
      // The id for a new entry has to be set to -1
      hotelbuilder = hotelbuilder.id(-1);
    } else {
      // the id of a record o change has to be set to the current id of the record
      hotelbuilder = hotelbuilder.id(id);
    }

    Category category = (Category) cbxSelectCategory.getSelectedItem();

    // Create a new hotel instance with the entered values
    // There is no validation of the data here because this class has to be as dumb as possible!!
    // To test here on validity would put too much knowledge into this class. This class is only
    // responsible for entering the data and nothing more!
    Hotel hotel = hotelbuilder
        .category(category)
        .name(txtName.getText())
        .owner(txtOwner.getText())
        .contact(txtContact.getText())
        .address(txtAddress.getText())
        .city(txtCity.getText())
        .cityCode(txtZip.getText())
        .phone(txtPhone.getText())
        .noBeds(Integer.parseInt(txtNoBeds.getText()))
        .noRooms(Integer.parseInt(txtNoRooms.getText()))
        .build();
    // return the new instance
    return hotel;
  }


}
