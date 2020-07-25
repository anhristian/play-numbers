package edu.cnm.deepdive.playnumbers.service;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;
import edu.cnm.deepdive.playnumbers.model.dao.ActivityDao;
import edu.cnm.deepdive.playnumbers.model.dao.ProgressDao;
import edu.cnm.deepdive.playnumbers.model.dao.UserDao;
import edu.cnm.deepdive.playnumbers.model.entity.Activity;
import edu.cnm.deepdive.playnumbers.model.entity.Activity.Type;
import edu.cnm.deepdive.playnumbers.model.entity.Progress;
import edu.cnm.deepdive.playnumbers.model.entity.User;
import edu.cnm.deepdive.playnumbers.service.PlayNumbersDatabase.Converters;
import io.reactivex.schedulers.Schedulers;
import java.util.Date;

@Database(
    entities = {User.class, Activity.class, Progress.class},
    version = 1,
    exportSchema = true
)
@TypeConverters({Converters.class, Activity.Type.class})
public abstract class PlayNumbersDatabase extends RoomDatabase {

  private static final String DB_NAME = "play_numbers_db";

  private static Application context;

  public static void setContext(Application context) {
    PlayNumbersDatabase.context = context;
  }

  public abstract UserDao getUserDao();

  public abstract ActivityDao getActivityDao();

  public abstract ProgressDao getProgressDao();

  public static PlayNumbersDatabase getInstance() {
    return InstanceHolder.INSTANCE;
  }

  private static class InstanceHolder {

    private static final PlayNumbersDatabase INSTANCE =
        Room.databaseBuilder(context, PlayNumbersDatabase.class, DB_NAME)
            .addCallback(new PlayNumbersCallBack())
            .build();

  }

  private static class PlayNumbersCallBack extends Callback {

    @Override
    public void onCreate(@NonNull SupportSQLiteDatabase db) {
      super.onCreate(db);
      Activity activityMatching = new Activity();
      activityMatching.setClassName("edu.cnm.deepdive.playnumbers.controller.MatchingNumberFragment");
      activityMatching.setType(Type.MATCHING);
      activityMatching.setLevel(1);
      activityMatching.setName("Match the number");
      PlayNumbersDatabase.getInstance().getActivityDao().insert(activityMatching)
          .subscribeOn(Schedulers.io())  //ReactiveX
          .subscribe(); //this put in db   the fragment with numbers. talking to a view model

      Activity activityMissing = new Activity();
      activityMissing.setClassName("edu.cnm.deepdive.playnumbers.controller.MissingNumberFragment");
      activityMissing.setType(Type.MISSING);
      activityMissing.setLevel(1);
      activityMissing.setName("Find the missing number");
      PlayNumbersDatabase.getInstance().getActivityDao().insert(activityMissing)
          .subscribeOn(Schedulers.io())
          .subscribe();

    }
  }

  public static class Converters {

    @TypeConverter
    public static Long dateToLong(Date value) {
      return (value != null) ? value.getTime() : null;
    }

    @TypeConverter
    public static Date longToDate(Long value) {
      return (value != null) ? new Date(value) : null;
    }

  }
}
