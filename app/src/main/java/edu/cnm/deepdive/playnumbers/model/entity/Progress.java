package edu.cnm.deepdive.playnumbers.model.entity;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import java.util.Date;

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

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Long getActivityId() {
    return activityId;
  }

  public void setActivityId(Long activityId) {
    this.activityId = activityId;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  @NonNull
  public Date getStart() {
    return start;
  }

  public void setStart(@NonNull Date start) {
    this.start = start;
  }

  public Date getEnd() {
    return end;
  }

  public void setEnd(Date end) {
    this.end = end;
  }
}

