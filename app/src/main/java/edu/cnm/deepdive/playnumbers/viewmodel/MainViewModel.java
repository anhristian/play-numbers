package edu.cnm.deepdive.playnumbers.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import edu.cnm.deepdive.playnumbers.model.entity.User;
import edu.cnm.deepdive.playnumbers.service.ActivityRepository;
import edu.cnm.deepdive.playnumbers.service.UserRepository;
import io.reactivex.disposables.CompositeDisposable;
import java.util.List;

public class MainViewModel extends AndroidViewModel implements LifecycleObserver {

  private final UserRepository userRepository;
  private final ActivityRepository activityRepository;
  private final MutableLiveData<Throwable> throwable;
  private final CompositeDisposable pending;
  //TODO ADD FIELD MUTABLE LIVE DATA FOR - POJO - USER WITH ACTIVITY

  public MainViewModel(@NonNull Application application) {
    super(application);
    userRepository = new UserRepository(application);
    activityRepository = new ActivityRepository(application);
    throwable = new MutableLiveData<>();
    pending = new CompositeDisposable();
    //Todo Add the constructor for UserWithActivity(POJO)
  }
  public LiveData<List<User>> getUsers() {
    return userRepository.getAll();
  }
}
