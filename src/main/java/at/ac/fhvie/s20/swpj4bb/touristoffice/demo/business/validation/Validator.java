package at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.validation;

import java.util.ArrayList;

/**
 * Interface for all validation classes.
 * @param <K> Class to be validated
 */
interface Validator<K> {

  ArrayList<String> validate(K value);

}
