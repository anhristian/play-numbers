package edu.cnm.deepdive.playnumbers.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Lifecycle.Event;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.Transformations;
import edu.cnm.deepdive.playnumbers.model.entity.Activity;
import edu.cnm.deepdive.playnumbers.model.entity.Activity.Type;
import edu.cnm.deepdive.playnumbers.model.entity.User;
import edu.cnm.deepdive.playnumbers.model.pojo.ActivityWithProgress;
import edu.cnm.deepdive.playnumbers.service.ActivityRepository;
import edu.cnm.deepdive.playnumbers.service.UserRepository;
import io.reactivex.disposables.CompositeDisposable;
import java.util.List;

/**
 * The class holds and manages the UI-related data and expose the information via LiveData
 */
public class MainViewModel extends AndroidViewModel implements LifecycleObserver {

  private final UserRepository userRepository;
  private final ActivityRepository activityRepository;
  private final MutableLiveData<Throwable> throwable;
  private final CompositeDisposable pending;
  private final MutableLiveData<ActivityWithProgress> activity;
  private final MutableLiveData<Type> type;
  private final LiveData<List<ActivityWithProgress>> activitiesForType;

  /**
   * The constructor prepares for use the new object of Application.
   *
   * @param application
   */
  public MainViewModel(@NonNull Application application) {
    super(application);
    userRepository = new UserRepository(application);
    activityRepository = new ActivityRepository(application);
    throwable = new MutableLiveData<>();
    pending = new CompositeDisposable();
    activity = new MutableLiveData<>();
    type = new MutableLiveData<>();
    activitiesForType = Transformations.switchMap(type, activityRepository::get);
  }

  /**
   * The method set the Type of application
   */
  public void setType(Type type) {
    this.type.setValue(type);
  }

  /**
   * The method get list of activities for type.
   *
   * @return A list of LiveData of activities for specific type.
   */
  public LiveData<List<ActivityWithProgress>> getActivitiesForType() {
    return activitiesForType;
  }

  /**
   * The method get the list of all users.
   *
   * @return A list of LiveData of all users.
   */
  public LiveData<List<User>> getUser() {
    return userRepository.getAll();
  }

  /**
   * The method get the list of all activities.
   *
   * @return A list of LiveData of all activities.
   */
  public LiveData<List<ActivityWithProgress>> getActivity() {
    return activityRepository.getAll();
  }

  /**
   * The method get the list of exceptions and errors.
   *
   * @return throwable as an exception or error.
   */
  public LiveData<Throwable> getThrowable() {
    return throwable;
  }

  /**
   * The method set the Id for an Activity.
   */
  public void setActivityId(long id) {
    throwable.setValue(null);
    pending.add(
        activityRepository.get(id)
            .subscribe(
                this.activity::postValue,
                this.throwable::postValue
            )

    );
  }

  /**
   * The method saves an Activity.
   */
  public void saveActivity(ActivityWithProgress activity) {
    throwable.setValue(null);
    pending.add(
        activityRepository.save(activity)
            .subscribe(
                () -> {
                },
                this.throwable::postValue
            )
    );
  }

  /**
   * The method deletes an Activity.
   */
  public void deleteActivity(Activity activity) {
    throwable.setValue(null);
    pending.add(
        activityRepository.delete(activity)
            .subscribe(
                () -> {
                },
                this.throwable::postValue
            )
    );
  }

  @OnLifecycleEvent(Event.ON_STOP)    //@ tells whatever task is in process trow it away
  private void clearPending() {
    pending.clear();
  }
}

