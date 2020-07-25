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
  private final OnClickListener listener;

  public ActivitiesAdapter(Context context,
      List<? extends Activity> activities, OnClickListener listener) {
    this.context = context;
    this.activities = activities;
    this.listener = listener;
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
    private final ImageView activityImage;
    private Activity activity;



    private Holder(@NonNull View itemView) {
      super(itemView);
      root = itemView;
      activityName = itemView.findViewById(R.id.activity_name);
      activityProgress = itemView.findViewById(R.id.activity_progress);
      activityImage = itemView.findViewById(R.id.activity_image);

    }

    private  void bind(int position) {

      activity = activities.get(position);

      activityName.setText(activity.getName());
      activityProgress.setProgress(activity.getLevel());
      activityImage.setImageResource(R.drawable.image_lion);
      root.setOnClickListener((v) -> listener.onClick(v, position, activity));

    }
  }

  public interface OnClickListener {

    void onClick(View v, int position, Activity activity);
  }

}
