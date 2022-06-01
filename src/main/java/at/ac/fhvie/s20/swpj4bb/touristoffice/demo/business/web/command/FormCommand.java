package at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.web.command;


import lombok.Data;

@Data
public class FormCommand {
  String textField;

  String textareaField;

  String datetimeField;

  String colorField;

  boolean singleCheckboxField;

  String[] multiCheckboxSelectedValues;

  String radioButtonSelectedValue;

  String dropdownSelectedValue;
}