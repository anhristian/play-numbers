package edu.cnm.deepdive.playnumbers.service;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import edu.cnm.deepdive.playnumbers.model.dao.ActivityDao;
import edu.cnm.deepdive.playnumbers.model.dao.ProgressDao;
import edu.cnm.deepdive.playnumbers.model.dao.UserDao;
import edu.cnm.deepdive.playnumbers.model.entity.Activity;
import edu.cnm.deepdive.playnumbers.model.entity.Progress;
import edu.cnm.deepdive.playnumbers.model.entity.User;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Database(
    entities = {User.class, Activity.class, Progress.class},
    version = 1,
    exportSchema = true
)
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
}
