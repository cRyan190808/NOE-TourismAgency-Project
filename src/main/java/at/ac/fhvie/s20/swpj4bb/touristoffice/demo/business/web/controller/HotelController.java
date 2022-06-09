package at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.web.controller;

import at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.entity.Hotel;
import at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.service.HotelService;
import at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.validation.HotelValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@Controller
public class HotelController {
  private final HotelValidator hotelValidator;
  private HotelService hotelService;

  @Autowired
  public HotelController(final HotelValidator hotelvalidator, final HotelService hotelService) {
    this.hotelValidator = hotelvalidator;
    this.hotelService = hotelService;
  }

  // Here the form is called and the template is provided with an empty Hotel Instance
  @GetMapping("/hotelform")
  public String fooForm(Model model) {
    model.addAttribute("hotel", new Hotel());
    return "hotelform";
  }


  // After submitting the Hotel form here is the entry point for the form POST where the form is validated
  // If there is an error the hotel entry form is presented once again with additional error information
  // If there is no error the page is redirected to an output page
  @PostMapping("/hotelform")
  public String hotelPost(
      @ModelAttribute("hotel") Hotel hotel,
      // WARN: BindingResult *must* immediately follow the Command.
      // https://stackoverflow.com/a/29883178/1626026
      BindingResult bindingResult,
      Model model,
      RedirectAttributes ra) {
    ArrayList<String> errors = hotelValidator.validate(hotel);

    // bindingResult is for a certain kind of data validation and not used in this example.
    // The RedirectAttributes are similar to Attributes but with the difference that the values are
    // maintained in the *Session* and are removed after the redirect.
    // With using Attribute the request parameters are created out of the attributes and are serialized
    // RedirectAttributes are not serialized and can therefore store any object

    //ArrayList<String> errors = hotelValidator.validate(command);
    // You have to format the error string for displaying it in HTML.
    // On a web page the \n is not valid and so no line feed is made.
    if (!errors.isEmpty()) {
      model.addAttribute("error", errors);
      return "hotelform";
    }

    hotelService.save(hotel);
    ra.addFlashAttribute("hotel", hotel);

    return "redirect:/hotelliste";
  }
}