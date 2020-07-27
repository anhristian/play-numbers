package edu.cnm.deepdive.playnumbers.model.entity;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import java.util.Date;

/**
 * The class provides the ability to store Progress objects in the database and retrieve them from
 * it.
 */
@Entity(foreignKeys = {
    @ForeignKey(
        entity = Activity.class,
        parentColumns = "activity_id",
        childColumns = "activity_id",
        onDelete = ForeignKey.CASCADE
    ),
    @ForeignKey(
        entity = User.class,
        parentColumns = "user_id",
        childColumns = "user_id",
        onDelete = ForeignKey.CASCADE
    )
})
public class Progress {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "progress_id")
  private long id;

  @ColumnInfo(name = "activity_id", index = true)
  private Long activityId;

  @ColumnInfo(name = "user_id", index = true)
  private Long userId;

  @NonNull
  private Date start;


  private Date end;

  /**
   * Method used to get the progress's id.
   *
   * @return a long containing the value for progress.
   */
  public long getId() {
    return id;
  }

  /**
   * Method used to set the progress's id.
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Method used to get the activity's id for the corresponding progress.
   *
   * @return a long containing the activity's id.
   */
  public Long getActivityId() {
    return activityId;
  }

  /**
   * Method used to set the activity's id for the corresponding progress.
   */
  public void setActivityId(Long activityId) {
    this.activityId = activityId;
  }

  /**
   * Method used to get the user's id for the corresponding progress.
   *
   * @return a long containing the user's id.
   */
  public Long getUserId() {
    return userId;
  }

  /**
   * Method used to set the user's id for the corresponding progress.
   */
  public void setUserId(Long userId) {
    this.userId = userId;
  }

  /**
   * Method used to get the start date for the corresponding progress.
   *
   * @return a date for progress's start.
   */
  @NonNull
  public Date getStart() {
    return start;
  }

  /**
   * Method used to set the start date for the corresponding progress.
   */
  public void setStart(@NonNull Date start) {
    this.start = start;
  }

  /**
   * Method used to get the end date for the corresponding progress.
   *
   * @return a date for progress's end.
   */
  public Date getEnd() {
    return end;
  }

  /**
   * Method used to set the end date for the corresponding progress.
   */
  public void setEnd(Date end) {
    this.end = end;
  }
}

