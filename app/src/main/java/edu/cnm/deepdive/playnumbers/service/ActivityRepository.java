package edu.cnm.deepdive.playnumbers.service;

import android.content.Context;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.playnumbers.model.dao.ActivityDao;
import edu.cnm.deepdive.playnumbers.model.dao.ProgressDao;
import edu.cnm.deepdive.playnumbers.model.entity.Activity;
import edu.cnm.deepdive.playnumbers.model.pojo.ActivityWithProgress;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
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

  public Completable save(Activity activity) {
    if (activity.getId() == 0) {
      return Completable.fromSingle(activityDao.insert(activity))
          .subscribeOn(Schedulers.io());

    } else {
      return Completable.fromSingle(activityDao.update(activity))
          .subscribeOn(Schedulers.io());

    }
  }
  public Completable delete(Activity activity) {
    if (activity.getId() == 0) {
      //the completable doesn't do anything.
      return Completable.fromAction(() -> {})
          .subscribeOn(Schedulers.io());
    } else {
      return Completable.fromSingle(activityDao.delete(activity))
          .subscribeOn(Schedulers.io());
    }
  }
}
