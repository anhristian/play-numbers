package edu.cnm.deepdive.playnumbers.controller;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.cnm.deepdive.playnumbers.R;


public class MissingHostFragment extends Fragment {

  private View root;


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    //TODO Add the corresponding code as in MatchHost
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_missing_host, container, false);
  }
}
