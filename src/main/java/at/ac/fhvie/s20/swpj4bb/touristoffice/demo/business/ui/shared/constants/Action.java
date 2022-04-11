package at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.ui.shared.constants;

/**
 * Action constants
 */
public enum Action {

  CREATE("create"),
  EDIT("edit"),
  SAVE("save"),
  CANCEL("cancel");

  private String action;

  Action(final String action) {
    this.action = action;
  }

  public String getAction() {
    return this.action;
  }

  @Override
  public String toString() {
    return  action;
  }
}
