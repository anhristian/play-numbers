package edu.cnm.deepdive.playnumbers.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import edu.cnm.deepdive.playnumbers.model.entity.Progress;
import io.reactivex.Single;
import java.util.Collection;
import java.util.List;

/**
 * Implements all functionality for inserting, deleting, updating, and querying the Progress Object
 * from the database.
 */
@Dao
public interface ProgressDao {

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  Single<Long> insert(Progress progress);

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  Single<List<Long>> insert(Progress... progresses);

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  Single<List<Long>> insert(Collection<Progress> progresses);

  @Update
  Single<Integer> update(Progress... progresses);

  @Delete
  Single<Integer> delete(Progress... progresses);

  @Query("SELECT * FROM Progress ORDER BY start")
  LiveData<List<Progress>> selectAll();

  @Query("SELECT * FROM Progress WHERE activity_id = :activityId")
  Single<List<Progress>> selectedByActivityId(Long activityId);

}
