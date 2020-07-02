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
import edu.cnm.deepdive.playnumbers.model.entity.Progress;
import edu.cnm.deepdive.playnumbers.model.entity.User;
import edu.cnm.deepdive.playnumbers.service.PlayNumbersDatabase.Converters;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Database(
    entities = {User.class, Activity.class, Progress.class},
    version = 1,
    exportSchema = true
)
@TypeConverters({Converters.class})
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
