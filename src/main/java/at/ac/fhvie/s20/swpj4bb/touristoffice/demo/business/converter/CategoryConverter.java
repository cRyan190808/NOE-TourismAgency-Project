package at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.converter;

import at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.constants.Category;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.logging.Logger;

/**
 * Converts between the textual representation of a hotel category for the application
 * into an integer needed by the database and vice versa.
 * Also see https://thoughts-on-java.org/jpa-21-how-to-implement-type-converter/
 */
@Converter(autoApply = true)
public class CategoryConverter implements AttributeConverter<Category, Integer> {
  private Logger log = Logger.getLogger(CategoryConverter.class.getSimpleName());

  @Override
  public Integer convertToDatabaseColumn(final Category category) {

    // CSOFF: MagicNumber
    switch (category) {
      case ONE:
        return 0;
      case TWO:
        return 1;
      case THREE:
        return 2;
      case FOUR:
        return 3;
      case FIVE:
        return 4;
      default:
        throw new IllegalArgumentException("Conversion " + category.toString() + " not "
            + "implemented");
    }
    // CSON: MagicNumber
  }

  // CSOFF: MagicNumber
  @Override
  public Category convertToEntityAttribute(final Integer dbData) {
    switch (dbData) {
      case 0:
        return Category.ONE;
      case 1:
        return Category.TWO;
      case 2:
        return Category.THREE;
      case 3:
        return Category.FOUR;
      case 4:
        return Category.FIVE;
      default:
        throw new IllegalArgumentException("Sternenanzahl muss zwischen 1 und 5 sein. (Eingebener Wert: " + dbData + ")");
    }
    // CSON: MagicNumber


  }

  private void logDbConversion(final Category category, final Integer dbData) {
    log.info("Convert Category enum [" + category + "] to [" + dbData + "]");
  }
}
