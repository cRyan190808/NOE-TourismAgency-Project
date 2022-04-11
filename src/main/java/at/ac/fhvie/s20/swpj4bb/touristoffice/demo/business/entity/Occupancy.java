package at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.entity;

import lombok.Getter;
import lombok.Setter;
// CSOFF: HiddenField

// Getter and setter are created by Lombok
@Getter
@Setter
/**
 * Entity class for the Occupancy database table
 */
public final class Occupancy {

  private String category;
  private int roomCount;
  private double roomUtilization;
  private int bedCount;
  private double bedUtilization;

  // Associate parameters to their getters
  // see https://docs.oracle.com/javase/7/docs/api/java/beans/ConstructorProperties.html
  @java.beans.ConstructorProperties({"category", "roomCount", "roomUtilization", "bedCount",
      "bedUtilization"})
  private Occupancy(final String category, final int roomCount, final double roomUtilization,
                    final int bedCount, final double bedUtilization) {
    this.category = category;
    this.roomCount = roomCount;
    this.roomUtilization = roomUtilization;
    this.bedCount = bedCount;
    this.bedUtilization = bedUtilization;
  }

  public static OccupancyBuilder builder() {
    return new OccupancyBuilder();
  }


  public static class OccupancyBuilder {
    private String category;
    private int roomCount;
    private double roomUtilization;
    private int bedCount;
    private double bedUtilization;

    public OccupancyBuilder() {
    }

    public OccupancyBuilder category(final String category) {
      this.category = category;
      return this;
    }

    public OccupancyBuilder roomCount(final int roomCount) {
      this.roomCount = roomCount;
      return this;
    }

    public OccupancyBuilder roomUtilization(final double roomUtilization) {
      this.roomUtilization = roomUtilization;
      return this;
    }

    public OccupancyBuilder bedCount(final int bedCount) {
      this.bedCount = bedCount;
      return this;
    }

    public OccupancyBuilder bedUtilization(final double bedUtilization) {
      this.bedUtilization = bedUtilization;
      return this;
    }

    public Occupancy build() {
      return new Occupancy(category, roomCount, roomUtilization, bedCount, bedUtilization);
    }

    public String toString() {
      return "Occupancy.OccupancyBuilder(category=" + this.category + ", roomCount="
          + this.roomCount + ", roomUtilization=" + this.roomUtilization + ", bedCount="
          + this.bedCount + ", bedUtilization=" + this.bedUtilization + ")";
    }
  }
  // CSON: HiddenField
}
