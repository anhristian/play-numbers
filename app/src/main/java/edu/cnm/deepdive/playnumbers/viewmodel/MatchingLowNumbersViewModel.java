package edu.cnm.deepdive.playnumbers.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import edu.cnm.deepdive.playnumbers.R;
import edu.cnm.deepdive.playnumbers.model.fsm.NumberButton;

public class MatchingLowNumbersViewModel extends AndroidViewModel {

  public static final int BUTTON_LEVEL_OFFSET = 6;
  public static final int BUTTON_COUNT = 5;

  private final MutableLiveData<NumberButton[]> numberButtons;

  public MatchingLowNumbersViewModel(
      @NonNull Application application) {
    super(application);
    numberButtons = new MutableLiveData<>();
  }

  private void setupButtons () {
    NumberButton[] buttons = new NumberButton[5];
    for (int i = 0; i< BUTTON_COUNT; i++) {
      NumberButton button = new NumberButton(i + BUTTON_LEVEL_OFFSET, R.drawable.button_layers);
      buttons[i] = button;
    }
    numberButtons.setValue(buttons);
  }
}
