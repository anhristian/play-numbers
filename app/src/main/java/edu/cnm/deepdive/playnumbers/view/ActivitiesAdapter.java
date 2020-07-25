package edu.cnm.deepdive.playnumbers.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import edu.cnm.deepdive.playnumbers.R;
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
    View root = LayoutInflater.from(context).inflate(R.layout.item_activity, parent, false);
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
    private final TextView activityName;
    private final ProgressBar activityProgress;
    //private final ImageView activityImage;

    //TODO Define additional field for view objects in custom layouts.

    private Holder(@NonNull View itemView) {
      super(itemView);
      root = itemView;
      activityName = itemView.findViewById(R.id.activity_name);
      activityProgress = itemView.findViewById(R.id.activity_progress);
      //activityImage = itemView.findViewById(R.id.activity_image);
    }

    private  void bind(int position) {
      //TODO get activity at position and use its content to populate view Object.
      Activity activity = activities.get(position);
      //((TextView) root).setText(activity.getName());

      activityName.setText(activity.getName());
      activityProgress.setProgress(activity.getLevel());/////?????????
      //activityImage.setImageDrawable(); ////?????

    }

  }

/*  public interface OnClickListener {

    void onClick(View v, int position, Activity activity);
  }*/

}
