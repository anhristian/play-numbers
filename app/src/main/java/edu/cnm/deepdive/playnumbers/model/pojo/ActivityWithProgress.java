package edu.cnm.deepdive.playnumbers.model.pojo;

import androidx.room.Relation;
import edu.cnm.deepdive.playnumbers.model.entity.Activity;
import edu.cnm.deepdive.playnumbers.model.entity.Progress;
import java.util.List;

public class ActivityWithProgress extends Activity {

  @Relation(entity = Progress.class, entityColumn = "activity_id", parentColumn = "activity_id")
  private List<Progress> progress; //type of field as a list.

  public List<Progress> getProgress() {
    return progress;
  }

  public void setProgress(List<Progress> progress) {
    this.progress = progress;
  }
}
