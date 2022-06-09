package at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.service;

import at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.entity.Occupancy2;
import at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.repository.OccupancyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OccupancyService {
  private final OccupancyRepository occupancyRepository;

  @Autowired
  public OccupancyService(final OccupancyRepository occupancyRepository) {
    this.occupancyRepository = occupancyRepository;
  }

  public Page<Occupancy2> findOccupanciesByHotelId(final Pageable pageRequest, final int hotel) {
    return occupancyRepository.findAllByHotelId(pageRequest, hotel);
  }
}