package at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.PositiveOrZero;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "occupancy")
public class Occupancy2 {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @PositiveOrZero
  @Column(name = "room_count")
  private Integer roomCount;

  @PositiveOrZero
  @Column(name = "room_utilization")
  private Integer roomUtilization;

  @PositiveOrZero
  @Column(name = "bed_count")
  private Integer bedCount;

  @PositiveOrZero
  @Column(name = "bed_utilization")
  private Integer bedUtilization;

  @Min(value = 2014, message = "Year must be over 2014!")
  @Column(name = "year")
  private Integer year;

  @Min(value = 1, message = "Month can only be between 1-12!")
  @Max(value = 12, message = "Month can only be between 1-12!")
  @Column(name = "month")
  private Integer month;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "hotel_id")
  private Hotel hotel;

  @Override
  public String toString() {
    return "Occupancy2"
        + "( id=" + this.id
        + ", roomCount=" + this.roomCount
        + ", roomUtilization=" + this.roomUtilization
        + ", bedCount=" + this.bedCount
        + ", bedUtilization=" + this.bedUtilization
        + ", year=" + this.year
        + ", month=" + this.month
        + ", hotel=" + this.hotel
        + " )";
  }
}
