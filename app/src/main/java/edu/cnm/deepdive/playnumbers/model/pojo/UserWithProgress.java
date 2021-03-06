package edu.cnm.deepdive.playnumbers.model.pojo;

import androidx.room.Relation;
import edu.cnm.deepdive.playnumbers.model.entity.Progress;
import edu.cnm.deepdive.playnumbers.model.entity.User;
import java.util.List;

/**
 * Implements the User and Progress entities. It contains the getter and setter methods.
 */
public class UserWithProgress extends User {

  @Relation(entity = Progress.class, entityColumn = "user_id", parentColumn = "user_id")
  private List<Progress> progresses; //one user has a list of progresses

  /**
   * Returns the list of progresses for user.
   *
   * @return The list of progresses.
   */
  public List<Progress> getProgresses() {
    return progresses;
  }

  /**
   * Sets the progresses for user in a list.
   */
  public void setProgresses(List<Progress> progresses) {
    this.progresses = progresses;
  }
}
