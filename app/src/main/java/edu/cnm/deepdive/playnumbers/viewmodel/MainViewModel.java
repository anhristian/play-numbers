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
 * Holds and manages the UI-related data and expose the information via LiveData
 */
public class MainViewModel extends AndroidViewModel implements LifecycleObserver {

  private final UserRepository userRepository;
  private final ActivityRepository activityRepository;
  private final MutableLiveData<Throwable> throwable;
  private final CompositeDisposable pending;
  private final MutableLiveData<ActivityWithProgress> activity;
  private final MutableLiveData<Type> type;
  private final LiveData<List<ActivityWithProgress>> activitiesForType;
  private final MutableLiveData<Boolean> permissionsChecked;

  /**
   * Prepares the new object of Application.
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
    permissionsChecked = new MutableLiveData<>(false);
  }

  /**
   * Sets the Type of application
   */
  public void setType(Type type) {
    this.type.setValue(type);
  }

  /**
   * Returns a list of activities for type.
   *
   * @return A list of LiveData of activities for specific type.
   */
  public LiveData<List<ActivityWithProgress>> getActivitiesForType() {
    return activitiesForType;
  }

  /**
   * Returns the list of all users.
   *
   * @return A list of LiveData of all users.
   */
  public LiveData<List<User>> getUser() {
    return userRepository.getAll();
  }

  /**
   * Returns the list of all activities.
   *
   * @return A list of LiveData of all activities.
   */
  public LiveData<List<ActivityWithProgress>> getActivity() {
    return activityRepository.getAll();
  }

  /**
   * Returns the list of exceptions and errors.
   *
   * @return throwable as an exception or error.
   */
  public LiveData<Throwable> getThrowable() {
    return throwable;
  }

  public LiveData<Boolean> getPermissionsChecked() {
    return permissionsChecked;
  }

  public void setPermissionsChecked(boolean checked) {
    permissionsChecked.setValue(checked);
  }


  /**
   * Sets the Id for an Activity.
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
   * Saves an Activity.
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
   * Deletes an Activity.
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

  @OnLifecycleEvent(Event.ON_STOP)
  private void clearPending() {
    pending.clear();
  }
}

