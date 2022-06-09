package at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.entity;

import at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.constants.Category;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "hotel")
public class Hotel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", updatable = false, nullable = false)
  private int id;

  private Category category;

 private String name;
  private String owner;
  private String contact;
  private String address;
  private String city;
  // Mapping between database field and variable
  @Column(name = "zip")
  private String cityCode;
  private String phone;
  // Mapping between database field and variable
  @Column(name = "rooms")
  private int noRooms;
  // Mapping between database field and variable
  @Column(name = "beds")
  private int noBeds;

  private String url;

  @Column(name = "family_friendly")
  private boolean familyFriendly;

  @Column(name = "dog_friendly")
  private boolean dogFriendly;

  private boolean spa;

  private boolean fitness;

  public static HotelBuilder builder() {
    return new HotelBuilder();
  }

  public Hotel updateWith(final Hotel other) {
    this.category = other.category;
    this.name = other.name;
    this.owner = other.owner;
    this.contact = other.contact;
    this.address = other.address;
    this.city = other.city;
    this.cityCode = other.cityCode;
    this.phone = other.phone;
    this.noRooms = other.noRooms;
    this.noBeds = other.noBeds;
    this.url = other.url;
    this.familyFriendly = other.familyFriendly;
    this.dogFriendly = other.dogFriendly;
    this.spa = other.spa;
    this.fitness = other.fitness;
    return this;
  }

  public static class HotelBuilder {
    private int id;
    private Category category;
    private String name;
    private String owner;
    private String contact;
    private String address;
    private String city;
    private String cityCode;
    private String phone;
    private int noRooms;
    private int noBeds;
    private String url;
    private boolean familyFriendly;
    private boolean dogFriendly;
    private boolean spa;
    private boolean fitness;

    public HotelBuilder() {
    }

    @SuppressWarnings("checkstyle:hiddenField")
    public HotelBuilder id(final int id) {
      this.id = id;
      return this;
    }

    public HotelBuilder category(final Category category) {
      this.category = category;
      return this;
    }

    @SuppressWarnings("checkstyle:hiddenField")
    public HotelBuilder name(final String name) {
      this.name = name;
      return this;
    }

    @SuppressWarnings("checkstyle:hiddenField")
    public HotelBuilder owner(final String owner) {
      this.owner = owner;
      return this;
    }

    @SuppressWarnings("checkstyle:hiddenField")
    public HotelBuilder contact(final String contact) {
      this.contact = contact;
      return this;
    }

    @SuppressWarnings("checkstyle:hiddenField")
    public HotelBuilder address(final String address) {
      this.address = address;
      return this;
    }

    @SuppressWarnings("checkstyle:hiddenField")
    public HotelBuilder city(final String city) {
      this.city = city;
      return this;
    }

    @SuppressWarnings("checkstyle:hiddenField")
    public HotelBuilder cityCode(final String cityCode) {
      this.cityCode = cityCode;
      return this;
    }

    @SuppressWarnings("checkstyle:hiddenField")
    public HotelBuilder phone(final String phone) {
      this.phone = phone;
      return this;
    }

    @SuppressWarnings("checkstyle:hiddenField")
    public HotelBuilder noRooms(final int noRooms) {
      this.noRooms = noRooms;
      return this;
    }

    @SuppressWarnings("checkstyle:hiddenField")
    public HotelBuilder noBeds(final int noBeds) {
      this.noBeds = noBeds;
      return this;
    }

    public HotelBuilder url(final String url) {
      this.url = url;
      return this;
    }

    public HotelBuilder familyFriendly(final boolean familyFriendly) {
      this.familyFriendly = familyFriendly;
      return this;
    }

    public HotelBuilder dogFriendly(final boolean dogFriendly) {
      this.dogFriendly = dogFriendly;
      return this;
    }

    public HotelBuilder spa(final boolean spa) {
      this.spa = spa;
      return this;
    }

    public HotelBuilder fitness(final boolean fitness) {
      this.fitness = fitness;
      return this;
    }

    public Hotel build() {
      return new Hotel(
          id,
          category,
          name,
          owner,
          contact,
          address,
          city,
          cityCode,
          phone,
          noRooms,
          noBeds,
          url,
          familyFriendly,
          dogFriendly,
          spa,
          fitness);
    }

    public String toString() {
      return "Hotel.HotelBuilder(id=" + this.id + ", category="
          + this.category + ", name=" + this.name + ", owner=" + this.owner
          + ", contact=" + this.contact + ", address=" + this.address + ", city="
          + this.city + ", cityCode=" + this.cityCode + ", phone=" + this.phone
          + ", noRooms=" + this.noRooms + ", noBeds=" + this.noBeds
          +", url= " + this.url + ", familyFriendly" + this.familyFriendly + ", dogFriendly"
          + this.dogFriendly + ", spa" + this.spa + ", fitness" + this.fitness + ")";
    }
  }
}