package at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.ui.main_menu.model;

import at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.entity.Capacity;
import at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.ui.shared.model.DefaultTableModel;
import org.springframework.stereotype.Component;

@Component
public class CapacityTableModel extends DefaultTableModel<Capacity> {

  private static String[] columnNames = {"Kategorie", "Betriebe", "Zimmer", "Betten"}; // ok

  @Override
  public String[] getColumnLabels() {
    return columnNames;
  }

  @Override
  public String getColumnName(final int column) {
    return columnNames[column];
  }

  @Override
  public Object getValueAt(final int rowIndex, final int columnIndex) {

    Capacity capacity = getEntities().get(rowIndex);

    // CSOFF: MagicNumber
    switch (columnIndex) {
      case 0:
        return capacity.getCategory();
      case 1:
        return capacity.getBusinessCount();
      case 2:
        return capacity.getRoomCount();
      case 3:
        return capacity.getBedCount();
      default:
        return "";
    }
    // CSON: MagicNumber

  }

  @Override
  public int getColumnCount() {
    return columnNames.length;
  }


}
