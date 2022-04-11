package at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.ui.main_menu.view;

import at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.ui.main_menu.model.OccupancyTableModel;
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
 * Panel for showing the occupancy data
 */
@Component
public class MainMenuOccupancyPanel extends JPanel {

  private static final int ROW_HEIGHT_MULTIPLIER = 5;
  private OccupancyTableModel occupancyTableModel;
  private MainMenuOccupancyButtonPanel occupancyButtonPanel;

  @Autowired
  public MainMenuOccupancyPanel(final OccupancyTableModel occupancyTableModel,
                                final MainMenuOccupancyButtonPanel occupancyButtonPanel) {
    this.occupancyTableModel = occupancyTableModel;
    this.occupancyButtonPanel = occupancyButtonPanel;
    setupPanel();
    initComponents();
  }

  private void setupPanel() {
    setLayout(new BorderLayout());
  }

  /**
   * Create and add the components to the panel
   */
  private void initComponents() {
    // Add the heading
    add(createHeading(), BorderLayout.NORTH);
    // Add the data table
    add(createDataTable(), BorderLayout.CENTER);
    // Add the Buttond
    add(occupancyButtonPanel, BorderLayout.SOUTH);
  }

  /**
   * Create the heading for the panel
   * @return panel with the heading
   */
  private JPanel createHeading() {
    JPanel panel = new JPanel(new FlowLayout());
    panel.add(new JLabel("Hotelübersicht:"));
    return panel;
  }

  /**
   * Create the data dable for the occupancy
   * @return Component with the occupancy dat atable
   */
  private JComponent createDataTable() {
    // Create a new table with the underlying data model
    JTable table = new JTable(occupancyTableModel);

    // set the *preferred* size of the data table. This *may* differ from the avtual size
    table.setPreferredScrollableViewportSize(new Dimension(table.getPreferredSize().width,
        table.getRowHeight() * ROW_HEIGHT_MULTIPLIER));

    // Embed the data table into a scrollpane, so that the dat atable is scrollable
    JScrollPane scrollpane = new JScrollPane(table);

    // return the datatable that is embedded in an scrollpane
    return scrollpane;
  }

}
