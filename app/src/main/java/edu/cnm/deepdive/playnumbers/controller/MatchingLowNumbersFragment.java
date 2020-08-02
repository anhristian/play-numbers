package edu.cnm.deepdive.playnumbers.controller;

import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Im;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import edu.cnm.deepdive.playnumbers.R;
import edu.cnm.deepdive.playnumbers.model.entity.Activity.Type;
import java.util.Random;

/**
 * Hosts the actions that are implemented on the activity match the low numbers.
 */
public class MatchingLowNumbersFragment extends LearningActivityFragment
implements View.OnClickListener{

  private ImageButton button1;
  private ImageButton button2;
  private ImageButton button3;
  private ImageButton button4;
  private ImageButton button5;
  private View root;
  private OnClickListener listener;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    //TODO DO any additional process to set this.
    //TODO Add Text to Speech.
    View view = inflater.inflate(R.layout.fragment_matching_low_numbers,container, false);
    view.findViewById(R.id.button_1).setOnClickListener(this);
    view.findViewById(R.id.button_2).setOnClickListener(this);
    view.findViewById(R.id.button_3).setOnClickListener(this);
    view.findViewById(R.id.button_4).setOnClickListener(this);
    view.findViewById(R.id.button_5).setOnClickListener(this);
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

  //TODO Use Toast to display the "correct",  "wrong" + textSpeech.
}
