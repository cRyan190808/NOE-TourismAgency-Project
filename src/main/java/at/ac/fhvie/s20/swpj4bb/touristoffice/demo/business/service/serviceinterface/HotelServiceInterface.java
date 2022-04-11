package at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.service.serviceinterface;

import at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.entity.Hotel;

import java.util.Set;

/**
 * Interface for the mandatory functions to implement into controllers for Hotels
 */
public interface HotelServiceInterface {

  Set<String> findAllHotelNames();

  Hotel findHotelByName(String name);

}
