package edu.cnm.deepdive.playnumbers.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import edu.cnm.deepdive.playnumbers.model.entity.Activity;

/**
 * The class provides the ability to store Activity objects in the database and retrieve them from
 * it.
 */
public abstract class LearningActivityFragment extends Fragment {

  private View root;


  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {

    root = inflater.inflate(getLayoutId(), container,
        false);
    return root;
  }

  /**
   * The method get the type of activity and the body of it is provided by the subclass.
   */
  public abstract Activity.Type getType();

  /**
   * The method get the layout id to be inflated lately.
   */
  public abstract int getLayoutId();

  /**
   * The class represents a fixed set of constants for Event types.
   */
  public enum Event {
    START,
    PAUSE,
    COMPLETE
  }

  /**
   * The interface represents the passing of a reference of Event.
   */
  public interface CallBack {

    void onEvent(Event event);
  }

}
