package edu.cnm.deepdive.playnumbers.model.fsm;

public enum ButtonState {

  ENABLED {
    @Override
    public ButtonState click() {
      return SELECTED;
    }
  },
  SELECTED{
    @Override
    public ButtonState click() {
      return ENABLED;
    }
  },
  DISABLED {
    @Override
    public boolean isClickable() {
      return false;
    }
  };


  public ButtonState click() {
    return DISABLED;
  }

  public boolean isClickable() {
    return true;
  }

}
