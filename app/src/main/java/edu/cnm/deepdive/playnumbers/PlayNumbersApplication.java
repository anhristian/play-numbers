package edu.cnm.deepdive.playnumbers;

import android.app.Application;
import com.facebook.stetho.Stetho;
import edu.cnm.deepdive.playnumbers.service.GoogleSignInService;
import edu.cnm.deepdive.playnumbers.service.PlayNumbersDatabase;
import io.reactivex.schedulers.Schedulers;

/**
 * Extends Application and initializes Stetho that gives access for inspecting the database.
 */
public class PlayNumbersApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    GoogleSignInService.setContext(this);

    PlayNumbersDatabase.setContext(this);
    PlayNumbersDatabase database = PlayNumbersDatabase.getInstance();
    database.getUserDao().delete()
        .subscribeOn(Schedulers.io())
        .subscribe();
    Stetho.initializeWithDefaults(this);
  }
}
