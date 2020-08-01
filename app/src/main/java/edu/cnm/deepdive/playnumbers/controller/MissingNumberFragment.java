package edu.cnm.deepdive.playnumbers.controller;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.RecyclerView;
import edu.cnm.deepdive.playnumbers.R;
import edu.cnm.deepdive.playnumbers.model.entity.Activity.Type;
import java.util.Random;


/**
 * Hosts the actions that are implemented on the activity missingNumbers.
 */
public class MissingNumberFragment extends LearningActivityFragment
    implements View.OnTouchListener {

  private Random rng = new Random();
  private boolean running;
  private boolean complete;
  private ImageView valueDisplay;



  private GestureDetectorCompat detector;


  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    //TODO Do any additional processes to set this activity.
    //TODO Add Text to Speech.
    //TODO Add Onclick Listener for activity button.
    return super.onCreateView(inflater, container, savedInstanceState);
  }


  @Override
  public Type getType() {
    return Type.MISSING;
  }

  @Override
  public int getLayoutId() {
    return R.layout.fragment_missing_number;
  }

  @Override
  public boolean onTouch(View v, MotionEvent event) {
    boolean handled = false;
    if (running) {
      handled = detector.onTouchEvent(event);
      if (!handled && event.getActionMasked() == MotionEvent.ACTION_UP) {
        valueDisplay.setTranslationX(0);
        valueDisplay.setTranslationY(0);
        handled = true;
      }
    }
    return handled;
  }



 /* class Holder extends RecyclerView.ViewHolder {

    private final ImageView imageFace;

    private Holder(@NonNull ImageView itemView) {
      super(itemView);
      imageFace = itemView.findViewById(R.id.image_face);
    }
    private void bind() {

      imageFace.setImageResource(R.drawable.train);
    }

    public void onBindViewHolder(@NonNull Holder holder, int position) {
      holder.bind();
    }
  }*/



}
