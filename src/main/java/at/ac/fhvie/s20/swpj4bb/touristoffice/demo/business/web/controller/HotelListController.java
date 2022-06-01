package at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.web.controller;

import at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.entity.Hotel;
import at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class HotelListController {

  private final HotelService hotelService;

  @Autowired
  public HotelListController(final HotelService hotelService) {
    this.hotelService = hotelService;
  }

  @GetMapping("/hotelliste")
  public String hotelOutput(
      final Model model,
      final @RequestParam("page") Optional<Integer> page,
      final @RequestParam("size") Optional<Integer> size) {
    int currentPage = page.orElse(1);
    int pageSize = size.orElse(10);

    Pageable pageRequest = PageRequest.of(currentPage - 1, pageSize);
    Page<Hotel> hotelsPage = hotelService.findAllOrderedById(pageRequest);
    model.addAttribute("hotelPage", hotelsPage);
    int totalPages = hotelsPage.getTotalPages();
    if (totalPages > 0) {
      List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
          .boxed()
          .collect(Collectors.toList());

      model.addAttribute("pageNumbers", pageNumbers);
    }

    model.addAttribute("activePage", "index");

    return "hotelliste";
  }
}

