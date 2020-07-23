package edu.cnm.deepdive.playnumbers.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import edu.cnm.deepdive.playnumbers.model.entity.Activity;

public abstract class LearningActivityFragment extends Fragment {

  private View root;


  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {

     root = inflater.inflate(getLayoutId(), container, false); //a subclass is created will now will inflate this class
    return root;
  }

  public abstract Activity.Type getType(); //everytime add activity is a subclass
  public abstract int getLayoutId();

  public enum Event {
    START,
    PAUSE,
    COMPLETE
  }

  public interface CallBack{
    void onEvent(Event event);  //to show suspend
  }

}
