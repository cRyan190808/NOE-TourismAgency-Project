package at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.ui.main_menu.controller;

import at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.ui.main_menu.view.modal.AddHotelDialog;
import at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.util.Notifications;
import at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.entity.Capacity;
import at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.entity.Hotel;
import at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.entity.Occupancy;
import at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.service.HotelService;
import at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.service.serviceinterface.HotelServiceInterface;
import at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.ui.main_menu.model.CapacityTableModel;
import at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.ui.main_menu.model.OccupancyTableModel;
import at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.ui.main_menu.view.MainMenuFrame;
import at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.ui.shared.constants.Action;
import at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.ui.shared.controller.AbstractFrameController;
import at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.validation.HotelValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Set;

/**
 * Main application controller.
 * As this class inherits from {@link AbstractFrameController} it has to implement
 * prepareAndOpenFrame(). The interface
 */
@Controller
public class MainMenuController extends AbstractFrameController implements HotelServiceInterface {

  private MainMenuFrame mainMenuFrame;
  private CapacityTableModel capacityTableModel;
  private OccupancyTableModel occupancyTableModel;
  private HotelService hotelService;
  private AddHotelDialog addHotelDialog;
  private HotelValidator hotelValidator;


  @Autowired
  public MainMenuController(final MainMenuFrame mainMenuFrame,
                            final CapacityTableModel capacityTableModel,
                            final OccupancyTableModel occupancyTableModel,
                            final HotelService hotelService,
                            final AddHotelDialog addHotelDialog,
                            final HotelValidator hotelvalidator) {

    this.mainMenuFrame = mainMenuFrame;
    this.capacityTableModel = capacityTableModel;
    this.occupancyTableModel = occupancyTableModel;
    this.hotelService = hotelService;
    this.addHotelDialog = addHotelDialog;
    this.hotelValidator = hotelvalidator;
  }

  /**
   * Main entry point. Prepare the contents of the UI and show the GUI
   */
  public void prepareAndOpenFrame() {
    loadEntities();
    mainMenuFrame.setVisible(true);
  }

  /**
   *
   */
  @PostConstruct
  private void prepareListeners() {
    registerAction(mainMenuFrame.getCreateHotelButton(), (e) -> openAddHotelDialogAdd(e));
    registerAction(mainMenuFrame.getEditHotelButton(), (e) -> openAddHotelDialogEdit(e));

    // Replacing the Lambda with the method reference is the easier way.
    // It is also suggested by IntelliJ
    registerAction(addHotelDialog.getCancelButton(), this::closeAddHotelDialogCancel);
    registerAction(addHotelDialog.getSaveButton(), this::closeAddHotelDialogSave);
  }


  /**
   * Handles the Cancel Button of the dialog to add or edit a  hotel
   *
   * @param e {@link ActionEvent}
   */
  private void closeAddHotelDialogCancel(final ActionEvent e) {
    addHotelDialog.dispose();
  }

  /**
   * Handles the closing the dialog for adding or editing  a hotel with validation
   * and saving the entered data
   *
   * @param e {@link ActionEvent}
   */
  private void closeAddHotelDialogSave(final ActionEvent e) {
    // Get the entered data from the dialog box
    Hotel hotel = addHotelDialog.getDataFromForm();

    // Validate the data with a distinct validator that is decoupled from the class
    String error = hotelValidator.validate(hotel);

    if (!error.isEmpty()) {
      // The entered data is not valid so show a notification to the ser
      Notifications.showFormValidationError(error);
    } else {
      // Data is valid, so depending n the action save the new data or update an existing record
      switch (addHotelDialog.getAction()) {
        case CREATE:
          hotelService.save(hotel);
          break;
        case EDIT:
          hotelService.update(hotel);
          break;
        default:

          break;
      }
      // Reload the data for the UI
      loadEntities();

      // Close the dialog box for editing or creating a new hotel
      addHotelDialog.dispose();
    }
  }

  /**
   * Load capacity and occupancy from database
   */
  private void loadEntities() {
    // Use a service for loading the data. The service returns a Collection of items
    List<Capacity> entitiesCapacity = hotelService.findAllByCapacity();

    // Delete the current entries of the data model for the Swing component and repopulate them
    capacityTableModel.clear();
    capacityTableModel.addEntities(entitiesCapacity);

    // Retrieve the occupancy data via a service that returns a collection of items
    List<Occupancy> entitiesOccupancy = hotelService.findAllByOccupancy();
    // Delete current entries of the data model fur the swing component and repopulate it
    occupancyTableModel.clear();
    occupancyTableModel.addEntities(entitiesOccupancy);
  }

  /**
   * Open the dialog box for adding a hotel
   *
   * @param e {@link ActionEvent}
   */
  private void openAddHotelDialogAdd(final ActionEvent e) {
    // this class will be responsible for the handling of pressed buttons
    addHotelDialog.setController(this);
    // Inform the dialog box that a NEW hotel will be created
    addHotelDialog.setAction(Action.CREATE);
    // show the dialog box
    addHotelDialog.setVisible(true);
  }

  /**
   * Open the dialog box for editing an existing hotel
   *
   * @param e {@link ActionEvent}
   */
  private void openAddHotelDialogEdit(final ActionEvent e) {
    // this class will be responsible for the handling of pressed buttons
    addHotelDialog.setController(this);
    // Inform the dialog box that an existing hotel will be EDITed
    addHotelDialog.setAction(Action.EDIT);
    // show the dialog box
    addHotelDialog.setVisible(true);
  }


  /**
   * Create and return a collection of all hotel names
   *
   * @return Set Collection of all hotel names
   */
  @Override
  public Set<String> findAllHotelNames() {
    // Use a service to retrieve all hotel names as a collection
    return hotelService.findAllHotelNames();
  }

  /**
   * @param name Name of the hotel which data is to retrieve
   * @return Hotel Instance with the data of the hotel
   */
  @Override
  public Hotel findHotelByName(final String name) {
    // use a service to retrieve the hotel data from the database
    return hotelService.findHotelByName(name);
  }


}
