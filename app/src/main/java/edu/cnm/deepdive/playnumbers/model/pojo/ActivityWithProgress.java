package edu.cnm.deepdive.playnumbers.model.pojo;

import androidx.room.Relation;
import edu.cnm.deepdive.playnumbers.model.entity.Activity;
import edu.cnm.deepdive.playnumbers.model.entity.Progress;

public class ActivityWithProgress extends Activity {

  @Relation(entity = Progress.class, entityColumn = "activity_id", parentColumn = "activity_id")
  private Progress progress; //will be single because one activity will have one progress

  public Progress getProgress() {
    return progress;
  }

  public void setProgress(Progress progress) {
    this.progress = progress;
  }
}
