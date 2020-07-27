package edu.cnm.deepdive.playnumbers.service;

import android.app.Application;
import android.app.ListActivity;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;
import edu.cnm.deepdive.playnumbers.R;
import edu.cnm.deepdive.playnumbers.model.dao.ActivityDao;
import edu.cnm.deepdive.playnumbers.model.dao.ProgressDao;
import edu.cnm.deepdive.playnumbers.model.dao.UserDao;
import edu.cnm.deepdive.playnumbers.model.entity.Activity;
import edu.cnm.deepdive.playnumbers.model.entity.Activity.Type;
import edu.cnm.deepdive.playnumbers.model.entity.Progress;
import edu.cnm.deepdive.playnumbers.model.entity.User;
import edu.cnm.deepdive.playnumbers.service.PlayNumbersDatabase.Converters;
import io.reactivex.schedulers.Schedulers;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 * A RoomDatabase subclass holding all the data from Entities an database version.
 */
@Database(
    entities = {User.class, Activity.class, Progress.class},
    version = 1,
    exportSchema = true
)
@TypeConverters({Converters.class, Activity.Type.class})
public abstract class PlayNumbersDatabase extends RoomDatabase {

  private static final String DB_NAME = "play_numbers_db";

  private static Application context;

  /**
   * Sets the application context from database.
   */
  public static void setContext(Application context) {
    PlayNumbersDatabase.context = context;
  }

  /**
   * Get a reference to room implof UserDao to get it from database.
   */
  public abstract UserDao getUserDao();

  /**
   * Sets reference of ActivityDao to get it from database.
   */
  public abstract ActivityDao getActivityDao();

  /**
   * Sets reference of ProgressDao to get it from database.
   */
  public abstract ProgressDao getProgressDao();

  /**
   * Obtains an instanceHolder from database.
   *
   * @return database instance.
   */
  public static PlayNumbersDatabase getInstance() {
    return InstanceHolder.INSTANCE;
  }

  private static class InstanceHolder {

    private static final PlayNumbersDatabase INSTANCE =
        Room.databaseBuilder(context, PlayNumbersDatabase.class, DB_NAME)
            .addCallback(new PlayNumbersCallBack(context))
            .build();

  }

  private static class PlayNumbersCallBack extends Callback {
  private final Context context;

    private PlayNumbersCallBack(Context context) {
      this.context = context;
    }

    @Override
    public void onCreate(@NonNull SupportSQLiteDatabase db) {
      super.onCreate(db);
      try (
          InputStream input = context.getResources().openRawResource(R.raw.preload);
          Reader reader = new InputStreamReader(input);
          CSVParser parser = new CSVParser(reader, CSVFormat.EXCEL.withIgnoreEmptyLines()
              .withIgnoreSurroundingSpaces().withFirstRecordAsHeader());
       ) {
        List<Activity> activities = new LinkedList<>();
        for (CSVRecord record : parser) {
          Activity activity = new Activity();
          activity.setName(record.get(0)); //colum
          activity.setType(Type.valueOf(record.get(1)));
          activity.setLevel(1);
          activity.setClassName(record.get(2));
          activities.add(activity);
        }
        PlayNumbersDatabase.getInstance().getActivityDao().insert(activities)
            .subscribeOn(Schedulers.io())
            .subscribe();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
      /*Activity activityMatching = new Activity();
      activityMatching
          .setClassName("edu.cnm.deepdive.playnumbers.controller.MatchingNumberFragment");
      activityMatching.setType(Type.MATCHING);
      activityMatching.setLevel(1);
      activityMatching.setName("Match the number");
      PlayNumbersDatabase.getInstance().getActivityDao().insert(activityMatching)
          .subscribeOn(Schedulers.io())  //this part use the ReactiveX
          .subscribe(); //this line put in db the fragment with numbers. talking to a view model

      Activity activityMissing = new Activity();
      activityMissing.setClassName("edu.cnm.deepdive.playnumbers.controller.MissingNumberFragment");
      activityMissing.setType(Type.MISSING);
      activityMissing.setLevel(1);
      activityMissing.setName("Find the missing number");
      PlayNumbersDatabase.getInstance().getActivityDao().insert(activityMissing)
          .subscribeOn(Schedulers.io())
          .subscribe();*/

    }
  }

  /**
   * Converts an object of one class to an object of another class.
   */
  public static class Converters {

    /**
     * Provides conversion between Objects of classes date to long for an easier mapping of their
     * instances.
     *
     * @param value represents the Data's input.
     * @return a Long value of time.
     */
    @TypeConverter
    public static Long dateToLong(Date value) {
      return (value != null) ? value.getTime() : null;
    }

    /**
     * Provides conversion of Objects of classes long to Date for an easier mapping of their
     * instances.
     *
     * @param value represents the Long's input.
     * @return a Date value.
     */
    @TypeConverter
    public static Date longToDate(Long value) {
      return (value != null) ? new Date(value) : null;
    }

  }
}
