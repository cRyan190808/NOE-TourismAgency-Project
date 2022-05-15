package at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LegalnoticeController {
  @GetMapping("/legalnotice")
  public String helpOutput() {

    return "legalnotice";
  }
}
