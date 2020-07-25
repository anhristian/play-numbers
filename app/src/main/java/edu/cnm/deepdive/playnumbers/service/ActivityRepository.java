package edu.cnm.deepdive.playnumbers.service;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.room.Transaction;
import edu.cnm.deepdive.playnumbers.model.dao.ActivityDao;
import edu.cnm.deepdive.playnumbers.model.dao.ProgressDao;
import edu.cnm.deepdive.playnumbers.model.entity.Activity;
import edu.cnm.deepdive.playnumbers.model.entity.Activity.Type;
import edu.cnm.deepdive.playnumbers.model.entity.Progress;
import edu.cnm.deepdive.playnumbers.model.pojo.ActivityWithProgress;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import java.util.LinkedList;
import java.util.List;

public class ActivityRepository {

  private final Context context;
  private final PlayNumbersDatabase database;
  private final ActivityDao activityDao;
  private final ProgressDao progressDao;

  public ActivityRepository(Context context) {
    this.context = context;
    database = PlayNumbersDatabase.getInstance();
    activityDao = database.getActivityDao();
    progressDao = database.getProgressDao();
  }

  public LiveData<List<ActivityWithProgress>> getAll() {
    return activityDao.selectAll();
  }

  public Single<ActivityWithProgress> get(long id) {
    return activityDao.selectedById(id)
        .subscribeOn(Schedulers.io());

  }

  public LiveData<List<ActivityWithProgress>> get(Type type) {
    return activityDao.selectByType(type);
  }

  @Transaction
  public Completable save(ActivityWithProgress activity) {
    if (activity.getId() == 0) {
      return Completable.fromSingle(
          activityDao.insert(activity)
              .flatMap((id) -> {
                List<Progress> progresses = activity.getProgress();
                for (Progress progress : progresses) {
                  progress.setActivityId(id);
                }
                return progressDao.insert(progresses);
              })
      )
          .subscribeOn(Schedulers.io());
    } else {
      return Completable.fromSingle(
          activityDao.update(activity)
              .flatMap((count) -> {
                long id = activity.getId();
                List<Progress> updates = new LinkedList<>();
                List<Progress> inserts = new LinkedList<>();
                for (Progress progress : activity.getProgress()) {
                  if (progress.getId() == 0) {
                    inserts.add(progress);
                    progress.setActivityId(id);
                  } else {
                    updates.add(progress);
                  }
                }
                return progressDao.insert(inserts)
                    .flatMap((ids) -> progressDao.update(updates.toArray(new Progress[0])));
              })
      )
          .subscribeOn(Schedulers.io());

    }
  }

  public Completable delete(Activity activity) {
    if (activity.getId() == 0) {
      return Completable.fromAction(() -> {
      })  //the completable doesn't do anything.
          .subscribeOn(Schedulers.io());
    } else {
      return Completable.fromSingle(activityDao.delete(activity))
          .subscribeOn(Schedulers.io());
    }
  }
}
