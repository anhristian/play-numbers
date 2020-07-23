package edu.cnm.deepdive.playnumbers.controller;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import edu.cnm.deepdive.playnumbers.R;
import edu.cnm.deepdive.playnumbers.model.entity.Activity.Type;
import edu.cnm.deepdive.playnumbers.model.pojo.ActivityWithProgress;
import edu.cnm.deepdive.playnumbers.view.ActivitiesAdapter;
import edu.cnm.deepdive.playnumbers.viewmodel.MainViewModel;
import java.util.List;

public class LearningActivitiesFragment extends Fragment {

  public static final String TYPE_KEY = "type";

  private MainViewModel viewModel;
  private RecyclerView typeActivities;


  private Type type;

  public static LearningActivitiesFragment newInstance(Type type) {
    LearningActivitiesFragment fragment = new LearningActivitiesFragment();
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
    View view = inflater.inflate(R.layout.fragment_learning_activities, container, false);
    typeActivities = view.findViewById(R.id.type_activities);
    return view;

  }
  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) { //!!!!!!!!!
    super.onViewCreated(view, savedInstanceState);
    viewModel = new ViewModelProvider(getActivity())
        .get(MainViewModel.class);
    viewModel.getActivitiesForType().observe(getViewLifecycleOwner(), (activities) ->{
      if (activities != null) {
        ActivitiesAdapter adapter = new ActivitiesAdapter(getContext(), activities);
        typeActivities.setAdapter(adapter);
      }
    });
    viewModel.setType(type);
  }
}


