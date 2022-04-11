package at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.ui.main_menu.view;

import at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.util.Borders;
import at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.util.LookAndFeelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.Color;


/**
 * Main Frame of the application
 */
@Component
public class MainMenuFrame extends JFrame {

  public static final float FONT_SIZE = 32.0f;
  private MainMenuCapacityPanel hotelsPanel;
  private MainMenuOccupancyPanel occupancyPanel;
  private MainMenuCapacityButtonPanel capacityButtonPanel;

  @Autowired
  public MainMenuFrame(final MainMenuCapacityPanel hotelsPanel,
                       final MainMenuOccupancyPanel occupancyPanel,
                       final MainMenuCapacityButtonPanel capacityButtonPanel) {
    this.hotelsPanel = hotelsPanel;
    this.occupancyPanel = occupancyPanel;
    this.capacityButtonPanel = capacityButtonPanel;
    setupFrame();
    initComponents();
    pack();
  }

  /**
   * Create and configure the main frame
   */
  private void setupFrame() {
    // create a border around the components used in this frame
    getRootPane().setBorder(Borders.createEmptyBorder());
    setTitle("Touristeninformation");
    // When closing this window the application is terminated
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    // This frame is not resizable. This is not a real good design and has to be altered
    setResizable(false);
    LookAndFeelUtils.setWindowsLookAndFeel();
    setLayout(new BorderLayout());
  }

  /**
   * This method calls all methods that create the parts of the loayout
   */
  private void initComponents() {
    // Just create the title label
    add(createTitleLabel(), BorderLayout.NORTH);
    // Create tbe tabbed pane for the different data views
    add(createTabbedPane(), BorderLayout.CENTER);

  }

  /**
   * Get the button for creating a new Hotel
   * @return Button to create a new hotel
   */
  public JButton getCreateHotelButton() {
    return capacityButtonPanel.getCreateHotelButton();
  }

  /**
   * Get the button for editing a hotel
   * @return Button to edit a hotel
   */
  public JButton getEditHotelButton() {
    return capacityButtonPanel.getEditHotelButton();
  }

  /**
   * Create the title label
   * @return Label with the title
   */
  private JLabel createTitleLabel() {
    // With swing a label can be styled with HTML. It is important, that the HTML code is
    // surrounded with <HTML></HTML> tags.
    JLabel titleLabel = new JLabel("<HTML><SPAN style='color:yellow'>N</SPAN><SPAN "
        + "style='color:white'>iederösterreich Touristeninformation</SPAN>");
    // Center the label
    titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
    // Set the font size of the label
    titleLabel.setFont(titleLabel.getFont().deriveFont(FONT_SIZE));
    titleLabel.setOpaque(true);
    titleLabel.setForeground(Color.WHITE);
    titleLabel.setBackground(Color.BLUE);
    return titleLabel;
  }

  /**
   * Create a tabbed pane with the different data views of the appliation
   * @return Tabbed pane with the different views
   */
  private JTabbedPane createTabbedPane() {
    JTabbedPane tabbedPane = new JTabbedPane();
    // The both panels are created automatically by Spring and injected via Autowire
    tabbedPane.addTab("Hotels", hotelsPanel);
    tabbedPane.addTab("Belegung", occupancyPanel);
    return tabbedPane;

  }



}
