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
import edu.cnm.deepdive.playnumbers.controller.LearningActivityFragment;
import edu.cnm.deepdive.playnumbers.model.entity.Activity.Type;

public class MatchingFragment extends LearningActivityFragment {

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    //TODO DO any additional process to set this.
    return super.onCreateView(inflater, container, savedInstanceState);
  }

  @Override
  public Type getType() {
    return Type.MATCHING;
  }

  @Override
  public int getLayoutId() {
    return R.layout.fragment_matching;
  }
}
