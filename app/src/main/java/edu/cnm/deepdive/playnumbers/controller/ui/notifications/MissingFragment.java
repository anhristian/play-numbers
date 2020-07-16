package edu.cnm.deepdive.playnumbers.controller.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import edu.cnm.deepdive.playnumbers.R;


public class MissingFragment extends Fragment {

  private NotificationsViewModel notificationsViewModel;

  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState) {
    notificationsViewModel =
        ViewModelProviders.of(this).get(NotificationsViewModel.class);
    View root = inflater.inflate(R.layout.fragment_missing, container, false);
    return root;
  }
}
