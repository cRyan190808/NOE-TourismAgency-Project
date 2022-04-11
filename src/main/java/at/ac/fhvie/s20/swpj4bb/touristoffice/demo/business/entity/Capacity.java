package at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * Entity for storing capacity data
 * Please be aware that there is absolutely no validation of data
 */

@Getter
@Setter
public final class Capacity {
  private String category;
  private int businessCount;
  private int roomCount;
  private int bedCount;

  // Associate parameters to their getters
  // see https://docs.oracle.com/javase/7/docs/api/java/beans/ConstructorProperties.html
  @java.beans.ConstructorProperties({"category", "businessCount", "roomCount", "bedCount"})
  private Capacity(final String category, final int businessCount, final int roomCount,
                   final int bedCount) {
    this.category = category;
    this.businessCount = businessCount;
    this.roomCount = roomCount;
    this.bedCount = bedCount;
  }

  public static CapacityBuilder builder() {
    return new CapacityBuilder();
  }

  public static class CapacityBuilder {
    private String category;
    private int businessCount;
    private int roomCount;
    private int bedCount;

    public CapacityBuilder() {
    }

    public CapacityBuilder category(final String value) {
      this.category = value;
      return this;
    }

    public CapacityBuilder businessCount(final int value) {
      this.businessCount = value;
      return this;
    }

    public CapacityBuilder roomCount(final int value) {
      this.roomCount = value;
      return this;
    }

    public CapacityBuilder bedCount(final int value) {
      this.bedCount = value;
      return this;
    }

    public Capacity build() {
      return new Capacity(category, businessCount, roomCount, bedCount);
    }

    public String toString() {
      return "Capacity.CapacityBuilder(category=" + this.category
          + ", businessCount=" + this.businessCount + ", roomCount=" + this.roomCount
          + ", bedCount=" + this.bedCount + ")";
    }
  }
}
