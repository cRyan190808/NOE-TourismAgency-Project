package at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.service;

import at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.constants.Category;
import at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.converter.CategoryConverter;
import at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.entity.Occupancy;
import at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.repository.HotelRepository;
import at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.service.comparator.CapacityComparator;
import at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.util.Utility;
import at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.entity.Capacity;
import at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.entity.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Service forhandling the hotel data
 * This class is able to read either from a csv file or reading from an H2 database.
 * Also is this class a middleman between the "outer world" and the storing of the data.
 * As in this class shown it can read data from a csv file or an H2 database. So the
 * programmer of the business logic has not to think about what to use. This class forwards
 * the reading and storing to the correct medium. In this wasy it is easy to switch from an
 * H2 database to MsSQL or MyDWL, MariaDB or other.
 * During development you may mock the database and return static fiexed data. so you can
 * continue writing on the GUI and business logic and be independent from the database
 * development-
 */
@Service
public class HotelService {


  public static final String TOTAL = "Total";
  public static final int GROUP_STAR_MIN = 2;
  private static int count = 0;
  private static final String FILENAME_HOTELS = "files" + File.separator + "hotels.txt";
  private static final String COMMA_DELIMITER = ",";
  private static Set<Hotel> hotels = new HashSet<>();

  private static Map<String, Hotel> hotelsByName = new TreeMap<>();

  /**
   * Collection Hotels sorted by capacity.
   */
  private static SortedMap<String, Capacity> hotelsByCapacity =
      new TreeMap<>(new CapacityComparator());

  /**
   * Collection of Occupancies sorted by capacity
   */
  private static SortedMap<String, Occupancy> hotelsByOccupancy =
      new TreeMap<>(new CapacityComparator());

  private HotelRepository hotelRepository;


  @Autowired
  public HotelService(final HotelRepository hotelRepository) {
    this.hotelRepository = hotelRepository;

    // Disable one of the two lines!!!!!!
    // Enable when reading from CSV file
    // readDatabaseFromCsv();

    // Enable when reading from H2 database
    readDatabaseFromH2();
  }

  /**
   * retrieve a hotel by id
   * @param id of the hotel to find
   * @return Instance of Hotel with the corresponding id
   */
  public Hotel findById(final int id) {
    // Accesses the repository i.e. the database
    return hotelRepository.findById(id).get();
  }

  /**
   * Retrieve all hotels
   * @return Collection of Hotels
   */
  public List<Hotel> findAll() {
    // Return the hotels currently loaded
    return new ArrayList<>(hotels);
  }

  public List<Hotel> findAllOrderedById() {
    // Return the hotels currently loaded
    return hotelRepository.findAllByOrderByIdAsc();
  }

  public Page<Hotel> findAllOrderedById(final Pageable pageRequest) {
    // Return the hotels currently loaded
    Page<Hotel> hotels = hotelRepository.findAll(pageRequest);
    return hotels;

  }

  /**
   * retrieve all Capacities
   * @return Collection of Capacities
   */
  public List<Capacity> findAllByCapacity() {
    return new ArrayList<>(hotelsByCapacity.values());
  }

  /**
   * retrieve all occupancies
   * @return Collection of Occupancies
   */
  public List<Occupancy> findAllByOccupancy() {
    return new ArrayList<>(hotelsByOccupancy.values());
  }

  /**
   * retrieve all hotel names
   * @return Collection of hotel names
   */
  public Set<String> findAllHotelNames() {
    return hotelsByName.keySet();
  }

  /**
   * Retrieve a hotel by name
   * @param name Name of the hotel to return data
   * @return Instance of Hotel
   */
  public Hotel findHotelByName(final String name) {
    return hotelsByName.get(name);
  }

  /**
   * Add a hotel to the capacity list
   * @param hotel Hotel to add
   */
  private static void addHotelToCapacity(final Hotel hotel) {
    // Get the category string of the hotel. Please keep in mind that category one and
    // two are combined together
    String index = getStarIndex((hotel.getCategory()));

    // Retrieve from the list the current count and increment the record by
    // count, room numbers and bed numbers
    Capacity capacity = hotelsByCapacity.get(index);
    Capacity newCapacity =
        new Capacity.CapacityBuilder()
            .category(index)
            .businessCount(capacity.getBusinessCount() + 1)
            .roomCount(capacity.getRoomCount() + hotel.getNoRooms())
            .bedCount(capacity.getBedCount() + hotel.getNoBeds())
            .build();
    // Store back the data
    hotelsByCapacity.put(index, newCapacity);

    // Update total capacity
    Capacity total = hotelsByCapacity.get(TOTAL);
    total.setBusinessCount(total.getBusinessCount() + 1);
    total.setRoomCount(total.getRoomCount() + hotel.getNoRooms());
    total.setBedCount(total.getBedCount() + hotel.getNoBeds());
    hotelsByCapacity.put(TOTAL, total);
  }

  /**
   * Add a hotel to the occupancy list
   * @param hotel Hotel to add
   */
  private static void addHotelToOccupancy(final Hotel hotel) {
    String index = getStarIndex(hotel.getCategory());

    Occupancy occupancy = hotelsByOccupancy.get(index);
    Occupancy newOccupancy =
        new Occupancy.OccupancyBuilder()
            .category(index)
            .roomCount(occupancy.getRoomCount() + hotel.getNoRooms())
            .roomUtilization(Math.random() * (double) hotel.getNoRooms())
            .bedCount(occupancy.getBedCount() + hotel.getNoBeds())
            .bedUtilization(Math.random() + (double) hotel.getNoBeds())
            .build();
    hotelsByOccupancy.put(index, newOccupancy);

    // Update total capacity
    Occupancy total = hotelsByOccupancy.get(TOTAL);
    total.setRoomCount(total.getRoomCount() + hotel.getNoRooms());
    total.setBedCount(total.getBedCount() + hotel.getNoBeds());
    // CSOFF: MagicNumber
    total.setBedUtilization(42.0f);
    total.setRoomUtilization(56.0f);
    // CSON: MagicNumber
    hotelsByOccupancy.put(TOTAL, total);
  }

  private static void addHotelToHotelNameList(final Hotel hotel) {
    hotelsByName.put(hotel.getName(), hotel);
  }


  private static void updateCount(final int newCount) {
    if (newCount < count) {
      throw new IllegalArgumentException("Consistency check for hotel id failed!");
    }
    count = newCount;
  }


  /**
   * Load data from H2 database
   */
  private void readDatabaseFromH2() {
    // Clear all existing data
    clearCapacityMap();
    clearOccupancyMap();

    // Retrieve data from database and add the hotels to the various collection
    for (Hotel hotel : hotelRepository.findAll()) {
      hotels.add(hotel);
      addHotelToCapacity(hotel);
      addHotelToOccupancy(hotel);
      addHotelToHotelNameList(hotel);
    }

  }

  /**
   * Read data from a csv file
   */
  private void readDatabaseFromCsv() {
    clearCapacityMap();
    clearOccupancyMap();

    BufferedReader fileReader = null;

    try {
      String line = "";

      //Create the file reader
      fileReader = new BufferedReader(new FileReader(Utility.getProjectDir() + FILENAME_HOTELS));

      //Read the CSV file header to skip it
      fileReader.readLine();

      //Read the file line by line starting from the second line
      while ((line = fileReader.readLine()) != null) {
        //Get all tokens available in line
        String[] tokens = line.split(COMMA_DELIMITER);
        if (tokens.length > 0) {
          int index = 0;
          //Create a new student object and fill his  data
          Hotel hotel = Hotel.builder()
              .id(Integer.parseInt(Utility.stripQuotes(tokens[index++])))
              .category(Category.get(Utility.stripQuotes(tokens[index++])))
              .name(Utility.stripQuotes(tokens[index++]))
              .owner(Utility.stripQuotes(tokens[index++]))
              .contact(Utility.stripQuotes(tokens[index++]))
              .address(Utility.stripQuotes(tokens[index++]))
              .city(Utility.stripQuotes(tokens[index++]))
              .cityCode(Utility.stripQuotes(tokens[index++]))
              .phone(Utility.stripQuotes(tokens[index++]))
              .noRooms(Integer.parseInt(Utility.stripQuotes(tokens[index++])))
              .noBeds(Integer.parseInt(Utility.stripQuotes(tokens[index++])))
              .build();
          // There is no validation of the data read from the file. Should there be
          // a validator? What to do if the validation fails?

          hotels.add(hotel);
          addHotelToCapacity(hotel);
          addHotelToOccupancy(hotel);
          addHotelToHotelNameList(hotel);
        }
      }

    } catch (Exception exc) {
      System.out.println("Error in CsvFileReader !!!");
      exc.printStackTrace();
    } finally {
      try {
        fileReader.close();
      } catch (IOException exc) {
        System.out.println("Error while closing fileReader !!!");
        exc.printStackTrace();
      }
    }
  }

  /**
   * Clear the Collection of the capacity
   */
  private static void clearCapacityMap() {

    hotelsByCapacity.clear();
    for (Category category : Category.values()) {
      Capacity defaultEntity =
          new Capacity.CapacityBuilder()
              .category(getStarIndex(category))
              .businessCount(0)
              .roomCount(0)
              .bedCount(0)
              .build();
      hotelsByCapacity.put(getStarIndex(category), defaultEntity);
    }

    Capacity total = new Capacity.CapacityBuilder()
        .category(TOTAL)
        .businessCount(0)
        .bedCount(0)
        .roomCount(0)
        .build();
    hotelsByCapacity.put(TOTAL, total);

  }

  /**
   * Clear the occupancy Collection
   */
  private static void clearOccupancyMap() {

    hotelsByOccupancy.clear();
    for (Category category : Category.values()) {
      Occupancy defaultEntity =
          new Occupancy.OccupancyBuilder()
              .category(getStarIndex(category))
              .bedCount(0)
              .bedUtilization(0f)
              .roomCount(0)
              .roomUtilization(0f)
              .build();
      hotelsByOccupancy.put(getStarIndex(category), defaultEntity);
    }

    Occupancy total = new Occupancy.OccupancyBuilder()
        .category(TOTAL)
        .bedCount(0)
        .bedUtilization(0f)
        .roomCount(0)
        .roomUtilization(0f)
        .build();
    hotelsByOccupancy.put(TOTAL, total);

  }

  /**
   * Create a string for the hotel category
   * Category * and ** are combined together
   * @param category Category to get the Star index of
   * @return String representing the category group
   */
  private static String getStarIndex(final Category category) {
    String index;

    if (category.getStarAsNumber() <= GROUP_STAR_MIN) {
      // category one and two are combined to "* & **"
      index = Category.TWO + " & " + Category.ONE;
    } else {
      index = category.getStars();
    }

    return index;
  }

  /**
   * Save the data of a new Hotel
   * @param hotel Hotel to save
   * @return true if saving is a success
   */
  public boolean save(final Hotel hotel) {
    hotels.add(hotel);
    addHotelToCapacity(hotel);
    addHotelToOccupancy(hotel);
    addHotelToHotelNameList(hotel);
    // write the hotel data to the database
    hotelRepository.save(hotel);
    exportDatabase();


    return true;
  }

  public void update(final Hotel newHotel) {

    // Data of the original one has to be altered with the new ones.
    Hotel oldHotel = findById(newHotel.getId());
    oldHotel.updateWith(newHotel);
    // update is the same as save! as long as the id is the same!!!!!!!!
    hotelRepository.save(oldHotel);

    // Export the database as SQL file
    exportDatabase();


  }

  /**
   * Dump the data as SQL file
   */
  public void exportDatabase() {
    DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-HHmmss");
    Date date = new Date();

    String sql = "";
    // Iterate over all hotels and create an SQL String
    // TODO: concatenating Strings like this is very unperformant and should be changed!!!!!!!
    for (Hotel hotel : hotelRepository.findAll()) {
      sql += "INSERT INTO HOTEL VALUES (";
      sql += "" + hotel.getId() + ", ";
      // The category is stored in the database as an integer, so the textual
      // representation with a string has to be converted to an integer value
      // Therefore we use a converter
      sql += "" + new CategoryConverter().convertToDatabaseColumn(hotel.getCategory()) + ", ";
      sql += "'" + hotel.getName() + "', ";
      sql += "'" + hotel.getOwner() + "', ";
      sql += "'" + hotel.getContact() + "', ";
      sql += "'" + hotel.getAddress() + "', ";
      sql += "'" + hotel.getCity() + "', ";
      sql += "'" + hotel.getCityCode() + "', ";
      sql += "'" + hotel.getPhone() + "', ";
      sql += "" + hotel.getNoRooms() + ", ";
      sql += "" + hotel.getNoBeds() + ", ";
      sql += "'" + hotel.getUrl() + "', ";
      sql += "" + hotel.isFamilyFriendly() + ", ";
      sql += "" + hotel.isDogFriendly() + ", ";
      sql += "" + hotel.isSpa() + ", ";
      sql += "" + hotel.isFitness() + ");\n";
    }

    // Write the SQL string to the file system
    BufferedWriter writer = null;
    try {
      //String fileName = "hotel" + dateFormat.format(date) + ".sql";
      String fileName = "hotels.sql";
      writer = new BufferedWriter(new FileWriter("src/main/resources/hotelData/" +fileName));
      writer.write(sql);
      writer.close();

    } catch (IOException exc) {
      exc.printStackTrace();
    }
  }

}
