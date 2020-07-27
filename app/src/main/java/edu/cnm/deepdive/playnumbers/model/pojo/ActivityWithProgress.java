package edu.cnm.deepdive.playnumbers.model.pojo;

import androidx.room.Relation;
import edu.cnm.deepdive.playnumbers.model.entity.Activity;
import edu.cnm.deepdive.playnumbers.model.entity.Progress;
import java.util.List;

/**
 * The class defines the Activity and Progress entities together, Progress entity being a dependable
 * child and contain the getter and setter methods.
 */
public class ActivityWithProgress extends Activity {

  @Relation(entity = Progress.class, entityColumn = "activity_id", parentColumn = "activity_id")
  private List<Progress> progress; //type of field as a list.

  /**
   * Method used to get the list of progress for a specific activity.
   *
   * @return The list of progresses.
   */
  public List<Progress> getProgress() {
    return progress;
  }

  /**
   * The method set the progresses for activities in a list.
   */
  public void setProgress(List<Progress> progress) {
    this.progress = progress;
  }
}
