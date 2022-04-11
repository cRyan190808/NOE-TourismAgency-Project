package at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.ui.main_menu.view;

import at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.ui.main_menu.model.CapacityTableModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

/**
 * Panel for displaying the occupancy data
 */
@Component
public class MainMenuCapacityPanel extends JPanel {

  private static final int ROW_HEIGHT_MULTIPLIER = 5;
  private CapacityTableModel capacityTableModel;
  private MainMenuCapacityButtonPanel capacityButtonPanel;


  @Autowired
  public MainMenuCapacityPanel(final CapacityTableModel capacityTableModel,
                               final MainMenuCapacityButtonPanel capacityButtonPanel) {
    this.capacityTableModel = capacityTableModel;
    this.capacityButtonPanel = capacityButtonPanel;

    setupPanel();
    initComponents();
  }

  /**
   * Setup the layout for the panel
   */
  private void setupPanel() {
    setLayout(new BorderLayout());
  }

  /**
   * Create and add the components to the panel
   */
  private void initComponents() {
    // Add the header
    add(createHeading(), BorderLayout.NORTH);
    // Add the data grid
    add(createDataTable(), BorderLayout.CENTER);
    // Add the buttons
    add(capacityButtonPanel, BorderLayout.SOUTH);

  }

  /**
   * Create the header for the panel
   * @return Panel with the header
   */
  private JPanel createHeading() {
    // This time a FlowLayout is used
    JPanel panel = new JPanel(new FlowLayout());
    panel.add(new JLabel("Hotelübersicht:"));
    return panel;
  }

  /**
   * Create the data view of the occupancy data
   * @return Component with the scrollable data table
   */
  private JComponent createDataTable() {
    // create a new table with the according data model
    JTable table = new JTable(capacityTableModel);

    // Set the *preferred* size of the component. The dimensions *may* differ!!
    table.setPreferredScrollableViewportSize(new Dimension(table.getPreferredSize().width,
        table.getRowHeight() * ROW_HEIGHT_MULTIPLIER));

    // Add the data table to a scroll pane so that the data table is scrollable
    JScrollPane scrollpane = new JScrollPane(table);

    // return the scrollpane with the embedded data table
    return scrollpane;
  }


}
