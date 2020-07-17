package edu.cnm.deepdive.playnumbers.controller;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import edu.cnm.deepdive.playnumbers.R;
import edu.cnm.deepdive.playnumbers.model.entity.Activity.Type;

public class LearningActivityFragment extends Fragment {


  public static final String TYPE_KEY = "type";

  private Type type;

    public static LearningActivityFragment newInstance(Type type) {
        LearningActivityFragment fragment = new LearningActivityFragment();
        Bundle args = new Bundle();
        args.putSerializable(TYPE_KEY, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
           this.type = (Type) args.getSerializable(TYPE_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_learning_activity, container, false);
    }

}
