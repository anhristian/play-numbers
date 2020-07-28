package edu.cnm.deepdive.playnumbers.model.entity;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import java.util.Date;

/**
 * Implements the ability to store Progress objects in the database and retrieve them from it.
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
   * Returns the progress's id.
   *
   * @return a long containing the value for progress.
   */
  public long getId() {
    return id;
  }

  /**
   * Sets the progress's id.
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Returns the activity's id for the corresponding progress.
   *
   * @return a long containing the activity's id.
   */
  public Long getActivityId() {
    return activityId;
  }

  /**
   * Sets the activity's id for the corresponding progress.
   */
  public void setActivityId(Long activityId) {
    this.activityId = activityId;
  }

  /**
   * Returns the user's id for the corresponding progress.
   *
   * @return a long containing the user's id.
   */
  public Long getUserId() {
    return userId;
  }

  /**
   * Sets the user's id for the corresponding progress.
   */
  public void setUserId(Long userId) {
    this.userId = userId;
  }

  /**
   * Returns the start date for the corresponding progress.
   *
   * @return a date for progress's start.
   */
  @NonNull
  public Date getStart() {
    return start;
  }

  /**
   * Sets the start date for the corresponding progress.
   */
  public void setStart(@NonNull Date start) {
    this.start = start;
  }

  /**
   * Returns the end date for the corresponding progress.
   *
   * @return a date for progress's end.
   */
  public Date getEnd() {
    return end;
  }

  /**
   * Sets the end date for the corresponding progress.
   */
  public void setEnd(Date end) {
    this.end = end;
  }
}

