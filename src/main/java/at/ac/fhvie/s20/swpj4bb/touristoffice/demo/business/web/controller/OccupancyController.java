package at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.web.controller;

import at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.entity.Occupancy2;
import at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.service.OccupancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;
import java.util.logging.Logger;

@Controller
public class OccupancyController {
  private final Logger LOG = Logger.getLogger(OccupancyController.class.getSimpleName());
  private final OccupancyService occupancyService;

  @Autowired
  public OccupancyController(final OccupancyService occupancyService) {
    this.occupancyService = occupancyService;
  }

  @GetMapping(value = "/occupancy/{hotelId}")
  public String getOccupancy(
      final Model model,
      final @RequestParam("page") Optional<Integer> page,
      final @RequestParam("size") Optional<Integer> size,
      @PathVariable("hotelId") final int hotelId) {
    LOG.info("GET REQUEST for page: " + page + " and size: " + size + " and hotelId: " + hotelId);
    int currentPage = page.orElse(1);
    int pageSize = size.orElse(2);

    Pageable pageRequest = PageRequest.of(currentPage - 1, pageSize);
    Page<Occupancy2> occupanciesPage =
        occupancyService.findOccupanciesByHotelId(pageRequest, hotelId);
    model.addAttribute("occupanciesPage", occupanciesPage);
    model.addAttribute("hotelId", hotelId);

    return "occupancy";
  }
}
