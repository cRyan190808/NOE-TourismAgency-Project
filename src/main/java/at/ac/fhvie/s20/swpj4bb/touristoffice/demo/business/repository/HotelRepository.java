package at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.repository;

import at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.entity.Hotel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This class declares the Database accesses.
 * See also the Spring SQL example on the Moodle page of the course!
 */
@Repository
public interface HotelRepository extends PagingAndSortingRepository<Hotel, Integer> {
  List<Hotel> findAllByOrderByIdAsc();

  @Query(value = "SELECT o FROM Hotel o ORDER BY id")
  Page<Hotel> findAll(Pageable pageable);

  List<Hotel> findByName(String lastName);
  List<Hotel> findAllById(int id, Pageable pageable);
}
