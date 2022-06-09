package at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.repository;

import at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.entity.Occupancy2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OccupancyRepository extends JpaRepository<Occupancy2, Integer>, PagingAndSortingRepository<Occupancy2, Integer> {
  Page<Occupancy2> findAllByHotelId(Pageable pageable, @Param("hotel_id") int hotelId);
}
