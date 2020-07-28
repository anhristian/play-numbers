package edu.cnm.deepdive.playnumbers.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import edu.cnm.deepdive.playnumbers.R;
import edu.cnm.deepdive.playnumbers.controller.LearningActivitiesFragmentDirections.OpenMatching;
import edu.cnm.deepdive.playnumbers.controller.LearningActivitiesFragmentDirections.OpenMissing;
import edu.cnm.deepdive.playnumbers.model.entity.Activity;
import edu.cnm.deepdive.playnumbers.model.entity.Activity.Type;
import edu.cnm.deepdive.playnumbers.view.ActivitiesAdapter;
import edu.cnm.deepdive.playnumbers.viewmodel.MainViewModel;

/**
 * Contains the behavior of the Fragment with the activities .
 */
public class LearningActivitiesFragment extends Fragment {

  /**
   * Defines and assigns the variable of type String inside this class.
   */
  public static final String TYPE_KEY = "type";

  private MainViewModel viewModel;
  private RecyclerView activityList;


  private Type type;

  /**
   * Creates a new instance of a fragment and puts the arguments to that fragment.
   *
   * @param type of arguments passed to the fragment.
   * @return fragment of a LearningActivities type.
   */
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
    activityList = view.findViewById(R.id.activity_list);
    return view;

  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    viewModel = new ViewModelProvider(getActivity())
        .get(MainViewModel.class);
    viewModel.getActivitiesForType().observe(getViewLifecycleOwner(), (activities) -> {
      if (activities != null) {
        ActivitiesAdapter adapter =
            new ActivitiesAdapter(getContext(), activities, this::openActivity);
        activityList.setAdapter(adapter);
      }
    });
    viewModel.setType(type);
  }

  private void openActivity(View view, int position, Activity activity) {

    switch (activity.getType()) {
      case MISSING:
        OpenMissing missingAction = LearningActivitiesFragmentDirections
            .openMissing(activity.getClassName());
        Navigation.findNavController(getView()).navigate(missingAction);
        break;
      case MATCHING:
        OpenMatching matchingAction = LearningActivitiesFragmentDirections
            .openMatching(activity.getClassName());
        Navigation.findNavController(getView())
            .navigate(matchingAction); //open up matching fragment and pass name
        break;
    }
  }
}


