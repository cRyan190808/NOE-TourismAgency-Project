package at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.ui.shared.model;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Data model for the data tables in the GUI
 * @param <T> Class of Element to display
 */
public abstract class DefaultTableModel<T> extends AbstractTableModel {

  // Collection of the items to be stored in the model
  private List<T> entities = new ArrayList<>();

  // Array for the header labels for the columns
  public abstract String[] getColumnLabels();

  @Override
  public int getRowCount() {
    return entities.size();
  }

  @Override
  public int getColumnCount() {
    return getColumnLabels().length;
  }

  @Override
  public String getColumnName(final int column) {
    return getColumnLabels()[column];
  }

  /**
   * Add a single entity to the model
   * @param entity Entity to add
   */
  public void addEntity(final T entity) {
    entities.add(entity);
    fireTableDataChanged();
  }

  /**
   * Add a Collection of entites to the table
   * @param values Entities to add
   */
  public void addEntities(final List<T> values) {
    this.entities.addAll(values);
    // As data has changed the model and therefore all listeners to that model have to be
    // informed of the data change
    fireTableDataChanged();
  }

  /**
   * Retrieve the entity of a certain row
   * @param rowIndex Row of which the data should be retrieved
   * @return Entity of that row
   */
  public T getEntityByRow(final int rowIndex) {
    return entities.get(rowIndex);
  }

  /**
   * Remove a row from the data model
   * @param row Row number to remove
   */
  public void removeRow(final int row) {
    entities.remove(row);
    fireTableDataChanged();
  }

  /**
   * Clear the whole table
   */
  public void clear() {
    entities.clear();
  }

  /**
   * Retrieve all entities from the table
   * @return Collection of Entities
   */
  public List<T> getEntities() {
    return new ArrayList<T>(entities);
  }
}
