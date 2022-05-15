package at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.service.comparator;

import java.io.Serializable;
import java.util.Comparator;

import static at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.service.HotelService.TOTAL;

/**
 * Class for comparing capacities
 */
public class CapacityComparator implements Comparator<String>, Serializable {
  private static final long serialVersionUID = 1;
  @Override
  public int compare(final String left, final String right) {

    if (left.equals(right)) {
      return 0;
    }

    if (left.equals(TOTAL)) {
      return 1;
    }

    if (right.equals(TOTAL)) {
      return -1;
    }

    // An easier solution for the following last lines of the method would be
    // return Integer.compare(0, left.compareTo(right));

    if (left.compareTo(right) > 0) {
      return -1;
    }
    if (left.compareTo(right) < 0) {
      return 1;
    }

    return 0;

  }
}
