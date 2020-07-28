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
 * Provides the ability to store Activity objects in the database and retrieves them from it.
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
   * Returns a reference of type of activity.
   */
  public abstract Activity.Type getType();

  /**
   * Returnst the layout's id to be inflated lately.
   */
  public abstract int getLayoutId();

  /**
   * Represents a fixed set of constants for Event types.
   */
  public enum Event {
    START,
    PAUSE,
    COMPLETE
  }

  /**
   * Represents the reference passed to the Event.
   */
  public interface CallBack {

    void onEvent(Event event);
  }

}
