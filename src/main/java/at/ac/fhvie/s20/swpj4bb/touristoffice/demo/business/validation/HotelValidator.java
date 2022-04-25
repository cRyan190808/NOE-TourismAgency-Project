package at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.validation;

import at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.entity.Hotel;
import org.springframework.stereotype.Component;

import java.util.ArrayList; // Note Christopher:
                            // Reference to ui/main_menu/controller/MainMenuController Row100
/**
 * Validation class for the Hotel class
 */
@Component
public class HotelValidator extends ValidationSupport implements Validator<Hotel> {

  /**
   * Validate the entity Hotel
   * @param hotel entity to validate
   * @return String with the error message. Empty when no error occured
   */
  @Override
  public ArrayList<String> validate(final Hotel hotel) {
    ArrayList<String> errors = new ArrayList<>();
    String error = "";

    if (isNullOrEmptyString(hotel.getName())) {
      errors.add(nameExceptionMessage());
    }

    if (isNullOrEmptyString(hotel.getOwner())) {
      errors.add(ownerExceptionMessage());
    }

    if (isNullOrEmptyString(hotel.getContact())) {
      errors.add(contactExceptionMessage());
    }
    if (isNullOrEmptyString(hotel.getAddress())) {
      errors.add(addressExceptionMessage());
    }
    if (isNullOrEmptyString(hotel.getCity())) {
      errors.add(cityExceptionMessage());
    }
    if (isNullOrEmptyString(hotel.getCityCode())) {
      errors.add(cityCodeExceptionMessage());
    }
    if (isNullOrEmptyString(hotel.getPhone())) {
      errors.add(phoneExceptionMessage());
    }
    if (isLessEqualZero(hotel.getNoBeds())) {
      errors.add(bedExceptionMessage(hotel.getNoBeds()));
    }

    if (isLessEqualZero(hotel.getNoRooms())) {
      errors.add(bedExceptionMessage(hotel.getNoRooms()));
    }

    // Maybe it is not so optimal that a String with all the error messages in one single String is returned.
    // Maybe an ArrayList with the entries separated is the better solution. In one single string it is good for swing
    // but separates Strings are better in HTML
    return errors;
  }

  private String nameExceptionMessage() {
    return "Name darf nicht leer sein.\n";
  }

  private String ownerExceptionMessage() {
    return "Owner darf nicht leer sein";
  }

  private String contactExceptionMessage() {
    return "Kontakt darf nicht leer sein.\n";
  }

  private String addressExceptionMessage() {
    return "Adresse darf nicht leer sein.\n";
  }

  private String cityExceptionMessage() {
    return "Stadt darf nicht leer sein.\n";
  }

  private String cityCodeExceptionMessage() {
    return "Postleitzahl darf nicht leer sein.\n";
  }

  private String phoneExceptionMessage() {
    return "Telefon darf nicht leer sein.\n";
  }

  private String bedExceptionMessage(final int errorValue) {
    return "Bettenzahl muss größer als 0 sein (Ist:" + errorValue + ").\n";
  }

  private String roomExceptionMessage(final int errorValue) {
    return "Raumzahl muss größer als 0 sein (Ist:" + errorValue + ").\n";
  }

}
