package edu.cnm.deepdive.playnumbers.model.fsm;

public class NumberButton {

  private final int level;
  private final int foregroundResource;
  private ButtonState state;

  public NumberButton(int level, int foregroundResource) {
    this.level = level;
    this.foregroundResource = foregroundResource;
    state = ButtonState.ENABLED;
  }

  public ButtonState getState() {
    return state;
  }

  public void setState(ButtonState state) {
    this.state = state;
  }
}
