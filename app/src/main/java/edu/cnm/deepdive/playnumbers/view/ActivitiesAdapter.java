package edu.cnm.deepdive.playnumbers.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import edu.cnm.deepdive.playnumbers.model.entity.Activity;
import edu.cnm.deepdive.playnumbers.view.ActivitiesAdapter.Holder;
import java.util.List;

public class ActivitiesAdapter extends RecyclerView.Adapter<Holder> {

  private final Context context;
  private final List<? extends Activity> activities;

  public ActivitiesAdapter(Context context,
      List<? extends Activity> activities) {
    this.context = context;
    this.activities = activities;
  }


  @NonNull
  @Override
  public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    //TODO Inflate a custom layout instead of default.
    View root = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false);
    return new Holder(root);
  }

  @Override
  public void onBindViewHolder(@NonNull Holder holder, int position) {
    holder.bind(position);
  }

  @Override
  public int getItemCount() {
    return activities.size();
  }

  class Holder extends RecyclerView.ViewHolder {

    private final View root;
    //TODO Definde additional field for viewobjects in custom layouts.

    private Holder(@NonNull View itemView) {
      super(itemView);
      root = itemView;
    }

    private  void bind(int position) {
      //TODO get activity at position and use its content to populate view Object.
      Activity activity = activities.get(position); //we know which activity we want
      ((TextView) root).setText(activity.getName());
    }

  }


}
