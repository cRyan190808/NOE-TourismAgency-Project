package at.ac.fhvie.s20.swpj4bb.touristoffice.demo;

import at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.ui.main_menu.controller.MainMenuController;
import at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.util.LookAndFeelUtils;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

// CSOFF: HideUtilityClassConstructor
@SpringBootApplication
public class Main {

  public static void main(final String[] args) {
    // Set the look and feel of the application
    LookAndFeelUtils.setWindowsLookAndFeel();

    ConfigurableApplicationContext context = new SpringApplicationBuilder(Main.class)
        .headless(false).run(args);

    MainMenuController mainMenuController = context.getBean(MainMenuController.class);
    mainMenuController.prepareAndOpenFrame();

    //TEXT

  }
}
// CSON: HideUtilityClassConstructor

