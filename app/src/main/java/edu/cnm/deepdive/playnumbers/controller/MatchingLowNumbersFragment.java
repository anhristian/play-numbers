package edu.cnm.deepdive.playnumbers.controller;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import edu.cnm.deepdive.playnumbers.R;
import edu.cnm.deepdive.playnumbers.model.entity.Activity.Type;
import edu.cnm.deepdive.playnumbers.viewmodel.MatchingHighNumbersViewModel;
import edu.cnm.deepdive.playnumbers.viewmodel.MatchingLowNumbersViewModel;
import java.util.List;


/**
 * Hosts the actions that are implemented on the activity match the low numbers.
 */
public class MatchingLowNumbersFragment extends LearningActivityFragment {

  public List<Integer> list;

  private MatchingLowNumbersViewModel viewModel;
  private ImageView[] numberButtons;


  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    numberButtons = new ImageView[MatchingLowNumbersViewModel.BUTTON_COUNT];
    Resources resources = getContext().getResources();
    String pkg = getContext().getPackageName();
    for (int i = 0; i < MatchingLowNumbersViewModel.BUTTON_COUNT; i++) {
      String idResName = "button_" + i;
      int id = resources.getIdentifier(idResName, "id", pkg);
      numberButtons[i] = view.findViewById(id);
    }
    viewModel = new ViewModelProvider(this).get(MatchingLowNumbersViewModel.class);
    viewModel.getNumberButtons().observe(getViewLifecycleOwner(), (buttons) -> {
      for(int i = 0; i < buttons.length; i++) {
        ImageView iv = numberButtons[i];
        LayerDrawable drawable = (LayerDrawable) iv.getDrawable();
        Drawable background = drawable.getDrawable(0);
        Drawable foreground = drawable.getDrawable(1);
        foreground.setLevel(i+MatchingHighNumbersViewModel.BUTTON_LEVEL_OFFSET);
        background.setLevel(buttons[i].getState().ordinal());
      }
    });
  }

  @Override
  public Type getType() {
    return Type.MATCHING;
  }

  @Override
  public int getLayoutId() {
    return R.layout.fragment_matching_low_numbers;
  }

}


/* if(!gamefinished){
    list.add(rndInt);
    String imgName = "i" + rndInt;
    int id = getResources().getIdentifier(imgName, "drawable", getPackageName());
    mainImage.setImageResource(id);
    switch (imgName){//initialize correctAnswer
      case "button1":
        correctAnswer = count_image_1;
        break;
      case "button2":
        correctAnswer = count_image_2;
        break;
      case "button3":
        correctAnswer = count_image_3;
        break;
      case "button4":
        correctAnswer = count_image_4;
        break;
      case "button5":
        correctAnswer = count_image_5;
        break;
}*/
