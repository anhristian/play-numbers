package edu.cnm.deepdive.playnumbers.model.fsm;

public enum PairState {

  NEITHER_SELECTED {
    @Override
    public PairState selectNumber(boolean same) {
      return NUMBER_SELECTED;
    }

    @Override
    public PairState selectImage(boolean same) {
      return IMAGE_SELECTED;
    }
  },
  NUMBER_SELECTED {
    @Override
    public PairState selectNumber(boolean same) {
      return same ? NEITHER_SELECTED : NUMBER_SELECTED;
    }

    @Override
    public PairState selectImage(boolean same) {
      return BOTH_SELECTED;
    }
  },
  IMAGE_SELECTED {
    @Override
    public PairState selectNumber(boolean same) {
      return BOTH_SELECTED;
    }

    @Override
    public PairState selectImage(boolean same) {
      return same ? NEITHER_SELECTED : IMAGE_SELECTED;
    }
  },
  BOTH_SELECTED;

  public PairState selectNumber(boolean same) {
    return this;
  }

  public PairState selectImage(boolean same) {
    return this; //don't change state
  }

}
