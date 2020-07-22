package edu.cnm.deepdive.playnumbers.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;
import edu.cnm.deepdive.playnumbers.model.entity.Activity;
import edu.cnm.deepdive.playnumbers.model.entity.Activity.Type;
import edu.cnm.deepdive.playnumbers.model.pojo.ActivityWithProgress;
import io.reactivex.Single;
import java.util.Collection;
import java.util.List;


@Dao
public interface ActivityDao {

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  Single<Long> insert(Activity activity);

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  Single<List<Long>> insert(Activity... activities);

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  Single<List<Long>> insert(Collection<Activity> activities);

  @Update
  Single<Integer> update(Activity... activities);

  @Delete
  Single<Integer> delete(Activity... activities);


  @Transaction
  @Query("SELECT * FROM Activity ORDER BY name")
  LiveData<List<ActivityWithProgress>> selectAll();

  @Transaction
  @Query("SELECT * FROM Activity WHERE activity_id = :activityId")
  Single<ActivityWithProgress> selectedById(long activityId);


  @Query("SELECT * FROM Activity ORDER BY type")
  LiveData<List<ActivityWithProgress>> selectedByType(Type type); // !!!!!!!!!!!!gives the activities by specific type

}
