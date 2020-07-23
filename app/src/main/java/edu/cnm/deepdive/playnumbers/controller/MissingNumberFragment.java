package edu.cnm.deepdive.playnumbers.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import edu.cnm.deepdive.playnumbers.R;
import edu.cnm.deepdive.playnumbers.controller.ui.notifications.NotificationsViewModel;
import edu.cnm.deepdive.playnumbers.model.entity.Activity.Type;


public class MissingNumberFragment extends LearningActivityFragment {

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
    return R.layout.fragment_missing;
  }
}
