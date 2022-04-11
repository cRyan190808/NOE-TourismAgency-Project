package at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.ui.main_menu.model;

import at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.entity.Occupancy;
import at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.ui.shared.model.DefaultTableModel;
import org.springframework.stereotype.Component;

@Component
public class OccupancyTableModel extends DefaultTableModel<Occupancy> {

  private static String[] columnNames = {"Kategorie", "Zimmer", "Zimmerauslastung", "Betten",
      "Bettenauslastung"};

  @Override
  public String[] getColumnLabels() {
    return columnNames;
  }

  @Override
  public String getColumnName(final int column) {
    return columnNames[column];
  }

  @Override
  public int getColumnCount() {
    return columnNames.length;
  }

  @Override
  public Object getValueAt(final int rowIndex, final int columnIndex) {

    Occupancy occupancy =  getEntities().get(rowIndex);

    // CSOFF: MagicNumber
    switch (columnIndex) {
      case 0:
        return occupancy.getCategory();
      case 1:
        return occupancy.getRoomCount();
      case 2:
        return occupancy.getRoomUtilization();
      case 3:
        return occupancy.getBedCount();
      case 4:
        return occupancy.getBedUtilization();
      default:
        return "";
    }
    // CSON: MagicNumber

  }

}
