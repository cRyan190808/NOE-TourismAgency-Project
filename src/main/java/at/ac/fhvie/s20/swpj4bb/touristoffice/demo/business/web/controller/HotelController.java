package at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.web.controller;

import at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.constants.Category;
import at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.entity.Hotel;
import at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.service.HotelService;
import at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.validation.HotelValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.converter.CategoryConverter;

import java.util.ArrayList;

@Controller
public class HotelController {

  private final HotelValidator hotelValidator;
  private HotelService hotelService;
  private static int tempCategory;

  @Autowired
  public HotelController(final HotelValidator hotelvalidator, final HotelService hotelService) {
    this.hotelValidator = hotelvalidator;
    this.hotelService = hotelService;
  }

  // Here the form is called and the template is provided with an empty Hotel Instance
  @GetMapping("/hotelform")
  public String fooForm(Model model) {
    model.addAttribute("command", new Hotel());
    model.addAttribute("tempCategory", tempCategory);

    return "hotelform";
  }


  // After submitting the Hotel form here is the entry point for the form POST where the form is validated
  // If there is an error the hotel entry form is presented once again with additional error information
  // If there is no error the page is redirected to an output page
  @PostMapping("/hotelform")
  public String hotelPost(
      @ModelAttribute("command") Hotel command,
      // WARN: BindingResult *must* immediately follow the Command.
      // https://stackoverflow.com/a/29883178/1626026
      BindingResult bindingResult,
      Model model,
      RedirectAttributes ra,
      @RequestParam(value = "tempCategory") int tempCategory) {
      command.setCategory(new CategoryConverter().convertToEntityAttribute(tempCategory));

      command.checkBoxToString();

    // bindingResult is for a certain kind of data validation and not used in this example.
    // The RedirectAttributes are similar to Attributes but witht he difference that the values are
    // maintained in the *Session* and are removed after the redirect.
    // With using Attribute the request parameters are created out of the attributes and are serialized
    // RedriectAttributes are not serialized and can therefore store any object

    ArrayList<String> errors = hotelValidator.validate(command);
    // You have to format the error string for displaying it in HTML.
    // On a web page the \n is not valid and so no line feed is made.
    if (!errors.isEmpty()) {
      // If there is an error the hotelform template gets the current values and the error string
      // Alternatively the error could be transmitted as a formatted HTML string or an ArrayList
      // with all the errors and then put together in the template
      // The ArrayList has the advantage that the creation of the error string is independent of the frontend
      // (Swing/Web/App) but it has to be formatted later. A possibility could be in a Factory Pattern where you
      // provide the data and request a certain format and receive a formatted String.
      // So the Factory is responsible for the correct formatting. And further formats are only implemented in that Factory.

      model.addAttribute("error", errors);

      //model.addAttribute("command", command);
      return "hotelform";
    }

    hotelService.save(command);
    ra.addFlashAttribute("command", command);

    // Redirect to the output page. The hotel data is stored in an FlashAttribute which is maintained in the session
    return "redirect:/hotelresult";

  }


  // Output of the hotel data entered by the user
  @GetMapping("/hotelresult")
  public String hotelOutput(
      @ModelAttribute("command") Hotel command,
      Model model) {

    System.out.println(command);

    return "hotelresult";
  }

/*
private static Category convertToCategory(final int value) {
    if (value == 1) {
      return Category.ONE;
    }
    if (value == 2) {
      return Category.TWO;
    }
    if (value == 3) {
      return Category.THREE;
    }
    if (value == 4) {
      return Category.FOUR;
    }
    if (value == 5) {
      return Category.FIVE;
    }
    throw new IllegalArgumentException("Zahl zwischen eins und fünf für Sterne");
  }
*/
}