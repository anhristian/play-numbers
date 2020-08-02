package edu.cnm.deepdive.playnumbers.controller;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import edu.cnm.deepdive.playnumbers.R;
import edu.cnm.deepdive.playnumbers.model.entity.Activity.Type;
import java.util.List;
import java.util.Random;

/**
 * Hosts the actions that are implemented on the activity match the low numbers.
 */
public class MatchingLowNumbersFragment extends LearningActivityFragment
    implements View.OnClickListener {

  public int correctAnswer;
  public int progressStatus;
  public Random random;
  public List<Integer> list;

  private ImageButton button1;
  private ImageButton button2;
  private ImageButton button3;
  private ImageButton button4;
  private ImageButton button5;
  private View root;
  private OnClickListener listener;

  private TextToSpeech t1;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    //TODO DO any additional process to set this.
    //TODO Add Text to Speech.
    View view = inflater.inflate(R.layout.fragment_matching_low_numbers, container, false);
    view.findViewById(R.id.button_0).setOnClickListener(this);
    view.findViewById(R.id.button_1).setOnClickListener(this);
    view.findViewById(R.id.button_2).setOnClickListener(this);
    view.findViewById(R.id.button_3).setOnClickListener(this);
    view.findViewById(R.id.button_4).setOnClickListener(this);
    view.findViewById(R.id.count_image_1).setOnClickListener(this);
    view.findViewById(R.id.count_image_2).setOnClickListener(this);
    view.findViewById(R.id.count_image_3).setOnClickListener(this);
    view.findViewById(R.id.count_image_4).setOnClickListener(this);
    view.findViewById(R.id.count_image_5).setOnClickListener(this);
    return view;
  }

  private void bind(int position) {

    button1.setImageResource(R.drawable.number_1);
    button2.setImageResource(R.drawable.number_2);
    button3.setImageResource(R.drawable.number_3);
    button4.setImageResource(R.drawable.number_4);
    button5.setImageResource(R.drawable.number_5);
    root.setOnClickListener((v) -> listener.onClick(v));

  }



  /*public void numbersRandom(ImageButton imageButton) {
    Random random = new Random();
    int image = ImageButton
  }*/

  @Override
  public Type getType() {
    return Type.MATCHING;
  }

  @Override
  public int getLayoutId() {
    return R.layout.fragment_matching_low_numbers;
  }

  @Override
  public void onClick(View v) {

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
