package edu.cnm.deepdive.playnumbers.controller;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import edu.cnm.deepdive.playnumbers.R;
import edu.cnm.deepdive.playnumbers.model.entity.Activity.Type;
import edu.cnm.deepdive.playnumbers.model.fsm.NumberButton;
import edu.cnm.deepdive.playnumbers.viewmodel.MatchingHighNumbersViewModel;

/**
 * Hosts the actions that are implemented on the activity matchingNumbers for high numbers.
 */
public class MatchingHighNumbersFragment extends LearningActivityFragment {

    private MatchingHighNumbersViewModel viewModel;
    private ImageView[] numberButtons;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        numberButtons = new ImageView[MatchingHighNumbersViewModel.BUTTON_COUNT];
        Resources resources = getContext().getResources();
        String pkg = getContext().getPackageName();
        for (int i = 0; i < MatchingHighNumbersViewModel.BUTTON_COUNT; i++) {
            String idResName = "button_" + i;
            int id = resources.getIdentifier(idResName, "id", pkg);
            numberButtons[i] = view.findViewById(id);
        }
        viewModel = new ViewModelProvider(this).get(MatchingHighNumbersViewModel.class);
        viewModel.getNumberButtons().observe(getViewLifecycleOwner(), (buttons) -> {
            for(int i = 0; i < buttons.length; i++) {
                ImageView iv = numberButtons[i];
                LayerDrawable drawable = (LayerDrawable) iv.getDrawable();
                Drawable background = drawable.getDrawable(0);
                Drawable foreground = drawable.getDrawable(1);
                foreground.setLevel(i+MatchingHighNumbersViewModel.BUTTON_LEVEL_OFFSET);
                background.setLevel(buttons[i].getState().ordinal());
            }
        });

    }

    @Override
    public Type getType() {
        return Type.MATCHING;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_matching_high_numbers;
    }

}
